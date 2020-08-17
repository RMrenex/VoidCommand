package com.ianlibanio.voidcommand.controller.impl;

import com.google.common.collect.Maps;
import com.ianlibanio.voidcommand.controller.IMapController;
import com.ianlibanio.voidcommand.data.Sub;

import java.util.Map;

public class SubCommandController implements IMapController<String, Sub> {

    private final Map<String, Sub> subCommandMethodMap = Maps.newHashMap();

    @Override
    public void put(String s, Sub sub) {
        subCommandMethodMap.put(s, sub);
    }

    @Override
    public void clear() {
        subCommandMethodMap.clear();
    }

    @Override
    public Map<String, Sub> get() {
        return subCommandMethodMap;
    }
}
