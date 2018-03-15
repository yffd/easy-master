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
import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.framework.domain.LoginInfo;
import com.yffd.easy.uupm.api.model.UupmUserModel;
import com.yffd.easy.uupm.base.UupmBaseServiceTest;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年03月07日 16时15分23秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class UupmUserServiceTest extends UupmBaseServiceTest {

	@Autowired
	private UupmUserService uupmUserService;
	
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
		UupmUserModel model = new UupmUserModel();
		model.setId("2010");
		PageResult<UupmUserModel> result1 = this.uupmUserService.findPage(model, pageParam);
		Assert.assertNotNull(result1);
		System.out.println(result1.getPageParam());
		System.out.println(result1.getRecordList());
		
		/*******************************/
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", "2010");
		PageResult<UupmUserModel> result2 = this.uupmUserService.findPage(paramMap, pageParam);
		Assert.assertNotNull(result2);
		System.out.println(result2.getPageParam());
		System.out.println(result2.getRecordList());
	}
	
	@Test
	public void findListTest() {
		UupmUserModel model = new UupmUserModel();
		model.setId("2010");
		List<UupmUserModel> result1 = this.uupmUserService.findList(model);
		Assert.assertNotNull(result1);
		System.out.println(result1.size());
		
		/*******************************/
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", "2010");
		List<UupmUserModel> result2 = this.uupmUserService.findList(paramMap);
		Assert.assertNotNull(result2);
		System.out.println(result2.size());
	}
	
	@Test
	public void findOneTest() {
		UupmUserModel model = new UupmUserModel();
		model.setId("2010");
		UupmUserModel result1 = this.uupmUserService.findOne(model);
		Assert.assertNotNull(result1);
		System.out.println(result1.getCreateTime());
		
		/*******************************/
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", "2010");
		UupmUserModel result2 = this.uupmUserService.findOne(paramMap);
		Assert.assertNotNull(result2);
		System.out.println(result2.getCreateTime());
	}
	
	@Test
	public void findByIdTest() {
		String id = "2010";
		UupmUserModel model = this.uupmUserService.findById(id);
		Assert.assertNotNull(model);
		System.out.println(model.getCreateTime());
	}
	
	@Test
	public void findByIdsTest() {
		List<String> ids = new ArrayList<String>();
		ids.add("2010");
		ids.add("2111");
		List<UupmUserModel> result1 = this.uupmUserService.findByIds(ids);
		Assert.assertNotNull(result1);
		System.out.println(result1.size());
		
		/*******************************/
		
		String idsStr = "2010,2111";
		List<UupmUserModel> result2 = this.uupmUserService.findByIds(idsStr);
		Assert.assertNotNull(result2);
		System.out.println(result2.size());
	}
	
	@Test
	public void findAllTest() {
		List<UupmUserModel> result1 = this.uupmUserService.findAll();
		Assert.assertNotNull(result1);
		System.out.println(result1.size());
	}
	
	/*********************** 查询 END *************************************/
	/*********************** 添加 BEGIN ***********************************/
	
	@Test
	public void addOneTest() {
		UupmUserModel model = new UupmUserModel();
		model.setId("2000");
		this.uupmUserService.addOne(model, loginInfo);
		
		/*******************************/
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", "2001");
		this.uupmUserService.addOne(paramMap, loginInfo);
	}
	
	@Test
	public void addBatchTest() {
		List<UupmUserModel> list = new ArrayList<UupmUserModel>();
		for(int i=10;i<15;i++) {
			UupmUserModel model = new UupmUserModel();
			model.setId("20"+i);
			list.add(model);
		}
		this.uupmUserService.addBatch(list, loginInfo);
		
		/*******************************/
		
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		for(int i=10;i<12;i++) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", "21"+i);
			listMap.add(paramMap);
		}
		this.uupmUserService.addBatch(listMap, loginInfo);
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
		int result = this.uupmUserService.updateBy(newMap, oldMap, null);
		System.out.println(result);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("updateTime", new Date());
		paramMap.put("updateBy", "tester");
		paramMap.put("id", "2006");
		int result2 = this.uupmUserService.updateBy(paramMap, "id", null);
		System.out.println(result2);
		
		int result3 = this.uupmUserService.updateBy(paramMap, null, "id", "updateBy");
		System.out.println(result3);
		
		UupmUserModel model = new UupmUserModel();
		model.setId(2007+"");
		model.setUpdateBy("tester");
		int result4 = this.uupmUserService.updateBy(model, "id", null);
		System.out.println(result4);
		
		int result5 = this.uupmUserService.updateById(model, null);
		System.out.println(result5);
	}
	
	@Test
	public void updateByIdTest() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("updateBy", "tester");
		paramMap.put("id", "2005");
		int result = this.uupmUserService.updateById(paramMap, null);
		System.out.println(result);
		
		UupmUserModel model = new UupmUserModel();
		model.setId(2006+"");
		int result2 = this.uupmUserService.updateById(model, null);
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
		int result = this.uupmUserService.updateByIds(newMap, idList, null);
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
		
		int result = this.uupmUserService.updateWithInBy(newMap, inName, inValueList, null);
		System.out.println(result);
		
		int result2 = this.uupmUserService.updateWithInBy(newMap, oldMap, inName, inValueList, null);
		System.out.println(result2);
		
		Map<String, Object> oldInMap = new HashMap<String, Object>();
		oldInMap.put("id", inValueList);
		
		int result3 = this.uupmUserService.updateWithInBy(newMap, oldMap, oldInMap, null);
		System.out.println(result3);
	}
	
	/*********************** 更新 END *************************************/
	/*********************** 物理删除 BEGIN ********************************/
	
	@Test
	public void delByTest() {
		UupmUserModel model = new UupmUserModel();
		model.setId(2000+"");
		int result = this.uupmUserService.delBy(model, null);
		System.out.println(result);
		
		Map<String, Object> paramMap = this.uupmUserService.model2map(model, null);
		int result2 = this.uupmUserService.delBy(paramMap, null);
		System.out.println(result2);
	}
	
	@Test
	public void delByIdTest() {
		String id = "2001";
		int result = this.uupmUserService.delById(id, null);
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
		int result = this.uupmUserService.delByIds(list, null);
		System.out.println(result);
		
		idsStr = idsStr.substring(0, idsStr.length()-1);
		int result2 = this.uupmUserService.delByIds(idsStr, null);
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
		
		int result = this.uupmUserService.delWithInBy(arributeName, values, delimiter, null);
		System.out.println(result);
		
		int result2 = this.uupmUserService.delWithInBy(arributeName, inValueList, null);
		System.out.println(result2);
		
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", "2000");
		Map<String, List<?>> inMap = new HashMap<String, List<?>>();
		inMap.put(arributeName, inValueList);
		
		int result3 = this.uupmUserService.delWithInBy(paramMap, inMap, null);
		System.out.println(result3);
		
	}
	
	/*********************** 物理删除 END **********************************/
	
	/*********************** 逻辑删除 BEGIN ********************************/
	/*********************** 逻辑删除 END **********************************/

}
