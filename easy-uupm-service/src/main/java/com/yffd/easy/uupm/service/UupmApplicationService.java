package com.yffd.easy.uupm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.framework.base.dao.GenericDao;
import com.yffd.easy.framework.base.service.GenericService;
import com.yffd.easy.uupm.dao.UupmApplicationDao;
import com.yffd.easy.uupm.api.model.UupmApplicationModel;

/**
 * @Description	简单描述该类的功能（可选）.
 * @Date		2018年03月08日 14时03分16秒 <br/>
 * @author		ZhangST
 * @version		1.0
 * @since		JDK 1.7+
 * @see
 */

@Service
public class UupmApplicationService extends GenericService<UupmApplicationModel> {

	@Autowired
	private UupmApplicationDao uupmApplicationDao;
	
	@Override
	public GenericDao<UupmApplicationModel> getBindDao() {
		return this.uupmApplicationDao;
	}

}
