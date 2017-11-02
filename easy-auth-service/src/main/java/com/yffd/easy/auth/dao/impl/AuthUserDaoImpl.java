package com.yffd.easy.auth.dao.impl;

import org.springframework.stereotype.Repository;
import org.yffd.easy.common.ssm.dao.impl.BaseDaoImpl;

import com.yffd.easy.auth.dao.AuthUserDao;
import com.yffd.easy.auth.model.AuthUser;

/**
 * @Description  系统模块：用户管理.
 * @Date		 2017年9月15日 上午11:19:22 <br/>
 * @author       zhangST
 * @version		 1.0
 * @param <T>
 * @since		 JDK 1.7+
 * @see 	 
 */
@Repository("authUserDao")
public class AuthUserDaoImpl extends BaseDaoImpl<AuthUser> implements AuthUserDao {
	
}

