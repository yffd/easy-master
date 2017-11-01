package com.yffd.easy.pmi.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.yffd.easy.common.ssm.dao.impl.BaseDaoImpl;

import com.yffd.easy.pmi.dao.PmiResourceDao;
import com.yffd.easy.pmi.model.PmiResource;

/**
 * @Description  系统模块：功能管理.
 * @Date		 2017年10月18日 下午4:51:28 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Repository("pmiResourceDao")
public class PmiResourceDaoImpl extends BaseDaoImpl<PmiResource> implements PmiResourceDao {
	
	@Override
	public List<PmiResource> findByRole(String roleCode) {
		return this.getSqlSession().selectList("findByRole", roleCode);
	}

}

