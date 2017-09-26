package org.yffd.easy.app.permission.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yffd.easy.app.SpringBaseTestCase;
import org.yffd.easy.app.permission.dao.PmsRoleFuncDao;
import org.yffd.easy.app.permission.entity.PmsRoleFunc;
import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月15日 上午9:42:55 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class PmsRoleFuncDaoImplTest extends SpringBaseTestCase {
	@Autowired
	private PmsRoleFuncDao dao;
	
	@Test
	public void selectPageTest() {
		PageParam pageParam = new PageParam(1, 3);
		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("funcCode", "pms:menu:add");
		paramMap.put("roleCode", "admin");
		PageResult<PmsRoleFunc> result = this.dao.selectPage(pageParam, paramMap);
		System.out.println(result.getPageInfo());
		System.out.println(result.getRecordList());
		System.out.println(result.getRecordList().size());
	}
	
	@Test
	public void selectCountByTest() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("funcCode", "pms:menu:add");
		paramMap.put("roleCode", "admin");
		Long count = this.dao.selectCountBy(paramMap);
		System.out.println(count);
	}
	
	@Test
	public void selectByPKTest() {
		String id = "1006";
		PmsRoleFunc entity = this.dao.selectByPK(id);
		System.out.println(entity);
	}
	
	@Test
	public void selectAllTest() {
		List<PmsRoleFunc> list = this.dao.selectAll();
		System.out.println(list);
		System.out.println(list.size());
	}
	
	@Test
	public void selectByTest() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("funcCode", "pms:menu:add");
		paramMap.put("roleCode", "admin");
		PmsRoleFunc entity = this.dao.selectBy(paramMap);
		System.out.println(entity);
	}
	
	@Test
	public void selectListByTest() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleCode", "admin");
		List<PmsRoleFunc> list = this.dao.selectListBy(paramMap);
		System.out.println(list);
		System.out.println(list.size());
	}
	
	@Test
	public void deleteBatchTest() {
		List<PmsRoleFunc> list = new ArrayList<PmsRoleFunc>();
		PmsRoleFunc entity = new PmsRoleFunc();
		entity.setId("1000");
		
		PmsRoleFunc entity1 = new PmsRoleFunc();
		entity1.setId("1001");
		
		PmsRoleFunc entity2 = new PmsRoleFunc();
		entity2.setId("1002");
		
		PmsRoleFunc entity3 = new PmsRoleFunc();
		entity3.setId("1003");
		
		PmsRoleFunc entity4 = new PmsRoleFunc();
		entity3.setId("1004");
		
		list.add(entity);
		list.add(entity1);
		list.add(entity2);
		list.add(entity3);
		list.add(entity4);
		this.dao.deleteBatch(list);
	}
	
	@Test
	public void deleteByTest(){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("funcCode", "pms:menu:add-");
		paramMap.put("roleCode", "admin");
		this.dao.deleteBy(paramMap );
	}
	
	@Test
	public void deleteByPKTest() {
		String id = "1003";
		this.dao.deleteByPK(id);
	}
	
	@Test
	public void updateBatchTest() {
		List<PmsRoleFunc> list = new ArrayList<PmsRoleFunc>();
		PmsRoleFunc entity = new PmsRoleFunc();
		entity.setDefault();
		entity.setId("1001");
		entity.setFuncCode("pms:menu:add-");
		entity.setRoleCode("admin-");
		
		PmsRoleFunc entity1 = new PmsRoleFunc();
		entity1.setDefault();
		entity1.setId("1002");
		entity1.setFuncCode("pms:menu:edit-");
		entity1.setRoleCode("admin-");
		
		PmsRoleFunc entity2 = new PmsRoleFunc();
		entity2.setDefault();
		entity2.setId("1003");
		entity2.setFuncCode("pms:menu:delete-");
		entity2.setRoleCode("admin-");
		
		PmsRoleFunc entity3 = new PmsRoleFunc();
		entity3.setDefault();
		entity3.setId("1004");
		entity3.setFuncCode("pms:menu:all-");
		entity3.setRoleCode("admin-");
		
		list.add(entity);
		list.add(entity1);
		list.add(entity2);
		list.add(entity3);
		this.dao.updateBatch(list);
	}
	
	@Test
	public void updateByPKTest() {
		PmsRoleFunc entity = new PmsRoleFunc();
		entity.setDefault();
		entity.setId("1000");
		entity.setFuncCode("pms:menu:view-");
		entity.setRoleCode("admin-");
		this.dao.updateByPK(entity);
	}
	
	@Test
	public void insertBatchTest() {
		List<PmsRoleFunc> list = new ArrayList<PmsRoleFunc>();
		PmsRoleFunc entity = new PmsRoleFunc();
		entity.setDefault();
		entity.setFuncCode("pms:menu:add");
		entity.setRoleCode("admin");
		
		PmsRoleFunc entity1 = new PmsRoleFunc();
		entity1.setDefault();
		entity1.setFuncCode("pms:menu:edit");
		entity1.setRoleCode("admin");
		
		PmsRoleFunc entity2 = new PmsRoleFunc();
		entity2.setDefault();
		entity2.setFuncCode("pms:menu:delete");
		entity2.setRoleCode("admin");
		
		PmsRoleFunc entity3 = new PmsRoleFunc();
		entity3.setDefault();
		entity3.setFuncCode("pms:menu:all");
		entity3.setRoleCode("admin");
		
		list.add(entity);
		list.add(entity1);
		list.add(entity2);
		list.add(entity3);
		this.dao.insertBatch(list);
	}
	
	@Test
	public void insertTest() {
		PmsRoleFunc entity = new PmsRoleFunc();
		entity.setDefault();
		entity.setRoleCode("admin");
		entity.setFuncCode("pms:menu:view");
		this.dao.insert(entity);
	}
}

