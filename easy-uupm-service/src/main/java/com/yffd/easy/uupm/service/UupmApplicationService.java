package com.yffd.easy.uupm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.framework.core.common.mapper.ICommonMapper;
import com.yffd.easy.framework.core.common.service.CommonServiceAbstract;
import com.yffd.easy.framework.core.exception.BizException;
import com.yffd.easy.framework.domain.LoginInfo;
import com.yffd.easy.uupm.api.model.UupmApplicationModel;
import com.yffd.easy.uupm.mapper.IUupmApplicationMapper;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年04月06日 14时08分50秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class UupmApplicationService extends CommonServiceAbstract<UupmApplicationModel> {

	@Autowired
	private IUupmApplicationMapper uupmApplicationMapper;
	
	@Override
	public ICommonMapper<UupmApplicationModel> getMapper() {
		return this.uupmApplicationMapper;
	}

	@Override
	public Class<?> getMapperClass() {
		return IUupmApplicationMapper.class;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int saveAppCfg(UupmApplicationModel model, LoginInfo loginInfo) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getTenantCode())) throw BizException.BIZ_TENANT_IS_EMPTY();
		this.deleteBy("appCode", model.getAppCode());
		return this.addOne(model, loginInfo);
	}
}
