package com.yffd.easy.uupm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yffd.easy.framework.core.common.mapper.ICommonMapper;
import com.yffd.easy.framework.core.common.service.CommonServiceAbstract;
import com.yffd.easy.uupm.mapper.IUupmRoleResourceMapper;
import com.yffd.easy.uupm.pojo.entity.UupmRoleResourceEntity;
import com.yffd.easy.uupm.pojo.entity.UupmUserRoleEntity;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年04月05日 16时46分25秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class UupmRoleResourceService extends CommonServiceAbstract<UupmRoleResourceEntity> {

	@Autowired
	private IUupmRoleResourceMapper uupmRoleResourceMapper;
	
	@Override
	public ICommonMapper<UupmRoleResourceEntity> getMapper() {
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
	public void saveRoleResource(String roleCode, List<UupmRoleResourceEntity> modelList) {
		this.delByRoleCode(roleCode);
		this.addList(modelList);
	}
	
	public void delByRoleCode(String roleCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleCode", roleCode);
		this.delete(paramMap);
	}
	
	public Set<String> findRsCode(String roleCode) {
		UupmRoleResourceEntity paramModel = new UupmRoleResourceEntity();
		paramModel.setRoleCode(roleCode);
		List<UupmRoleResourceEntity> resultList = this.findList(paramModel, null);
		if(null==resultList ||resultList.size()==0) return null;
		Set<String> rsCodes = new HashSet<String>();
		for(UupmRoleResourceEntity model : resultList) {
			rsCodes.add(model.getRsCode());
		}
		return rsCodes;
	}
	
	public Set<String> findRsCode(Set<String> roleCodes) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rsCodeList", roleCodes);
		List<UupmRoleResourceEntity> resultList = this.findList(null, paramMap);
		if(null==resultList ||resultList.size()==0) return null;
		Set<String> rsCodes = new HashSet<String>();
		for(UupmRoleResourceEntity model : resultList) {
			rsCodes.add(model.getRsCode());
		}
		return rsCodes;
	}
	
	public List<UupmRoleResourceEntity> findRsCode(List<UupmUserRoleEntity> userRoleList) {
		if(null==userRoleList || userRoleList.size()==0) return null;
		List<String> roleCodeList = new ArrayList<String>();
		for(UupmUserRoleEntity model : userRoleList) {
			roleCodeList.add(model.getRoleCode());
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rsCodeList", roleCodeList);
		return this.findList(null, paramMap);
	}
}
