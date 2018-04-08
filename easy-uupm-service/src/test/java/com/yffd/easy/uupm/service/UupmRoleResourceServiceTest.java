package com.yffd.easy.uupm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.uupm.api.model.UupmRoleResourceModel;
import com.yffd.easy.uupm.base.UupmBaseServiceTest;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年04月05日 16时46分25秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class UupmRoleResourceServiceTest extends UupmBaseServiceTest {

	@Autowired
	private UupmRoleResourceService uupmRoleResourceService;
	
	@Test
	public void findPageTest() {
		PageParam pageParam = new PageParam(1L, 10L);
		UupmRoleResourceModel paramModel = new UupmRoleResourceModel();
		paramModel.setTenantCode("default");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);
		PageResult<UupmRoleResourceModel> pageResult = this.uupmRoleResourceService.findPage(paramModel, paramMap, pageParam, null);
		Assert.assertNotNull(pageResult);
		Assert.assertNotNull(pageResult.getRecordList());
		System.out.println(pageResult.getRecordList().size());
		for(Object obj : pageResult.getRecordList()) {
			System.out.println(obj);
		}
	}
	
	@Test
	public void findListTest() {
		UupmRoleResourceModel paramModel = new UupmRoleResourceModel();
		paramModel.setTenantCode("default");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);
		List<UupmRoleResourceModel> result = this.uupmRoleResourceService.findList(paramModel, paramMap, null);
		System.out.println(result);
	}
	
	@Test
	public void findCountTest() {
		UupmRoleResourceModel paramModel = new UupmRoleResourceModel();
		paramModel.setTenantCode("default");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);
		Long result = this.uupmRoleResourceService.findCount(paramModel, paramMap, null);
		System.out.println(result);
	}
	
	@Test
	public void findOneTest() {
		UupmRoleResourceModel paramModel = new UupmRoleResourceModel();
		paramModel.setTenantCode("default");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);
		UupmRoleResourceModel result = this.uupmRoleResourceService.findOne(paramModel, paramMap, null);
		System.out.println(result);
	}
	
	@Test
	public void addOneTest() {
		UupmRoleResourceModel paramModel = new UupmRoleResourceModel();
		paramModel.setTenantCode("default");
		int result = this.uupmRoleResourceService.addOne(paramModel, null);
		System.out.println(result);
		System.out.println(paramModel.getId());
	}
	
	@Test
	public void addListTest() {
		List<UupmRoleResourceModel> list = new ArrayList<UupmRoleResourceModel>();
		for(int i=0;i<5;i++) {
			UupmRoleResourceModel paramModel = new UupmRoleResourceModel();
			paramModel.setTenantCode("default_" + i);
			list.add(paramModel);
		}
		int result = this.uupmRoleResourceService.addList(list, null);
		System.out.println(result);
		for(UupmRoleResourceModel model : list) {
			System.out.println(model.getId());
		}
	}
	
	@Test
	public void updateTest() {
		UupmRoleResourceModel newModel = new UupmRoleResourceModel();
		newModel.setTenantCode("default-new");
		UupmRoleResourceModel oldModel = new UupmRoleResourceModel();
		oldModel.setTenantCode("default");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);
		
		int result = this.uupmRoleResourceService.update(newModel, oldModel, paramMap, null);
		System.out.println(result);
	}
	
	@Test
	public void deleteTest() {
		UupmRoleResourceModel paramModel = new UupmRoleResourceModel();
		paramModel.setTenantCode("default_0");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);

		int result = this.uupmRoleResourceService.delete(paramModel, paramMap);
		System.out.println(result);
	}
	
}
