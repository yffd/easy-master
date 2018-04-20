package com.yffd.easy.uupm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yffd.easy.framework.core.common.mapper.ICommonMapper;
import com.yffd.easy.framework.core.common.service.CommonServiceAbstract;
import com.yffd.easy.framework.core.exception.BizException;
import com.yffd.easy.uupm.mapper.IUupmTenantMapper;
import com.yffd.easy.uupm.pojo.entity.UupmAccountEntity;
import com.yffd.easy.uupm.pojo.entity.UupmTenantEntity;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年04月04日 17时31分10秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class UupmTenantService extends CommonServiceAbstract<UupmTenantEntity> {

	@Autowired
	private UupmAccountService uupmAccountService;
	
	@Autowired
	private IUupmTenantMapper uupmTenantMapper;
	
	@Override
	public ICommonMapper<UupmTenantEntity> getMapper() {
		return this.uupmTenantMapper;
	}

	@Override
	public Class<?> getMapperClass() {
		return IUupmTenantMapper.class;
	}
	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int addTenantWithAccount(UupmTenantEntity model) {
		if(null==model) throw BizException.BIZ_PARAMS_IS_EMPTY();
		int num = this.addOne(model);
		// 生成账号
		UupmAccountEntity account = new UupmAccountEntity();
		account.setAccountId(model.getTenantCode());
		account.setAccountPwd("123456");
		account.setAccountStatus("active");
		account.setAccountType("default");
		this.uupmAccountService.addOne(account);
		return num;
	}

}
