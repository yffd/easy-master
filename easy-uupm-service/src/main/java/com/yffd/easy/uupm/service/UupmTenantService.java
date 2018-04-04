package com.yffd.easy.uupm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.framework.common.mapper.ICommonMapper;
import com.yffd.easy.framework.common.service.CommonServiceAbstract;
import com.yffd.easy.uupm.mapper.IUupmTenantMapper;
import com.yffd.easy.uupm.api.model.UupmTenantModel;

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
	private IUupmTenantMapper uupmTenantMapper;
	
	@Override
	public ICommonMapper<UupmTenantModel> getMapper() {
		return this.uupmTenantMapper;
	}

	@Override
	public Class<?> getMapperClass() {
		return IUupmTenantMapper.class;
	}

}
