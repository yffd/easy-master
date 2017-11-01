package com.yffd.easy.pmi.service.impl;

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

import com.yffd.easy.pmi.dao.PmiRoleDao;
import com.yffd.easy.pmi.model.PmiRole;
import com.yffd.easy.pmi.service.PmiRoleService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月23日 上午11:20:57 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("pmiRoleService")
public class PmiRoleServiceImpl implements PmiRoleService {

	@Autowired
	private PmiRoleDao pmiRoleDao;
	
	@Override
	public PageResult<PmiRole> findPage(PmiRole role, PageParam pageParam) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(!ValidUtils.isEmpty(role)) {
			paramMap.put("roleCode", role.getRoleCode());
			paramMap.put("roleName", role.getRoleName());
		}
		return this.pmiRoleDao.selectPage(pageParam, paramMap);
	}

	@Override
	public List<PmiRole> findList(PmiRole role) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(!ValidUtils.isEmpty(role)) {
			paramMap.put("roleCode", role.getRoleCode());
			paramMap.put("roleName", role.getRoleName());
		}
		return this.pmiRoleDao.selectListBy(paramMap);
	}

	@Override
	public PmiRole findByCode(String roleCode) {
		if(ValidUtils.isBlank(roleCode)) return null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleCode", roleCode);
		return this.pmiRoleDao.selectOne(paramMap);
	}

	@Override
	public void add(PmiRole role) {
		this.pmiRoleDao.insert(role);
	}

	@Override
	public void editByCode(PmiRole role) {
		this.pmiRoleDao.updateByPK(role);
	}

	@Override
	public void delByCode(String roleCode) {
		if(ValidUtils.isBlank(roleCode)) return;
		this.pmiRoleDao.deleteByPK(roleCode);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void saveRoleResource(String roleCode, List<String> rsCodes) {
		if(ValidUtils.isBlank(roleCode)) return;
		this.pmiRoleDao.deleteRoleResource(roleCode);
		if(!ValidUtils.isEmpty(rsCodes)) {
			this.pmiRoleDao.saveRoleResource(roleCode, rsCodes);
		}
	}

	
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void saveRoleUser(String userCode, List<String> roleCodes) {
		if(ValidUtils.isBlank(userCode)) return;
		this.pmiRoleDao.deleteRoleUser(userCode);
		if(!ValidUtils.isEmpty(roleCodes)) {
			this.pmiRoleDao.saveRoleUser(userCode, roleCodes);
		}
	}

	@Override
	public List<PmiRole> findByUser(String userCode) {
		return this.pmiRoleDao.findByUser(userCode);
	}

}

