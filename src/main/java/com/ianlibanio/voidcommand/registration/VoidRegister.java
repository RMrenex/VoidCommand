package com.ianlibanio.voidcommand.registration;

import com.ianlibanio.voidcommand.VoidCommand;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;

@AllArgsConstructor
public class VoidRegister {

    private final Plugin plugin;

    @SneakyThrows
    public void add(VoidCommand... commands)  {
        Field bukkitCommandMap = plugin.getServer().getClass().getDeclaredField("commandMap");
        bukkitCommandMap.setAccessible(true);

        CommandMap commandMap = (CommandMap) bukkitCommandMap.get(plugin.getServer());

        for (VoidCommand command : commands) {
            commandMap.register(plugin.getName(), command);
        }
    }
}
