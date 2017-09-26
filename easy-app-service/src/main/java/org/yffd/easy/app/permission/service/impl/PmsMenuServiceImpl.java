package org.yffd.easy.app.permission.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yffd.easy.app.permission.dao.PmsMenuDao;
import org.yffd.easy.app.permission.entity.PmsMenu;
import org.yffd.easy.app.permission.service.PmsMenuService;
import org.yffd.easy.common.core.util.ValidUtils;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月13日 上午10:55:16 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("pmsMenuService")
public class PmsMenuServiceImpl implements PmsMenuService {
    @Autowired
    private PmsMenuDao pmsMenuDao;

	@Override
	public List<PmsMenu> findAll() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return this.pmsMenuDao.selectListBy(paramMap);
	}

	@Override
	public PmsMenu findByMenuCode(String menuCode) {
		if(ValidUtils.isEmpty(menuCode)) return null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menuCode", menuCode);
		return this.pmsMenuDao.selectBy(paramMap);
	}
	
	@Override
	public PmsMenu findMenuWithParent(String menuCode) {
		PmsMenu pmsMenu = this.findByMenuCode(menuCode);
		if(null==pmsMenu) return null;
		
		PmsMenu parentMenu = this.findByMenuCode(pmsMenu.getParentCode());
		pmsMenu.setParent(parentMenu);
		
		return pmsMenu;
	}

	@Override
	public void add(PmsMenu pmsMenu) {
		this.pmsMenuDao.insert(pmsMenu);
	}

	@Override
	public void update(PmsMenu pmsMenu) {
		this.pmsMenuDao.updateByPK(pmsMenu);
	}

	@Override
	public void deleteByMenuCode(String menuCode) {
		if(ValidUtils.isEmpty(menuCode)) return;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("menuCode", menuCode);
		this.pmsMenuDao.deleteBy(paramMap);
	}

    

}

