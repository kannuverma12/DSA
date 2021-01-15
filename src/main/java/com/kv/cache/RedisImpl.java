package com.kv.cache;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class RedisImpl {

	@Autowired
	private RedisService redisService;
	
	public Map<Object, Object> findAllGrids() {
		return redisService.findAllGrids();
	}
	
	public void add(Grid grid) {
		redisService.add(grid);
	}
	
	public Grid get(String gridId) {
		return redisService.findGrid(gridId);
	}
	
	public static void main(String[] args) {
		RedisImpl ri = new RedisImpl();
		
		Grid grid = new Grid("ff-glpid","response 1");
		ri.add(grid);
		
		Grid g = ri.get("ff-glpid");
		System.out.println("Find grid "+g);
		
		for(Map.Entry<Object, Object> entry : ri.findAllGrids().entrySet() ) {
			System.out.println("Key : "+entry.getKey().toString() + " : Value : "+entry.getValue().toString());
		}
		

	}

}
