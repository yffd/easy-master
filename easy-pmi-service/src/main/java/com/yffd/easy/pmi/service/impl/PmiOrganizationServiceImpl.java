package com.yffd.easy.pmi.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yffd.easy.common.core.util.ValidUtils;

import com.yffd.easy.pmi.dao.PmiOrganizationDao;
import com.yffd.easy.pmi.model.PmiOrganization;
import com.yffd.easy.pmi.service.PmiOrganizationService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月29日 下午2:07:57 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("pmiOrganizationService")
public class PmiOrganizationServiceImpl implements PmiOrganizationService {

	@Autowired
	private PmiOrganizationDao pmiOrganizationDao;
	
	@Override
	public List<PmiOrganization> findAll() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return this.pmiOrganizationDao.selectListBy(paramMap);
	}

	@Override
	public List<PmiOrganization> findByParentCode(String parentCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentCode", parentCode);
		return this.pmiOrganizationDao.selectListBy(paramMap);
	}

	@Override
	public PmiOrganization findByCode(String orgCode) {
		if(ValidUtils.isBlank(orgCode)) return null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgCode", orgCode);
		return this.pmiOrganizationDao.selectOne(paramMap);
	}

	@Override
	public void add(PmiOrganization organization) {
		this.pmiOrganizationDao.insert(organization);
	}

	@Override
	public void editByCode(PmiOrganization organization) {
		this.pmiOrganizationDao.updateByPK(organization);
	}

	@Override
	public void delByCode(String orgCode) {
		this.pmiOrganizationDao.deleteByPK(orgCode);
	}

}

