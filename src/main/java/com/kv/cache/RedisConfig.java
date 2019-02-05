package com.kv.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setUsePool(true);
		return jedisConnectionFactory;
	}

	@Bean
	public RedisSerializer redisStringSerializer() {
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		return stringRedisSerializer;
	}

	@Bean(name = "redisTemplate")
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf, RedisSerializer redisSerializer) {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
		redisTemplate.setConnectionFactory(cf);
		redisTemplate.setDefaultSerializer(redisSerializer);
		return redisTemplate;
	}

//	@Bean
//	public CacheManager cacheManager() {
//		return new RedisCacheManager(redisTemplate(redisConnectionFactory(), redisStringSerializer()));
//	}
}
