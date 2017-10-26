package org.yffd.easy.app.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yffd.easy.app.system.dao.SysRoleDao;
import org.yffd.easy.app.system.model.SysRole;
import org.yffd.easy.app.system.service.SysRoleService;
import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;
import org.yffd.easy.common.core.util.ValidUtils;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月23日 上午11:20:57 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleDao sysRoleDao;
	
	@Override
	public PageResult<SysRole> findPage(SysRole sysRole, PageParam pageParam) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(!ValidUtils.isEmpty(sysRole)) {
			paramMap.put("roleCode", sysRole.getRoleCode());
			paramMap.put("roleName", sysRole.getRoleName());
		}
		return this.sysRoleDao.selectPage(pageParam, paramMap);
	}

	@Override
	public List<SysRole> findList(SysRole sysRole) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(!ValidUtils.isEmpty(sysRole)) {
			paramMap.put("roleCode", sysRole.getRoleCode());
			paramMap.put("roleName", sysRole.getRoleName());
		}
		return this.sysRoleDao.selectListBy(paramMap);
	}

	@Override
	public SysRole findByCode(String roleCode) {
		if(ValidUtils.isBlank(roleCode)) return null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleCode", roleCode);
		return this.sysRoleDao.selectOne(paramMap);
	}

	@Override
	public void add(SysRole sysRole) {
		this.sysRoleDao.insert(sysRole);
	}

	@Override
	public void editByCode(SysRole sysRole) {
		this.sysRoleDao.updateByPK(sysRole);
	}

	@Override
	public void delByCode(String sysRole) {
		if(ValidUtils.isBlank(sysRole)) return;
		this.sysRoleDao.deleteByPK(sysRole);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void saveRoleFunc(String roleCode, List<String> funcCodes) {
		if(ValidUtils.isBlank(roleCode)) return;
		this.sysRoleDao.deleteRoleFunc(roleCode);
		if(!ValidUtils.isEmpty(funcCodes)) {
			this.sysRoleDao.saveRoleFunc(roleCode, funcCodes);
		}
	}

	
	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void saveRoleUser(String userCode, List<String> roleCodes) {
		if(ValidUtils.isBlank(userCode)) return;
		this.sysRoleDao.deleteRoleUser(userCode);
		if(!ValidUtils.isEmpty(roleCodes)) {
			this.sysRoleDao.saveRoleUser(userCode, roleCodes);
		}
	}

	@Override
	public List<SysRole> findByUser(String userCode) {
		return this.sysRoleDao.findByUser(userCode);
	}

}

