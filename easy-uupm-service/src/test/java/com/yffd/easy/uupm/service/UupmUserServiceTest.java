package com.yffd.easy.uupm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.uupm.api.model.UupmUserModel;
import com.yffd.easy.uupm.base.UupmBaseServiceTest;
import com.yffd.easy.uupm.mapper.UupmUserMapper;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年3月28日 上午10:07:35 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class UupmUserServiceTest extends UupmBaseServiceTest {

	@Resource
	private UupmUserMapper uupmUserMapper;
	
	@Test
	public void selectListByTest() {
		PageParam pageParam = new PageParam(1L, 10L);
		UupmUserModel paramModel = new UupmUserModel();
		paramModel.setUserName("123");
		paramModel.setUserCode("qwe");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2001");
		idList.add("2002");
		paramMap.put("idList", idList);
		List<UupmUserModel> result = this.uupmUserMapper.selectListBy(paramModel , paramMap, pageParam);
		System.out.println(result);
	}
	
	@Test
	public void selectCountByTest() {
		UupmUserModel paramModel = new UupmUserModel();
		paramModel.setUserName("123");
		paramModel.setUserCode("qwe");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2001");
		idList.add("2002");
		paramMap.put("idList", idList);
		Long result = this.uupmUserMapper.selectCountBy(paramModel , paramMap);
		System.out.println(result);
	}
	
	@Test
	public void selectOneByTest() {
		UupmUserModel paramModel = new UupmUserModel();
		paramModel.setUserName("123");
		paramModel.setUserCode("qwe");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2001");
		idList.add("2002");
		paramMap.put("idList", idList);
		UupmUserModel result = this.uupmUserMapper.selectOneBy(paramModel , paramMap);
		System.out.println(result);
	}
	
	@Test
	public void insertOneTest() {
		UupmUserModel paramModel = new UupmUserModel();
		paramModel.setUserName("123");
		paramModel.setUserCode("qwe");
		int result = this.uupmUserMapper.insertOne(paramModel);
		System.out.println(result);
		System.out.println(paramModel.getId());
	}
	
	@Test
	public void insertListTest() {
		List<UupmUserModel> list = new ArrayList<UupmUserModel>();
		for(int i=0;i<5;i++) {
			UupmUserModel paramModel = new UupmUserModel();
			paramModel.setUserName("123" + i);
			paramModel.setUserCode("qwe" + i);
			list.add(paramModel);
		}
		
		int result = this.uupmUserMapper.insertList(list);
		System.out.println(result);
		for(UupmUserModel model : list) {
			System.out.println(model.getId());
		}
	}
	
	@Test
	public void updateByTest() {
		UupmUserModel oldModel = new UupmUserModel();
		oldModel.setUserName("123123");
		oldModel.setUserCode("qweqwe");
		UupmUserModel newModel = new UupmUserModel();
		newModel.setUserName("123");
		newModel.setUserCode("qwe");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2001");
		idList.add("2002");
		paramMap.put("idList", idList);
		
		int result = this.uupmUserMapper.updateBy(newModel, oldModel, paramMap);
		System.out.println(result);
	}
	
	@Test
	public void deleteByTest() {
		UupmUserModel paramModel = new UupmUserModel();
		paramModel.setUserName("123");
		paramModel.setUserCode("qwe");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> idList = new ArrayList<String>();
		idList.add("2001");
		idList.add("2002");
		paramMap.put("idList", idList);

		int result = this.uupmUserMapper.deleteBy(paramModel, paramMap);
		System.out.println(result);
	}
}

