package com.yffd.easy.uupm.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.framework.core.common.mapper.ICommonMapper;
import com.yffd.easy.framework.core.common.service.CommonServiceAbstract;
import com.yffd.easy.uupm.mapper.IUupmAccountMapper;
import com.yffd.easy.uupm.pojo.entity.UupmAccountEntity;
import com.yffd.easy.uupm.pojo.entity.UupmOrganizationEntity;
import com.yffd.easy.uupm.pojo.entity.UupmRoleResourceEntity;
import com.yffd.easy.uupm.pojo.entity.UupmUserRoleEntity;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年04月06日 13时40分09秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class UupmAccountService extends CommonServiceAbstract<UupmAccountEntity> {

	@Autowired
	private IUupmAccountMapper uupmAccountMapper;
	@Autowired
	private UupmUserRoleService uupmUserRoleService;
	@Autowired
	private UupmRoleResourceService uupmRoleResourceService;
	@Autowired
	private UupmOrganizationService uupmOrganizationService;
	
	@Override
	public ICommonMapper<UupmAccountEntity> getMapper() {
		return this.uupmAccountMapper;
	}

	@Override
	public Class<?> getMapperClass() {
		return IUupmAccountMapper.class;
	}

	public int addAccount(UupmAccountEntity model, String encryptPwd, String salt) {
		model.setSalt(salt);
		model.setAccountPwd(encryptPwd);
		return super.addOne(model);
	}
	
	public Map<String, Object> findLoginInfo(String accountId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountId", accountId);
		Map<String, Object> resultMap = this.selectOneBy("selectLoginInfo", paramMap, true);
		// 角色
		String userCode = (String) resultMap.get("userCode");
		UupmUserRoleEntity userRoleModel = new UupmUserRoleEntity();
		userRoleModel.setUserCode(userCode);
		List<UupmUserRoleEntity> userRoleList = this.uupmUserRoleService.findRoleCodes(userRoleModel);
		if(null==userRoleList || userRoleList.size()==0) return null;
		Set<String> roles = new HashSet<String>();
		for(UupmUserRoleEntity model : userRoleList) {
			roles.add(model.getRoleCode());
		}
		paramMap.put("roles", roles);
		// 资源
		List<UupmRoleResourceEntity> roleResourceList = this.uupmRoleResourceService.findRsCode(userRoleList);
		if(null==roleResourceList ||roleResourceList.size()==0) return null;
		Set<String> resources = new HashSet<String>();
		for(UupmRoleResourceEntity model : roleResourceList) {
			resources.add(model.getRsCode());
		}
		paramMap.put("resources", resources);
		// 机构
		String orgCode = (String) resultMap.get("orgCode");
		UupmOrganizationEntity orgModel = new UupmOrganizationEntity();
		orgModel.setOrgCode(orgCode);
		String orgNamePath = this.uupmOrganizationService.findParentNamePath(orgModel);
		paramMap.put("orgCode", orgCode);
		paramMap.put("orgName", (String) resultMap.get("orgName"));
		paramMap.put("orgNamePath", orgNamePath);
		
		return paramMap;
	}
	
	
}
