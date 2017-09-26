package org.yffd.easy.app.permission.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yffd.easy.app.permission.dao.PmsUserDao;
import org.yffd.easy.app.permission.entity.PmsUser;
import org.yffd.easy.app.permission.service.PmsUserService;
import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月18日 下午3:56:15 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("pmsUserService")
public class PmsUserServiceImpl implements PmsUserService {
	
	@Autowired
	private PmsUserDao pmsUserDao;
	
	@Override
	public PageResult<PmsUser> listPage(PageParam pageParam, PmsUser pmsUser) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", pmsUser.getUserName());
		paramMap.put("userCode", pmsUser.getUserCode());
		paramMap.put("userPwd", pmsUser.getUserPwd());
		paramMap.put("realName", pmsUser.getRealName());
		paramMap.put("mobileNo", pmsUser.getMobileNo());
		return this.pmsUserDao.selectPage(pageParam, paramMap);
	}

	@Override
	public PmsUser findByName(String userName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userName", userName);
		return this.pmsUserDao.selectBy(paramMap);
	}

	@Override
	public PmsUser findByCode(String userCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userCode", userCode);
		return this.pmsUserDao.selectBy(paramMap);
	}

	@Override
	public void deleteByCode(String userCode) {
		this.pmsUserDao.deleteByPK(userCode);
	}

	@Override
	public void update(PmsUser pmsUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updatePwd(String userCode, String userPwd) {
		PmsUser pmsUser = this.pmsUserDao.selectByPK(userCode);
		pmsUser.setUserPwd(userPwd);
		this.pmsUserDao.updateByPK(pmsUser);

	}

	@Override
	public void add(PmsUser pmsUser) {
		this.pmsUserDao.insert(pmsUser);
	}

}

