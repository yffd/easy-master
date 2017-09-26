package org.yffd.easy.app.permission.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yffd.easy.app.SpringBaseTestCase;
import org.yffd.easy.app.permission.dao.PmsRoleUserDao;
import org.yffd.easy.app.permission.entity.PmsRole;
import org.yffd.easy.app.permission.entity.PmsRoleUser;
import org.yffd.easy.app.permission.entity.PmsRoleUser;
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
public class PmsRoleUserDaoImplTest extends SpringBaseTestCase {
	@Autowired
	private PmsRoleUserDao dao;
	
	@Test
	public void selectPageTest() {
		PageParam pageParam = new PageParam(1, 3);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleCode", "admin_0");
//		paramMap.put("userCode", "zhangsan_0");
		PageResult<PmsRoleUser> result = this.dao.selectPage(pageParam, paramMap);
		System.out.println(result.getPageInfo());
		System.out.println(result.getRecordList());
		System.out.println(result.getRecordList().size());
	}
	
	@Test
	public void selectCountByTest() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleCode", "admin_0");
//		paramMap.put("userCode", "zhangsan_0");
		Long count = this.dao.selectCountBy(paramMap);
		System.out.println(count);
	}
	
	@Test
	public void selectByPKTest() {
		String id = "1006";
		PmsRoleUser entity = this.dao.selectByPK(id);
		System.out.println(entity);
	}
	
	@Test
	public void selectAllTest() {
		List<PmsRoleUser> list = this.dao.selectAll();
		System.out.println(list);
		System.out.println(list.size());
	}
	
	@Test
	public void selectByTest() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleCode", "admin_0");
		paramMap.put("userCode", "zhangsan_0");
		PmsRoleUser entity = this.dao.selectBy(paramMap);
		System.out.println(entity);
	}
	
	@Test
	public void selectListByTest() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleCode", "admin_0");
//		paramMap.put("userCode", "zhangsan_0");
		List<PmsRoleUser> list = this.dao.selectListBy(paramMap);
		System.out.println(list);
		System.out.println(list.size());
	}
	
	@Test
	public void deleteBatchTest() {
		List<PmsRoleUser> list = new ArrayList<PmsRoleUser>();
		for(int i=0;i<4;i++) {
			PmsRoleUser entity = new PmsRoleUser();
			entity.setId("100"+i);
			list.add(entity);
		}
		this.dao.deleteBatch(list);
	}
	
	@Test
	public void deleteByTest(){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userCode", "zhangsan-");
		paramMap.put("roleCode", "admin-");
		this.dao.deleteBy(paramMap );
	}
	
	@Test
	public void deleteByPKTest() {
		String id = "1003";
		this.dao.deleteByPK(id);
	}
	
	@Test
	public void updateBatchTest() {
		List<PmsRoleUser> list = new ArrayList<PmsRoleUser>();
		for(int i=0;i<4;i++) {
			PmsRoleUser entity = new PmsRoleUser();
			entity.setDefault();
			entity.setId("100"+i);
			entity.setUserCode("zhangsan_"+i*100);
			entity.setRoleCode("admin_"+i*100);
			list.add(entity);
		}
		
		this.dao.updateBatch(list);
	}
	
	@Test
	public void updateByPKTest() {
		PmsRoleUser entity = new PmsRoleUser();
		entity.setDefault();
		entity.setId("1000");
		entity.setUserCode("zhangsan-");
		entity.setRoleCode("admin-");
		this.dao.updateByPK(entity);
	}
	
	@Test
	public void insertBatchTest() {
		List<PmsRoleUser> list = new ArrayList<PmsRoleUser>();
		for(int i=0;i<4;i++) {
			PmsRoleUser entity = new PmsRoleUser();
			entity.setDefault();
			entity.setUserCode("zhangsan_"+i);
			entity.setRoleCode("admin_"+i);
			list.add(entity);
		}
		
		this.dao.insertBatch(list);
	}
	
	@Test
	public void insertTest() {
		PmsRoleUser entity = new PmsRoleUser();
		entity.setDefault();
		entity.setRoleCode("admin");
		entity.setUserCode("zhangsan");
		this.dao.insert(entity);
	}
}

