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
import com.yffd.easy.uupm.mapper.IUupmTenantResourceMapper;
import com.yffd.easy.uupm.pojo.entity.UupmResourceEntity;
import com.yffd.easy.uupm.pojo.entity.UupmTenantResourceEntity;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年04月05日 15时04分21秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class UupmTenantResourceService extends CommonServiceAbstract<UupmTenantResourceEntity> {

	@Autowired
	private IUupmTenantResourceMapper uupmTenantResourceMapper;
	
	@Override
	public ICommonMapper<UupmTenantResourceEntity> getMapper() {
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
	public void saveTenantResource(String tenantCode, List<UupmTenantResourceEntity> modelList) {
		this.delByTenantCode(tenantCode);
		this.addList(modelList);
	}
	
	public void delByTenantCode(String tenantCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tenantCode", tenantCode);
		this.delete(paramMap);
	}
	 
	public List<UupmResourceEntity> findTenantResource(String tenantCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tenantCode", tenantCode);
		List<Map<String, Object>> list = this.selectListBy("selectTenantResource", paramMap, true);
		return this.map2model(list, UupmResourceEntity.class, null);
	}
}
