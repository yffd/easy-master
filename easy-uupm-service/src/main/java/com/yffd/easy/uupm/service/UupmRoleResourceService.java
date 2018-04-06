package com.yffd.easy.uupm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yffd.easy.framework.common.mapper.ICommonMapper;
import com.yffd.easy.framework.common.service.CommonServiceAbstract;
import com.yffd.easy.framework.domain.LoginInfo;
import com.yffd.easy.uupm.api.model.UupmRoleResourceModel;
import com.yffd.easy.uupm.mapper.IUupmRoleResourceMapper;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年04月05日 16时46分25秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class UupmRoleResourceService extends CommonServiceAbstract<UupmRoleResourceModel> {

	@Autowired
	private IUupmRoleResourceMapper uupmRoleResourceMapper;
	
	@Override
	public ICommonMapper<UupmRoleResourceModel> getMapper() {
		return this.uupmRoleResourceMapper;
	}

	@Override
	public Class<?> getMapperClass() {
		return IUupmRoleResourceMapper.class;
	}
	
	@Override
	protected String getStatement(String sqlId) {
		String namespace = IUupmRoleResourceMapper.class.getName();
		StringBuilder sb = new StringBuilder();
		sb.append(namespace).append(".").append(sqlId);
		return sb.toString();
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void saveRoleResource(String roleCode, List<UupmRoleResourceModel> modelList, LoginInfo loginInfo) {
		this.delByRoleCode(roleCode);
		this.addList(modelList, loginInfo);
	}
	
	public void delByRoleCode(String roleCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleCode", roleCode);
		this.delete(paramMap);
	}
	
}
