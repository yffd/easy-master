package com.yffd.easy.auth.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.yffd.easy.common.ssm.dao.impl.BaseDaoImpl;

import com.yffd.easy.auth.dao.AuthResourceDao;
import com.yffd.easy.auth.model.AuthResource;

/**
 * @Description  系统模块：功能管理.
 * @Date		 2017年10月18日 下午4:51:28 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Repository("authResourceDao")
public class AuthResourceDaoImpl extends BaseDaoImpl<AuthResource> implements AuthResourceDao {
	
	@Override
	public List<AuthResource> findByRole(String roleCode) {
		return this.getSqlSession().selectList("findByRole", roleCode);
	}

}

