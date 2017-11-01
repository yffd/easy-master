package com.yffd.easy.pmi.dao;

import java.util.List;

import org.yffd.easy.common.ssm.dao.IBaseDao;

import com.yffd.easy.pmi.model.PmiResource;

/**
 * @Description  系统模块：资源管理.
 * @Date		 2017年9月15日 上午11:19:22 <br/>
 * @author       zhangST
 * @version		 1.0
 * @param <T>	
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface PmiResourceDao extends IBaseDao<PmiResource> {
	
	List<PmiResource> findByRole(String roleCode);
	
}

