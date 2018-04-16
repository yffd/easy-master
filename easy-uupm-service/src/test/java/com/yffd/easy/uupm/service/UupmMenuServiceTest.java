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
import com.yffd.easy.uupm.api.model.UupmMenuModel;
import com.yffd.easy.uupm.base.UupmBaseServiceTest;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年04月08日 16时48分28秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class UupmMenuServiceTest extends UupmBaseServiceTest {

	@Autowired
	private UupmMenuService uupmMenuService;
	
	@Test
	public void findPageTest() {
		PageParam pageParam = new PageParam(1L, 10L);
		UupmMenuModel paramModel = new UupmMenuModel();
		paramModel.setTenantCode("default");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);
		PageResult<UupmMenuModel> pageResult = this.uupmMenuService.findPage(paramModel, paramMap, pageParam, null);
		Assert.assertNotNull(pageResult);
		Assert.assertNotNull(pageResult.getRecordList());
		System.out.println(pageResult.getRecordList().size());
		for(Object obj : pageResult.getRecordList()) {
			System.out.println(obj);
		}
	}
	
	@Test
	public void findListTest() {
		UupmMenuModel paramModel = new UupmMenuModel();
		paramModel.setTenantCode("default");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);
		List<UupmMenuModel> result = this.uupmMenuService.findList(paramModel, paramMap, null);
		System.out.println(result);
	}
	
	@Test
	public void findCountTest() {
		UupmMenuModel paramModel = new UupmMenuModel();
		paramModel.setTenantCode("default");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);
		Long result = this.uupmMenuService.findCount(paramModel, paramMap, null);
		System.out.println(result);
	}
	
	@Test
	public void findOneTest() {
		UupmMenuModel paramModel = new UupmMenuModel();
		paramModel.setTenantCode("default");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);
		UupmMenuModel result = this.uupmMenuService.findOne(paramModel, paramMap, null);
		System.out.println(result);
	}
	
	@Test
	public void addOneTest() {
		UupmMenuModel paramModel = new UupmMenuModel();
		paramModel.setTenantCode("default");
		int result = this.uupmMenuService.addOne(paramModel, null);
		System.out.println(result);
		System.out.println(paramModel.getId());
	}
	
	@Test
	public void addListTest() {
		List<UupmMenuModel> list = new ArrayList<UupmMenuModel>();
		for(int i=0;i<5;i++) {
			UupmMenuModel paramModel = new UupmMenuModel();
			paramModel.setTenantCode("default_" + i);
			list.add(paramModel);
		}
		int result = this.uupmMenuService.addList(list, null);
		System.out.println(result);
		for(UupmMenuModel model : list) {
			System.out.println(model.getId());
		}
	}
	
	@Test
	public void updateTest() {
		UupmMenuModel newModel = new UupmMenuModel();
		newModel.setTenantCode("default-new");
		UupmMenuModel oldModel = new UupmMenuModel();
		oldModel.setTenantCode("default");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);
		
		int result = this.uupmMenuService.update(newModel, oldModel, paramMap, null);
		System.out.println(result);
	}
	
	@Test
	public void deleteTest() {
		UupmMenuModel paramModel = new UupmMenuModel();
		paramModel.setTenantCode("default_0");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);

		int result = this.uupmMenuService.delete(paramModel, paramMap);
		System.out.println(result);
	}
	
}
