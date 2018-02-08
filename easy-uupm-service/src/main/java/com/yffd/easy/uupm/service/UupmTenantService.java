package com.yffd.easy.uupm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.framework.dao.IGenericDao;
import com.yffd.easy.framework.service.GenericService;
import com.yffd.easy.uupm.dao.UupmTenantDao;

/**
 * @Description	简单描述该类的功能（可选）.
 * @Date		2018年02月08日 16时48分38秒 <br/>
 * @author		ZhangST
 * @version		1.0
 * @since		JDK 1.7+
 * @see
 */

@Service
public class UupmTenantService extends GenericService {

	@Autowired
	private UupmTenantDao uupmTenantDao;
	
	@Override
	public IGenericDao getBindDao() {
		return this.uupmTenantDao;
	}

}
