package com.yffd.easy.pmi.service;

import java.util.List;

import com.yffd.easy.pmi.model.PmiOrganization;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月13日 上午10:44:41 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface PmiOrganizationService {

	List<PmiOrganization> findAll();
	
	List<PmiOrganization> findByParentCode(String parentCode);
	
	PmiOrganization findByCode(String orgCode);
	
	void add(PmiOrganization organization);
	
	void editByCode(PmiOrganization organization);
	
	void delByCode(String orgCode);
	
}

