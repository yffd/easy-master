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
import com.yffd.easy.uupm.api.model.UupmTenantModel;
import com.yffd.easy.uupm.base.UupmBaseServiceTest;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年03月02日 16时46分04秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class UupmTenantServiceTest extends UupmBaseServiceTest {

	@Autowired
	private UupmTenantService uupmTenantService;
	
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
		UupmTenantModel model = new UupmTenantModel();
		model.setId("2010");
		PageResult<UupmTenantModel> result1 = this.uupmTenantService.findPage(model, pageParam);
		Assert.assertNotNull(result1);
		System.out.println(result1.getPageParam());
		System.out.println(result1.getRecordList());
		
		/*******************************/
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", "2010");
		PageResult<UupmTenantModel> result2 = this.uupmTenantService.findPage(paramMap, pageParam);
		Assert.assertNotNull(result2);
		System.out.println(result2.getPageParam());
		System.out.println(result2.getRecordList());
	}
	
	@Test
	public void findListTest() {
		UupmTenantModel model = new UupmTenantModel();
		model.setId("2010");
		List<UupmTenantModel> result1 = this.uupmTenantService.findList(model);
		Assert.assertNotNull(result1);
		System.out.println(result1.size());
		
		/*******************************/
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", "2010");
		List<UupmTenantModel> result2 = this.uupmTenantService.findList(paramMap);
		Assert.assertNotNull(result2);
		System.out.println(result2.size());
	}
	
	@Test
	public void findOneTest() {
		UupmTenantModel model = new UupmTenantModel();
		model.setId("2010");
		UupmTenantModel result1 = this.uupmTenantService.findOne(model);
		Assert.assertNotNull(result1);
		System.out.println(result1.getCreateTime());
		
		/*******************************/
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", "2010");
		UupmTenantModel result2 = this.uupmTenantService.findOne(paramMap);
		Assert.assertNotNull(result2);
		System.out.println(result2.getCreateTime());
	}
	
	@Test
	public void findByIdTest() {
		String id = "2010";
		UupmTenantModel model = this.uupmTenantService.findById(id);
		Assert.assertNotNull(model);
		System.out.println(model.getCreateTime());
	}
	
	@Test
	public void findByIdsTest() {
		List<String> ids = new ArrayList<String>();
		ids.add("2010");
		ids.add("2111");
		List<UupmTenantModel> result1 = this.uupmTenantService.findByIds(ids);
		Assert.assertNotNull(result1);
		System.out.println(result1.size());
		
		/*******************************/
		
		String idsStr = "2010,2111";
		List<UupmTenantModel> result2 = this.uupmTenantService.findByIds(idsStr);
		Assert.assertNotNull(result2);
		System.out.println(result2.size());
	}
	
	@Test
	public void findAllTest() {
		List<UupmTenantModel> result1 = this.uupmTenantService.findAll();
		Assert.assertNotNull(result1);
		System.out.println(result1.size());
	}
	
	/***********************添加********************************/
	
	@Test
	public void addOneTest() {
		UupmTenantModel model = new UupmTenantModel();
		model.setId("2000");
		this.uupmTenantService.addOne(model, loginInfo);
		
		/*******************************/
		
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("id", "2001");
//		this.uupmTenantService.addOne(paramMap, loginInfo);
	}
	
	@Test
	public void addBatchTest() {
		List<UupmTenantModel> list = new ArrayList<UupmTenantModel>();
		for(int i=10;i<15;i++) {
			UupmTenantModel model = new UupmTenantModel();
			model.setId("20"+i);
			list.add(model);
		}
		this.uupmTenantService.addBatch(list, loginInfo);
		
		/*******************************/
		
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		for(int i=10;i<12;i++) {
//			Map<String, Object> paramMap = new HashMap<String, Object>();
//			paramMap.put("id", "21"+i);
//			list.add(paramMap);
//		}
//		this.uupmTenantService.addBatch(list, loginInfo);
	}

	/***********************修改********************************/
	
	@Test
	public void updateByTest() {
		UupmTenantModel model = new UupmTenantModel();
		model.setId("2000");
		this.uupmTenantService.updateBy(model, loginInfo);
		
		/*******************************/
		
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("id", "2001");
//		this.uupmTenantService.updateBy(paramMap, loginInfo);
	}
	
	@Test
	public void updateBatchByTest() {
		List<UupmTenantModel> list = new ArrayList<UupmTenantModel>();
		int startId = 2010;
		for(int i=0;i<3;i++) {
			UupmTenantModel model = new UupmTenantModel();
			model.setId((startId+i) + "");
			list.add(model);
		}
		this.uupmTenantService.updateBatchBy(list, loginInfo);
		
		/*******************************/
		
//		int startId = 2110;
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		for(int i=0;i<4;i++) {
//			Map<String, Object> paramMap = new HashMap<String, Object>();
//			paramMap.put("id", (startId+i) + "");
//			list.add(paramMap);
//		}
//		this.uupmTenantService.updateBatchBy(list, loginInfo);
	}

	/***********************物理删除********************************/

	@Test
	public void delByTest() {
		UupmTenantModel model = new UupmTenantModel();
		model.setId("2000");
		this.uupmTenantService.delBy(model);

		/*******************************/
		
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("id", "2001");
//		this.uupmTenantService.delBy(paramMap);
	}
	
	@Test
	public void delByIdTest() {
		String id = "2110";
		this.uupmTenantService.delById(id);
	}
	
	@Test
	public void delByIdsTest() {
		List<String> list = new ArrayList<String>();
		int startId = 2011;
		for(int i=0;i<2;i++) {
			list.add((startId+i) + "");
		}
		this.uupmTenantService.delByIds(list);
		
		/*******************************/
		
//		String ids = "2011,2012";
//		this.uupmTenantService.delByIds(ids);
		
	}
	
	/***********************逻辑删除********************************/
	
	@Test
	public void removeByTest() {
		UupmTenantModel model = new UupmTenantModel();
		model.setId("2000");
		this.uupmTenantService.removeBy(model, null);
		
		/*******************************/
		
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("id", "2001");
//		this.uupmTenantService.removeBy(paramMap, null);
	}
	
	@Test
	public void removeByIdTest() {
		String id = "2110";
		this.uupmTenantService.removeById(id, null);
	}
	
	@Test
	public void removeByIdsTest() {
		String ids = "2110,2111";
		this.uupmTenantService.removeByIds(ids, null);
	}
	
}
