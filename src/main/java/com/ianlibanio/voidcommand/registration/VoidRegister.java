package com.ianlibanio.voidcommand.registration;

import com.ianlibanio.voidcommand.VoidCommand;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;
import java.util.Collections;

@AllArgsConstructor
public class VoidRegister {

    Plugin plugin;

    @SneakyThrows
    public void add(VoidCommand... commands)  {
        Field bukkitCommandMap = plugin.getServer().getClass().getDeclaredField("commandMap");
        bukkitCommandMap.setAccessible(true);

        CommandMap commandMap = (CommandMap) bukkitCommandMap.get(plugin.getServer());

        for (VoidCommand command : commands) {
            commandMap.register(plugin.getName(), command);

            String aliases = !command.getAliases().equals(Collections.emptyList()) ? "(Aliases: " + ChatColor.YELLOW + StringUtils.join(command.getAliases(), ChatColor.GRAY + ", "
                    + ChatColor.YELLOW) + ChatColor.GRAY + ") " : "";

            Bukkit.getConsoleSender().sendMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + "VoidCommand" + ChatColor.GRAY + "] -> The command '" + ChatColor.YELLOW
                    + command.getName() + ChatColor.GRAY + "' " + aliases + "was registered with success.");
        }
    }

}
