package com.yffd.easy.common.shiro.service;

import java.util.Set;

import com.yffd.easy.common.shiro.model.AccountInfo;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月7日 下午6:04:19 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface AccountService {

	/**
	 * 查询账户信息
	 * @Date	2017年11月7日 下午6:22:13 <br/>
	 * @author  zhangST
	 * @param accountName
	 * @return
	 */
	public AccountInfo findAccount(String accountName);
	
	/**
	 * 获取该账户所拥有的角色
	 * @Date	2017年11月7日 下午6:22:42 <br/>
	 * @author  zhangST
	 * @param accountName
	 * @return
	 */
    public Set<String> findRoles(String accountName);
    
    /**
     * 获取该账户所拥有的权限
     * @Date	2017年11月7日 下午6:23:08 <br/>
     * @author  zhangST
     * @param accountName
     * @return
     */
    public Set<String> findPermissions(String accountName);
}

