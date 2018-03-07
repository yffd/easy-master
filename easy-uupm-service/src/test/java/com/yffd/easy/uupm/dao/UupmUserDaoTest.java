package com.yffd.easy.uupm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.uupm.api.model.UupmUserModel;
import com.yffd.easy.uupm.base.UupmBaseDaoTest;

/**
 * @Description	简单描述该类的功能（可选）.
 * @Date		2018年03月07日 16时15分23秒 <br/>
 * @author		ZhangST
 * @version		1.0
 * @since		JDK 1.7+
 * @see
 */
public class UupmUserDaoTest extends UupmBaseDaoTest {

	@Autowired
	private UupmUserDao dao;

	@Override
	public Class<?> getModelClass() {
		return UupmUserModel.class;
	}

	@Test
	public void selectCountByTest() {
		UupmUserModel model = new UupmUserModel();
		long result = this.dao.selectCountBy(model);
		System.out.println(result);
		
//		Map<String, Object> paramMap = this.dao.model2map(model, null);
//		long result = this.dao.selectCountBy(paramMap);
//		System.out.println(result);
	}
	
	@Test
	public void selectPageTest() {
		PageParam pageParam = new PageParam(1L, 10L);
		UupmUserModel model = new UupmUserModel();
		if(null!=model) {
			PageResult<UupmUserModel> pageResult = (PageResult<UupmUserModel>) this.dao.selectPage(model, pageParam);
			
//			Map<String, Object> paramMap = this.dao.model2map(model, null);
//			PageResult<UupmUserModel> pageResult = (PageResult<UupmUserModel>) this.dao.selectPage(paramMap, pageParam);
			
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
		UupmUserModel model = new UupmUserModel();
		List<UupmUserModel> resultList = (List<UupmUserModel>) this.dao.selectListBy(model);
		
//		Map<String, Object> paramMap = this.dao.model2map(model, null);
//		List<UupmUserModel> resultList = (List<UupmUserModel>) this.dao.selectListBy(paramMap);
		
		Assert.assertNotNull(resultList);
		System.out.println(resultList.size());
		for(UupmUserModel tmp : resultList) {
			System.out.println(tmp.getId());
		}
	}
	
	@Test
	public void selectOneTest() {
		UupmUserModel model = new UupmUserModel();
		model.setId(2000+"");
		UupmUserModel result = this.dao.selectOne(model);
		
//		Map<String, Object> paramMap = this.dao.model2map(model, null);
//		UupmUserModel result = this.dao.selectOne(paramMap);
		
		Assert.assertNotNull(result);
		System.out.println(result);
	}
	
	@Test
	public void insertOneTest() {
		UupmUserModel model = (UupmUserModel) this.getRandomModel();
		int result = this.dao.insertOne(model);
		
//		Map<String, Object> paramMap = this.dao.model2map(model, null);
//		int result = this.dao.insertOne(paramMap);
		
		System.out.println(result);
	}
	
	@Test
	public void updateByTest() {
		UupmUserModel model = new UupmUserModel();
		model.setId(2000+"");
		int result = this.dao.updateBy(model);
		
//		Map<String, Object> paramMap = this.dao.model2map(model, null);
//		int result = this.dao.updateBy(paramMap);
		
		System.out.println(result);
	}
	
	@Test
	public void deleteByTest() {
		UupmUserModel model = new UupmUserModel();
		model.setId(2000+"");
		int result = this.dao.deleteBy(model);
		
//		Map<String, Object> paramMap = this.dao.model2map(model, null);
//		int result = this.dao.deleteBy(paramMap);
		
		System.out.println(result);
	}
	
	/**********************************************************************/
	
	@Test
	public void insertBatchTest() {
		List<UupmUserModel> models = (List<UupmUserModel>) this.getRandomModelList();
		this.dao.insertBatch(models);
	}

	@Test
	public void updateBatchTest() {
		int i = 2001;
		List<UupmUserModel> models = (List<UupmUserModel>) this.getRandomModelList();
		for(UupmUserModel model : models) {
			model.setId(""+i);
			i++;
		}
		this.dao.updateBatch(models);
	}
	
	@Test
	public void selectByIdTest() {
		String id = "2001";
		UupmUserModel result = this.dao.selectById(id);
		Assert.assertNotNull(result);
		System.out.println(result);
	}
	
	@Test
	public void selectListByIdsTest() {
		List<String> list = new ArrayList<String>();
		for(int i=2001;i<2005;i++) {
			list.add("" + i);
		}
		List<UupmUserModel> result = (List<UupmUserModel>) this.dao.selectListByIds(list);
		Assert.assertNotNull(result);
		System.out.println(result.size());
		for(UupmUserModel tmp : result) {
			System.out.println(tmp);
		}
	}
	
	@Test
	public void selectAllTest() {
		List<UupmUserModel> result = (List<UupmUserModel>) this.dao.selectAll();
		Assert.assertNotNull(result);
		System.out.println(result.size());
		for(UupmUserModel tmp : result) {
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
