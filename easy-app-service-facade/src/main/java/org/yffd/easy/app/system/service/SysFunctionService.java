package org.yffd.easy.app.system.service;

import java.util.List;

import org.yffd.easy.app.system.entity.SysFunction;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月13日 上午10:44:41 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface SysFunctionService {

	List<SysFunction> findFunction();
	
	List<SysFunction> findOperation();
	
	void add(SysFunction sysFunction);
	
	void edit(SysFunction sysFunction);
	
	void delByCode(String code);
	
}

