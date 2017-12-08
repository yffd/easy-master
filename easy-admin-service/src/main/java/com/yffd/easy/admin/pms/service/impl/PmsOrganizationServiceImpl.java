package com.yffd.easy.admin.pms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.admin.pms.dao.PmsOrganizationDao;
import com.yffd.easy.admin.pms.model.PmsOrganization;
import com.yffd.easy.admin.pms.service.PmsOrganizationService;
import com.yffd.easy.common.core.util.ValidUtils;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月29日 下午2:07:57 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("pmsOrganizationService")
public class PmsOrganizationServiceImpl implements PmsOrganizationService {

	@Autowired
	private PmsOrganizationDao pmsOrganizationDao;
	
	@Override
	public List<PmsOrganization> findAll() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return this.pmsOrganizationDao.selectListBy(paramMap);
	}

	@Override
	public List<PmsOrganization> findByParentCode(String parentCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentCode", parentCode);
		return this.pmsOrganizationDao.selectListBy(paramMap);
	}

	@Override
	public PmsOrganization findByCode(String orgCode) {
		if(ValidUtils.isBlank(orgCode)) return null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgCode", orgCode);
		return this.pmsOrganizationDao.selectOne(paramMap);
	}

	@Override
	public void add(PmsOrganization organization) {
		this.pmsOrganizationDao.insert(organization);
	}

	@Override
	public void editByCode(PmsOrganization organization) {
		this.pmsOrganizationDao.updateByPK(organization);
	}

	@Override
	public void delByCode(String orgCode) {
		this.pmsOrganizationDao.deleteByPK(orgCode);
	}

}

