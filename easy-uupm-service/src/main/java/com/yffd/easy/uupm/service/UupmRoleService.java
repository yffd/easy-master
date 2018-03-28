package com.yffd.easy.uupm.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yffd.easy.framework.base.dao.GenericDao;
import com.yffd.easy.framework.base.service.GenericService;
import com.yffd.easy.framework.domain.LoginInfo;
import com.yffd.easy.uupm.api.model.UupmRoleModel;
import com.yffd.easy.uupm.dao.UupmRoleDao;

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
	@Autowired
	private UupmRoleResourceService uupmReRoleResourceService;
	
	@Override
	public GenericDao<UupmRoleModel> getBindDao() {
		return this.uupmRoleDao;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void delByRoleCode(String roleCode, LoginInfo loginInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleCode", roleCode);
		this.uupmRoleDao.deleteBy(paramMap);
		this.uupmReRoleResourceService.delByRoleCode(roleCode, loginInfo);//删除关联的权限
	}
}
