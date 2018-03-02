package com.yffd.easy.uupm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.framework.base.dao.GenericDao;
import com.yffd.easy.framework.base.service.GenericService;
import com.yffd.easy.uupm.dao.UupmTenantDao;
import com.yffd.easy.uupm.api.model.UupmTenantModel;

/**
 * @Description	简单描述该类的功能（可选）.
 * @Date		2018年03月02日 16时10分57秒 <br/>
 * @author		ZhangST
 * @version		1.0
 * @since		JDK 1.7+
 * @see
 */

@Service
public class UupmTenantService extends GenericService<UupmTenantModel> {

	@Autowired
	private UupmTenantDao uupmTenantDao;
	
	@Override
	public GenericDao<UupmTenantModel> getBindDao() {
		return this.uupmTenantDao;
	}

}
