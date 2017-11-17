package com.yffd.easy.common.shiro.support;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.yffd.easy.common.shiro.model.AccountInfo;
import com.yffd.easy.common.shiro.service.AccountService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月7日 下午6:24:21 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class AccountRealmSupport {
	private static final Logger LOG = LoggerFactory.getLogger(AccountRealmSupport.class);
	
	private AccountService accountService;
	private RedisTemplate redisTemplate;
	private AccountRedisCacheSupport cacheSupport;
	
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
		if(null!=redisTemplate) {
			cacheSupport = new AccountRedisCacheSupport(redisTemplate);
		}
	}

	public AccountInfo findAccount(String accountName) {
		if(null==accountName || "".equals(accountName)) {
			return null;
		}
		//1.缓存
		AccountInfo info = null;
		if(this.cacheEnabled()) {
			info = this.cacheSupport.findAccount(accountName);
			if(null!=info) return info;
		}
		if(null==info) {
			//2.数据库
			try {
				info = this.accountService.findAccount(accountName);
			} catch (Exception e) {
				LOG.error("=======[shiro db] find account error, key:" + accountName, e);
				return null;
			}
			if(this.cacheEnabled() && null!=info) {//放入缓存
				this.cacheSupport.addAccount(info);
			}
		}
		return info;
	}

	public Set<String> findRoles(String accountName) {
		if(null==accountName || "".equals(accountName)) {
			return null;
		}
		Set<String> roles = null;
		//1.缓存
		if(this.cacheEnabled()) {
			roles = this.cacheSupport.findRoles(accountName);
			if(null!=roles) return roles;
		}
		//2.数据库
		try {
			roles = this.accountService.findRoles(accountName);
		} catch (Exception e) {
			LOG.error("=======[shiro db] find roles error, key:" + accountName, e);
			return null;
		}
		if(this.cacheEnabled() && null!=roles) {//放入缓存
			this.cacheSupport.addRoles(accountName, roles);
		}
		return roles;
	}

	public Set<String> findPermissions(String accountName) {
		if(null==accountName || "".equals(accountName)) {
			return null;
		}
		Set<String> permissions = null;
		//1.缓存
		if(this.cacheEnabled()) {
			permissions = this.cacheSupport.findPermissions(accountName);
			if(null!=permissions) return permissions;
		}
		//2.数据库
		try {
			permissions = this.accountService.findPermissions(accountName);
		} catch (Exception e) {
			LOG.error("=======[shiro db] find permission error, key:" + accountName, e);
			return null;
		}
		if(this.cacheEnabled() && null!=permissions) {//放入缓存
			this.cacheSupport.addPermissions(accountName, permissions);
		}
		return permissions;
	}

	private boolean cacheEnabled() {
		return null!=this.cacheSupport;
	}
}

