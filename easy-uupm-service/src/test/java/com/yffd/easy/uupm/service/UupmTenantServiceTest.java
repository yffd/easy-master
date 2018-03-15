package com.yffd.easy.uupm.service;

import java.util.ArrayList;
import java.util.Date;
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
 * @Date		2018年03月14日 18时17分05秒 <br/>
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
	
	
	/*********************** 查询 BEGIN ***********************************/
	
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
	
	/*********************** 查询 END *************************************/
	/*********************** 添加 BEGIN ***********************************/
	
	@Test
	public void addOneTest() {
		UupmTenantModel model = new UupmTenantModel();
		model.setId("2000");
		this.uupmTenantService.addOne(model, loginInfo);
		
		/*******************************/
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", "2001");
		this.uupmTenantService.addOne(paramMap, loginInfo);
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
		
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for(int i=10;i<12;i++) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", "21"+i);
			listMap.add(paramMap);
		}
		this.uupmTenantService.addBatch(listMap, loginInfo);
	}
	
	/*********************** 插入 END *************************************/
	/*********************** 更新 BEGIN ***********************************/
	
	@Test
	public void updateByTest() {
		Map<String, Object> newMap = new HashMap<String, Object>();
		newMap.put("updateTime", new Date());
		newMap.put("updateBy", "tester");
		Map<String, Object> oldMap = new HashMap<String, Object>();
		oldMap.put("id", "2005");
		int result = this.uupmTenantService.updateBy(newMap, oldMap, null);
		System.out.println(result);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("updateTime", new Date());
		paramMap.put("updateBy", "tester");
		paramMap.put("id", "2006");
		int result2 = this.uupmTenantService.updateBy(paramMap, "id", null);
		System.out.println(result2);
		
		int result3 = this.uupmTenantService.updateBy(paramMap, null, "id", "updateBy");
		System.out.println(result3);
		
		UupmTenantModel model = new UupmTenantModel();
		model.setId(2007+"");
		model.setUpdateBy("tester");
		int result4 = this.uupmTenantService.updateBy(model, "id", null);
		System.out.println(result4);
		
		int result5 = this.uupmTenantService.updateById(model, null);
		System.out.println(result5);
	}
	
	@Test
	public void updateByIdTest() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("updateBy", "tester");
		paramMap.put("id", "2005");
		int result = this.uupmTenantService.updateById(paramMap, null);
		System.out.println(result);
		
		UupmTenantModel model = new UupmTenantModel();
		model.setId(2006+"");
		int result2 = this.uupmTenantService.updateById(model, null);
		System.out.println(result2);
	}
	
	@Test
	public void updateByIdsTest() {
		Map<String, Object> newMap = new HashMap<String, Object>();
		newMap.put("updateBy", "tester");
		
		List<String> idList = new ArrayList<String>();
		for(int i=0;i<5;i++) {
			idList.add("200"+i);
		}
		int result = this.uupmTenantService.updateByIds(newMap, idList, null);
		System.out.println(result);
	}
	
	@Test
	public void updateWithInByTest() {
		String inName = "id";
		Map<String, Object> newMap = new HashMap<String, Object>();
		newMap.put("updateBy", "tester");
		Map<String, Object> oldMap = new HashMap<String, Object>();
		oldMap.put("id", "2005");
		
		List<String> inValueList = new ArrayList<String>();
		for(int i=0;i<5;i++) {
			inValueList.add("200"+i);
		}
		
		int result = this.uupmTenantService.updateWithInBy(newMap, inName, inValueList, null);
		System.out.println(result);
		
		int result2 = this.uupmTenantService.updateWithInBy(newMap, oldMap, inName, inValueList, null);
		System.out.println(result2);
		
		Map<String, Object> oldInMap = new HashMap<String, Object>();
		oldInMap.put("id", inValueList);
		
		int result3 = this.uupmTenantService.updateWithInBy(newMap, oldMap, oldInMap, null);
		System.out.println(result3);
	}
	
	/*********************** 更新 END *************************************/
	/*********************** 物理删除 BEGIN ********************************/
	
	@Test
	public void delByTest() {
		UupmTenantModel model = new UupmTenantModel();
		model.setId(2000+"");
		int result = this.uupmTenantService.delBy(model, null);
		System.out.println(result);
		
		Map<String, Object> paramMap = this.uupmTenantService.model2map(model, null);
		int result2 = this.uupmTenantService.delBy(paramMap, null);
		System.out.println(result2);
	}
	
	@Test
	public void delByIdTest() {
		String id = "2001";
		int result = this.uupmTenantService.delById(id, null);
		System.out.println(result);
	}

	@Test
	public void delByIdsTest() {
		List<String> list = new ArrayList<String>();
		String idsStr = "";
		for(int i=2002;i<2005;i++) {
			list.add("" + i);
			idsStr += i + ",";
		}
		int result = this.uupmTenantService.delByIds(list, null);
		System.out.println(result);
		
		idsStr = idsStr.substring(0, idsStr.length()-1);
		int result2 = this.uupmTenantService.delByIds(idsStr, null);
		System.out.println(result2);
	}
	
	@Test
	public void delWithInByTest() {
		String arributeName = "id";
		String delimiter = ",";
		String values = "";
		List<String> inValueList = new ArrayList<String>();
		for(int i=0;i<5;i++) {
			inValueList.add("200"+i);
			values += i + ",";
		}
		values = values.substring(0, values.length()-1);
		
		int result = this.uupmTenantService.delWithInBy(arributeName, values, delimiter, null);
		System.out.println(result);
		
		int result2 = this.uupmTenantService.delWithInBy(arributeName, inValueList, null);
		System.out.println(result2);
		
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", "2000");
		Map<String, List<?>> inMap = new HashMap<String, List<?>>();
		inMap.put(arributeName, inValueList);
		
		int result3 = this.uupmTenantService.delWithInBy(paramMap, inMap, null);
		System.out.println(result3);
		
	}
	
	/*********************** 物理删除 END **********************************/
	
	/*********************** 逻辑删除 BEGIN ********************************/
	/*********************** 逻辑删除 END **********************************/
	
}
