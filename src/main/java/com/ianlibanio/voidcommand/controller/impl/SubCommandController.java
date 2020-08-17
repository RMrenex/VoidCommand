package com.ianlibanio.voidcommand.controller.impl;

import com.google.common.collect.Maps;
import com.ianlibanio.voidcommand.annotation.subcommand.SubCommand;
import com.ianlibanio.voidcommand.controller.IMapController;

import java.lang.reflect.Method;
import java.util.Map;

public class SubCommandController implements IMapController<SubCommand, Method> {

    private final Map<SubCommand, Method> subCommandMethodMap = Maps.newHashMap();

    @Override
    public void put(SubCommand subCommand, Method method) {
        subCommandMethodMap.put(subCommand, method);
    }

    @Override
    public void clear() {
        subCommandMethodMap.clear();
    }

    @Override
    public Map<SubCommand, Method> get() {
        return subCommandMethodMap;
    }
}
