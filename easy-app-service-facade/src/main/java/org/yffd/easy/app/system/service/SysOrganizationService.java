package org.yffd.easy.app.system.service;

import java.util.List;

import org.yffd.easy.app.system.model.SysOrganizationModel;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月13日 上午10:44:41 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface SysOrganizationService {

	List<SysOrganizationModel> findAll();
	
	List<SysOrganizationModel> findByParentCode(String parentCode);
	
	SysOrganizationModel findByCode(String orgCode);
	
	void add(SysOrganizationModel sysOrganization);
	
	void editByCode(SysOrganizationModel sysOrganization);
	
	void delByCode(String orgCode);
	
}

