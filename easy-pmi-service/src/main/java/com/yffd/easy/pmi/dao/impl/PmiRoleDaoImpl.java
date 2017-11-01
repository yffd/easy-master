package com.yffd.easy.pmi.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.yffd.easy.common.core.exception.DaoException;
import org.yffd.easy.common.core.util.ValidUtils;
import org.yffd.easy.common.ssm.dao.impl.BaseDaoImpl;

import com.yffd.easy.pmi.dao.PmiRoleDao;
import com.yffd.easy.pmi.model.PmiRole;

/**
 * @Description  系统模块：角色管理.
 * @Date		 2017年9月15日 上午11:19:22 <br/>
 * @author       zhangST
 * @version		 1.0
 * @param <T>
 * @since		 JDK 1.7+
 * @see 	 
 */
@Repository("pmiRoleDao")
public class PmiRoleDaoImpl extends BaseDaoImpl<PmiRole> implements PmiRoleDao {
	private static final String STMT_SAVE_ROLE_RESOURCE = "saveRoleResource";
	private static final String STMT_DELETE_ROLE_RESOURCE = "deleteRoleResource";
	private static final String STMT_SAVE_ROLE_USER = "saveRoleUser";
	private static final String STMT_DELET_EROLE_USER = "deleteRoleUser";
	private static final String STMT_FIND_BY_USER = "findByUser";
	
	@Override
	public void deleteRoleResource(String roleCode) {
		if(ValidUtils.isBlank(roleCode)) return;
		this.getSqlSession().delete(STMT_DELETE_ROLE_RESOURCE, roleCode);
	}

	@Override
	public void saveRoleResource(String roleCode, List<String> rsCodes) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleCode", roleCode);
		paramMap.put("rsCodes", rsCodes);
		paramMap.put("createTime", new Date());
		int result = this.getSqlSession().insert(getStatement(STMT_SAVE_ROLE_RESOURCE), paramMap);
	    if (result <= 0) {
	        throw DaoException.DB_INSERT_RESULT_0(getStatement(SQL_INSERT));
	    }
	}

	@Override
	public void deleteRoleUser(String roleCode) {
		if(ValidUtils.isBlank(roleCode)) return;
		this.getSqlSession().delete(STMT_DELET_EROLE_USER, roleCode);
	}

	@Override
	public void saveRoleUser(String userCode, List<String> roleCodes) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userCode", userCode);
		paramMap.put("roleCodes", roleCodes);
		paramMap.put("createTime", new Date());
		int result = this.getSqlSession().insert(getStatement(STMT_SAVE_ROLE_USER), paramMap);
	    if (result <= 0) {
	        throw DaoException.DB_INSERT_RESULT_0(getStatement(SQL_INSERT));
	    }
	}

	@Override
	public List<PmiRole> findByUser(String userCode) {
		return this.getSqlSession().selectList(STMT_FIND_BY_USER, userCode);
	}
	
	
}

