package com.ianlibanio.voidcommand.controller;

import java.util.Map;

public interface IMapController<K, V> {

    void put(K k, V v);
    void clear();
    Map<K, V> get();

}
