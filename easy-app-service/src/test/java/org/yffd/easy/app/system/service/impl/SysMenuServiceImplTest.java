package org.yffd.easy.app.system.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yffd.easy.app.SpringBaseTestCase;
import org.yffd.easy.app.system.model.SysMenu;
import org.yffd.easy.app.system.service.SysMenuService;
import org.yffd.easy.common.core.enums.SysConstantsEnum;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月27日 下午5:30:34 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class SysMenuServiceImplTest extends SpringBaseTestCase {

	@Autowired
	private SysMenuService sysMenuService;
	
	@Test
	public void addTest() {
		SysMenu entity = new SysMenu();
		entity.setDefault();
		entity.setName("系统管理");
		entity.setCode("sys:sys");
		entity.setIcon("icon-easy icon-easy-sys");
		entity.setUrl("javascript:void(0);");
		entity.setSort(1.0F);
		entity.setStatus(SysConstantsEnum.ACTIVE.getValue());
		entity.setRemark("菜单");
		
		this.sysMenuService.add(entity );
		
		/*******************************************/
		
		entity = new SysMenu();
		entity.setDefault();
		entity.setName("公司管理");
		entity.setCode("sys:comp");
		entity.setIcon("icon-easy icon-easy-role");
		entity.setUrl("javascript:void(0);");
		entity.setSort(2.0F);
		entity.setStatus(SysConstantsEnum.ACTIVE.getValue());
		entity.setRemark("菜单");
		
		this.sysMenuService.add(entity );
		
/*******************************************/
		
		entity = new SysMenu();
		entity.setDefault();
		entity.setName("功能管理");
		entity.setCode("sys:func");
		entity.setParentCode("sys:sys");
		entity.setIcon("icon-easy icon-easy-users");
		entity.setUrl("javascript:void(0);");
		entity.setSort(1.1F);
		entity.setStatus(SysConstantsEnum.ACTIVE.getValue());
		entity.setRemark("菜单");
		
		this.sysMenuService.add(entity );
		
/*******************************************/
		
		entity = new SysMenu();
		entity.setDefault();
		entity.setName("数据库管理");
		entity.setCode("sys:db");
		entity.setParentCode("sys:sys");
		entity.setIcon("icon-easy icon-easy-role");
		entity.setUrl("javascript:void(0);");
		entity.setSort(1.2F);
		entity.setStatus(SysConstantsEnum.ACTIVE.getValue());
		entity.setRemark("菜单");
		
		this.sysMenuService.add(entity );
	}
}

