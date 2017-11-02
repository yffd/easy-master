package com.yffd.easy.auth.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;
import org.yffd.easy.common.core.util.ValidUtils;

import com.yffd.easy.auth.dao.AuthRoleDao;
import com.yffd.easy.auth.model.AuthRole;
import com.yffd.easy.auth.service.AuthRoleService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月23日 上午11:20:57 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("authRoleService")
public class AuthRoleServiceImpl implements AuthRoleService {

	@Autowired
	private AuthRoleDao authRoleDao;
	
	@Override
	public PageResult<AuthRole> findPage(AuthRole role, PageParam pageParam) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(!ValidUtils.isEmpty(role)) {
			paramMap.put("roleCode", role.getRoleCode());
			paramMap.put("roleName", role.getRoleName());
		}
		return this.authRoleDao.selectPage(pageParam, paramMap);
	}

	@Override
	public List<AuthRole> findList(AuthRole role) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(!ValidUtils.isEmpty(role)) {
			paramMap.put("roleCode", role.getRoleCode());
			paramMap.put("roleName", role.getRoleName());
		}
		return this.authRoleDao.selectListBy(paramMap);
	}

	@Override
	public AuthRole findByCode(String roleCode) {
		if(ValidUtils.isBlank(roleCode)) return null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleCode", roleCode);
		return this.authRoleDao.selectOne(paramMap);
	}

	@Override
	public void add(AuthRole role) {
		this.authRoleDao.insert(role);
	}

	@Override
	public void editByCode(AuthRole role) {
		this.authRoleDao.updateByPK(role);
	}

	@Override
	public void delByCode(String roleCode) {
		if(ValidUtils.isBlank(roleCode)) return;
		this.authRoleDao.deleteByPK(roleCode);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void saveRoleResource(String roleCode, List<String> rsCodes) {
		if(ValidUtils.isBlank(roleCode)) return;
		this.authRoleDao.deleteRoleResource(roleCode);
		if(!ValidUtils.isEmpty(rsCodes)) {
			this.authRoleDao.saveRoleResource(roleCode, rsCodes);
		}
	}

	
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void saveRoleUser(String userCode, List<String> roleCodes) {
		if(ValidUtils.isBlank(userCode)) return;
		this.authRoleDao.deleteRoleUser(userCode);
		if(!ValidUtils.isEmpty(roleCodes)) {
			this.authRoleDao.saveRoleUser(userCode, roleCodes);
		}
	}

	@Override
	public List<AuthRole> findByUser(String userCode) {
		return this.authRoleDao.findByUser(userCode);
	}

}

