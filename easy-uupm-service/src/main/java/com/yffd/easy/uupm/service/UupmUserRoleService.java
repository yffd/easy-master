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
import com.yffd.easy.uupm.api.model.UupmUserRoleModel;
import com.yffd.easy.uupm.dao.UupmUserRoleDao;

/**
 * @Description	简单描述该类的功能（可选）.
 * @Date		2018年03月21日 09时37分09秒 <br/>
 * @author		ZhangST
 * @version		1.0
 * @since		JDK 1.7+
 * @see
 */

@Service
public class UupmUserRoleService extends GenericService<UupmUserRoleModel> {

	@Autowired
	private UupmUserRoleDao uupmUserRoleDao;
	
	@Override
	public GenericDao<UupmUserRoleModel> getBindDao() {
		return this.uupmUserRoleDao;
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void saveUserRole(String userCode, List<UupmUserRoleModel> list, LoginInfo loginInfo) {
		this.delByUserCode(userCode, loginInfo);
		this.addBatch(list, loginInfo);
	}
	
	public void delByUserCode(String userCode, LoginInfo loginInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userCode", userCode);
		this.uupmUserRoleDao.deleteBy(paramMap);
	}
	
}
