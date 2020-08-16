package com.ianlibanio.voidcommand.context;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
@AllArgsConstructor
@Accessors(fluent = true)
public class Context {

    // Default
    CommandSender sender;
    String label;
    String[] args;

    // Add-ons
    Player player;

}
