package com.yffd.easy.admin.pms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yffd.easy.common.core.util.ValidUtils;

import com.yffd.easy.admin.pms.dao.PmsResourceDao;
import com.yffd.easy.admin.pms.model.PmsResource;
import com.yffd.easy.admin.pms.model.PmsUser;
import com.yffd.easy.admin.pms.service.PmsResourceService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月18日 下午5:17:26 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("pmsResourceService")
public class PmsResourceServiceImpl implements PmsResourceService {
	@Autowired
	private PmsResourceDao pmsResourceDao;
	
	@Override
	public List<PmsResource> findAll() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return this.pmsResourceDao.selectListBy(paramMap);
	}

	@Override
	public List<PmsResource> findAllMenu() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rsType", "M");
		return this.pmsResourceDao.selectListBy(paramMap);
	}

	@Override
	public List<PmsResource> findMenuByUser(String userCode) {
		return this.pmsResourceDao.findByUser(userCode);
	}

	@Override
	public List<PmsResource> findByParentCode(String parentCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentCode", parentCode);
		return this.pmsResourceDao.selectListBy(paramMap);
	}

	@Override
	public PmsResource findByCode(String rsCode) {
		if(ValidUtils.isBlank(rsCode)) return null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rsCode", rsCode);
		return this.pmsResourceDao.selectOne(paramMap);
	}

	@Override
	public void add(PmsResource resource) {
		this.pmsResourceDao.insert(resource);
	}

	@Override
	public void editByCode(PmsResource resource) {
		this.pmsResourceDao.updateByPK(resource);
	}

	@Override
	public void delByCode(String rsCode) {
		this.pmsResourceDao.deleteByPK(rsCode);
	}

	@Override
	public List<PmsResource> findByRole(String roleCode) {
		return this.pmsResourceDao.findByRole(roleCode);
	}
	
}

