package org.yffd.easy.app.permission.dao.impl;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.yffd.easy.common.core.entity.PersistEntity;
import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;
import org.yffd.easy.common.ssm.dao.IBaseDao;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月15日 下午6:16:10 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class BaseDaoImplTest {
	
	public void selectPageTest() {
		PageParam pageParam = this.getPageParam();
		Map<String, Object> paramMap = this.getParamMap();
		PageResult<? extends PersistEntity> result = this.getDaoImpl().selectPage(pageParam, paramMap);
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getPageInfo());
		Assert.assertNotNull(result.getRecordList());
		boolean tmp = result.getRecordList().size() > 0;
		Assert.assertEquals(true, tmp);
		System.out.println("::selectPage::" + result.getPageInfo());
		System.out.println("::selectPage::" + result.getRecordList());
	}
	
	public void selectCountByTest() {
		Map<String, Object> paramMap = this.getParamMap();
		Long count = this.getDaoImpl().selectCountBy(paramMap);
		Assert.assertEquals(true, count > 0);
		System.out.println("::selectCountBy::" + count);
	}
	
	public void selectByPKTest() {
		String id = this.getId();
		PersistEntity entity = (PersistEntity) this.getDaoImpl().selectByPK(id);
		Assert.assertNotNull(entity);
		System.out.println("::selectByPK::" + entity);
	}
	
	public void selectAllTest() {
		List<? extends PersistEntity> list = this.getDaoImpl().selectAll();
		Assert.assertNotNull(list);
		boolean tmp = list.size() > 0;
		Assert.assertEquals(true, tmp);
		System.out.println("::selectAll::" + list);
		System.out.println("::selectAll::" + list.size());
	}
	
	@Test
	public void selectByTest() {
		Map<String, Object> paramMap = this.getParamMap();
		PersistEntity entity = (PersistEntity) this.getDaoImpl().selectBy(paramMap);
		Assert.assertNotNull(entity);
		System.out.println("::selectBy::" + entity);
	}
	
	@Test
	public void selectListByTest() {
		Map<String, Object> paramMap = this.getParamMap();
		List<? extends PersistEntity> list = this.getDaoImpl().selectListBy(paramMap);
		Assert.assertNotNull(list);
		boolean tmp = list.size() > 0;
		Assert.assertEquals(true, tmp);
		System.out.println("::selectListBy::" + list);
		System.out.println("::selectListBy::" + list.size());
	}
	
	@Test
	public void deleteBatchTest() {
		List<? extends PersistEntity> list = this.getEntityList();
		int result = this.getDaoImpl().deleteBatch(list);
		Assert.assertEquals(true, result > 0);
		System.out.println("::deleteBatch::");
	}
	
	public void deleteByTest(){
		Map<String, Object> paramMap = this.getParamMap();
		int result = this.getDaoImpl().deleteBy(paramMap);
		Assert.assertEquals(true, result > 0);
		System.out.println("::deleteBy::");
	}
	
	public void deleteByPKTest() {
		String id = this.getId();
		int result = this.getDaoImpl().deleteByPK(id);
		Assert.assertEquals(true, result > 0);
		System.out.println("::deleteByPK::");
	}
	
	public void updateBatchTest() {
		List<? extends PersistEntity> list = this.getEntityList();
		int result = this.getDaoImpl().updateBatch(list);
		Assert.assertEquals(true, result > 0);
		System.out.println("::updateBatch::");
	}
	
	public void updateByPKTest() {
		PersistEntity entity = this.getEntity();
		int result = this.getDaoImpl().updateBy(entity);
		Assert.assertEquals(true, result > 0);
		System.out.println("::updateByPK::");
	}
	
	public void insertBatchTest() {
		List<? extends PersistEntity> list = this.getEntityList();
		int result = this.getDaoImpl().insertBatch(list);
		Assert.assertEquals(true, result > 0);
		System.out.println("::insertBatch::");
	}
	
	public void insertTest() {
		PersistEntity entity = this.getEntity();
		int result = this.getDaoImpl().insert(entity);
		Assert.assertEquals(true, result > 0);
		this.setId(entity.getId());
		System.out.println("::insertBatch::");
		
	}
	
	private IBaseDao daoImpl;
	private PersistEntity entity;
	private PageParam pageParam;
	private Map<String, Object> paramMap;
	private String id;
	private List<? extends PersistEntity> entityList;
	
	public IBaseDao getDaoImpl() {
		return daoImpl;
	}

	public void setDaoImpl(IBaseDao daoImpl) {
		this.daoImpl = daoImpl;
	}

	public PersistEntity getEntity() {
		return entity;
	}

	public void setEntity(PersistEntity entity) {
		this.entity = entity;
	}

	public PageParam getPageParam() {
		return pageParam;
	}

	public void setPageParam(PageParam pageParam) {
		this.pageParam = pageParam;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<? extends PersistEntity> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<? extends PersistEntity> entityList) {
		this.entityList = entityList;
	}
	
}

