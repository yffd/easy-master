package com.yffd.easy.admin.pms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;
import org.yffd.easy.common.core.util.ValidUtils;

import com.yffd.easy.admin.pms.dao.PmsAccountDao;
import com.yffd.easy.admin.pms.model.PmsAccount;
import com.yffd.easy.admin.pms.service.PmsAccountService;
import com.yffd.easy.admin.pms.service.PmsUserService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月13日 下午2:16:01 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("pmsAccountService")
public class PmsAccountServiceImpl implements PmsAccountService {
	@Autowired
	private PmsAccountDao pmsAccountDao;
//	@Autowired
//	private RedisTemplate redisTemplate;
	
	@Override
	public PmsAccount findAccount(String accountName) {
		return this.findByCode(accountName);
	}

	@Override
	public PageResult<PmsAccount> findPage(PmsAccount model, PageParam pageParam) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(!ValidUtils.isNull(model)) {
			paramMap.put("accountName", model.getAccountName());
			paramMap.put("accountPwd", model.getAccountPwd());
			paramMap.put("status", model.getStatus());
			paramMap.put("type", model.getType());
		}
		return this.pmsAccountDao.selectPage(pageParam, paramMap);
	}

	@Override
	public List<PmsAccount> findList(PmsAccount model) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(!ValidUtils.isNull(model)) {
			paramMap.put("accountName", model.getAccountName());
			paramMap.put("accountPwd", model.getAccountPwd());
			paramMap.put("status", model.getStatus());
			paramMap.put("type", model.getType());
		}
		return this.pmsAccountDao.selectListBy(paramMap);
	}

	@Override
	public List<PmsAccount> findAll() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return this.pmsAccountDao.selectListBy(paramMap);
	}

	@Override
	public PmsAccount findByCode(String code) {
		if(ValidUtils.isBlank(code)) return null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("accountName", code);
		return this.pmsAccountDao.selectOne(paramMap);
	}

	@Override
	public void save(PmsAccount model) {
		this.pmsAccountDao.insert(model);
	}

	@Override
	public void update(PmsAccount model) {
		this.pmsAccountDao.updateByPK(model);
	}

	@Override
	public void delete(String code) {
		if(ValidUtils.isBlank(code)) return;
		this.pmsAccountDao.deleteByPK(code);
	}

}

