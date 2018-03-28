package com.yffd.easy.uupm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.framework.base.dao.GenericDao;
import com.yffd.easy.framework.base.service.GenericService;
import com.yffd.easy.uupm.api.model.UupmOrganizationModel;
import com.yffd.easy.uupm.dao.UupmOrganizationDao;

/**
 * @Description	简单描述该类的功能（可选）.
 * @Date		2018年03月19日 10时07分47秒 <br/>
 * @author		ZhangST
 * @version		1.0
 * @since		JDK 1.7+
 * @see
 */

@Service
public class UupmOrganizationService extends GenericService<UupmOrganizationModel> {

	@Autowired
	private UupmOrganizationDao uupmOrganizationDao;
	
	@Override
	public GenericDao<UupmOrganizationModel> getBindDao() {
		return this.uupmOrganizationDao;
	}

}
