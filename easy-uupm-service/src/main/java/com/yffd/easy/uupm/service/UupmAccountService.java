package com.yffd.easy.uupm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.framework.base.dao.GenericDao;
import com.yffd.easy.framework.base.service.GenericService;
import com.yffd.easy.uupm.api.model.UupmAccountModel;
import com.yffd.easy.uupm.dao.UupmAccountDao;

/**
 * @Description	简单描述该类的功能（可选）.
 * @Date		2018年03月23日 17时37分15秒 <br/>
 * @author		ZhangST
 * @version		1.0
 * @since		JDK 1.7+
 * @see
 */

@Service
public class UupmAccountService extends GenericService<UupmAccountModel> {

	@Autowired
	private UupmAccountDao uupmAccountDao;
	
	@Override
	public GenericDao<UupmAccountModel> getBindDao() {
		return this.uupmAccountDao;
	}

}
