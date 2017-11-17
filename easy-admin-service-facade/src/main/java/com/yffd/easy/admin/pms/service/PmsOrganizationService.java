package com.yffd.easy.admin.pms.service;

import java.util.List;

import com.yffd.easy.admin.pms.model.PmsOrganization;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月13日 上午10:44:41 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface PmsOrganizationService {

	List<PmsOrganization> findAll();
	
	List<PmsOrganization> findByParentCode(String parentCode);
	
	PmsOrganization findByCode(String orgCode);
	
	void add(PmsOrganization organization);
	
	void editByCode(PmsOrganization organization);
	
	void delByCode(String orgCode);
	
}

