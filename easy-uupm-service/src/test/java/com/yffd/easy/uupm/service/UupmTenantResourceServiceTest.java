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
import com.yffd.easy.uupm.api.model.UupmTenantResourceModel;
import com.yffd.easy.uupm.base.UupmBaseServiceTest;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年04月04日 17时30分01秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class UupmTenantResourceServiceTest extends UupmBaseServiceTest {

	@Autowired
	private UupmTenantResourceService uupmTenantResourceService;
	
	@Test
	public void findPageTest() {
		PageParam pageParam = new PageParam(1L, 10L);
		UupmTenantResourceModel paramModel = new UupmTenantResourceModel();
		paramModel.setTenantCode("default");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);
		PageResult<UupmTenantResourceModel> pageResult = this.uupmTenantResourceService.findPage(paramModel, paramMap, pageParam, null);
		Assert.assertNotNull(pageResult);
		Assert.assertNotNull(pageResult.getRecordList());
		System.out.println(pageResult.getRecordList().size());
		for(Object obj : pageResult.getRecordList()) {
			System.out.println(obj);
		}
	}
	
	@Test
	public void findListTest() {
		UupmTenantResourceModel paramModel = new UupmTenantResourceModel();
		paramModel.setTenantCode("default");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);
		List<UupmTenantResourceModel> result = this.uupmTenantResourceService.findList(paramModel, paramMap, null);
		System.out.println(result);
	}
	
	@Test
	public void findCountTest() {
		UupmTenantResourceModel paramModel = new UupmTenantResourceModel();
		paramModel.setTenantCode("default");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);
		Long result = this.uupmTenantResourceService.findCount(paramModel, paramMap, null);
		System.out.println(result);
	}
	
	@Test
	public void findOneTest() {
		UupmTenantResourceModel paramModel = new UupmTenantResourceModel();
		paramModel.setTenantCode("default");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);
		UupmTenantResourceModel result = this.uupmTenantResourceService.findOne(paramModel, paramMap, null);
		System.out.println(result);
	}
	
	@Test
	public void addOneTest() {
		UupmTenantResourceModel paramModel = new UupmTenantResourceModel();
		paramModel.setTenantCode("default");
		int result = this.uupmTenantResourceService.addOne(paramModel, null);
		System.out.println(result);
		System.out.println(paramModel.getId());
	}
	
	@Test
	public void addListTest() {
		List<UupmTenantResourceModel> list = new ArrayList<UupmTenantResourceModel>();
		for(int i=0;i<5;i++) {
			UupmTenantResourceModel paramModel = new UupmTenantResourceModel();
			paramModel.setTenantCode("default_" + i);
			list.add(paramModel);
		}
		int result = this.uupmTenantResourceService.addList(list, null);
		System.out.println(result);
		for(UupmTenantResourceModel model : list) {
			System.out.println(model.getId());
		}
	}
	
	@Test
	public void updateTest() {
		UupmTenantResourceModel newModel = new UupmTenantResourceModel();
		newModel.setTenantCode("default-new");
		UupmTenantResourceModel oldModel = new UupmTenantResourceModel();
		oldModel.setTenantCode("default");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);
		
		int result = this.uupmTenantResourceService.update(newModel, oldModel, paramMap, null);
		System.out.println(result);
	}
	
	@Test
	public void deleteTest() {
		UupmTenantResourceModel paramModel = new UupmTenantResourceModel();
		paramModel.setTenantCode("default_0");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);

		int result = this.uupmTenantResourceService.delete(paramModel, paramMap);
		System.out.println(result);
	}
	
}
