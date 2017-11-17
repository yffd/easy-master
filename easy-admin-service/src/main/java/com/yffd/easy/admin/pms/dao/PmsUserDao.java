package com.yffd.easy.admin.pms.dao;

import java.util.List;

import org.yffd.easy.common.ssm.dao.IBaseDao;

import com.yffd.easy.admin.pms.model.PmsUser;

/**
 * @Description  系统模块：用户管理.
 * @Date		 2017年9月15日 上午11:19:22 <br/>
 * @author       zhangST
 * @version		 1.0
 * @param <T>
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface PmsUserDao extends IBaseDao<PmsUser> {
	
	/**
	 * 根据用户编号查询所属角色
	 * @Date	2017年11月2日 下午3:30:35 <br/>
	 * @author  zhangST
	 * @param userCode
	 * @return
	 */
	public List<String> findRoles(String userCode);

	/**
	 * 根据用户编号查询所属资源
	 * @Date	2017年11月2日 下午3:31:02 <br/>
	 * @author  zhangST
	 * @param userCode
	 * @return
	 */
    public List<String> findResources(String userCode);
    
}

