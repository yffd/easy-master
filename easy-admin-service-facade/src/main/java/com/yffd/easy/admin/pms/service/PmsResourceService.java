package com.yffd.easy.admin.pms.service;

import java.util.List;

import com.yffd.easy.admin.pms.model.PmsResource;
import com.yffd.easy.admin.pms.model.PmsUser;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月18日 下午5:12:08 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface PmsResourceService {

	List<PmsResource> findAll();
	
	List<PmsResource> findAllMenu();
	
	List<PmsResource> findMenuByUser(String userCode);
	
	List<PmsResource> findByParentCode(String parentCode);
	
	PmsResource findByCode(String rsCode);
	
	void add(PmsResource resource);
	
	void editByCode(PmsResource resource);
	
	void delByCode(String rsCode);
	
	List<PmsResource> findByRole(String roleCode);
	
}

