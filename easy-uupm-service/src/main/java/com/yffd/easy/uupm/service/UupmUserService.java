package com.yffd.easy.uupm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.framework.base.dao.GenericDao;
import com.yffd.easy.framework.base.service.GenericService;
import com.yffd.easy.uupm.dao.UupmUserDao;
import com.yffd.easy.uupm.api.model.UupmUserModel;

/**
 * @Description	简单描述该类的功能（可选）.
 * @Date		2018年03月07日 16时15分23秒 <br/>
 * @author		ZhangST
 * @version		1.0
 * @since		JDK 1.7+
 * @see
 */

@Service
public class UupmUserService extends GenericService<UupmUserModel> {

	@Autowired
	private UupmUserDao uupmUserDao;
	
	@Override
	public GenericDao<UupmUserModel> getBindDao() {
		return this.uupmUserDao;
	}

}
