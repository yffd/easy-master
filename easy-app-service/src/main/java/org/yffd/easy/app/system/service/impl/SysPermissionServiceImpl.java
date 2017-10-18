package org.yffd.easy.app.system.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yffd.easy.app.system.dao.SysPermissionDao;
import org.yffd.easy.app.system.model.SysPermission;
import org.yffd.easy.app.system.service.SysPermissionService;
import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月12日 下午1:56:41 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("sysPermissionService")
public class SysPermissionServiceImpl implements SysPermissionService {
	@Autowired
	private SysPermissionDao sysPermissionDao;
	
	@Override
	public PageResult<SysPermission> findList(String name, String code, PageParam pageParam) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", name);
		paramMap.put("code", code);
		return this.sysPermissionDao.selectPage(pageParam, paramMap);
	}

	@Override
	public SysPermission findByCode(String code) {
		SysPermission pms = this.sysPermissionDao.selectByPK(code);
		return pms;
	}
	
	@Override
	public void add(SysPermission sysPermission) {
		this.sysPermissionDao.insert(sysPermission);
	}

	@Override
	public void editByCode(SysPermission sysPermission) {
		this.sysPermissionDao.updateByPK(sysPermission);
	}

	@Override
	public void delByCode(String code) {
		this.sysPermissionDao.deleteByPK(code);
	}

}

