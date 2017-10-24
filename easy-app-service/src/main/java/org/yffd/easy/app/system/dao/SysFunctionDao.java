package org.yffd.easy.app.system.dao;

import java.util.List;

import org.yffd.easy.app.system.model.SysFunction;
import org.yffd.easy.common.ssm.dao.IBaseDao;

/**
 * @Description  系统模块：功能管理.
 * @Date		 2017年9月15日 上午11:19:22 <br/>
 * @author       zhangST
 * @version		 1.0
 * @param <T>
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface SysFunctionDao extends IBaseDao<SysFunction> {
	
	List<SysFunction> findByRole(String roleCode);
}

