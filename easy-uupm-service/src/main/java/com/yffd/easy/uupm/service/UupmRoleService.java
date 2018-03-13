package com.yffd.easy.uupm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.framework.base.dao.GenericDao;
import com.yffd.easy.framework.base.service.GenericService;
import com.yffd.easy.uupm.dao.UupmRoleDao;
import com.yffd.easy.uupm.api.model.UupmRoleModel;

/**
 * @Description	简单描述该类的功能（可选）.
 * @Date		2018年03月12日 16时06分18秒 <br/>
 * @author		ZhangST
 * @version		1.0
 * @since		JDK 1.7+
 * @see
 */

@Service
public class UupmRoleService extends GenericService<UupmRoleModel> {

	@Autowired
	private UupmRoleDao uupmRoleDao;
	
	@Override
	public GenericDao<UupmRoleModel> getBindDao() {
		return this.uupmRoleDao;
	}

}
