package org.yffd.easy.app.system.service;

import java.util.List;

import org.yffd.easy.app.system.model.SysFunctionModel;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月18日 下午5:12:08 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface SysFunctionService {

	List<SysFunctionModel> findAll();
	
	List<SysFunctionModel> findAllMenu();
	
	List<SysFunctionModel> findByParentCode(String parentCode);
	
	SysFunctionModel findByCode(String funcCode);
	
	List<SysFunctionModel> findOperation();
	
	void add(SysFunctionModel model);
	
	void editByCode(SysFunctionModel model);
	
	void delByCode(String funcCode);
	
	
}

