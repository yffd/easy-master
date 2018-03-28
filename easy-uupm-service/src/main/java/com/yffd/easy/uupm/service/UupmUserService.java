package com.yffd.easy.uupm.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yffd.easy.common.core.exception.EasyBizException;
import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.framework.base.dao.GenericDao;
import com.yffd.easy.framework.base.service.GenericService;
import com.yffd.easy.framework.domain.LoginInfo;
import com.yffd.easy.uupm.api.model.UupmAccountModel;
import com.yffd.easy.uupm.api.model.UupmUserModel;
import com.yffd.easy.uupm.dao.UupmUserDao;

/**
 * @Description	简单描述该类的功能（可选）.
 * @Date		2018年03月23日 11时01分34秒 <br/>
 * @author		ZhangST
 * @version		1.0
 * @since		JDK 1.7+
 * @see
 */

@Service
public class UupmUserService extends GenericService<UupmUserModel> {

	@Autowired
	private UupmUserDao uupmUserDao;
	@Autowired
	private UupmAccountService uupmAccountService;
	
	@Override
	public GenericDao<UupmUserModel> getBindDao() {
		return this.uupmUserDao;
	}

	public PageResult<Map<String, Object>> findPageForUserInfo(Map<String, Object> paramMap, PageParam pageParam) {
		return this.uupmUserDao.selectUserListPage(paramMap, pageParam);
	}
	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public void addUserWithAccount(UupmUserModel model, LoginInfo loginInfo) {
		if(null==model) throw EasyBizException.BIZ_PARAMS_NULL();
		this.setAddDefault(model, loginInfo);
		this.uupmUserDao.insertOne(model);
		// 生成账号
		UupmAccountModel account = new UupmAccountModel();
		account.setAccountId(model.getUserCode());
		account.setAccountPwd("123456");
		account.setAccountStatus("A");
		account.setAccountType("default");
		this.uupmAccountService.addOne(account, loginInfo);
	}
}
