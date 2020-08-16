package com.ianlibanio.voidcommand;

import com.google.common.collect.Maps;
import com.ianlibanio.voidcommand.annotation.command.Aliases;
import com.ianlibanio.voidcommand.annotation.command.Command;
import com.ianlibanio.voidcommand.annotation.subcommand.SubCommand;
import com.ianlibanio.voidcommand.context.Context;
import com.ianlibanio.voidcommand.settings.Executor;
import lombok.SneakyThrows;
import lombok.val;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public abstract class VoidCommand extends org.bukkit.command.Command {

    private final Map<SubCommand, Method> subCommands = Maps.newHashMap();

    private Player player;
    private Command command;

    public VoidCommand() {
        super("");

        for (Method method : this.getClass().getDeclaredMethods()) {
            // Commands
            val command = method.getAnnotation(Command.class);
            val aliases = method.getAnnotation(Aliases.class);

            if (command != null) {
                this.command = command;
                setName(command.name());
            }

            if (aliases != null) setAliases(Arrays.asList(aliases.value()));

            // SubCommands
            val subAnnotation = method.getAnnotation(SubCommand.class);

            if ((command == null && aliases == null) && subAnnotation != null) {
                subCommands.put(subAnnotation, method);
            }
        }
    }

    public abstract void command(Context context);

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        val executor = command.executor();

        if (executor.equals(Executor.PLAYER_ONLY) && !isPlayer(sender)) {
            sender.sendMessage(ChatColor.GRAY + "You can't use this command in the " + ChatColor.RED + "CONSOLE" + ChatColor.GRAY + ".");
            return true;
        }

        if (executor.equals(Executor.CONSOLE_ONLY) && isPlayer(sender)) {
            sender.sendMessage(ChatColor.GRAY + "You can't use this command as a " + ChatColor.RED + "PLAYER" + ChatColor.GRAY + ".");
            return true;
        }

        if (!hasPermission(sender, command.permission())) {
            sender.sendMessage(ChatColor.GRAY + "You don't have the permission '" + ChatColor.RED + command.permission() + ChatColor.GRAY + "' to use this command!");
            return true;
        }
        if (isPlayer(sender)) this.player = (Player) sender;

        AtomicBoolean use = new AtomicBoolean(true);
        Map<SubCommand, Method> validSubCommands = Maps.newHashMap();

        if (args.length > 0) {
            subCommands.forEach((subCommand, method) -> {
                val parameters = method.getParameterTypes();

                if (parameters.length >= 1 && parameters[0].isAssignableFrom(Context.class)) {
                    val split = subCommand.name().split("\\.");

                    if (split.length == 0 || args[0].equals("") || args.length < split.length) {
                        use.set(false);
                        return;
                    }

                    for (int i = 0; i < split.length; i++) {
                        if (!args[i].equalsIgnoreCase(split[i])) {
                            use.set(false);
                            return;
                        }
                    }

                    if (hasPermission(sender, command.permission()) && hasPermission(sender, subCommand.permission())) {
                        validSubCommands.put(subCommand, method);
                    }
                }
            });

            if (validSubCommands.size() == 1) {
                validSubCommands.forEach((subCommand, method) -> invoke(method, sender, label, args));
            }

            if (validSubCommands.size() > 1) {
                AtomicReference<Map.Entry<SubCommand, Method>> max = new AtomicReference<>(null);

                validSubCommands.forEach((subCommand, method) -> {
                    if (max.get() == null || subCommand.name().split("\\.").length > max.get().getKey().name().split("\\.").length)
                        max.set(Maps.immutableEntry(subCommand, method));
                });

                invoke(max.get().getValue(), sender, label, args);
            }

            use.set(false);
        }

        val execute = use.get();

        if (execute) {
            command(new Context(sender, label, args, player));
        }
        if (!execute && validSubCommands.equals(Collections.emptyMap())) {
            sender.sendMessage(ChatColor.RED + command.invalid());
        }

        return false;
    }

    private boolean isPlayer(CommandSender sender) {
        return sender instanceof Player;
    }

    private boolean hasPermission(CommandSender sender, String permission) {
        return permission == null || permission.equals("") || sender.hasPermission(permission);
    }

    @SneakyThrows
    private void invoke(Method method, CommandSender sender, String label, String[] args) {
        method.invoke(this, new Context(sender, label, args, player));
    }
}
