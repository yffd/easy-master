package org.yffd.easy.app.permission.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yffd.easy.app.SpringBaseTestCase;
import org.yffd.easy.app.permission.dao.PmsMenuDao;
import org.yffd.easy.app.permission.entity.PmsMenu;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月18日 上午11:33:09 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class PmsMenuDaoImplTest extends SpringBaseTestCase {

	@Autowired
	private PmsMenuDao pmsMenuDao;
	
	@Test
	public void selectByPK() {
		String id = "1006";
		PmsMenu entity = this.pmsMenuDao.selectByPK(id);
		System.out.println(entity);
	}

	@Test
	public void selectListBy() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menuName", "权限管理");
		List<PmsMenu> list = this.pmsMenuDao.selectListBy(paramMap);
		System.out.println(list);
		System.out.println(list.size());
	}

	@Test
	public void selectBy() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menuCode", "qxgl_1");
		paramMap.put("menuName", "权限管理_1");
		paramMap.put("parentCode", "0_1");
		PmsMenu entity = this.pmsMenuDao.selectBy(paramMap);
		System.out.println(entity);
	}

	@Test
	public void selectAll() {
		List<PmsMenu> list = this.pmsMenuDao.selectAll();
		System.out.println(list);
		System.out.println(list.size());
	}

	@Test
	public void insertTest() {
		PmsMenu menu = new PmsMenu();
        menu.setDefault();
        menu.setMenuName("权限管理");
        menu.setMenuCode("qxgl");
        menu.setMenuUrl("/test/test");
        menu.setParentCode("0");
        menu.setMenuNum(1.0F);
        this.pmsMenuDao.insert(menu);
	}

	@Test
	public void insertBatch() {
		List<PmsMenu> list = new ArrayList<PmsMenu>();
//		for(int i=0;i<5;i++) {
//			PmsMenu menu = new PmsMenu();
//	        menu.setDefault();
//	        menu.setMenuName("权限管理_"+i);
//	        menu.setMenuCode("qxgl_"+i);
//	        menu.setMenuUrl("/test/test_"+i);
//	        menu.setParentCode("0_"+i);
//	        menu.setMenuNum(1.0F + i);
//	        list.add(menu);
//		}
		
		PmsMenu menu1 = new PmsMenu();
        menu1.setDefault();
        menu1.setMenuName("权限管理");
        menu1.setMenuCode("qxgl");
        menu1.setMenuUrl("http://www.baidu.com/");
        menu1.setParentCode("0");
        menu1.setMenuNum(1.0F);
        menu1.setMenuIcon("icon-chart-organisation");
        list.add(menu1);
        
        PmsMenu menu11 = new PmsMenu();
        menu11.setDefault();
        menu11.setMenuName("菜单管理");
        menu11.setMenuCode("cdgl");
        menu11.setMenuUrl("http://www.baidu.com/");
        menu11.setParentCode("qxgl");
        menu11.setMenuNum(1.1F);
        menu11.setMenuIcon("icon-chart-organisation");
        list.add(menu11);
        
        PmsMenu menu12 = new PmsMenu();
        menu12.setDefault();
        menu12.setMenuName("流程管理");
        menu12.setMenuCode("lcgl");
        menu12.setMenuUrl("http://www.baidu.com/");
        menu12.setParentCode("qxgl");
        menu12.setMenuNum(1.2F);
        menu12.setMenuIcon("icon-chart-organisation");
        list.add(menu12);
        
        PmsMenu menu12_1 = new PmsMenu();
        menu12_1.setDefault();
        menu12_1.setMenuName("流程查看");
        menu12_1.setMenuCode("lcck");
        menu12_1.setMenuUrl("http://www.baidu.com/");
        menu12_1.setParentCode("lcgl");
        menu12_1.setMenuNum(1.2F);
        menu12_1.setMenuIcon("icon-chart-organisation");
        list.add(menu12_1);
        
        /**********************************************/
        
        PmsMenu menu2 = new PmsMenu();
        menu2.setDefault();
        menu2.setMenuName("用户管理");
        menu2.setMenuCode("yhgl");
        menu2.setMenuUrl("http://www.baidu.com/");
        menu2.setParentCode("0");
        menu2.setMenuNum(2.0F);
        menu2.setMenuIcon("icon-chart-organisation");
        list.add(menu2);
        
        PmsMenu menu21 = new PmsMenu();
        menu21.setDefault();
        menu21.setMenuName("用户信息");
        menu21.setMenuCode("yhxx");
        menu21.setMenuUrl("http://www.baidu.com/");
        menu21.setParentCode("yhgl");
        menu21.setMenuNum(2.1F);
        menu21.setMenuIcon("icon-chart-organisation");
        list.add(menu21);
        
		this.pmsMenuDao.insertBatch(list);
	}

	@Test
	public void updateByPK() {
		PmsMenu menu = new PmsMenu();
        menu.setDefault();
        menu.setId("1000");
        menu.setMenuName("权限管理-");
        menu.setMenuCode("qxgl-");
        menu.setMenuUrl("/test/test-");
        menu.setParentCode("1000");
        menu.setMenuNum(2.0F);
		this.pmsMenuDao.updateBy(menu);
	}

	@Test
	public void updateBatch() {
		List<PmsMenu> list = new ArrayList<PmsMenu>();
		for(int i=0;i<4;i++) {
			PmsMenu menu = new PmsMenu();
	        menu.setDefault();
	        menu.setId("100"+i);
	        menu.setMenuName("权限管理_"+i);
	        menu.setMenuCode("qxgl_"+i);
	        menu.setMenuUrl("/test/test_"+i);
	        menu.setParentCode("0_"+i);
	        menu.setMenuNum(1.0F + i);
	        list.add(menu);
		}
		this.pmsMenuDao.updateBatch(list);
	}

	@Test
	public void deleteByPK() {
		String id = "1000";
		this.pmsMenuDao.deleteByPK(id);
	}

	@Test
	public void deleteBy() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menuCode", "qxgl_1");
		paramMap.put("menuName", "权限管理_1");
		paramMap.put("parentCode", "0_1");
		this.pmsMenuDao.deleteBy(paramMap);
	}

	@Test
	public void deleteBatch() {
		List<PmsMenu> list = new ArrayList<PmsMenu>();
		PmsMenu entity = new PmsMenu();
		entity.setId("1002");
		
		PmsMenu entity1 = new PmsMenu();
		entity1.setId("1003");
		
		PmsMenu entity2 = new PmsMenu();
		entity2.setId("1004");
		
		PmsMenu entity3 = new PmsMenu();
		entity3.setId("1005");
		
		list.add(entity);
		list.add(entity1);
		list.add(entity2);
		list.add(entity3);
		
		this.pmsMenuDao.deleteBatch(list);
	}


	
}

