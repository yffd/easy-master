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
import com.yffd.easy.uupm.api.model.UupmUserRoleModel;
import com.yffd.easy.uupm.base.UupmBaseServiceTest;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年04月06日 13时19分44秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class UupmUserRoleServiceTest extends UupmBaseServiceTest {

	@Autowired
	private UupmUserRoleService uupmUserRoleService;
	
	@Test
	public void findPageTest() {
		PageParam pageParam = new PageParam(1L, 10L);
		UupmUserRoleModel paramModel = new UupmUserRoleModel();
		paramModel.setTenantCode("default");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);
		PageResult<UupmUserRoleModel> pageResult = this.uupmUserRoleService.findPage(paramModel, paramMap, pageParam, null);
		Assert.assertNotNull(pageResult);
		Assert.assertNotNull(pageResult.getRecordList());
		System.out.println(pageResult.getRecordList().size());
		for(Object obj : pageResult.getRecordList()) {
			System.out.println(obj);
		}
	}
	
	@Test
	public void findListTest() {
		UupmUserRoleModel paramModel = new UupmUserRoleModel();
		paramModel.setTenantCode("default");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);
		List<UupmUserRoleModel> result = this.uupmUserRoleService.findList(paramModel, paramMap, null);
		System.out.println(result);
	}
	
	@Test
	public void findCountTest() {
		UupmUserRoleModel paramModel = new UupmUserRoleModel();
		paramModel.setTenantCode("default");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);
		Long result = this.uupmUserRoleService.findCount(paramModel, paramMap, null);
		System.out.println(result);
	}
	
	@Test
	public void findOneTest() {
		UupmUserRoleModel paramModel = new UupmUserRoleModel();
		paramModel.setTenantCode("default");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);
		UupmUserRoleModel result = this.uupmUserRoleService.findOne(paramModel, paramMap, null);
		System.out.println(result);
	}
	
	@Test
	public void addOneTest() {
		UupmUserRoleModel paramModel = new UupmUserRoleModel();
		paramModel.setTenantCode("default");
		int result = this.uupmUserRoleService.addOne(paramModel, null);
		System.out.println(result);
		System.out.println(paramModel.getId());
	}
	
	@Test
	public void addListTest() {
		List<UupmUserRoleModel> list = new ArrayList<UupmUserRoleModel>();
		for(int i=0;i<5;i++) {
			UupmUserRoleModel paramModel = new UupmUserRoleModel();
			paramModel.setTenantCode("default_" + i);
			list.add(paramModel);
		}
		int result = this.uupmUserRoleService.addList(list, null);
		System.out.println(result);
		for(UupmUserRoleModel model : list) {
			System.out.println(model.getId());
		}
	}
	
	@Test
	public void updateTest() {
		UupmUserRoleModel newModel = new UupmUserRoleModel();
		newModel.setTenantCode("default-new");
		UupmUserRoleModel oldModel = new UupmUserRoleModel();
		oldModel.setTenantCode("default");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);
		
		int result = this.uupmUserRoleService.update(newModel, oldModel, paramMap, null);
		System.out.println(result);
	}
	
	@Test
	public void deleteTest() {
		UupmUserRoleModel paramModel = new UupmUserRoleModel();
		paramModel.setTenantCode("default_0");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);

		int result = this.uupmUserRoleService.delete(paramModel, paramMap);
		System.out.println(result);
	}
	
}
