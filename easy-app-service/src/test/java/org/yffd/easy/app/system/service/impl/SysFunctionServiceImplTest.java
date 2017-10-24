package org.yffd.easy.app.system.service.impl;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yffd.easy.app.SpringBaseTestCase;
import org.yffd.easy.app.system.model.SysFunction;
import org.yffd.easy.app.system.service.SysFunctionService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月18日 下午5:25:10 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class SysFunctionServiceImplTest extends SpringBaseTestCase {

	@Autowired
	private SysFunctionService sysFunctionService;
	
	@Test
	public void findByRoleTest() {
		String roleCode = "ss";
		List<SysFunction> list = this.sysFunctionService.findByRole(roleCode);
		System.out.println(list.size());
	}
	
	@Test
	public void findMenuTest() {
		List<SysFunction> list = this.sysFunctionService.findByParentCode("-1");
		System.out.println(list.size());
	}
	
	@Test
	public void addTest() {
		SysFunction model = new SysFunction();
		model.setFuncName("系统管理");
		model.setFuncCode("sys");
		model.setParentCode("-1");
		model.setUrl("javascript:void(0);");
		model.setType("M");
		model.setActive("I");
		model.setSort(1.0F);
		model.setIconCls("icon-sys");
		model.setRemark("系统管理 》 菜单");
		model.setDefaultAdd("admin", new Date());
		this.sysFunctionService.add(model);
		
		SysFunction model1 = new SysFunction();
		model1.setFuncName("功能管理");
		model1.setFuncCode("sys:func");
		model1.setParentCode("sys");
		model1.setUrl("jsp/system/function/functionMain.jsp");
		model1.setType("M");
		model1.setActive("I");
		model1.setSort(1.0F);
		model1.setIconCls("icon-sys");
		model1.setRemark("系统管理 》 菜单");
		model1.setDefaultAdd("admin", new Date());
		this.sysFunctionService.add(model1);
	}
	
	@Test
	public void addMultiTest() {
		for(int i=0;i<5;i++) {
			SysFunction model = new SysFunction();
			model.setFuncName("一级_" + i);
			model.setFuncCode("code_" + i);
			model.setParentCode("-1");
			model.setUrl("www.baidu.com");
			model.setType("M");
			model.setActive("I");
			model.setSort(1.1F + i);
			model.setIconCls("icon-sys");
			model.setDefaultAdd("admin", new Date());
			this.sysFunctionService.add(model);
			
			for(int j=0;j<5;j++) {
				SysFunction child = new SysFunction();
				child.setFuncName("二级_" + i + "_" + j);
				child.setFuncCode("code_" + i + "_" + j);
				child.setParentCode("code_" + i);
				child.setUrl("www.baidu.com");
				child.setType("M");
				child.setActive("I");
				child.setSort(1.1F + i*10 + j);
				child.setIconCls("icon-sys");
				child.setDefaultAdd("admin", new Date());
				this.sysFunctionService.add(child);
			}
		}
	}
	
}

