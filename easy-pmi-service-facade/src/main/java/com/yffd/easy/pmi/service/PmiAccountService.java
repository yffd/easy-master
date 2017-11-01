package com.yffd.easy.pmi.service;

import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;

import com.yffd.easy.pmi.model.PmiAccount;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月13日 上午10:44:41 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface PmiAccountService {

	PageResult<PmiAccount> findList(PmiAccount account, PageParam pageParam);
	
	PmiAccount findByCode(String account, String password);
	
}

