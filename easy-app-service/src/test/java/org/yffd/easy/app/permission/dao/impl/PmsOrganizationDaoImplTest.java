package org.yffd.easy.app.permission.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yffd.easy.app.SpringBaseTestCase;
import org.yffd.easy.app.permission.dao.PmsOrganizationDao;
import org.yffd.easy.app.permission.entity.PmsOrganization;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月18日 上午11:33:09 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class PmsOrganizationDaoImplTest extends SpringBaseTestCase {

	@Autowired
	private PmsOrganizationDao dao;

	@Test
	public void selectByPK() {
		String id = "1006";
		PmsOrganization entity = this.dao.selectByPK(id);
		System.out.println(entity);
	}

	@Test
	public void selectListBy() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgName", "财务部门");
		List<PmsOrganization> list = this.dao.selectListBy(paramMap);
		System.out.println(list);
		System.out.println(list.size());
	}

	@Test
	public void selectBy() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgCode", "cwbm_1");
		PmsOrganization entity = this.dao.selectBy(paramMap);
		System.out.println(entity);
	}

	@Test
	public void selectAll() {
		List<PmsOrganization> list = this.dao.selectAll();
		System.out.println(list);
		System.out.println(list.size());
	}

	@Test
	public void insertTest() {
		PmsOrganization entity = new PmsOrganization();
		entity.setDefault();
		entity.setOrgName("财务部门");
		entity.setOrgCode("cwbm");
		entity.setParentCode("0");
		entity.setOrgNum(1.0F);
        this.dao.insert(entity);
	}

	@Test
	public void insertBatch() {
		List<PmsOrganization> list = new ArrayList<PmsOrganization>();
		for(int i=0;i<5;i++) {
			PmsOrganization entity = new PmsOrganization();
			entity.setDefault();
	        entity.setOrgName("财务部门_"+i);
			entity.setOrgCode("cwbm_"+i);
			entity.setParentCode("0_"+i);
			entity.setOrgNum(1.0F+i);
	        list.add(entity);
		}
		this.dao.insertBatch(list);
	}

	@Test
	public void updateByPK() {
		PmsOrganization entity = new PmsOrganization();
		entity.setDefault();
		entity.setId("1000");
        entity.setOrgName("财务部门-");
		entity.setOrgCode("cwbm-");
		entity.setParentCode("1");
		entity.setOrgNum(2.0F);
		this.dao.updateByPK(entity);
	}

	@Test
	public void updateBatch() {
		List<PmsOrganization> list = new ArrayList<PmsOrganization>();
		for(int i=0;i<4;i++) {
			PmsOrganization entity = new PmsOrganization();
			entity.setDefault();
			entity.setId("100"+i);
	        entity.setOrgName("财务部门_"+i);
			entity.setOrgCode("cwbm_"+i);
			entity.setParentCode("0_"+i);
			entity.setOrgNum(1.0F+i);
	        list.add(entity);
		}
		this.dao.updateBatch(list);
	}

	@Test
	public void deleteByPK() {
		String id = "1000";
		this.dao.deleteByPK(id);
	}

	@Test
	public void deleteBy() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgCode", "cwbm_1");
		paramMap.put("orgName", "财务部门_1");
		paramMap.put("parentCode", "0_1");
		this.dao.deleteBy(paramMap);
	}

	@Test
	public void deleteBatch() {
		List<PmsOrganization> list = new ArrayList<PmsOrganization>();
		PmsOrganization entity = new PmsOrganization();
		entity.setId("1002");
		
		PmsOrganization entity1 = new PmsOrganization();
		entity1.setId("1003");
		
		PmsOrganization entity2 = new PmsOrganization();
		entity2.setId("1004");
		
		PmsOrganization entity3 = new PmsOrganization();
		entity3.setId("1005");
		
		list.add(entity);
		list.add(entity1);
		list.add(entity2);
		list.add(entity3);
		
		this.dao.deleteBatch(list);
	}


	
}

