package com.yffd.easy.uupm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.framework.base.dao.GenericDao;
import com.yffd.easy.framework.base.service.GenericService;
import com.yffd.easy.uupm.api.model.UupmResourceModel;
import com.yffd.easy.uupm.dao.UupmResourceDao;

/**
 * @Description	简单描述该类的功能（可选）.
 * @Date		2018年03月08日 15时17分17秒 <br/>
 * @author		ZhangST
 * @version		1.0
 * @since		JDK 1.7+
 * @see
 */

@Service
public class UupmResourceService extends GenericService<UupmResourceModel> {

	@Autowired
	private UupmResourceDao uupmResourceDao;
	
	@Override
	public GenericDao<UupmResourceModel> getBindDao() {
		return this.uupmResourceDao;
	}
	
	public List<Map<String, Object>> findResWithApp() {
		return this.uupmResourceDao.selectResWithApp();
	}
	
}
