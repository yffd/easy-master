package org.yffd.easy.app.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yffd.easy.app.system.dao.SysFunctionDao;
import org.yffd.easy.app.system.entity.SysFunction;
import org.yffd.easy.app.system.service.SysFunctionService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月13日 上午10:55:16 <br/>
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
	public List<SysFunction> findFunction() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("type", SysFunction.TYPE_FUNCTION);
		return this.sysFunctionDao.selectListBy(paramMap);
	}

	@Override
	public List<SysFunction> findOperation() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("type", SysFunction.TYPE_OPRATION);
		return this.sysFunctionDao.selectListBy(paramMap);
	}

	@Override
	public void add(SysFunction sysFunction) {
		this.sysFunctionDao.insert(sysFunction);
	}

	@Override
	public void edit(SysFunction sysFunction) {
		this.sysFunctionDao.updateBy(sysFunction);
	}

	@Override
	public void delByCode(String code) {
		this.delByCode(code);
	}

}

