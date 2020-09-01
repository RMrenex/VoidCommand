package com.ianlibanio.voidcommand.context;

import com.ianlibanio.voidcommand.annotation.command.Command;
import com.ianlibanio.voidcommand.annotation.subcommand.SubCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Getter
@AllArgsConstructor
@Accessors(fluent = true)
public class Context {

    // Default
    private final CommandSender sender;
    private final String label;
    private final String[] args;

    // Add-ons
    private final Player player;
    private final Command command;
    private final SubCommand subCommand;

    public void sendInvalidMessage() {
        for (String message : command.invalid()) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }

}
