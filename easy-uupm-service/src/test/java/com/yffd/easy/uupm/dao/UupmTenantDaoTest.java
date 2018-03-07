package com.yffd.easy.uupm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.uupm.api.model.UupmTenantModel;
import com.yffd.easy.uupm.base.UupmBaseDaoTest;

/**
 * @Description	简单描述该类的功能（可选）.
 * @Date		2018年03月07日 15时30分32秒 <br/>
 * @author		ZhangST
 * @version		1.0
 * @since		JDK 1.7+
 * @see
 */
public class UupmTenantDaoTest extends UupmBaseDaoTest {

	@Autowired
	private UupmTenantDao dao;

	@Override
	public Class<?> getModelClass() {
		return UupmTenantModel.class;
	}

	@Test
	public void selectCountByTest() {
		UupmTenantModel model = new UupmTenantModel();
		long result = this.dao.selectCountBy(model);
		System.out.println(result);
		
//		Map<String, Object> paramMap = this.dao.model2map(model, null);
//		long result = this.dao.selectCountBy(paramMap);
//		System.out.println(result);
	}
	
	@Test
	public void selectPageTest() {
		PageParam pageParam = new PageParam(1L, 10L);
		UupmTenantModel model = new UupmTenantModel();
		if(null!=model) {
			PageResult<UupmTenantModel> pageResult = (PageResult<UupmTenantModel>) this.dao.selectPage(model, pageParam);
			
//			Map<String, Object> paramMap = this.dao.model2map(model, null);
//			PageResult<UupmTenantModel> pageResult = (PageResult<UupmTenantModel>) this.dao.selectPage(paramMap, pageParam);
			
			Assert.assertNotNull(pageResult);
			Assert.assertNotNull(pageResult.getRecordList());
			System.out.println(pageResult.getRecordList().size());
			for(Object obj : pageResult.getRecordList()) {
				System.out.println(obj);
			}
		} else {
			Assert.fail();
		}
	}
	
	@Test
	public void selectListByTest() {
		UupmTenantModel model = new UupmTenantModel();
		List<UupmTenantModel> resultList = (List<UupmTenantModel>) this.dao.selectListBy(model);
		
//		Map<String, Object> paramMap = this.dao.model2map(model, null);
//		List<UupmTenantModel> resultList = (List<UupmTenantModel>) this.dao.selectListBy(paramMap);
		
		Assert.assertNotNull(resultList);
		System.out.println(resultList.size());
		for(UupmTenantModel tmp : resultList) {
			System.out.println(tmp.getId());
		}
	}
	
	@Test
	public void selectOneTest() {
		UupmTenantModel model = new UupmTenantModel();
		model.setId(2000+"");
		UupmTenantModel result = this.dao.selectOne(model);
		
//		Map<String, Object> paramMap = this.dao.model2map(model, null);
//		UupmTenantModel result = this.dao.selectOne(paramMap);
		
		Assert.assertNotNull(result);
		System.out.println(result);
	}
	
	@Test
	public void insertOneTest() {
		UupmTenantModel model = (UupmTenantModel) this.getRandomModel();
		int result = this.dao.insertOne(model);
		
//		Map<String, Object> paramMap = this.dao.model2map(model, null);
//		int result = this.dao.insertOne(paramMap);
		
		System.out.println(result);
	}
	
	@Test
	public void updateByTest() {
		UupmTenantModel model = new UupmTenantModel();
		model.setId(2000+"");
		int result = this.dao.updateBy(model);
		
//		Map<String, Object> paramMap = this.dao.model2map(model, null);
//		int result = this.dao.updateBy(paramMap);
		
		System.out.println(result);
	}
	
	@Test
	public void deleteByTest() {
		UupmTenantModel model = new UupmTenantModel();
		model.setId(2000+"");
		int result = this.dao.deleteBy(model);
		
//		Map<String, Object> paramMap = this.dao.model2map(model, null);
//		int result = this.dao.deleteBy(paramMap);
		
		System.out.println(result);
	}
	
	/**********************************************************************/
	
	@Test
	public void insertBatchTest() {
		List<UupmTenantModel> models = (List<UupmTenantModel>) this.getRandomModelList();
		this.dao.insertBatch(models);
	}

	@Test
	public void updateBatchTest() {
		int i = 2001;
		List<UupmTenantModel> models = (List<UupmTenantModel>) this.getRandomModelList();
		for(UupmTenantModel model : models) {
			model.setId(""+i);
			i++;
		}
		this.dao.updateBatch(models);
	}
	
	@Test
	public void selectByIdTest() {
		String id = "2001";
		UupmTenantModel result = this.dao.selectById(id);
		Assert.assertNotNull(result);
		System.out.println(result);
	}
	
	@Test
	public void selectListByIdsTest() {
		List<String> list = new ArrayList<String>();
		for(int i=2001;i<2005;i++) {
			list.add("" + i);
		}
		List<UupmTenantModel> result = (List<UupmTenantModel>) this.dao.selectListByIds(list);
		Assert.assertNotNull(result);
		System.out.println(result.size());
		for(UupmTenantModel tmp : result) {
			System.out.println(tmp);
		}
	}
	
	@Test
	public void selectAllTest() {
		List<UupmTenantModel> result = (List<UupmTenantModel>) this.dao.selectAll();
		Assert.assertNotNull(result);
		System.out.println(result.size());
		for(UupmTenantModel tmp : result) {
			System.out.println(tmp);
		}
	}
	
	@Test
	public void deleteByIdTest() {
		String id = "2001";
		int result = this.dao.deleteById(id);
		System.out.println(result);
	}

	@Test
	public void deleteByIdsTest() {
		List<String> list = new ArrayList<String>();
		for(int i=2002;i<20016;i++) {
			list.add("" + i);
		}
		int result = this.dao.deleteByIds(list);
		System.out.println(result);
	}
	
	
}
