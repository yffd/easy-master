package com.yffd.easy.uupm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yffd.easy.framework.core.common.mapper.ICommonMapper;
import com.yffd.easy.framework.core.common.service.CommonServiceAbstract;
import com.yffd.easy.framework.domain.LoginInfo;
import com.yffd.easy.uupm.api.model.UupmResourceModel;
import com.yffd.easy.uupm.api.model.UupmTenantResourceModel;
import com.yffd.easy.uupm.mapper.IUupmTenantResourceMapper;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年04月05日 15时04分21秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class UupmTenantResourceService extends CommonServiceAbstract<UupmTenantResourceModel> {

	@Autowired
	private IUupmTenantResourceMapper uupmTenantResourceMapper;
	
	@Override
	public ICommonMapper<UupmTenantResourceModel> getMapper() {
		return this.uupmTenantResourceMapper;
	}

	@Override
	public Class<?> getMapperClass() {
		return IUupmTenantResourceMapper.class;
	}

	@Override
	protected String getStatement(String sqlId) {
		String namespace = IUupmTenantResourceMapper.class.getName();
		StringBuilder sb = new StringBuilder();
		sb.append(namespace).append(".").append(sqlId);
		return sb.toString();
	}
	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void saveTenantResource(String tenantCode, List<UupmTenantResourceModel> modelList, LoginInfo loginInfo) {
		this.delByTenantCode(tenantCode);
		this.addList(modelList, loginInfo);
	}
	
	public void delByTenantCode(String tenantCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tenantCode", tenantCode);
		this.delete(paramMap);
	}
	 
	public List<UupmResourceModel> findTenantResource(String tenantCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tenantCode", tenantCode);
		List<Map<String, Object>> list = this.selectListBy("selectTenantResource", paramMap, true);
		return this.map2model(list, UupmResourceModel.class, null);
	}
}
