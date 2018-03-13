package com.yffd.easy.uupm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.uupm.api.model.UupmApplicationModel;
import com.yffd.easy.uupm.base.UupmBaseDaoTest;

/**
 * @Description	简单描述该类的功能（可选）.
 * @Date		2018年03月08日 14时03分16秒 <br/>
 * @author		ZhangST
 * @version		1.0
 * @since		JDK 1.7+
 * @see
 */
public class UupmApplicationDaoTest extends UupmBaseDaoTest {

	@Autowired
	private UupmApplicationDao dao;

	@Override
	public Class<?> getModelClass() {
		return UupmApplicationModel.class;
	}

	@Test
	public void selectCountByTest() {
		UupmApplicationModel model = new UupmApplicationModel();
		long result = this.dao.selectCountBy(model);
		System.out.println(result);
		
//		Map<String, Object> paramMap = this.dao.model2map(model, null);
//		long result = this.dao.selectCountBy(paramMap);
//		System.out.println(result);
	}
	
	@Test
	public void selectPageTest() {
		PageParam pageParam = new PageParam(1L, 10L);
		UupmApplicationModel model = new UupmApplicationModel();
		if(null!=model) {
			PageResult<UupmApplicationModel> pageResult = (PageResult<UupmApplicationModel>) this.dao.selectPage(model, pageParam);
			
//			Map<String, Object> paramMap = this.dao.model2map(model, null);
//			PageResult<UupmApplicationModel> pageResult = (PageResult<UupmApplicationModel>) this.dao.selectPage(paramMap, pageParam);
			
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
		UupmApplicationModel model = new UupmApplicationModel();
		List<UupmApplicationModel> resultList = (List<UupmApplicationModel>) this.dao.selectListBy(model);
		
//		Map<String, Object> paramMap = this.dao.model2map(model, null);
//		List<UupmApplicationModel> resultList = (List<UupmApplicationModel>) this.dao.selectListBy(paramMap);
		
		Assert.assertNotNull(resultList);
		System.out.println(resultList.size());
		for(UupmApplicationModel tmp : resultList) {
			System.out.println(tmp.getId());
		}
	}
	
	@Test
	public void selectOneTest() {
		UupmApplicationModel model = new UupmApplicationModel();
		model.setId(2000+"");
		UupmApplicationModel result = this.dao.selectOne(model);
		
//		Map<String, Object> paramMap = this.dao.model2map(model, null);
//		UupmApplicationModel result = this.dao.selectOne(paramMap);
		
		Assert.assertNotNull(result);
		System.out.println(result);
	}
	
	@Test
	public void insertOneTest() {
		UupmApplicationModel model = (UupmApplicationModel) this.getRandomModel();
		int result = this.dao.insertOne(model);
		
//		Map<String, Object> paramMap = this.dao.model2map(model, null);
//		int result = this.dao.insertOne(paramMap);
		
		System.out.println(result);
	}
	
	@Test
	public void updateByTest() {
		UupmApplicationModel model = new UupmApplicationModel();
		model.setId(2000+"");
		int result = this.dao.updateBy(model);
		
//		Map<String, Object> paramMap = this.dao.model2map(model, null);
//		int result = this.dao.updateBy(paramMap);
		
		System.out.println(result);
	}
	
	@Test
	public void deleteByTest() {
		UupmApplicationModel model = new UupmApplicationModel();
		model.setId(2000+"");
		int result = this.dao.deleteBy(model);
		
//		Map<String, Object> paramMap = this.dao.model2map(model, null);
//		int result = this.dao.deleteBy(paramMap);
		
		System.out.println(result);
	}
	
	/**********************************************************************/
	
	@Test
	public void insertBatchTest() {
		List<UupmApplicationModel> models = (List<UupmApplicationModel>) this.getRandomModelList();
		this.dao.insertBatch(models);
	}

	@Test
	public void updateBatchTest() {
		int i = 2001;
		List<UupmApplicationModel> models = (List<UupmApplicationModel>) this.getRandomModelList();
		for(UupmApplicationModel model : models) {
			model.setId(""+i);
			i++;
		}
		this.dao.updateBatch(models);
	}
	
	@Test
	public void selectByIdTest() {
		String id = "2001";
		UupmApplicationModel result = this.dao.selectById(id);
		Assert.assertNotNull(result);
		System.out.println(result);
	}
	
	@Test
	public void selectListByIdsTest() {
		List<String> list = new ArrayList<String>();
		for(int i=2001;i<2005;i++) {
			list.add("" + i);
		}
		List<UupmApplicationModel> result = (List<UupmApplicationModel>) this.dao.selectListByIds(list);
		Assert.assertNotNull(result);
		System.out.println(result.size());
		for(UupmApplicationModel tmp : result) {
			System.out.println(tmp);
		}
	}
	
	@Test
	public void selectAllTest() {
		List<UupmApplicationModel> result = (List<UupmApplicationModel>) this.dao.selectAll();
		Assert.assertNotNull(result);
		System.out.println(result.size());
		for(UupmApplicationModel tmp : result) {
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
