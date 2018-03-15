package com.yffd.easy.uupm.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
 * @Date		2018年03月14日 18时15分23秒 <br/>
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

	/******************* select begin *****************************************************/

	@Test
	public void selectCountByTest() {
		UupmTenantModel model = new UupmTenantModel();
		
		long result = this.dao.selectCountBy(model);
		System.out.println(result);
		
		Map<String, Object> paramMap = this.dao.model2map(model, null);
		long result2 = this.dao.selectCountBy(paramMap);
		System.out.println(result2);
	}
	
	@Test
	public void selectPageTest() {
		PageParam pageParam = new PageParam(1L, 10L);
		UupmTenantModel model = new UupmTenantModel();
		if(null!=model) {
			PageResult<UupmTenantModel> pageResult = (PageResult<UupmTenantModel>) this.dao.selectPage(model, pageParam);
			Assert.assertNotNull(pageResult);
			Assert.assertNotNull(pageResult.getRecordList());
			System.out.println(pageResult.getRecordList().size());
			for(Object obj : pageResult.getRecordList()) {
				System.out.println(obj);
			}
			
			Map<String, Object> paramMap = this.dao.model2map(model, null);
			PageResult<UupmTenantModel> pageResult2 = (PageResult<UupmTenantModel>) this.dao.selectPage(paramMap, pageParam);
			Assert.assertNotNull(pageResult2);
			Assert.assertNotNull(pageResult2.getRecordList());
			System.out.println(pageResult2.getRecordList().size());
			for(Object obj : pageResult2.getRecordList()) {
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
		Assert.assertNotNull(resultList);
		System.out.println(resultList.size());
		for(UupmTenantModel tmp : resultList) {
			System.out.println(tmp.getId());
		}
		
		Map<String, Object> paramMap = this.dao.model2map(model, null);
		List<UupmTenantModel> resultList2 = (List<UupmTenantModel>) this.dao.selectListBy(paramMap);
		Assert.assertNotNull(resultList2);
		System.out.println(resultList2.size());
		for(UupmTenantModel tmp : resultList2) {
			System.out.println(tmp.getId());
		}
	}
	
	@Test
	public void selectOneTest() {
		UupmTenantModel model = new UupmTenantModel();
		model.setId(2000+"");
		
		UupmTenantModel result = this.dao.selectOne(model);
		System.out.println(result);
		
		Map<String, Object> paramMap = this.dao.model2map(model, null);
		UupmTenantModel result2 = this.dao.selectOne(paramMap);
		System.out.println(result2);
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
	
	/******************* select end *****************************************************/
	/******************* insert begin ***************************************************/
	
	@Test
	public void insertOneTest() {
		UupmTenantModel model = (UupmTenantModel) this.getRandomModel();
		
		int result = this.dao.insertOne(model);
		System.out.println(result);
		
		Map<String, Object> paramMap = this.dao.model2map(model, null);
		int result2 = this.dao.insertOne(paramMap);
		System.out.println(result2);
	}
	
	@Test
	public void insertBatchTest() {
		List<UupmTenantModel> models = (List<UupmTenantModel>) this.getRandomModelList();
		int result = this.dao.insertBatch(models);
		System.out.println(result);
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for(UupmTenantModel model : models) {
			Map<String, Object> map = this.dao.model2map(model, null);
			list.add(map);
		}
		int result2 = this.dao.insertBatch(list);
		System.out.println(result2);
	}
	
	/******************* insert end***************************************************/
	/******************* update begin***************************************************/
	
	@Test
	public void updateByTest() {
		Map<String, Object> newMap = new HashMap<String, Object>();
		newMap.put("updateTime", new Date());
		newMap.put("updateBy", "tester");
		Map<String, Object> oldMap = new HashMap<String, Object>();
		oldMap.put("id", "2005");
		int result = this.dao.updateBy(newMap, oldMap);
		System.out.println(result);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("updateTime", new Date());
		paramMap.put("updateBy", "tester");
		paramMap.put("id", "2006");
		int result2 = this.dao.updateBy(paramMap, "id");
		System.out.println(result2);
		
		int result3 = this.dao.updateBy(paramMap, "id", "updateBy");
		System.out.println(result3);
		
		UupmTenantModel model = new UupmTenantModel();
		model.setId(2007+"");
		model.setUpdateBy("tester");
		int result4 = this.dao.updateBy(model, "id");
		System.out.println(result4);
		
		int result5 = this.dao.updateById(model);
		System.out.println(result5);
	}
	
	@Test
	public void updateByIdTest() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("updateBy", "tester");
		paramMap.put("id", "2005");
		int result = this.dao.updateById(paramMap);
		System.out.println(result);
		
		UupmTenantModel model = new UupmTenantModel();
		model.setId(2006+"");
		int result2 = this.dao.updateById(model);
		System.out.println(result2);
	}
	
	@Test
	public void updateByIdsTest() {
		Map<String, Object> newMap = new HashMap<String, Object>();
		newMap.put("updateBy", "tester");
		
		List<String> idList = new ArrayList<String>();
		for(int i=0;i<5;i++) {
			idList.add("200"+i);
		}
		int result = this.dao.updateByIds(newMap, idList);
		System.out.println(result);
	}
	
	@Test
	public void updateWithInByTest() {
		String inName = "id";
		Map<String, Object> newMap = new HashMap<String, Object>();
		newMap.put("updateBy", "tester");
		Map<String, Object> oldMap = new HashMap<String, Object>();
		oldMap.put("id", "2005");
		
		List<String> inValueList = new ArrayList<String>();
		for(int i=0;i<5;i++) {
			inValueList.add("200"+i);
		}
		
		int result = this.dao.updateWithInBy(newMap, inName, inValueList);
		System.out.println(result);
		
		int result2 = this.dao.updateWithInBy(newMap, oldMap, inName, inValueList);
		System.out.println(result2);
		
		Map<String, Object> oldInMap = new HashMap<String, Object>();
		oldInMap.put("id", inValueList);
		
		int result3 = this.dao.updateWithInBy(newMap, oldMap, oldInMap);
		System.out.println(result3);
	}
		
	/******************* update end ***************************************************/
	/******************* delete begin *************************************************/
	
	@Test
	public void deleteByTest() {
		UupmTenantModel model = new UupmTenantModel();
		model.setId(2000+"");
		int result = this.dao.deleteBy(model);
		System.out.println(result);
		
		Map<String, Object> paramMap = this.dao.model2map(model, null);
		int result2 = this.dao.deleteBy(paramMap);
		System.out.println(result2);
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
		for(int i=2002;i<2005;i++) {
			list.add("" + i);
		}
		int result = this.dao.deleteByIds(list);
		System.out.println(result);
	}
	
	@Test
	public void deleteWithInByTest() {
		String inName = "id";
		List<String> inValueList = new ArrayList<String>();
		for(int i=0;i<5;i++) {
			inValueList.add("200"+i);
		}
		
		int result = this.dao.deleteWithInBy(inName, inValueList);
		System.out.println(result);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", "2000");
		int result2 = this.dao.deleteWithInBy(paramMap, inName, inValueList);
		System.out.println(result2);
		
		Map<String, Object> inMap = new HashMap<String, Object>();
		inMap.put(inName, inValueList);
		int result3 = this.dao.deleteWithInBy(paramMap, inMap);
		System.out.println(result3);
		
	}
	
	/******************* delete end *************************************************/
	
}
