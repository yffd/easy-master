package org.yffd.easy.app.system.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yffd.easy.app.system.dao.SysUserDao;
import org.yffd.easy.app.system.model.SysUserModel;
import org.yffd.easy.app.system.service.SysUserService;
import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;
import org.yffd.easy.common.core.util.ValidUtils;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月20日 上午10:17:58 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao sysUserDao;
	
	@Override
	public PageResult<SysUserModel> findList(SysUserModel sysUser, PageParam pageParam) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(!ValidUtils.isEmpty(sysUser)) {
			paramMap.put("userCode", sysUser.getUserCode());
			paramMap.put("userName", sysUser.getUserName());
			paramMap.put("account", sysUser.getAccount());
			paramMap.put("tel", sysUser.getTel());
			paramMap.put("orgCode", sysUser.getOrgCode());
		}
		return this.sysUserDao.selectPage(pageParam, paramMap);
	}

	@Override
	public SysUserModel findByCode(String userCode) {
		if(ValidUtils.isBlank(userCode)) return null;
		return this.sysUserDao.selectByPK(userCode);
	}

	@Override
	public void add(SysUserModel sysUserModel) {
		this.sysUserDao.insert(sysUserModel);
	}

	@Override
	public void editByCode(SysUserModel sysUserModel) {
		this.sysUserDao.updateByPK(sysUserModel);
	}

	@Override
	public void delByCode(String userCode) {
		this.sysUserDao.deleteByPK(userCode);
	}

}

