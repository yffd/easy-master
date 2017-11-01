package com.yffd.easy.pmi.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yffd.easy.common.core.util.ValidUtils;

import com.yffd.easy.pmi.dao.PmiResourceDao;
import com.yffd.easy.pmi.model.PmiResource;
import com.yffd.easy.pmi.model.PmiUser;
import com.yffd.easy.pmi.service.PmiResourceService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月18日 下午5:17:26 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("pmiResourceService")
public class PmiResourceServiceImpl implements PmiResourceService {
	@Autowired
	private PmiResourceDao pmiResourceDao;
	
	@Override
	public List<PmiResource> findAll() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return this.pmiResourceDao.selectListBy(paramMap);
	}

	@Override
	public List<PmiResource> findAllMenu() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rsType", "M");
		return this.pmiResourceDao.selectListBy(paramMap);
	}

	@Override
	public List<PmiResource> findMenuByUser(PmiUser user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PmiResource> findByParentCode(String parentCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentCode", parentCode);
		return this.pmiResourceDao.selectListBy(paramMap);
	}

	@Override
	public PmiResource findByCode(String rsCode) {
		if(ValidUtils.isBlank(rsCode)) return null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rsCode", rsCode);
		return this.pmiResourceDao.selectOne(paramMap);
	}

	@Override
	public void add(PmiResource resource) {
		this.pmiResourceDao.insert(resource);
	}

	@Override
	public void editByCode(PmiResource resource) {
		this.pmiResourceDao.updateByPK(resource);
	}

	@Override
	public void delByCode(String rsCode) {
		this.pmiResourceDao.deleteByPK(rsCode);
	}

	@Override
	public List<PmiResource> findByRole(String roleCode) {
		return this.pmiResourceDao.findByRole(roleCode);
	}
	
}

