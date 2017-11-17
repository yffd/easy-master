package com.yffd.easy.admin.pms.service;

import com.yffd.easy.admin.pms.model.PmsAccount;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月13日 上午10:44:41 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface PmsAccountService extends PmsBaseService<PmsAccount>{

	PmsAccount findAccount(String accountName);

}

