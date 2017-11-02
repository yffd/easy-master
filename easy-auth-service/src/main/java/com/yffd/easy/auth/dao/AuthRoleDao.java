package com.yffd.easy.auth.dao;

import java.util.List;

import org.yffd.easy.common.ssm.dao.IBaseDao;

import com.yffd.easy.auth.model.AuthRole;

/**
 * @Description  系统模块：角色管理.
 * @Date		 2017年10月23日 上午11:10:29 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface AuthRoleDao extends IBaseDao<AuthRole> {

	void deleteRoleResource(String roleCode);
	
	void saveRoleResource(String roleCode, List<String> rsCodes);
	
	void deleteRoleUser(String roleCode);
	
	void saveRoleUser(String userCode, List<String> roleCodes);
	
	List<AuthRole> findByUser(String userCode);

}

