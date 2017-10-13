package org.yffd.easy.app.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yffd.easy.app.system.dao.SysMenuDao;
import org.yffd.easy.app.system.model.SysMenu;
import org.yffd.easy.app.system.service.SysMenuService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月13日 上午10:55:16 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuDao sysMenuDao;

	@Override
	public List<SysMenu> findAll() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return this.sysMenuDao.selectListBy(paramMap);
	}

	@Override
	public SysMenu findByCode(String code) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("code", code);
		return this.sysMenuDao.selectBy(paramMap);
	}

	@Override
	public List<SysMenu> findByParentCode(String parentCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentCode", parentCode);
		return this.sysMenuDao.selectListBy(paramMap);
	}

	@Override
	public void add(SysMenu sysFunction) {
		this.sysMenuDao.insert(sysFunction);
	}

	@Override
	public void editByCode(SysMenu sysFunction) {
		this.sysMenuDao.updateByPK(sysFunction);
	}

	@Override
	public void delByCode(String code) {
		this.sysMenuDao.deleteByPK(code);
	}

}

