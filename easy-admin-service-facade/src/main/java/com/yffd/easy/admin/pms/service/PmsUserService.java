package com.yffd.easy.admin.pms.service;

import java.util.Set;

import com.yffd.easy.admin.pms.model.PmsUser;
import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月13日 上午10:44:41 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface PmsUserService {

	PageResult<PmsUser> findList(PmsUser user, PageParam pageParam);
	
	void add(PmsUser user);
	
	void editByCode(PmsUser user);
	
	void delByCode(String userCode);
	
	/**
	 * 根据用户编号查询用户对象
	 * @Date	2017年11月2日 下午3:30:10 <br/>
	 * @author  zhangST
	 * @param userCode
	 * @return
	 */
	PmsUser findUser(String userCode);
	
	/**
	 * 根据用户编号查询所属角色
	 * @Date	2017年11月2日 下午3:30:35 <br/>
	 * @author  zhangST
	 * @param userCode
	 * @return
	 */
	public Set<String> findRoles(String userCode);

	/**
	 * 根据用户编号查询所属资源
	 * @Date	2017年11月2日 下午3:31:02 <br/>
	 * @author  zhangST
	 * @param userCode
	 * @return
	 */
    public Set<String> findResources(String userCode);

}

