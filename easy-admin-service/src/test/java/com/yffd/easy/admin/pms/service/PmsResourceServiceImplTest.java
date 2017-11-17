package com.yffd.easy.admin.pms.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yffd.easy.admin.pms.SpringBaseTestCase;
import com.yffd.easy.admin.pms.model.PmsResource;
import com.yffd.easy.admin.pms.service.PmsResourceService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月18日 下午5:25:10 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class PmsResourceServiceImplTest extends SpringBaseTestCase {

	@Autowired
	private PmsResourceService pmsResourceService;
	
	@Test
	public void findMenuByUserTest() {
		String userCode = "admin";
		List<PmsResource> list = this.pmsResourceService.findMenuByUser(userCode);
		System.out.println(list.size());
	}
	@Test
	public void findByRoleTest() {
		String roleCode = "ss";
		List<PmsResource> list = this.pmsResourceService.findByRole(roleCode);
		System.out.println(list.size());
	}
	
	@Test
	public void findMenuTest() {
		List<PmsResource> list = this.pmsResourceService.findByParentCode("-1");
		System.out.println(list.size());
	}
	
	@Test
	public void addTest() {
		PmsResource model = new PmsResource();
		model.setRsName("系统管理");
		model.setRsCode("sys");
		model.setParentCode("-1");
		model.setInUrl("javascript:void(0);");
		model.setRsType("M");
		model.setRsState("A");
		model.setRsNum(1);
		model.setRemark("系统管理 》 菜单");
		this.setAddDefault(model, "test", new Date());
		this.pmsResourceService.add(model);
		
		PmsResource model1 = new PmsResource();
		model1.setRsName("资源管理");
		model1.setRsCode("sys-rs");
		model1.setParentCode("sys");
		model1.setInUrl("jsp/pmi/resource/resourceMain.jsp");
		model1.setRsType("M");
		model1.setRsState("A");
		model1.setRsNum(2);
		model1.setRemark("系统管理 》 资源");
		this.setAddDefault(model, "test", new Date());
		this.pmsResourceService.add(model1);
	}
	
	@Test
	public void addMultiTest() {
		for(int i=0;i<5;i++) {
			PmsResource model = new PmsResource();
			model.setRsName("一级_" + i);
			model.setRsCode("code_" + i);
			model.setParentCode("-1");
			model.setInUrl("www.baidu.com");
			model.setRsType("M");
			model.setRsState("A");
			model.setRsNum(1 + i);
			this.setAddDefault(model, "test", new Date());
			this.pmsResourceService.add(model);
			
			for(int j=0;j<5;j++) {
				PmsResource child = new PmsResource();
				child.setRsName("二级_" + i + "_" + j);
				child.setRsCode("code_" + i + "_" + j);
				child.setParentCode("code_" + i);
				child.setInUrl("www.baidu.com");
				child.setRsType("M");
				child.setRsState("A");
				child.setRsNum(1 + i*10 + j);
				this.setAddDefault(child, "test", new Date());
				this.pmsResourceService.add(child);
			}
		}
	}
	
}

