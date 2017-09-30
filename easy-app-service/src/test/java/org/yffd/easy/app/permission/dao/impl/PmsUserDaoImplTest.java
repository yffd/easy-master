package org.yffd.easy.app.permission.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yffd.easy.app.SpringBaseTestCase;
import org.yffd.easy.app.permission.dao.PmsUserDao;
import org.yffd.easy.app.permission.entity.PmsUser;
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
public class PmsUserDaoImplTest extends SpringBaseTestCase {
	@Autowired
	private PmsUserDao dao;
	
	@Test
	public void selectPageTest() {
		PageParam pageParam = new PageParam(1, 3);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", "张三_3");
		paramMap.put("userCode", "zhangsan_3");
		paramMap.put("userPwd", "123456");
		paramMap.put("realName", "张三_3");
		paramMap.put("mobileNo", "11111111113");
		PageResult<PmsUser> result = this.dao.selectPage(pageParam, paramMap);
		System.out.println(result.getPageInfo());
		System.out.println(result.getRecordList());
		System.out.println(result.getRecordList().size());
	}
	
	@Test
	public void selectCountByTest() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", "张三_3");
		paramMap.put("userCode", "zhangsan_3");
		paramMap.put("userPwd", "123456");
		paramMap.put("realName", "张三_3");
		paramMap.put("mobileNo", "11111111113");
		Long count = this.dao.selectCountBy(paramMap);
		System.out.println(count);
	}
	
	@Test
	public void selectByPKTest() {
		String id = "1006";
		PmsUser entity = this.dao.selectByPK(id);
		System.out.println(entity);
	}
	
	@Test
	public void selectAllTest() {
		List<PmsUser> list = this.dao.selectAll();
		System.out.println(list);
		System.out.println(list.size());
	}
	
	@Test
	public void selectByTest() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", "张三_1");
		paramMap.put("userCode", "zhangsan_1");
		paramMap.put("userPwd", "123456");
		paramMap.put("realName", "张三_1");
		paramMap.put("mobileNo", "11111111111");
		PmsUser entity = this.dao.selectBy(paramMap);
		System.out.println(entity);
	}
	
	@Test
	public void selectListByTest() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", "张三_3");
		paramMap.put("userCode", "zhangsan_3");
		paramMap.put("userPwd", "123456");
		paramMap.put("realName", "张三_3");
		paramMap.put("mobileNo", "11111111113");
		List<PmsUser> list = this.dao.selectListBy(paramMap);
		System.out.println(list);
		System.out.println(list.size());
	}
	
	@Test
	public void deleteBatchTest() {
		List<PmsUser> list = new ArrayList<PmsUser>();
		for(int i=0;i<4;i++) {
			PmsUser entity = new PmsUser();
			entity.setId("100"+i);
			list.add(entity);
		}
		this.dao.deleteBatch(list);
	}
	
	@Test
	public void deleteByTest(){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", "张三-");
		paramMap.put("userCode", "zhangsan-");
		paramMap.put("userPwd", "123456");
		paramMap.put("realName", "张三-");
		paramMap.put("mobileNo", "22222222222");
		this.dao.deleteBy(paramMap );
	}
	
	@Test
	public void deleteByPKTest() {
		String id = "1003";
		this.dao.deleteByPK(id);
	}
	
	@Test
	public void updateBatchTest() {
		List<PmsUser> list = new ArrayList<PmsUser>();
		for(int i=0;i<4;i++) {
			PmsUser entity = new PmsUser();
			entity.setDefault();
			entity.setId("100"+i);
			entity.setUserCode("zhangsan_"+i*100);
			entity.setUserName("张三_"+i*100);
			entity.setUserPwd("123456");
			entity.setRealName("张三_"+i*100);
			entity.setMobileNo("1111111111"+i);
			list.add(entity);
		}
		
		this.dao.updateBatch(list);
	}
	
	@Test
	public void updateByPKTest() {
		PmsUser entity = new PmsUser();
		entity.setDefault();
		entity.setId("1000");
		entity.setUserCode("zhangsan-");
		entity.setUserName("张三-");
		entity.setUserPwd("123456");
		entity.setRealName("张三-");
		entity.setMobileNo("22222222222");
		this.dao.updateBy(entity);
	}
	
	@Test
	public void insertBatchTest() {
		List<PmsUser> list = new ArrayList<PmsUser>();
		for(int i=0;i<4;i++) {
			PmsUser entity = new PmsUser();
			entity.setDefault();
			entity.setUserCode("zhangsan_"+i);
			entity.setUserName("张三_"+i);
			entity.setUserPwd("123456");
			entity.setRealName("张三_"+i);
			entity.setMobileNo("1111111111"+i);
			list.add(entity);
		}
		this.dao.insertBatch(list);
	}
	
	@Test
	public void insertTest() {
		PmsUser entity = new PmsUser();
		entity.setDefault();
		entity.setUserCode("zhangsan");
		entity.setUserName("张三");
		entity.setUserPwd("123456");
		entity.setRealName("张三");
		entity.setMobileNo("11111111111");
		this.dao.insert(entity);
	}
}

