package com.yffd.easy.pmi.service;

import java.util.List;

import com.yffd.easy.pmi.model.PmiResource;
import com.yffd.easy.pmi.model.PmiUser;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月18日 下午5:12:08 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface PmiResourceService {

	List<PmiResource> findAll();
	
	List<PmiResource> findAllMenu();
	
	List<PmiResource> findMenuByUser(PmiUser user);
	
	List<PmiResource> findByParentCode(String parentCode);
	
	PmiResource findByCode(String rsCode);
	
	void add(PmiResource resource);
	
	void editByCode(PmiResource resource);
	
	void delByCode(String rsCode);
	
	List<PmiResource> findByRole(String roleCode);
	
}

