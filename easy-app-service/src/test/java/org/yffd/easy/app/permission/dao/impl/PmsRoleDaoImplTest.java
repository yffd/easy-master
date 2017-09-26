package org.yffd.easy.app.permission.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yffd.easy.app.SpringBaseTestCase;
import org.yffd.easy.app.permission.dao.PmsRoleDao;
import org.yffd.easy.app.permission.entity.PmsRole;
import org.yffd.easy.app.permission.entity.PmsRole;
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
public class PmsRoleDaoImplTest extends SpringBaseTestCase {
	@Autowired
	private PmsRoleDao dao;
	
	@Test
	public void selectPageTest() {
		PageParam pageParam = new PageParam(1, 3);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleName", "管理员");
//		paramMap.put("roleCode", "admin_0");
		PageResult<PmsRole> result = this.dao.selectPage(pageParam, paramMap);
		System.out.println(result.getPageInfo());
		System.out.println(result.getRecordList());
		System.out.println(result.getRecordList().size());
	}
	
	@Test
	public void selectCountByTest() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleName", "管理员");
//		paramMap.put("roleCode", "admin_0");
		Long count = this.dao.selectCountBy(paramMap);
		System.out.println(count);
	}
	
	@Test
	public void selectByPKTest() {
		String id = "1016";
		PmsRole entity = this.dao.selectByPK(id);
		System.out.println(entity);
	}
	
	@Test
	public void selectAllTest() {
		List<PmsRole> list = this.dao.selectAll();
		System.out.println(list);
		System.out.println(list.size());
	}
	
	@Test
	public void selectByTest() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleName", "管理员_0");
		paramMap.put("roleCode", "admin_0");
		PmsRole entity = this.dao.selectBy(paramMap);
		System.out.println(entity);
	}
	
	@Test
	public void selectListByTest() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleName", "管理员");
		paramMap.put("roleCode", "admin");
		List<PmsRole> list = this.dao.selectListBy(paramMap);
		System.out.println(list);
		System.out.println(list.size());
	}
	
	@Test
	public void deleteBatchTest() {
		List<PmsRole> list = new ArrayList<PmsRole>();
		PmsRole entity = new PmsRole();
		entity.setId("1000");
		
		PmsRole entity1 = new PmsRole();
		entity1.setId("1001");
		
		PmsRole entity2 = new PmsRole();
		entity2.setId("1002");
		
		PmsRole entity3 = new PmsRole();
		entity3.setId("1003");
		
		PmsRole entity4 = new PmsRole();
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
		paramMap.put("roleName", "管理员");
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
		List<PmsRole> list = new ArrayList<PmsRole>();
		PmsRole entity = new PmsRole();
		entity.setDefault();
		entity.setId("1001");
		entity.setRoleName("管理员-");
		entity.setRoleCode("admin-");
		
		PmsRole entity1 = new PmsRole();
		entity1.setDefault();
		entity1.setId("1002");
		entity1.setRoleName("管理员-");
		entity1.setRoleCode("admin-");
		
		PmsRole entity2 = new PmsRole();
		entity2.setDefault();
		entity2.setId("1003");
		entity2.setRoleName("管理员-");
		entity2.setRoleCode("admin-");
		
		PmsRole entity3 = new PmsRole();
		entity3.setDefault();
		entity3.setId("1004");
		entity3.setRoleName("管理员-");
		entity3.setRoleCode("admin-");
		
		list.add(entity);
		list.add(entity1);
		list.add(entity2);
		list.add(entity3);
		this.dao.updateBatch(list);
	}
	
	@Test
	public void updateByPKTest() {
		PmsRole entity = new PmsRole();
		entity.setDefault();
		entity.setId("1000");
		entity.setRoleName("管理员");
		entity.setRoleCode("admin");
		this.dao.updateByPK(entity);
	}
	
	@Test
	public void insertBatchTest() {
		List<PmsRole> list = new ArrayList<PmsRole>();
		for(int i=0;i<4;i++) {
			PmsRole entity = new PmsRole();
			entity.setDefault();
			entity.setRoleName("管理员_"+i);
			entity.setRoleCode("admin_"+i);
			list.add(entity);
		}
		
		this.dao.insertBatch(list);
	}
	
	@Test
	public void insertTest() {
		PmsRole entity = new PmsRole();
		entity.setDefault();
		entity.setRoleCode("admin");
		entity.setRoleName("管理员");
		this.dao.insert(entity);
	}
}

