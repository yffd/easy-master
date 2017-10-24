package org.yffd.easy.app.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yffd.easy.app.system.dao.SysOrganizationDao;
import org.yffd.easy.app.system.model.SysOrganization;
import org.yffd.easy.app.system.service.SysOrganizationService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月29日 下午2:07:57 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("sysOrganizationService")
public class SysOrganizationServiceImpl implements SysOrganizationService {

	@Autowired
	private SysOrganizationDao sysOrganizationDao;
	
	@Override
	public List<SysOrganization> findAll() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return this.sysOrganizationDao.selectListBy(paramMap);
	}

	@Override
	public List<SysOrganization> findByParentCode(String parentCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentCode", parentCode);
		return this.sysOrganizationDao.selectListBy(paramMap);
	}

	@Override
	public SysOrganization findByCode(String orgCode) {
		return this.sysOrganizationDao.selectByPK(orgCode);
	}

	@Override
	public void add(SysOrganization sysOrganization) {
		this.sysOrganizationDao.insert(sysOrganization);
	}

	@Override
	public void editByCode(SysOrganization sysOrganization) {
		this.sysOrganizationDao.updateByPK(sysOrganization);
	}

	@Override
	public void delByCode(String orgCode) {
		this.sysOrganizationDao.deleteByPK(orgCode);
	}

}

