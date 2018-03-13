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
import com.yffd.easy.uupm.api.model.UupmRoleModel;
import com.yffd.easy.uupm.base.UupmBaseServiceTest;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年03月12日 16时06分18秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class UupmRoleServiceTest extends UupmBaseServiceTest {

	@Autowired
	private UupmRoleService uupmRoleService;
	
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
		UupmRoleModel model = new UupmRoleModel();
		model.setId("2010");
		PageResult<UupmRoleModel> result1 = this.uupmRoleService.findPage(model, pageParam);
		Assert.assertNotNull(result1);
		System.out.println(result1.getPageParam());
		System.out.println(result1.getRecordList());
		
		/*******************************/
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", "2010");
		PageResult<UupmRoleModel> result2 = this.uupmRoleService.findPage(paramMap, pageParam);
		Assert.assertNotNull(result2);
		System.out.println(result2.getPageParam());
		System.out.println(result2.getRecordList());
	}
	
	@Test
	public void findListTest() {
		UupmRoleModel model = new UupmRoleModel();
		model.setId("2010");
		List<UupmRoleModel> result1 = this.uupmRoleService.findList(model);
		Assert.assertNotNull(result1);
		System.out.println(result1.size());
		
		/*******************************/
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", "2010");
		List<UupmRoleModel> result2 = this.uupmRoleService.findList(paramMap);
		Assert.assertNotNull(result2);
		System.out.println(result2.size());
	}
	
	@Test
	public void findOneTest() {
		UupmRoleModel model = new UupmRoleModel();
		model.setId("2010");
		UupmRoleModel result1 = this.uupmRoleService.findOne(model);
		Assert.assertNotNull(result1);
		System.out.println(result1.getCreateTime());
		
		/*******************************/
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", "2010");
		UupmRoleModel result2 = this.uupmRoleService.findOne(paramMap);
		Assert.assertNotNull(result2);
		System.out.println(result2.getCreateTime());
	}
	
	@Test
	public void findByIdTest() {
		String id = "2010";
		UupmRoleModel model = this.uupmRoleService.findById(id);
		Assert.assertNotNull(model);
		System.out.println(model.getCreateTime());
	}
	
	@Test
	public void findByIdsTest() {
		List<String> ids = new ArrayList<String>();
		ids.add("2010");
		ids.add("2111");
		List<UupmRoleModel> result1 = this.uupmRoleService.findByIds(ids);
		Assert.assertNotNull(result1);
		System.out.println(result1.size());
		
		/*******************************/
		
		String idsStr = "2010,2111";
		List<UupmRoleModel> result2 = this.uupmRoleService.findByIds(idsStr);
		Assert.assertNotNull(result2);
		System.out.println(result2.size());
	}
	
	@Test
	public void findAllTest() {
		List<UupmRoleModel> result1 = this.uupmRoleService.findAll();
		Assert.assertNotNull(result1);
		System.out.println(result1.size());
	}
	
	/***********************添加********************************/
	
	@Test
	public void addOneTest() {
		UupmRoleModel model = new UupmRoleModel();
		model.setId("2000");
		this.uupmRoleService.addOne(model, loginInfo);
		
		/*******************************/
		
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("id", "2001");
//		this.uupmRoleService.addOne(paramMap, loginInfo);
	}
	
	@Test
	public void addBatchTest() {
		List<UupmRoleModel> list = new ArrayList<UupmRoleModel>();
		for(int i=10;i<15;i++) {
			UupmRoleModel model = new UupmRoleModel();
			model.setId("20"+i);
			list.add(model);
		}
		this.uupmRoleService.addBatch(list, loginInfo);
		
		/*******************************/
		
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		for(int i=10;i<12;i++) {
//			Map<String, Object> paramMap = new HashMap<String, Object>();
//			paramMap.put("id", "21"+i);
//			list.add(paramMap);
//		}
//		this.uupmRoleService.addBatch(list, loginInfo);
	}

	/***********************修改********************************/
	
	@Test
	public void updateByTest() {
		UupmRoleModel model = new UupmRoleModel();
		model.setId("2000");
		this.uupmRoleService.updateBy(model, loginInfo);
		
		/*******************************/
		
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("id", "2001");
//		this.uupmRoleService.updateBy(paramMap, loginInfo);
	}
	
	@Test
	public void updateBatchByTest() {
		List<UupmRoleModel> list = new ArrayList<UupmRoleModel>();
		int startId = 2010;
		for(int i=0;i<3;i++) {
			UupmRoleModel model = new UupmRoleModel();
			model.setId((startId+i) + "");
			list.add(model);
		}
		this.uupmRoleService.updateBatchBy(list, loginInfo);
		
		/*******************************/
		
//		int startId = 2110;
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		for(int i=0;i<4;i++) {
//			Map<String, Object> paramMap = new HashMap<String, Object>();
//			paramMap.put("id", (startId+i) + "");
//			list.add(paramMap);
//		}
//		this.uupmRoleService.updateBatchBy(list, loginInfo);
	}

	/***********************物理删除********************************/

	@Test
	public void delByTest() {
		UupmRoleModel model = new UupmRoleModel();
		model.setId("2000");
		this.uupmRoleService.delBy(model, null);

		/*******************************/
		
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("id", "2001");
//		this.uupmRoleService.delBy(paramMap, null);
	}
	
	
	@Test
	public void delByIdTest() {
		String id = "2110";
		this.uupmRoleService.delById(id, null);
	}
	
	@Test
	public void delByIdsTest() {
		List<String> list = new ArrayList<String>();
		int startId = 2011;
		for(int i=0;i<2;i++) {
			list.add((startId+i) + "");
		}
		this.uupmRoleService.delByIds(list, null);
		
		/*******************************/
		
//		String ids = "2011,2012";
//		this.uupmRoleService.delByIds(ids, null);
		
	}
	
	/***********************逻辑删除********************************/
	
	@Test
	public void removeByTest() {
		UupmRoleModel model = new UupmRoleModel();
		model.setId("2000");
		this.uupmRoleService.removeBy(model, null);
		
		/*******************************/
		
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("id", "2001");
//		this.uupmRoleService.removeBy(paramMap, null);
	}
	
	@Test
	public void removeByIdTest() {
		String id = "2110";
		this.uupmRoleService.removeById(id, null);
	}
	
	@Test
	public void removeByIdsTest() {
		String ids = "2110,2111";
		this.uupmRoleService.removeByIds(ids, null);
	}
	
}
