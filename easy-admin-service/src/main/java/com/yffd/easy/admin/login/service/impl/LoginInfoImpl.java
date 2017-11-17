package com.yffd.easy.admin.login.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.admin.login.model.LoginInfo;
import com.yffd.easy.admin.login.model.UsernamePasswordInfo;
import com.yffd.easy.admin.login.service.LoginService;
import com.yffd.easy.admin.pms.model.PmsAccount;
import com.yffd.easy.admin.pms.model.PmsUser;
import com.yffd.easy.admin.pms.service.PmsUserService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月15日 上午11:00:43 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("loginInfo")
public class LoginInfoImpl implements LoginService {

	@Autowired
	private PmsUserService pmsUserService;
	
	@Override
	public UsernamePasswordInfo findAccount(String userCode) {
		PmsUser user = this.pmsUserService.findUser(userCode);
		if(null==user) return null;
		UsernamePasswordInfo info = new UsernamePasswordInfo(
				user.getUserCode(),
				user.getPassword(),
				user.getSalt(),
				user.getUserStatus());
		return info;
	}

	@Override
	public Set<String> findRoles(String userCode) {
		return this.pmsUserService.findRoles(userCode);
	}

	@Override
	public Set<String> findPermissions(String userCode) {
		return this.pmsUserService.findResources(userCode);
	}

	@Override
	public LoginInfo findLoginInfo(String userCode) {
		PmsUser user = this.pmsUserService.findUser(userCode);
		Set<String> permissions = this.findPermissions(userCode);
		LoginInfo info = new LoginInfo();
		info.setUserCode(user.getUserCode());
		info.setNickName(user.getUserName());
		info.setPermissions(permissions);
		return info;
	}

}

