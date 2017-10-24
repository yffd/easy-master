package org.yffd.easy.app.system.dao.impl;

import org.springframework.stereotype.Repository;
import org.yffd.easy.app.system.dao.SysUserDao;
import org.yffd.easy.app.system.model.SysUser;
import org.yffd.easy.common.ssm.dao.impl.BaseDaoImpl;

/**
 * @Description  系统模块：用户管理.
 * @Date		 2017年9月15日 上午11:19:22 <br/>
 * @author       zhangST
 * @version		 1.0
 * @param <T>
 * @since		 JDK 1.7+
 * @see 	 
 */
@Repository("sysUserDao")
public class SysUserDaoImpl extends BaseDaoImpl<SysUser> implements SysUserDao {
	
}

