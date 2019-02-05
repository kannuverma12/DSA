package com.kv.cache;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {
	
	@Autowired
	private RedisRepository redisRepository;

	@Override
	public Map<Object, Object> findAllGrids() {
		return redisRepository.findAllGrids();
	}

	@Override
	public void add(Grid grid) {
		redisRepository.add(grid);
	}

	@Override
	public void delete(String gridId) {
		redisRepository.delete(gridId);
	}

	@Override
	public Grid findGrid(String gridId) {
		return redisRepository.findGrid(gridId);
	}

}
