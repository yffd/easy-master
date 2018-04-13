package com.yffd.easy.uupm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.framework.core.common.mapper.ICommonMapper;
import com.yffd.easy.framework.core.common.service.CommonServiceAbstract;
import com.yffd.easy.framework.core.exception.BizException;
import com.yffd.easy.framework.domain.LoginInfo;
import com.yffd.easy.uupm.api.model.UupmAccountModel;
import com.yffd.easy.uupm.api.model.UupmUserModel;
import com.yffd.easy.uupm.mapper.IUupmUserMapper;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年04月05日 17时24分03秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class UupmUserService extends CommonServiceAbstract<UupmUserModel> {

	@Autowired
	private UupmAccountService uupmAccountService;
	
	@Autowired
	private IUupmUserMapper uupmUserMapper;
	
	@Override
	public ICommonMapper<UupmUserModel> getMapper() {
		return this.uupmUserMapper;
	}

	@Override
	public Class<?> getMapperClass() {
		return IUupmUserMapper.class;
	}

	public PageResult<Map<String, Object>> findUserInfo(Map<String, Object> paramMap, PageParam pageParam, LoginInfo loginInfo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("map", paramMap);
		params.put("page", pageParam);
		return this.selectPage(params, pageParam, "selectUserInfo", "selectUserInfoCount", true);
	}
	
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public int addUserWithAccount(UupmUserModel model, LoginInfo loginInfo) {
		if(null==model) throw BizException.BIZ_PARAMS_IS_EMPTY();
		int num = this.addOne(model, loginInfo);
		// 生成账号
		UupmAccountModel account = new UupmAccountModel();
		account.setAccountId(model.getUserCode());
		account.setAccountPwd("123456");
		account.setAccountStatus("active");
		account.setAccountType("default");
		this.uupmAccountService.addOne(account, loginInfo);
		return num;
	}
	
	private List<Map<String, Object>> findUserInfo(String tenantCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tenantCode", tenantCode);
		return this.selectListBy("selectUserInfo", paramMap, true);
	}
	
	private long findUserInfoCount(String tenantCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tenantCode", tenantCode);
		return this.selectOneBy("selectUserInfoCount", paramMap, true);
	}
}
