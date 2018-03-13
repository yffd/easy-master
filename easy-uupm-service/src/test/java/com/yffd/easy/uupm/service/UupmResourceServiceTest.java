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
import com.yffd.easy.framework.domain.LoginInfo;
import com.yffd.easy.uupm.api.model.UupmResourceModel;
import com.yffd.easy.uupm.base.UupmBaseServiceTest;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年03月08日 15时17分17秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class UupmResourceServiceTest extends UupmBaseServiceTest {

	@Autowired
	private UupmResourceService uupmResourceService;
	
	private LoginInfo loginInfo = null;
	
	{
		loginInfo = new LoginInfo();
		loginInfo.setUserCode("tester");
		loginInfo.setUserName("测试");
	}
	
	/***********************查询********************************/
	
	@Test
	public void findPageTest() {
		PageParam pageParam = new PageParam(1L, 10L);
		UupmResourceModel model = new UupmResourceModel();
		model.setId("2010");
		PageResult<UupmResourceModel> result1 = this.uupmResourceService.findPage(model, pageParam);
		Assert.assertNotNull(result1);
		System.out.println(result1.getPageParam());
		System.out.println(result1.getRecordList());
		
		/*******************************/
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", "2010");
		PageResult<UupmResourceModel> result2 = this.uupmResourceService.findPage(paramMap, pageParam);
		Assert.assertNotNull(result2);
		System.out.println(result2.getPageParam());
		System.out.println(result2.getRecordList());
	}
	
	@Test
	public void findListTest() {
		UupmResourceModel model = new UupmResourceModel();
		model.setId("2010");
		List<UupmResourceModel> result1 = this.uupmResourceService.findList(model);
		Assert.assertNotNull(result1);
		System.out.println(result1.size());
		
		/*******************************/
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", "2010");
		List<UupmResourceModel> result2 = this.uupmResourceService.findList(paramMap);
		Assert.assertNotNull(result2);
		System.out.println(result2.size());
	}
	
	@Test
	public void findOneTest() {
		UupmResourceModel model = new UupmResourceModel();
		model.setId("2010");
		UupmResourceModel result1 = this.uupmResourceService.findOne(model);
		Assert.assertNotNull(result1);
		System.out.println(result1.getCreateTime());
		
		/*******************************/
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", "2010");
		UupmResourceModel result2 = this.uupmResourceService.findOne(paramMap);
		Assert.assertNotNull(result2);
		System.out.println(result2.getCreateTime());
	}
	
	@Test
	public void findByIdTest() {
		String id = "2010";
		UupmResourceModel model = this.uupmResourceService.findById(id);
		Assert.assertNotNull(model);
		System.out.println(model.getCreateTime());
	}
	
	@Test
	public void findByIdsTest() {
		List<String> ids = new ArrayList<String>();
		ids.add("2010");
		ids.add("2111");
		List<UupmResourceModel> result1 = this.uupmResourceService.findByIds(ids);
		Assert.assertNotNull(result1);
		System.out.println(result1.size());
		
		/*******************************/
		
		String idsStr = "2010,2111";
		List<UupmResourceModel> result2 = this.uupmResourceService.findByIds(idsStr);
		Assert.assertNotNull(result2);
		System.out.println(result2.size());
	}
	
	@Test
	public void findAllTest() {
		List<UupmResourceModel> result1 = this.uupmResourceService.findAll();
		Assert.assertNotNull(result1);
		System.out.println(result1.size());
	}
	
	/***********************添加********************************/
	
	@Test
	public void addOneTest() {
		UupmResourceModel model = new UupmResourceModel();
		model.setId("2000");
		this.uupmResourceService.addOne(model, loginInfo);
		
		/*******************************/
		
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("id", "2001");
//		this.uupmResourceService.addOne(paramMap, loginInfo);
	}
	
	@Test
	public void addBatchTest() {
		List<UupmResourceModel> list = new ArrayList<UupmResourceModel>();
		for(int i=10;i<15;i++) {
			UupmResourceModel model = new UupmResourceModel();
			model.setId("20"+i);
			list.add(model);
		}
		this.uupmResourceService.addBatch(list, loginInfo);
		
		/*******************************/
		
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		for(int i=10;i<12;i++) {
//			Map<String, Object> paramMap = new HashMap<String, Object>();
//			paramMap.put("id", "21"+i);
//			list.add(paramMap);
//		}
//		this.uupmResourceService.addBatch(list, loginInfo);
	}

	/***********************修改********************************/
	
	@Test
	public void updateByTest() {
		UupmResourceModel model = new UupmResourceModel();
		model.setId("2000");
		this.uupmResourceService.updateBy(model, loginInfo);
		
		/*******************************/
		
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("id", "2001");
//		this.uupmResourceService.updateBy(paramMap, loginInfo);
	}
	
	@Test
	public void updateBatchByTest() {
		List<UupmResourceModel> list = new ArrayList<UupmResourceModel>();
		int startId = 2010;
		for(int i=0;i<3;i++) {
			UupmResourceModel model = new UupmResourceModel();
			model.setId((startId+i) + "");
			list.add(model);
		}
		this.uupmResourceService.updateBatchBy(list, loginInfo);
		
		/*******************************/
		
//		int startId = 2110;
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		for(int i=0;i<4;i++) {
//			Map<String, Object> paramMap = new HashMap<String, Object>();
//			paramMap.put("id", (startId+i) + "");
//			list.add(paramMap);
//		}
//		this.uupmResourceService.updateBatchBy(list, loginInfo);
	}

	/***********************物理删除********************************/

	@Test
	public void delByTest() {
		UupmResourceModel model = new UupmResourceModel();
		model.setId("2000");
		this.uupmResourceService.delBy(model, null);

		/*******************************/
		
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("id", "2001");
//		this.uupmResourceService.delBy(paramMap, null);
	}
	
	@Test
	public void deleteWithInByTest() {
		String attributeName = "id";
		String values = "";
		for(int i=2014;i<2016;i++) {
			values += i + ",";
		}
		this.uupmResourceService.deleteWithInBy(attributeName, values, null, null);
	}
	
	@Test
	public void delByIdTest() {
		String id = "2110";
		this.uupmResourceService.delById(id, null);
	}
	
	@Test
	public void delByIdsTest() {
		List<String> list = new ArrayList<String>();
		int startId = 2011;
		for(int i=0;i<2;i++) {
			list.add((startId+i) + "");
		}
		this.uupmResourceService.delByIds(list, null);
		
		/*******************************/
		
//		String ids = "2011,2012";
//		this.uupmResourceService.delByIds(ids, null);
		
	}
	
	/***********************逻辑删除********************************/
	
	@Test
	public void removeByTest() {
		UupmResourceModel model = new UupmResourceModel();
		model.setId("2000");
		this.uupmResourceService.removeBy(model, null);
		
		/*******************************/
		
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("id", "2001");
//		this.uupmResourceService.removeBy(paramMap, null);
	}
	
	@Test
	public void removeByIdTest() {
		String id = "2110";
		this.uupmResourceService.removeById(id, null);
	}
	
	@Test
	public void removeByIdsTest() {
		String ids = "2110,2111";
		this.uupmResourceService.removeByIds(ids, null);
	}
	
}
