package org.yffd.easy.app.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yffd.easy.app.system.dao.SysFunctionDao;
import org.yffd.easy.app.system.model.SysFunction;
import org.yffd.easy.app.system.model.SysUser;
import org.yffd.easy.app.system.service.SysFunctionService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月18日 下午5:17:26 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("sysFunctionService")
public class SysFunctionServiceImpl implements SysFunctionService {
	@Autowired
	private SysFunctionDao sysFunctionDao;
	
	@Override
	public List<SysFunction> findAll() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return this.sysFunctionDao.selectListBy(paramMap);
	}

	@Override
	public List<SysFunction> findAllMenu() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("type", "M");
		return this.sysFunctionDao.selectListBy(paramMap);
	}

	@Override
	public List<SysFunction> findMenuByUser(SysUser user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysFunction> findByParentCode(String parentCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentCode", parentCode);
		return this.sysFunctionDao.selectListBy(paramMap);
	}

	@Override
	public SysFunction findByCode(String funcCode) {
		return this.sysFunctionDao.selectByPK(funcCode);
	}

	@Override
	public void add(SysFunction model) {
		this.sysFunctionDao.insert(model);
	}

	@Override
	public void editByCode(SysFunction model) {
		this.sysFunctionDao.updateByPK(model);
	}

	@Override
	public void delByCode(String funcCode) {
		this.sysFunctionDao.deleteByPK(funcCode);
	}

	@Override
	public List<SysFunction> findByRole(String roleCode) {
		return this.sysFunctionDao.findByRole(roleCode);
	}
	
}

