package com.yffd.easy.uupm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yffd.easy.framework.base.dao.GenericDao;
import com.yffd.easy.framework.base.service.GenericService;
import com.yffd.easy.framework.domain.LoginInfo;
import com.yffd.easy.uupm.api.model.UupmRoleResourceModel;
import com.yffd.easy.uupm.dao.UupmRoleResourceDao;

/**
 * @Description	简单描述该类的功能（可选）.
 * @Date		2018年03月15日 17时35分20秒 <br/>
 * @author		ZhangST
 * @version		1.0
 * @since		JDK 1.7+
 * @see
 */

@Service
public class UupmRoleResourceService extends GenericService<UupmRoleResourceModel> {

	@Autowired
	private UupmRoleResourceDao uupmRoleResourceDao;
	
	@Override
	public GenericDao<UupmRoleResourceModel> getBindDao() {
		return this.uupmRoleResourceDao;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void saveRoleResource(String roleCode, List<UupmRoleResourceModel> list, LoginInfo loginInfo) {
		this.delByRoleCode(roleCode, loginInfo);
		this.addBatch(list, loginInfo);
	}
	
	public void delByRoleCode(String roleCode, LoginInfo loginInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("roleCode", roleCode);
		this.uupmRoleResourceDao.deleteBy(paramMap);
	}
	
}
