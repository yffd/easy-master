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
import com.yffd.easy.uupm.mapper.IUupmUserRoleMapper;
import com.yffd.easy.uupm.api.model.UupmRoleResourceModel;
import com.yffd.easy.uupm.api.model.UupmUserRoleModel;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年04月06日 13时19分44秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class UupmUserRoleService extends CommonServiceAbstract<UupmUserRoleModel> {

	@Autowired
	private IUupmUserRoleMapper uupmUserRoleMapper;
	
	@Override
	public ICommonMapper<UupmUserRoleModel> getMapper() {
		return this.uupmUserRoleMapper;
	}

	@Override
	public Class<?> getMapperClass() {
		return IUupmUserRoleMapper.class;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void saveUserRole(String tennantCode, String userCode, List<UupmUserRoleModel> modelList, LoginInfo loginInfo) {
		this.delByUserCode(tennantCode, userCode);
		this.addList(modelList, loginInfo);
	}
	
	public void delByUserCode(String tennantCode, String userCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tennantCode", tennantCode);
		paramMap.put("userCode", userCode);
		this.delete(paramMap);
	}
	
}
