package org.yffd.easy.app.permission.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yffd.easy.app.SpringBaseTestCase;
import org.yffd.easy.app.permission.dao.PmsFunctionDao;
import org.yffd.easy.app.permission.entity.PmsFunction;
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
public class PmsFunctionDaoImplTest extends SpringBaseTestCase {
	@Autowired
	private PmsFunctionDao pmsFunctionDao;
	
//	@Test
//	public void listAllByMenuIdTest() {
//		
//	}
//	
//	@Test
//	public void getByPermissionNameNotEqIdTest() {
//		String id = "2000";
//		String permissionName = "权限管理-菜单-查看-";
//		PmsFunctionDao entity = this.pmsFunctionDao.pmsFunctionDao(id, permissionName);
//		System.out.println(entity);
//	}
//	
//	@Test
//	public void getByPermissionTest() {
//		String permission = "pms:menu:view-";
//		PmsFunctionDao entity = this.pmsFunctionDao.getByPermission(permission);
//		System.out.println(entity);
//	}
//	
//	@Test
//	public void getByPermissionNameTest() {
//		String permissionName = "权限管理-菜单-查看-";
//		PmsFunctionDao entity = this.pmsFunctionDao.getByPermissionName(permissionName);
//		System.out.println(entity);
//	}
	
//	@Test
//	public void findByIdsTest() {
//		String idStr = "2000,2001,2002";
//		List<PmsFunctionDao> list = this.pmsFunctionDao.findByIds(idStr);
//		System.out.println(list);
//		System.out.println(list.size());
//	}
	
	/***********************************************************************/
	/***********************************************************************/
	
	
	@Test
	public void selectPageTest() {
		PageParam pageParam = new PageParam(1, 3);
		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("funcCode", "pms:menu:add");
		paramMap.put("funcName", "权限管理-菜单");
//		paramMap.put("menuCode", "qxgl");
		PageResult<PmsFunction> result = this.pmsFunctionDao.selectPage(pageParam, paramMap);
		System.out.println(result.getPageInfo());
		System.out.println(result.getRecordList());
	}
	
	@Test
	public void selectCountByTest() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("funcCode", "pms:menu:add");
		paramMap.put("funcName", "权限管理-菜单");
//		paramMap.put("menuCode", "qxgl");
		Long count = this.pmsFunctionDao.selectCountBy(paramMap);
		System.out.println(count);
	}
	
	@Test
	public void selectByPKTest() {
		String id = "2000";
		PmsFunction entity = this.pmsFunctionDao.selectByPK(id);
		System.out.println(entity);
	}
	
	@Test
	public void selectAllTest() {
		List<PmsFunction> list = this.pmsFunctionDao.selectAll();
		System.out.println(list);
		System.out.println(list.size());
	}
	
	@Test
	public void selectByTest() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("funcCode", "pms:menu:add");
		paramMap.put("funcName", "权限管理-菜单-添加");
		paramMap.put("menuCode", "qxgl");
		PmsFunction entity = this.pmsFunctionDao.selectBy(paramMap);
		System.out.println(entity);
	}
	
	@Test
	public void selectListByTest() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("funcName", "权限管理-菜单");
		List<PmsFunction> list = this.pmsFunctionDao.selectListBy(paramMap);
		System.out.println(list);
		System.out.println(list.size());
	}
	
	@Test
	public void deleteBatchTest() {
		List<PmsFunction> list = new ArrayList<PmsFunction>();
		PmsFunction entity = new PmsFunction();
		entity.setId("2004");
		
		PmsFunction entity1 = new PmsFunction();
		entity1.setId("2001");
		
		PmsFunction entity2 = new PmsFunction();
		entity2.setId("2002");
		
		PmsFunction entity3 = new PmsFunction();
		entity3.setId("2003");
		
		list.add(entity);
		list.add(entity1);
		list.add(entity2);
		list.add(entity3);
		this.pmsFunctionDao.deleteBatch(list);
	}
	
	@Test
	public void deleteByTest(){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("funcCode", "pms:menu:add-");
		paramMap.put("funcName", "权限管理-菜单-添加-");
		this.pmsFunctionDao.deleteBy(paramMap );
	}
	
	@Test
	public void deleteByPKTest() {
		String id = "2003";
		this.pmsFunctionDao.deleteByPK(id);
	}
	
	@Test
	public void updateBatchTest() {
		List<PmsFunction> list = new ArrayList<PmsFunction>();
		PmsFunction entity = new PmsFunction();
		entity.setDefault();
		entity.setId("2001");
		entity.setFuncCode("pms:menu:add-");
		entity.setFuncName("权限管理-菜单-添加-");
		entity.setMenuCode("qxgl-");
		
		PmsFunction entity1 = new PmsFunction();
		entity1.setDefault();
		entity1.setId("2002");
		entity1.setFuncCode("pms:menu:edit-");
		entity1.setFuncName("权限管理-菜单-修改-");
		entity1.setMenuCode("qxgl-");
		
		PmsFunction entity2 = new PmsFunction();
		entity2.setDefault();
		entity2.setId("2003");
		entity2.setFuncName("权限管理-菜单-删除-");
		entity2.setFuncCode("pms:menu:delete-");
		entity2.setMenuCode("qxgl-");
		
		PmsFunction entity3 = new PmsFunction();
		entity3.setDefault();
		entity3.setId("2004");
		entity3.setFuncName("权限管理-菜单-所有-");
		entity3.setFuncCode("pms:menu:all-");
		entity3.setMenuCode("qxgl-");
		
		list.add(entity);
		list.add(entity1);
		list.add(entity2);
		list.add(entity3);
		this.pmsFunctionDao.updateBatch(list);
	}
	
	@Test
	public void updateByPKTest() {
		PmsFunction entity = new PmsFunction();
		entity.setDefault();
		entity.setId("2000");
		entity.setFuncName("权限管理-菜单-查看-");
		entity.setFuncCode("pms:menu:view-");
		entity.setMenuCode("qxgl");
		this.pmsFunctionDao.updateBy(entity);
	}
	
	@Test
	public void insertBatchTest() {
		List<PmsFunction> list = new ArrayList<PmsFunction>();
		PmsFunction entity = new PmsFunction();
		entity.setDefault();
		entity.setFuncCode("pms:menu:add");
		entity.setFuncName("权限管理-菜单-添加");
		entity.setMenuCode("qxgl");
		
		PmsFunction entity1 = new PmsFunction();
		entity1.setDefault();
		entity1.setFuncCode("pms:menu:edit");
		entity1.setFuncName("权限管理-菜单-修改");
		entity1.setMenuCode("qxgl");
		
		PmsFunction entity2 = new PmsFunction();
		entity2.setDefault();
		entity2.setFuncName("权限管理-菜单-删除");
		entity2.setFuncCode("pms:menu:delete");
		entity2.setMenuCode("qxgl");
		
		PmsFunction entity3 = new PmsFunction();
		entity3.setDefault();
		entity3.setFuncName("权限管理-菜单-所有");
		entity3.setFuncCode("pms:menu:all");
		entity3.setMenuCode("qxgl");
		
		list.add(entity);
		list.add(entity1);
		list.add(entity2);
		list.add(entity3);
		this.pmsFunctionDao.insertBatch(list);
	}
	
	@Test
	public void insertTest() {
		PmsFunction entity = new PmsFunction();
		entity.setDefault();
		entity.setFuncName("权限管理-菜单-查看");
		entity.setFuncCode("pms:menu:view");
		entity.setMenuCode("qxgl");
		this.pmsFunctionDao.insert(entity);
	}
}

