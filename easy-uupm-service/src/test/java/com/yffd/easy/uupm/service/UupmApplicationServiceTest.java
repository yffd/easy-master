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
import com.yffd.easy.uupm.api.model.UupmApplicationModel;
import com.yffd.easy.uupm.base.UupmBaseServiceTest;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年04月06日 14时08分50秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class UupmApplicationServiceTest extends UupmBaseServiceTest {

	@Autowired
	private UupmApplicationService uupmApplicationService;
	
	@Test
	public void findPageTest() {
		PageParam pageParam = new PageParam(1L, 10L);
		UupmApplicationModel paramModel = new UupmApplicationModel();
		paramModel.setTenantCode("default");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);
		PageResult<UupmApplicationModel> pageResult = this.uupmApplicationService.findPage(paramModel, paramMap, pageParam, null);
		Assert.assertNotNull(pageResult);
		Assert.assertNotNull(pageResult.getRecordList());
		System.out.println(pageResult.getRecordList().size());
		for(Object obj : pageResult.getRecordList()) {
			System.out.println(obj);
		}
	}
	
	@Test
	public void findListTest() {
		UupmApplicationModel paramModel = new UupmApplicationModel();
		paramModel.setTenantCode("default");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);
		List<UupmApplicationModel> result = this.uupmApplicationService.findList(paramModel, paramMap, null);
		System.out.println(result);
	}
	
	@Test
	public void findCountTest() {
		UupmApplicationModel paramModel = new UupmApplicationModel();
		paramModel.setTenantCode("default");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);
		Long result = this.uupmApplicationService.findCount(paramModel, paramMap, null);
		System.out.println(result);
	}
	
	@Test
	public void findOneTest() {
		UupmApplicationModel paramModel = new UupmApplicationModel();
		paramModel.setTenantCode("default");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);
		UupmApplicationModel result = this.uupmApplicationService.findOne(paramModel, paramMap, null);
		System.out.println(result);
	}
	
	@Test
	public void addOneTest() {
		UupmApplicationModel paramModel = new UupmApplicationModel();
		paramModel.setTenantCode("default");
		int result = this.uupmApplicationService.addOne(paramModel, null);
		System.out.println(result);
		System.out.println(paramModel.getId());
	}
	
	@Test
	public void addListTest() {
		List<UupmApplicationModel> list = new ArrayList<UupmApplicationModel>();
		for(int i=0;i<5;i++) {
			UupmApplicationModel paramModel = new UupmApplicationModel();
			paramModel.setTenantCode("default_" + i);
			list.add(paramModel);
		}
		int result = this.uupmApplicationService.addList(list, null);
		System.out.println(result);
		for(UupmApplicationModel model : list) {
			System.out.println(model.getId());
		}
	}
	
	@Test
	public void updateTest() {
		UupmApplicationModel newModel = new UupmApplicationModel();
		newModel.setTenantCode("default-new");
		UupmApplicationModel oldModel = new UupmApplicationModel();
		oldModel.setTenantCode("default");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);
		
		int result = this.uupmApplicationService.update(newModel, oldModel, paramMap, null);
		System.out.println(result);
	}
	
	@Test
	public void deleteTest() {
		UupmApplicationModel paramModel = new UupmApplicationModel();
		paramModel.setTenantCode("default_0");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2000");
		idList.add("2001");
		paramMap.put("idList", idList);

		int result = this.uupmApplicationService.delete(paramModel, paramMap);
		System.out.println(result);
	}
	
}
