package com.yffd.easy.uupm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yffd.easy.common.core.exception.EasyBizException;
import com.yffd.easy.framework.common.mapper.ICommonMapper;
import com.yffd.easy.framework.common.service.CommonServiceAbstract;
import com.yffd.easy.framework.domain.LoginInfo;
import com.yffd.easy.uupm.api.model.UupmAccountModel;
import com.yffd.easy.uupm.api.model.UupmTenantModel;
import com.yffd.easy.uupm.mapper.IUupmTenantMapper;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年04月04日 17时31分10秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class UupmTenantService extends CommonServiceAbstract<UupmTenantModel> {

	@Autowired
	private UupmAccountService uupmAccountService;
	
	@Autowired
	private IUupmTenantMapper uupmTenantMapper;
	
	@Override
	public ICommonMapper<UupmTenantModel> getMapper() {
		return this.uupmTenantMapper;
	}

	@Override
	public Class<?> getMapperClass() {
		return IUupmTenantMapper.class;
	}
	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int addTenantWithAccount(UupmTenantModel model, LoginInfo loginInfo) {
		if(null==model) throw EasyBizException.BIZ_PARAMS_NULL();
		int num = this.addOne(model, loginInfo);
		// 生成账号
		UupmAccountModel account = new UupmAccountModel();
		account.setAccountId(model.getTenantCode());
		account.setAccountPwd("123456");
		account.setAccountStatus("active");
		account.setAccountType("default");
		this.uupmAccountService.addOne(account, loginInfo);
		return num;
	}

}
