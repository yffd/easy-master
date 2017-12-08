package com.yffd.easy.admin.pms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yffd.easy.admin.pms.dao.PmsUserDao;
import com.yffd.easy.admin.pms.model.PmsUser;
import com.yffd.easy.common.support.dao.impl.BaseDaoImpl;

/**
 * @Description  系统模块：用户管理.
 * @Date		 2017年9月15日 上午11:19:22 <br/>
 * @author       zhangST
 * @version		 1.0
 * @param <T>
 * @since		 JDK 1.7+
 * @see 	 
 */
@Repository("pmsUserDao")
public class PmsUserDaoImpl extends BaseDaoImpl<PmsUser> implements PmsUserDao {

	@Override
	public List<String> findRoles(String userCode) {
		return this.getSqlSession().selectList(this.getStatement("findRoles"), userCode);
	}

	@Override
	public List<String> findResources(String userCode) {
		return this.getSqlSession().selectList(this.getStatement("findResources"), userCode);
	}
	
}

