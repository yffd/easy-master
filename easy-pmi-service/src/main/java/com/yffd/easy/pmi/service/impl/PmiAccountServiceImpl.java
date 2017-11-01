package com.yffd.easy.pmi.service.impl;

import org.springframework.stereotype.Service;
import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;

import com.yffd.easy.pmi.model.PmiAccount;
import com.yffd.easy.pmi.service.PmiAccountService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月31日 下午2:52:08 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("pmiAccountService")
public class PmiAccountServiceImpl implements PmiAccountService {

	@Override
	public PageResult<PmiAccount> findList(PmiAccount account, PageParam pageParam) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PmiAccount findByCode(String account, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}

