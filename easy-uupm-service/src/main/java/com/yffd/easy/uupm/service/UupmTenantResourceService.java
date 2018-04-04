package com.yffd.easy.uupm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.framework.common.mapper.ICommonMapper;
import com.yffd.easy.framework.common.service.CommonServiceAbstract;
import com.yffd.easy.uupm.mapper.IUupmTenantResourceMapper;
import com.yffd.easy.uupm.api.model.UupmTenantResourceModel;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年04月04日 17时30分01秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class UupmTenantResourceService extends CommonServiceAbstract<UupmTenantResourceModel> {

	@Autowired
	private IUupmTenantResourceMapper uupmTenantResourceMapper;
	
	@Override
	public ICommonMapper<UupmTenantResourceModel> getMapper() {
		return this.uupmTenantResourceMapper;
	}

	@Override
	public Class<?> getMapperClass() {
		return IUupmTenantResourceMapper.class;
	}

}
