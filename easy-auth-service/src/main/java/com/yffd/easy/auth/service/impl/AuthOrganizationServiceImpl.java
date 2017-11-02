package com.yffd.easy.auth.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yffd.easy.common.core.util.ValidUtils;

import com.yffd.easy.auth.dao.AuthOrganizationDao;
import com.yffd.easy.auth.model.AuthOrganization;
import com.yffd.easy.auth.service.AuthOrganizationService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月29日 下午2:07:57 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("authOrganizationService")
public class AuthOrganizationServiceImpl implements AuthOrganizationService {

	@Autowired
	private AuthOrganizationDao authOrganizationDao;
	
	@Override
	public List<AuthOrganization> findAll() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return this.authOrganizationDao.selectListBy(paramMap);
	}

	@Override
	public List<AuthOrganization> findByParentCode(String parentCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentCode", parentCode);
		return this.authOrganizationDao.selectListBy(paramMap);
	}

	@Override
	public AuthOrganization findByCode(String orgCode) {
		if(ValidUtils.isBlank(orgCode)) return null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgCode", orgCode);
		return this.authOrganizationDao.selectOne(paramMap);
	}

	@Override
	public void add(AuthOrganization organization) {
		this.authOrganizationDao.insert(organization);
	}

	@Override
	public void editByCode(AuthOrganization organization) {
		this.authOrganizationDao.updateByPK(organization);
	}

	@Override
	public void delByCode(String orgCode) {
		this.authOrganizationDao.deleteByPK(orgCode);
	}

}

