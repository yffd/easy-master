package com.yffd.easy.common.shiro.support;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.yffd.easy.common.shiro.model.AccountInfo;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月10日 下午5:04:27 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml"})
@TransactionConfiguration(defaultRollback = false)
public class AccountRedisCacheSupportTest {
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Test
	public void findAccountTest() {
		AccountRedisCacheSupport cacheSupport = new AccountRedisCacheSupport(this.redisTemplate);
		AccountInfo info = cacheSupport.findAccount("qwe");
		System.out.println(info);
	}
	@Test
	public void findRolesTest() {
		AccountRedisCacheSupport cacheSupport = new AccountRedisCacheSupport(this.redisTemplate);
		Set<String> roles = cacheSupport.findRoles("qwe");
		System.out.println(roles);
	}
}

