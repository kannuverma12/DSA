package com.kv.cache;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

//@Repository
public class RedisRepositoryImpl implements RedisRepository {
	
	private static final String KEY = "grid";
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;
    
    @Autowired
    public RedisRepositoryImpl(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }
    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }

	@Override
	public Map<Object, Object> findAllGrids() {
		return hashOperations.entries(KEY);
	}

	@Override
	public void add(Grid grid) {
		hashOperations.put(KEY, grid.getGridId(), grid.getResponse());
	}

	@Override
	public void delete(String gridId) {
		hashOperations.delete(KEY, gridId);
	}

	@Override
	public Grid findGrid(String gridId) {
		return (Grid) hashOperations.get(KEY, gridId);
	}

}
