package com.yffd.easy.uupm.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.uupm.api.model.UupmTenantModel;
import com.yffd.easy.uupm.base.UupmBaseDaoTest;

/**
 * @Description	简单描述该类的功能（可选）.
 * @Date		2018年02月07日 14时32分25秒 <br/>
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

	@Override
	public String getRangeId() {
		return "1000:1015";
	}

	@Test
	public void selectPageTest() {
		PageParam pageParam = new PageParam(1L, 10L);
		Object model = this.getModelInstanceForSelect();
		if(null!=model) {
			PageResult<Object> pageResult = this.dao.selectPage(model, pageParam);
			Assert.assertNotNull(pageResult);
			Assert.assertNotNull(pageResult.getRecordList());
			System.out.println(pageResult.getRecordList().size());
			for(Object obj : pageResult.getRecordList()) {
				System.out.println(obj);
			}
			
			List<Object> list = (List<Object>) this.dao.selectListBy(model);
			Assert.assertNotNull(list);
			System.out.println(list);
			
		} else {
			Assert.fail();
		}
	}

	@Test
	public void insertOneTest() {
		Object model = this.getModelInstanceForInsertOne();
		if(null!=model) {
			Object obj = this.dao.insertOne(model);
			Assert.assertNotNull(obj);
		} else {
			Assert.fail();
		}
	}

	@Test
	public void insertBatchTest() {
		List<Object> models = this.getModelInstanceForInsertBatch();
		this.dao.insertBatch(models);
	}

	@Test
	public void updateByTest() {
		Object model = this.getModelInstanceForUpdate();
		this.dao.updateBy(model);
	}

	@Test
	public void updateBatchTest() {
		List<Object> models = this.getModelInstanceForUpdateBatch();
		this.dao.updateBatch(models);
	}

	@Test
	public void deleteByTest() {
		Object model = this.getModelInstanceForDelete();
		this.dao.deleteBy(model);
	}

	@Test
	public void deleteBatchTest() {
		List<Object> models = this.getModelInstanceForDeleteBatch();
		this.dao.deleteBatch(models);
	}
}
