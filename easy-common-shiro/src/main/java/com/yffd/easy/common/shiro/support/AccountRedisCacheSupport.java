package com.yffd.easy.common.shiro.support;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.fastjson.JSON;
import com.yffd.easy.common.shiro.model.AccountInfo;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月10日 下午3:59:19 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class AccountRedisCacheSupport {
	private static final Logger LOG = LoggerFactory.getLogger(AccountRedisCacheSupport.class);
	private static final String PREFIX = "shiro:";
	
	private RedisTemplate redisTemplate;
	
	public AccountRedisCacheSupport(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public AccountInfo findAccount(String accountName) {
		LOG.info("=======[shiro cache] find account, key:" + accountName);
		if(null==accountName || "".equals(accountName)) return null;
		String str = (String) this.redisTemplate.opsForValue().get(PREFIX + "account:" + accountName);
		AccountInfo info = JSON.parseObject(str, AccountInfo.class);
		return info;
	}

	public void addAccount(AccountInfo info) {
		LOG.info("=======[shiro cache] insert account, key:" + info);
		if(null==info || null==info.getAccountName() || "".equals(info.getAccountName())) return;
		String key = info.getAccountName();
		String value = JSON.toJSONString(info);
		this.redisTemplate.opsForValue().set(PREFIX + "account:" + key, value);
	}

	public Set<String> findRoles(String accountName) {
		LOG.info("=======[shiro cache] find roles, key:" + accountName);
    	if(null==accountName || "".equals(accountName)) return null;
    	String str = (String) this.redisTemplate.opsForValue().get(PREFIX + "roles:" + accountName);
    	if(null==str) return null;
    	Set<String> roles = JSON.parseObject(str, HashSet.class);
    	if(null==roles || roles.size()==0) return null;
    	return roles;
	}

	public void addRoles(String accountName, Set<String> roles) {
		LOG.info("=======[shiro cache] insert roles, key:" + accountName);
    	if(null==accountName || "".equals(accountName) || null==roles || roles.size()==0) return;
    	String value = JSON.toJSONString(roles);
		this.redisTemplate.opsForValue().set(PREFIX + "roles:" + accountName, value);
	}

	public Set<String> findPermissions(String accountName) {
		LOG.info("=======[shiro cache] find permissions, key:" + accountName);
    	if(null==accountName || "".equals(accountName)) return null;
    	String str = (String) this.redisTemplate.opsForValue().get(PREFIX + "pms:" + accountName);
    	if(null==str) return null;
    	Set<String> permissions = JSON.parseObject(str, HashSet.class);
    	if(null==permissions || permissions.size()==0) return null;
    	return permissions;
	}

	public void addPermissions(String accountName, Set<String> permissions) {
		LOG.info("=======[shiro cache] insert permissions, key:" + accountName);
    	if(null==accountName || "".equals(accountName) || null==permissions || permissions.size()==0) return;
    	String value = JSON.toJSONString(permissions);
    	this.redisTemplate.opsForValue().set(PREFIX + "pms:" + accountName, value);
	}
	
	public void delete(String accountName) {
		LOG.info("=======[shiro cache] delete, key:" + accountName);
		if(null==accountName || "".equals(accountName)) return;
		this.redisTemplate.delete(accountName);
	}
}

