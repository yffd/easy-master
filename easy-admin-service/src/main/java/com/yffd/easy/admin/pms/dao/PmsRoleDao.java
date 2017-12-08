package com.yffd.easy.admin.pms.dao;

import java.util.List;

import com.yffd.easy.admin.pms.model.PmsRole;
import com.yffd.easy.common.support.dao.IBaseDao;

/**
 * @Description  系统模块：角色管理.
 * @Date		 2017年10月23日 上午11:10:29 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface PmsRoleDao extends IBaseDao<PmsRole> {

	void deleteRoleResource(String roleCode);
	
	void saveRoleResource(String roleCode, List<String> rsCodes);
	
	void deleteRoleUser(String roleCode);
	
	void saveRoleUser(String userCode, List<String> roleCodes);
	
	List<PmsRole> findByUser(String userCode);

}

