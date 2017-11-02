package com.yffd.easy.auth.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yffd.easy.common.core.util.ValidUtils;

import com.yffd.easy.auth.dao.AuthResourceDao;
import com.yffd.easy.auth.model.AuthResource;
import com.yffd.easy.auth.model.AuthUser;
import com.yffd.easy.auth.service.AuthResourceService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月18日 下午5:17:26 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("authResourceService")
public class AuthResourceServiceImpl implements AuthResourceService {
	@Autowired
	private AuthResourceDao authResourceDao;
	
	@Override
	public List<AuthResource> findAll() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return this.authResourceDao.selectListBy(paramMap);
	}

	@Override
	public List<AuthResource> findAllMenu() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rsType", "M");
		return this.authResourceDao.selectListBy(paramMap);
	}

	@Override
	public List<AuthResource> findMenuByUser(AuthUser user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AuthResource> findByParentCode(String parentCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentCode", parentCode);
		return this.authResourceDao.selectListBy(paramMap);
	}

	@Override
	public AuthResource findByCode(String rsCode) {
		if(ValidUtils.isBlank(rsCode)) return null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rsCode", rsCode);
		return this.authResourceDao.selectOne(paramMap);
	}

	@Override
	public void add(AuthResource resource) {
		this.authResourceDao.insert(resource);
	}

	@Override
	public void editByCode(AuthResource resource) {
		this.authResourceDao.updateByPK(resource);
	}

	@Override
	public void delByCode(String rsCode) {
		this.authResourceDao.deleteByPK(rsCode);
	}

	@Override
	public List<AuthResource> findByRole(String roleCode) {
		return this.authResourceDao.findByRole(roleCode);
	}
	
}

