package org.yffd.easy.app.system.service;

import java.util.List;

import org.yffd.easy.app.system.model.SysFunction;
import org.yffd.easy.app.system.model.SysUser;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月18日 下午5:12:08 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface SysFunctionService {

	List<SysFunction> findAll();
	
	List<SysFunction> findAllMenu();
	
	List<SysFunction> findMenuByUser(SysUser user);
	
	List<SysFunction> findByParentCode(String parentCode);
	
	SysFunction findByCode(String funcCode);
	
	void add(SysFunction model);
	
	void editByCode(SysFunction model);
	
	void delByCode(String funcCode);
	
	List<SysFunction> findByRole(String roleCode);
	
}

