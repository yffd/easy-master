package org.yffd.easy.app.system.service.impl;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yffd.easy.app.SpringBaseTestCase;
import org.yffd.easy.app.system.model.SysPermission;
import org.yffd.easy.app.system.service.SysPermissionService;
import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月12日 下午2:48:34 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class SysPermissionServiceImplTest extends SpringBaseTestCase {
	@Autowired
	private SysPermissionService sysPermissionService;
	
	@Test
	public void findListTest() {
		String code = "";
		String name = "";
		PageParam pageParam = new PageParam(1L, 10L);
		PageResult<SysPermission> pageResult = this.sysPermissionService.findList(name, code, pageParam);
		System.out.println(pageResult.getRecordList().size());
	}
	@Test
	public void addTest() {
		for(int i=0;i<20;i++) {
			SysPermission permission = new SysPermission();
			permission.setDefaultAdd("admin", new Date());
			permission.setCode("pms:menu:view_" + i);
			permission.setName("系统管理-菜单-查看_" + i);
			this.sysPermissionService.add(permission);
		}
		
	}
}

