package com.yffd.easy.uupm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.framework.base.dao.GenericDao;
import com.yffd.easy.framework.base.service.GenericService;
import com.yffd.easy.uupm.api.model.UupmTenantModel;
import com.yffd.easy.uupm.dao.UupmTenantDao;

/**
 * @Description	简单描述该类的功能（可选）.
 * @Date		2018年02月27日 13时51分08秒 <br/>
 * @author		ZhangST
 * @version		1.0
 * @since		JDK 1.7+
 * @see
 */

@Service
public class UupmTenantService2 extends GenericService<UupmTenantModel> {

	@Autowired
	private UupmTenantDao uupmTenantDao;
	
	@Override
	public GenericDao<UupmTenantModel> getBindDao() {
		return this.uupmTenantDao;
	}

}
