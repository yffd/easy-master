package com.yffd.easy.admin.pms.service.impl;

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

import com.yffd.easy.admin.pms.dao.PmsRoleDao;
import com.yffd.easy.admin.pms.model.PmsRole;
import com.yffd.easy.admin.pms.service.PmsRoleService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月23日 上午11:20:57 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("pmsRoleService")
public class PmsRoleServiceImpl implements PmsRoleService {

	@Autowired
	private PmsRoleDao pmsRoleDao;
	
	@Override
	public PageResult<PmsRole> findPage(PmsRole role, PageParam pageParam) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(!ValidUtils.isEmpty(role)) {
			paramMap.put("roleCode", role.getRoleCode());
			paramMap.put("roleName", role.getRoleName());
		}
		return this.pmsRoleDao.selectPage(pageParam, paramMap);
	}

	@Override
	public List<PmsRole> findList(PmsRole role) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(!ValidUtils.isEmpty(role)) {
			paramMap.put("roleCode", role.getRoleCode());
			paramMap.put("roleName", role.getRoleName());
		}
		return this.pmsRoleDao.selectListBy(paramMap);
	}

	@Override
	public PmsRole findByCode(String roleCode) {
		if(ValidUtils.isBlank(roleCode)) return null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleCode", roleCode);
		return this.pmsRoleDao.selectOne(paramMap);
	}

	@Override
	public void add(PmsRole role) {
		this.pmsRoleDao.insert(role);
	}

	@Override
	public void editByCode(PmsRole role) {
		this.pmsRoleDao.updateByPK(role);
	}

	@Override
	public void delByCode(String roleCode) {
		if(ValidUtils.isBlank(roleCode)) return;
		this.pmsRoleDao.deleteByPK(roleCode);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void saveRoleResource(String roleCode, List<String> rsCodes) {
		if(ValidUtils.isBlank(roleCode)) return;
		this.pmsRoleDao.deleteRoleResource(roleCode);
		if(!ValidUtils.isEmpty(rsCodes)) {
			this.pmsRoleDao.saveRoleResource(roleCode, rsCodes);
		}
	}

	
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void saveRoleUser(String userCode, List<String> roleCodes) {
		if(ValidUtils.isBlank(userCode)) return;
		this.pmsRoleDao.deleteRoleUser(userCode);
		if(!ValidUtils.isEmpty(roleCodes)) {
			this.pmsRoleDao.saveRoleUser(userCode, roleCodes);
		}
	}

	@Override
	public List<PmsRole> findByUser(String userCode) {
		return this.pmsRoleDao.findByUser(userCode);
	}

}

