package com.yffd.easy.auth.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;
import org.yffd.easy.common.core.util.ValidUtils;

import com.yffd.easy.auth.dao.AuthUserDao;
import com.yffd.easy.auth.model.AuthUser;
import com.yffd.easy.auth.service.AuthUserService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月20日 上午10:17:58 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("authUserService")
public class AuthUserServiceImpl implements AuthUserService {

	@Autowired
	private AuthUserDao authUserDao;
	
	@Override
	public PageResult<AuthUser> findList(AuthUser user, PageParam pageParam) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(!ValidUtils.isEmpty(user)) {
			paramMap.put("userCode", user.getUserCode());
			paramMap.put("userName", user.getUserName());
			paramMap.put("userState", user.getUserState());
			paramMap.put("tel", user.getTel());
		}
		return this.authUserDao.selectPage(pageParam, paramMap);
	}

	@Override
	public AuthUser findByCode(String userCode) {
		if(ValidUtils.isBlank(userCode)) return null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userCode", userCode);
		return this.authUserDao.selectOne(paramMap);
	}

	@Override
	public AuthUser findByCodeAndPwd(String userCode, String userPwd) {
		if(ValidUtils.isBlank(userCode) || ValidUtils.isBlank(userPwd)) return null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userCode", userCode);
		paramMap.put("userPwd", userPwd);
		return this.authUserDao.selectOne(paramMap);
	}

	@Override
	public void add(AuthUser user) {
		if(ValidUtils.isNull(user) || ValidUtils.isBlank(user.getUserCode()) || ValidUtils.isBlank(user.getUserName())) {
			return;
		}
		this.authUserDao.insert(user);
	}

	@Override
	public void editByCode(AuthUser user) {
		this.authUserDao.updateByPK(user);
	}

	@Override
	public void delByCode(String userCode) {
		if(ValidUtils.isBlank(userCode)) return;
		this.authUserDao.deleteByPK(userCode);
	}

}

