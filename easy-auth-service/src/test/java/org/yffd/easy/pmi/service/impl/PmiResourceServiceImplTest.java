package org.yffd.easy.pmi.service.impl;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yffd.easy.pmi.SpringBaseTestCase;

import com.yffd.easy.auth.model.AuthResource;
import com.yffd.easy.auth.service.AuthResourceService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月18日 下午5:25:10 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class PmiResourceServiceImplTest extends SpringBaseTestCase {

	@Autowired
	private AuthResourceService pmiResourceService;
	
	@Test
	public void findByRoleTest() {
		String roleCode = "ss";
		List<AuthResource> list = this.pmiResourceService.findByRole(roleCode);
		System.out.println(list.size());
	}
	
	@Test
	public void findMenuTest() {
		List<AuthResource> list = this.pmiResourceService.findByParentCode("-1");
		System.out.println(list.size());
	}
	
	@Test
	public void addTest() {
		AuthResource model = new AuthResource();
		model.setRsName("系统管理");
		model.setRsCode("sys");
		model.setParentCode("-1");
		model.setInUrl("javascript:void(0);");
		model.setRsType("M");
		model.setRsState("A");
		model.setRsNum(1);
		model.setRemark("系统管理 》 菜单");
		model.setDefaultAdd("admin", new Date());
		this.pmiResourceService.add(model);
		
		AuthResource model1 = new AuthResource();
		model1.setRsName("资源管理");
		model1.setRsCode("sys-rs");
		model1.setParentCode("sys");
		model1.setInUrl("jsp/pmi/resource/resourceMain.jsp");
		model1.setRsType("M");
		model1.setRsState("A");
		model1.setRsNum(2);
		model1.setRemark("系统管理 》 资源");
		model1.setDefaultAdd("admin", new Date());
		this.pmiResourceService.add(model1);
	}
	
	@Test
	public void addMultiTest() {
		for(int i=0;i<5;i++) {
			AuthResource model = new AuthResource();
			model.setRsName("一级_" + i);
			model.setRsCode("code_" + i);
			model.setParentCode("-1");
			model.setInUrl("www.baidu.com");
			model.setRsType("M");
			model.setRsState("A");
			model.setRsNum(1 + i);
			model.setDefaultAdd("admin", new Date());
			this.pmiResourceService.add(model);
			
			for(int j=0;j<5;j++) {
				AuthResource child = new AuthResource();
				child.setRsName("二级_" + i + "_" + j);
				child.setRsCode("code_" + i + "_" + j);
				child.setParentCode("code_" + i);
				child.setInUrl("www.baidu.com");
				child.setRsType("M");
				child.setRsState("A");
				child.setRsNum(1 + i*10 + j);
				child.setDefaultAdd("admin", new Date());
				this.pmiResourceService.add(child);
			}
		}
	}
	
}

