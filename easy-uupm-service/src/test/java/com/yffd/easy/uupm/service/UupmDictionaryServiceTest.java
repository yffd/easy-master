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
import com.yffd.easy.uupm.api.model.UupmDictionaryModel;
import com.yffd.easy.uupm.base.UupmBaseServiceTest;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年03月02日 17时50分41秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class UupmDictionaryServiceTest extends UupmBaseServiceTest {

	@Autowired
	private UupmDictionaryService uupmDictionaryService;
	
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
		UupmDictionaryModel model = new UupmDictionaryModel();
		model.setId("2010");
		PageResult<UupmDictionaryModel> result1 = this.uupmDictionaryService.findPage(model, pageParam);
		Assert.assertNotNull(result1);
		System.out.println(result1.getPageParam());
		System.out.println(result1.getRecordList());
		
		/*******************************/
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", "2010");
		PageResult<UupmDictionaryModel> result2 = this.uupmDictionaryService.findPage(paramMap, pageParam);
		Assert.assertNotNull(result2);
		System.out.println(result2.getPageParam());
		System.out.println(result2.getRecordList());
	}
	
	@Test
	public void findListTest() {
		UupmDictionaryModel model = new UupmDictionaryModel();
		model.setId("2010");
		List<UupmDictionaryModel> result1 = this.uupmDictionaryService.findList(model);
		Assert.assertNotNull(result1);
		System.out.println(result1.size());
		
		/*******************************/
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", "2010");
		List<UupmDictionaryModel> result2 = this.uupmDictionaryService.findList(paramMap);
		Assert.assertNotNull(result2);
		System.out.println(result2.size());
	}
	
	@Test
	public void findOneTest() {
		UupmDictionaryModel model = new UupmDictionaryModel();
		model.setId("2010");
		UupmDictionaryModel result1 = this.uupmDictionaryService.findOne(model);
		Assert.assertNotNull(result1);
		System.out.println(result1.getCreateTime());
		
		/*******************************/
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", "2010");
		UupmDictionaryModel result2 = this.uupmDictionaryService.findOne(paramMap);
		Assert.assertNotNull(result2);
		System.out.println(result2.getCreateTime());
	}
	
	@Test
	public void findByIdTest() {
		String id = "2010";
		UupmDictionaryModel model = this.uupmDictionaryService.findById(id);
		Assert.assertNotNull(model);
		System.out.println(model.getCreateTime());
	}
	
	@Test
	public void findByIdsTest() {
		List<String> ids = new ArrayList<String>();
		ids.add("2010");
		ids.add("2111");
		List<UupmDictionaryModel> result1 = this.uupmDictionaryService.findByIds(ids);
		Assert.assertNotNull(result1);
		System.out.println(result1.size());
		
		/*******************************/
		
		String idsStr = "2010,2111";
		List<UupmDictionaryModel> result2 = this.uupmDictionaryService.findByIds(idsStr);
		Assert.assertNotNull(result2);
		System.out.println(result2.size());
	}
	
	@Test
	public void findAllTest() {
		List<UupmDictionaryModel> result1 = this.uupmDictionaryService.findAll();
		Assert.assertNotNull(result1);
		System.out.println(result1.size());
	}
	
	/***********************添加********************************/
	
	@Test
	public void addOneTest() {
		UupmDictionaryModel model = new UupmDictionaryModel();
		model.setId("2000");
		this.uupmDictionaryService.addOne(model, loginInfo);
		
		/*******************************/
		
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("id", "2001");
//		this.uupmDictionaryService.addOne(paramMap, loginInfo);
	}
	
	@Test
	public void addBatchTest() {
		List<UupmDictionaryModel> list = new ArrayList<UupmDictionaryModel>();
		for(int i=10;i<15;i++) {
			UupmDictionaryModel model = new UupmDictionaryModel();
			model.setId("20"+i);
			list.add(model);
		}
		this.uupmDictionaryService.addBatch(list, loginInfo);
		
		/*******************************/
		
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		for(int i=10;i<12;i++) {
//			Map<String, Object> paramMap = new HashMap<String, Object>();
//			paramMap.put("id", "21"+i);
//			list.add(paramMap);
//		}
//		this.uupmDictionaryService.addBatch(list, loginInfo);
	}

	/***********************修改********************************/
	
	@Test
	public void updateByTest() {
		UupmDictionaryModel model = new UupmDictionaryModel();
		model.setId("2000");
		this.uupmDictionaryService.updateBy(model, loginInfo);
		
		/*******************************/
		
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("id", "2001");
//		this.uupmDictionaryService.updateBy(paramMap, loginInfo);
	}
	
	@Test
	public void updateBatchByTest() {
		List<UupmDictionaryModel> list = new ArrayList<UupmDictionaryModel>();
		int startId = 2010;
		for(int i=0;i<3;i++) {
			UupmDictionaryModel model = new UupmDictionaryModel();
			model.setId((startId+i) + "");
			list.add(model);
		}
		this.uupmDictionaryService.updateBatchBy(list, loginInfo);
		
		/*******************************/
		
//		int startId = 2110;
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		for(int i=0;i<4;i++) {
//			Map<String, Object> paramMap = new HashMap<String, Object>();
//			paramMap.put("id", (startId+i) + "");
//			list.add(paramMap);
//		}
//		this.uupmDictionaryService.updateBatchBy(list, loginInfo);
	}

	/***********************物理删除********************************/

	@Test
	public void delByTest() {
		UupmDictionaryModel model = new UupmDictionaryModel();
		model.setId("2000");
		this.uupmDictionaryService.delBy(model);

		/*******************************/
		
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("id", "2001");
//		this.uupmDictionaryService.delBy(paramMap);
	}
	
	@Test
	public void delByIdTest() {
		String id = "2110";
		this.uupmDictionaryService.delById(id);
	}
	
	@Test
	public void delByIdsTest() {
		List<String> list = new ArrayList<String>();
		int startId = 2011;
		for(int i=0;i<2;i++) {
			list.add((startId+i) + "");
		}
		this.uupmDictionaryService.delByIds(list);
		
		/*******************************/
		
//		String ids = "2011,2012";
//		this.uupmDictionaryService.delByIds(ids);
		
	}
	
	/***********************逻辑删除********************************/
	
	@Test
	public void removeByTest() {
		UupmDictionaryModel model = new UupmDictionaryModel();
		model.setId("2000");
		this.uupmDictionaryService.removeBy(model, null);
		
		/*******************************/
		
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("id", "2001");
//		this.uupmDictionaryService.removeBy(paramMap, null);
	}
	
	@Test
	public void removeByIdTest() {
		String id = "2110";
		this.uupmDictionaryService.removeById(id, null);
	}
	
	@Test
	public void removeByIdsTest() {
		String ids = "2110,2111";
		this.uupmDictionaryService.removeByIds(ids, null);
	}
	
}
