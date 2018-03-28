package com.yffd.easy.uupm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yffd.easy.common.core.exception.EasyBizException;
import com.yffd.easy.framework.base.dao.GenericDao;
import com.yffd.easy.framework.base.service.GenericService;
import com.yffd.easy.framework.domain.LoginInfo;
import com.yffd.easy.uupm.api.model.UupmResourceModel;
import com.yffd.easy.uupm.api.model.UupmTenantResourceModel;
import com.yffd.easy.uupm.dao.UupmTenantResourceDao;

/**
 * @Description	简单描述该类的功能（可选）.
 * @Date		2018年03月22日 10时29分51秒 <br/>
 * @author		ZhangST
 * @version		1.0
 * @since		JDK 1.7+
 * @see
 */

@Service
public class UupmTenantResourceService extends GenericService<UupmTenantResourceModel> {

	@Autowired
	private UupmTenantResourceDao uupmTenantResourceDao;
	
	@Override
	public GenericDao<UupmTenantResourceModel> getBindDao() {
		return this.uupmTenantResourceDao;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void saveTenantResource(String tenantCode, List<UupmTenantResourceModel> list, LoginInfo loginInfo) {
		this.delByTenantCode(tenantCode, loginInfo);
		this.addBatch(list, loginInfo);
	}
	
	public void delByTenantCode(String tenantCode, LoginInfo loginInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tenantCode", tenantCode);
		this.uupmTenantResourceDao.deleteBy(paramMap);
	}
	
	public List<UupmResourceModel> findTenantResourceListBy(UupmTenantResourceModel model) {
		if(null==model) throw EasyBizException.BIZ_PARAMS_ERROR();
		Map<String, Object> paramMap = this.model2map(model, null);
		List<Map<String, Object>> listResult =  this.uupmTenantResourceDao.selectTenantResourceListBy(paramMap);
		return this.map2model(listResult, UupmResourceModel.class, true);
	}
}
