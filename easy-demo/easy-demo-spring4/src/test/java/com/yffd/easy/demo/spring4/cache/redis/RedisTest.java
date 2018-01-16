package com.yffd.easy.demo.spring4.cache.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.yffd.easy.common.shiro.model.AccountInfo;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月10日 上午9:41:06 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-cache-redis.xml"})
@TransactionConfiguration(defaultRollback = false)
public class RedisTest {

	@Autowired
	private RedisTemplate redisTemplate;
	
	@Test
	public void test() {
		final String key = "admin";
		final AccountInfo value = new AccountInfo("admin", "admin");
		this.redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer serializer = getRedisSerializer();
				byte[] keyBytes = serializer.serialize(key);
				byte[] valueBytes = serializer.serialize(value);
				connection.set(keyBytes, valueBytes);
				return "ok";
			}
		});
	}
	
	public RedisSerializer getRedisSerializer() {
		return this.redisTemplate.getDefaultSerializer();
	}
}

