package org.yffd.easy.spring4.shiro.service;

import java.util.HashSet;
import java.util.Set;

import org.yffd.easy.spring4.shiro.model.User;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月7日 下午2:12:53 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class UserServiceImpl implements UserService {

	private PasswordHelper passwordHelper = new PasswordHelper();
	
	@Override
	public void changePassword(Long userId, String newPassword) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findByUsername(String username) {
		User u = new User();
		u.setId(100L);
		u.setUsername("admin");
		u.setPassword("admin");
		passwordHelper.encryptPassword(u);
		return u;
	}

	@Override
	public Set<String> findRoles(String username) {
		Set<String> set = new HashSet<String>();
		set.add("role1");
		set.add("role2");
		set.add("admin");
		return set;
	}

	@Override
	public Set<String> findPermissions(String username) {
		Set<String> set = new HashSet<String>();
		set.add("user:create");
		set.add("user:delete");
		set.add("user:update");
		return set;
	}

}

