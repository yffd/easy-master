package com.yffd.easy.pmi.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;
import org.yffd.easy.common.core.util.ValidUtils;

import com.yffd.easy.pmi.dao.PmiUserDao;
import com.yffd.easy.pmi.model.PmiUser;
import com.yffd.easy.pmi.service.PmiUserService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月20日 上午10:17:58 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("pmiUserService")
public class PmiUserServiceImpl implements PmiUserService {

	@Autowired
	private PmiUserDao pmiUserDao;
	
	@Override
	public PageResult<PmiUser> findList(PmiUser user, PageParam pageParam) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(!ValidUtils.isEmpty(user)) {
			paramMap.put("userCode", user.getUserCode());
			paramMap.put("userName", user.getUserName());
			paramMap.put("userState", user.getUserState());
			paramMap.put("tel", user.getTel());
		}
		return this.pmiUserDao.selectPage(pageParam, paramMap);
	}

	@Override
	public PmiUser findByCode(String userCode) {
		if(ValidUtils.isBlank(userCode)) return null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userCode", userCode);
		return this.pmiUserDao.selectOne(paramMap);
	}

	@Override
	public void add(PmiUser user) {
		this.pmiUserDao.insert(user);
	}

	@Override
	public void editByCode(PmiUser user) {
		this.pmiUserDao.updateByPK(user);
	}

	@Override
	public void delByCode(String userCode) {
		if(ValidUtils.isBlank(userCode)) return;
		this.pmiUserDao.deleteByPK(userCode);
	}

}

