package com.yffd.easy.admin.pms.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.admin.pms.dao.PmsUserDao;
import com.yffd.easy.admin.pms.model.PmsUser;
import com.yffd.easy.admin.pms.service.PmsUserService;
import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.common.core.util.ValidUtils;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月20日 上午10:17:58 <br/>
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
	public PageResult<PmsUser> findList(PmsUser user, PageParam pageParam) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(!ValidUtils.isEmpty(user)) {
			paramMap.put("userCode", user.getUserCode());
			paramMap.put("userName", user.getUserName());
			paramMap.put("userState", user.getUserStatus());
			paramMap.put("tel", user.getTel());
		}
		return this.pmsUserDao.selectPage(pageParam, paramMap);
	}

	@Override
	public void add(PmsUser user) {
		if(ValidUtils.isNull(user) || ValidUtils.isBlank(user.getUserCode()) || ValidUtils.isBlank(user.getUserName())) {
			return;
		}
		this.pmsUserDao.insert(user);
	}

	@Override
	public void editByCode(PmsUser user) {
		this.pmsUserDao.updateByPK(user);
	}

	@Override
	public void delByCode(String userCode) {
		if(ValidUtils.isBlank(userCode)) return;
		this.pmsUserDao.deleteByPK(userCode);
	}
	
	@Override
	public PmsUser findUser(String userCode) {
		if(ValidUtils.isBlank(userCode)) return null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userCode", userCode);
		return this.pmsUserDao.selectOne(paramMap);
	}

	@Override
	public Set<String> findRoles(String userCode) {
		List<String> list = this.pmsUserDao.findRoles(userCode);
		return new HashSet<String>(list);
	}

	@Override
	public Set<String> findResources(String userCode) {
		List<String> list = this.pmsUserDao.findResources(userCode);
		return new HashSet<String>(list);
	}
	
}

