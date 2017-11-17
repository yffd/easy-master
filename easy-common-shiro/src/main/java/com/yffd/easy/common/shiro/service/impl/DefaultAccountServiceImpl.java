package com.yffd.easy.common.shiro.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.yffd.easy.common.shiro.model.AccountInfo;
import com.yffd.easy.common.shiro.service.AccountService;
import com.yffd.easy.common.shiro.support.AccountRedisCacheSupport;
import com.yffd.easy.common.shiro.support.PasswordSupport;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月7日 下午6:24:21 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Deprecated
public class DefaultAccountServiceImpl implements AccountService {
	private static final Logger LOG = LoggerFactory.getLogger(DefaultAccountServiceImpl.class);
	
	private static Map<String, AccountInfo> cacheMap = new HashMap<String, AccountInfo>();
	static {
		AccountInfo info = new AccountInfo("admin", "admin");
		PasswordSupport passwordSupport = new PasswordSupport();
		passwordSupport.encryptPassword(info);
		cacheMap.put("admin", info);
	}
	
	@Override
	public AccountInfo findAccount(String accountName) {
		if(null==accountName || "".equals(accountName)) {
			return null;
		}
		return cacheMap.get(accountName);
	}

	@Override
	public Set<String> findRoles(String accountName) {
		if(null==accountName || "".equals(accountName)) {
			return null;
		}
		Set<String> roles = null;
		roles = new HashSet<String>();
		roles.add("admin");
		roles.add("role1");
		return roles;
	}

	@Override
	public Set<String> findPermissions(String accountName) {
		if(null==accountName || "".equals(accountName)) {
			return null;
		}
		Set<String> permissions = null;
		permissions = new HashSet<String>();
		permissions.add("user:create");
		permissions.add("user:update");
		permissions.add("user:delete");
		return permissions;
	}

}

