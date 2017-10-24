package org.yffd.easy.app.system.dao;

import java.util.List;

import org.yffd.easy.app.system.model.SysRole;
import org.yffd.easy.common.ssm.dao.IBaseDao;

/**
 * @Description  系统模块：角色管理.
 * @Date		 2017年10月23日 上午11:10:29 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface SysRoleDao extends IBaseDao<SysRole> {

	void deleteRoleFunc(String roleCode);
	
	void saveRoleFunc(String roleCode, List<String> funcCodes);
	
	void deleteRoleUser(String roleCode);
	
	void saveRoleUser(String userCode, List<String> roleCodes);
	
	List<SysRole> findByUser(String userCode);

}

