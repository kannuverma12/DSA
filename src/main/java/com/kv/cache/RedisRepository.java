package com.kv.cache;

import java.util.Map;

public interface RedisRepository {
    Map<Object, Object> findAllGrids();
    void add(Grid grid);
    void delete(String gridId);
    Grid findGrid(String gridId);
}
