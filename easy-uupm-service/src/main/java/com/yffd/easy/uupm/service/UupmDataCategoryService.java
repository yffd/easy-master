package com.yffd.easy.uupm.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.framework.base.dao.GenericDao;
import com.yffd.easy.framework.base.service.GenericService;
import com.yffd.easy.framework.domain.LoginInfo;
import com.yffd.easy.uupm.api.model.UupmDataCategoryModel;
import com.yffd.easy.uupm.dao.UupmDataCategoryDao;

/**
 * @Description	简单描述该类的功能（可选）.
 * @Date		2018年02月28日 09时53分33秒 <br/>
 * @author		ZhangST
 * @version		1.0
 * @since		JDK 1.7+
 * @see
 */

@Service
public class UupmDataCategoryService extends GenericService<UupmDataCategoryModel> {

	@Autowired
	private UupmDataCategoryDao uupmDataCategoryDao;
	
	@Override
	public GenericDao<UupmDataCategoryModel> getBindDao() {
		return this.uupmDataCategoryDao;
	}

	@Override
	public void addOne(UupmDataCategoryModel model, LoginInfo loginInfo) {
		if(null==model) return;
		if("".equals(model.getParentCode()) || "-1".equals(model.getParentCode())) {
			model.setParentCode("-1");
			model.setDataPath("-1.");
			model.setDataLabel("LEAF");
		} else {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("categoryCode", model.getParentCode());
			UupmDataCategoryModel parent = this.uupmDataCategoryDao.selectOne(paramMap);
			parent.setDataLabel("BRANCH");
			this.uupmDataCategoryDao.updateBy(parent);
			
			model.setDataPath(parent.getDataPath() + model.getCategoryCode() + ".");
			model.setDataLabel("LEAF");
		}
		this.setAddDefault(model, loginInfo);
		this.uupmDataCategoryDao.insertOne(model);
	}

}
