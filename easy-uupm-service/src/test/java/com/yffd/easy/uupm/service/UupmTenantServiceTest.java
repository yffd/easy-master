package com.yffd.easy.uupm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yffd.easy.uupm.api.model.UupmTenantModel;
import com.yffd.easy.uupm.base.UupmBaseServiceTest;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年02月08日 17时42分34秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class UupmTenantServiceTest extends UupmBaseServiceTest {

	@Autowired
	private UupmTenantService uupmTenantService;
	
	/***********************添加********************************/
	
	@Test
	public void addByModelTest() {
		UupmTenantModel model = new UupmTenantModel();
		model.setId("2000");
		this.uupmTenantService.addByModel(model, null);
	}
	
	@Test
	public void addByMapTest() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", "2001");
		this.uupmTenantService.addByMap(paramMap, null);
	}
	
	@Test
	public void addBatchByModelTest() {
		List<UupmTenantModel> list = new ArrayList<UupmTenantModel>();
		for(int i=10;i<15;i++) {
			UupmTenantModel model = new UupmTenantModel();
			model.setId("20"+i);
			list.add(model);
		}
		this.uupmTenantService.addBatchByModel(list, null);
	}
	
	@Test
	public void addBatchByMapTest() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(int i=10;i<12;i++) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", "21"+i);
			list.add(paramMap);
		}
		this.uupmTenantService.addBatchByMap(list, null);
	}
	
	/***********************修改********************************/
	
	@Test
	public void updateByModelTest() {
		UupmTenantModel model = new UupmTenantModel();
		model.setId("2000");
		this.uupmTenantService.updateByModel(model, null);
	}
	
	@Test
	public void updateByMapTest() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", "2001");
		this.uupmTenantService.updateByMap(paramMap, null);
	}
	
	@Test
	public void updateBatchByModelTest() {
		List<UupmTenantModel> list = new ArrayList<UupmTenantModel>();
		int startId = 2010;
		for(int i=0;i<3;i++) {
			UupmTenantModel model = new UupmTenantModel();
			model.setId((startId+i) + "");
			list.add(model);
		}
		this.uupmTenantService.updateBatchByModel(list, null);
	}
	
	@Test
	public void updateBatchByMapTest() {
		int startId = 2110;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(int i=0;i<4;i++) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", (startId+i) + "");
			list.add(paramMap);
		}
		this.uupmTenantService.updateBatchByMap(list, null);
	}
	
	/***********************逻辑删除********************************/
	
	@Test
	public void delByModelTest() {
		UupmTenantModel model = new UupmTenantModel();
		model.setId("2000");
		this.uupmTenantService.delByModel(model, null);
	}
	
	@Test
	public void delByMapTest() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", "2001");
		this.uupmTenantService.delByMap(paramMap, null);
	}
	
	@Test
	public void delBatchByModelTest() {
		List<UupmTenantModel> list = new ArrayList<UupmTenantModel>();
		int startId = 2011;
		for(int i=0;i<2;i++) {
			UupmTenantModel model = new UupmTenantModel();
			model.setId((startId+i) + "");
			list.add(model);
		}
		this.uupmTenantService.delBatchByModel(list, null);
	}
	
	@Test
	public void delBatchByMapTest() {
		int startId = 2013;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(int i=0;i<2;i++) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", (startId+i) + "");
			list.add(paramMap);
		}
		this.uupmTenantService.delBatchByMap(list, null);
	}
	
	@Test
	public void delByIdTest() {
		String id = "2110";
		this.uupmTenantService.delById(id, null);
	}
	
	/***********************物理删除********************************/
	
	@Test
	public void physicalDelByModelTest() {
		UupmTenantModel model = new UupmTenantModel();
		model.setId("2000");
		this.uupmTenantService.physicalDelByModel(model, null);
	}
	
	@Test
	public void physicalDelByMapTest() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", "2001");
		this.uupmTenantService.physicalDelByMap(paramMap, null);
	}
	
	@Test
	public void physicalDelBatchByModelTest() {
		List<UupmTenantModel> list = new ArrayList<UupmTenantModel>();
		int startId = 2011;
		for(int i=0;i<2;i++) {
			UupmTenantModel model = new UupmTenantModel();
			model.setId((startId+i) + "");
			list.add(model);
		}
		this.uupmTenantService.physicalDelBatchByModel(list, null);
	}
	
	@Test
	public void physicalDelBatchByMapTest() {
		int startId = 2013;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(int i=0;i<2;i++) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", (startId+i) + "");
			list.add(paramMap);
		}
		this.uupmTenantService.physicalDelBatchByMap(list, null);
	}
	
	@Test
	public void physicalDelByIdTest() {
		String id = "2110";
		this.uupmTenantService.physicalDelById(id, null);
	}
	
}
