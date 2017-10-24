package org.yffd.easy.app.system.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.yffd.easy.app.system.dao.SysFunctionDao;
import org.yffd.easy.app.system.model.SysFunction;
import org.yffd.easy.common.ssm.dao.impl.BaseDaoImpl;

/**
 * @Description  系统模块：功能管理.
 * @Date		 2017年10月18日 下午4:51:28 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Repository("sysFunctionDao")
public class SysFunctionDaoImpl extends BaseDaoImpl<SysFunction> implements SysFunctionDao {
	
	@Override
	public List<SysFunction> findByRole(String roleCode) {
		return this.getSqlSession().selectList("findByRole", roleCode);
	}
	
}

