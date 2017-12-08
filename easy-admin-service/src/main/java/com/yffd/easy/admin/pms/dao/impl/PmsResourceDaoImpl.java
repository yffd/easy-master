package com.yffd.easy.admin.pms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yffd.easy.admin.pms.dao.PmsResourceDao;
import com.yffd.easy.admin.pms.model.PmsResource;
import com.yffd.easy.common.support.dao.impl.BaseDaoImpl;

/**
 * @Description  系统模块：功能管理.
 * @Date		 2017年10月18日 下午4:51:28 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Repository("pmsResourceDao")
public class PmsResourceDaoImpl extends BaseDaoImpl<PmsResource> implements PmsResourceDao {
	
	@Override
	public List<PmsResource> findByRole(String roleCode) {
		return this.getSqlSession().selectList(getStatement("findByRole"), roleCode);
	}

	@Override
	public List<PmsResource> findByUser(String userCode) {
		return this.getSqlSession().selectList(getStatement("findByUser"), userCode);
	}

}

