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

    // Methods
    public void message(String message) {
        sender.sendMessage(message);
    }

    public void message(BaseComponent... message) {
        sender.sendMessage(message);
    }

    public void hexMessage(BaseComponent... message) {
        sender.spigot().sendMessage(message);
    }

    public boolean hasPermission(CommandSender sender, String permission) {
        return permission == null || permission.equals("") || sender.hasPermission(permission);
    }

    public void addItems(ItemStack... itemStack) {
        player.getInventory().addItem(itemStack);
    }

    public boolean isPlayer() {
        return sender instanceof Player;
    }

    public boolean isConsole() {
        return !isPlayer();
    }

}
