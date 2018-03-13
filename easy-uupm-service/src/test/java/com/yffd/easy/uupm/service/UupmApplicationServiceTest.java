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
import com.yffd.easy.uupm.api.model.UupmApplicationModel;
import com.yffd.easy.uupm.base.UupmBaseServiceTest;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年03月08日 14时03分16秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class UupmApplicationServiceTest extends UupmBaseServiceTest {

	@Autowired
	private UupmApplicationService uupmApplicationService;
	
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
		UupmApplicationModel model = new UupmApplicationModel();
		model.setId("2010");
		PageResult<UupmApplicationModel> result1 = this.uupmApplicationService.findPage(model, pageParam);
		Assert.assertNotNull(result1);
		System.out.println(result1.getPageParam());
		System.out.println(result1.getRecordList());
		
		/*******************************/
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", "2010");
		PageResult<UupmApplicationModel> result2 = this.uupmApplicationService.findPage(paramMap, pageParam);
		Assert.assertNotNull(result2);
		System.out.println(result2.getPageParam());
		System.out.println(result2.getRecordList());
	}
	
	@Test
	public void findListTest() {
		UupmApplicationModel model = new UupmApplicationModel();
		model.setId("2010");
		List<UupmApplicationModel> result1 = this.uupmApplicationService.findList(model);
		Assert.assertNotNull(result1);
		System.out.println(result1.size());
		
		/*******************************/
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", "2010");
		List<UupmApplicationModel> result2 = this.uupmApplicationService.findList(paramMap);
		Assert.assertNotNull(result2);
		System.out.println(result2.size());
	}
	
	@Test
	public void findOneTest() {
		UupmApplicationModel model = new UupmApplicationModel();
		model.setId("2010");
		UupmApplicationModel result1 = this.uupmApplicationService.findOne(model);
		Assert.assertNotNull(result1);
		System.out.println(result1.getCreateTime());
		
		/*******************************/
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", "2010");
		UupmApplicationModel result2 = this.uupmApplicationService.findOne(paramMap);
		Assert.assertNotNull(result2);
		System.out.println(result2.getCreateTime());
	}
	
	@Test
	public void findByIdTest() {
		String id = "2010";
		UupmApplicationModel model = this.uupmApplicationService.findById(id);
		Assert.assertNotNull(model);
		System.out.println(model.getCreateTime());
	}
	
	@Test
	public void findByIdsTest() {
		List<String> ids = new ArrayList<String>();
		ids.add("2010");
		ids.add("2111");
		List<UupmApplicationModel> result1 = this.uupmApplicationService.findByIds(ids);
		Assert.assertNotNull(result1);
		System.out.println(result1.size());
		
		/*******************************/
		
		String idsStr = "2010,2111";
		List<UupmApplicationModel> result2 = this.uupmApplicationService.findByIds(idsStr);
		Assert.assertNotNull(result2);
		System.out.println(result2.size());
	}
	
	@Test
	public void findAllTest() {
		List<UupmApplicationModel> result1 = this.uupmApplicationService.findAll();
		Assert.assertNotNull(result1);
		System.out.println(result1.size());
	}
	
	/***********************添加********************************/
	
	@Test
	public void addOneTest() {
		UupmApplicationModel model = new UupmApplicationModel();
		model.setId("2000");
		this.uupmApplicationService.addOne(model, loginInfo);
		
		/*******************************/
		
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("id", "2001");
//		this.uupmApplicationService.addOne(paramMap, loginInfo);
	}
	
	@Test
	public void addBatchTest() {
		List<UupmApplicationModel> list = new ArrayList<UupmApplicationModel>();
		for(int i=10;i<15;i++) {
			UupmApplicationModel model = new UupmApplicationModel();
			model.setId("20"+i);
			list.add(model);
		}
		this.uupmApplicationService.addBatch(list, loginInfo);
		
		/*******************************/
		
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		for(int i=10;i<12;i++) {
//			Map<String, Object> paramMap = new HashMap<String, Object>();
//			paramMap.put("id", "21"+i);
//			list.add(paramMap);
//		}
//		this.uupmApplicationService.addBatch(list, loginInfo);
	}

	/***********************修改********************************/
	
	@Test
	public void updateByTest() {
		UupmApplicationModel model = new UupmApplicationModel();
		model.setId("2000");
		this.uupmApplicationService.updateBy(model, loginInfo);
		
		/*******************************/
		
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("id", "2001");
//		this.uupmApplicationService.updateBy(paramMap, loginInfo);
	}
	
	@Test
	public void updateBatchByTest() {
		List<UupmApplicationModel> list = new ArrayList<UupmApplicationModel>();
		int startId = 2010;
		for(int i=0;i<3;i++) {
			UupmApplicationModel model = new UupmApplicationModel();
			model.setId((startId+i) + "");
			list.add(model);
		}
		this.uupmApplicationService.updateBatchBy(list, loginInfo);
		
		/*******************************/
		
//		int startId = 2110;
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		for(int i=0;i<4;i++) {
//			Map<String, Object> paramMap = new HashMap<String, Object>();
//			paramMap.put("id", (startId+i) + "");
//			list.add(paramMap);
//		}
//		this.uupmApplicationService.updateBatchBy(list, loginInfo);
	}

	/***********************物理删除********************************/

	@Test
	public void delByTest() {
		UupmApplicationModel model = new UupmApplicationModel();
		model.setId("2000");
		this.uupmApplicationService.delBy(model, null);

		/*******************************/
		
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("id", "2001");
//		this.uupmApplicationService.delBy(paramMap, null);
	}
	
	@Test
	public void delByIdTest() {
		String id = "2110";
		this.uupmApplicationService.delById(id, null);
	}
	
	@Test
	public void delByIdsTest() {
		List<String> list = new ArrayList<String>();
		int startId = 2011;
		for(int i=0;i<2;i++) {
			list.add((startId+i) + "");
		}
		this.uupmApplicationService.delByIds(list, null);
		
		/*******************************/
		
//		String ids = "2011,2012";
//		this.uupmApplicationService.delByIds(ids, null);
		
	}
	
	/***********************逻辑删除********************************/
	
	@Test
	public void removeByTest() {
		UupmApplicationModel model = new UupmApplicationModel();
		model.setId("2000");
		this.uupmApplicationService.removeBy(model, null);
		
		/*******************************/
		
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("id", "2001");
//		this.uupmApplicationService.removeBy(paramMap, null);
	}
	
	@Test
	public void removeByIdTest() {
		String id = "2110";
		this.uupmApplicationService.removeById(id, null);
	}
	
	@Test
	public void removeByIdsTest() {
		String ids = "2110,2111";
		this.uupmApplicationService.removeByIds(ids, null);
	}
	
}
