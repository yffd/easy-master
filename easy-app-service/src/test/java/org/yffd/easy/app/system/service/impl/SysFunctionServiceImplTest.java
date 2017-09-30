package org.yffd.easy.app.system.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yffd.easy.app.SpringBaseTestCase;
import org.yffd.easy.app.system.entity.SysFunction;
import org.yffd.easy.app.system.service.SysFunctionService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月27日 下午5:30:34 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class SysFunctionServiceImplTest extends SpringBaseTestCase {

	@Autowired
	private SysFunctionService sysFunctionService;
	
	@Test
	public void addTest() {
		SysFunction sysFunction = new SysFunction();
		sysFunction.setDefault();
		sysFunction.setName("系统管理");
		sysFunction.setCode("sys:sys");
		sysFunction.setIcon("icon-sys");
		sysFunction.setType(SysFunction.TYPE_FUNCTION);
		sysFunction.setUrl("javascript:void(0);");
		sysFunction.setSort(1.0F);
		sysFunction.setRemark("菜单");
		
		this.sysFunctionService.add(sysFunction );
		
		/*******************************************/
		
		sysFunction = new SysFunction();
		sysFunction.setDefault();
		sysFunction.setName("公司管理");
		sysFunction.setCode("sys:comp");
		sysFunction.setIcon("icon-comp");
		sysFunction.setType(SysFunction.TYPE_FUNCTION);
		sysFunction.setUrl("javascript:void(0);");
		sysFunction.setSort(2.0F);
		sysFunction.setRemark("菜单");
		
		this.sysFunctionService.add(sysFunction );
		
/*******************************************/
		
		sysFunction = new SysFunction();
		sysFunction.setDefault();
		sysFunction.setName("功能管理");
		sysFunction.setCode("sys:func");
		sysFunction.setParentCode("sys:sys");
		sysFunction.setIcon("icon-pro");
		sysFunction.setType(SysFunction.TYPE_FUNCTION);
		sysFunction.setUrl("javascript:void(0);");
		sysFunction.setSort(1.1F);
		sysFunction.setRemark("菜单");
		
		this.sysFunctionService.add(sysFunction );
		
/*******************************************/
		
		sysFunction = new SysFunction();
		sysFunction.setDefault();
		sysFunction.setName("数据库管理");
		sysFunction.setCode("sys:db");
		sysFunction.setParentCode("sys:sys");
		sysFunction.setIcon("icon-db");
		sysFunction.setType(SysFunction.TYPE_FUNCTION);
		sysFunction.setUrl("javascript:void(0);");
		sysFunction.setSort(1.2F);
		sysFunction.setRemark("菜单");
		
		this.sysFunctionService.add(sysFunction );
	}
}

