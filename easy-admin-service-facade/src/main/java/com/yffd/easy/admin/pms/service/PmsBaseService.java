package com.yffd.easy.admin.pms.service;

import java.util.List;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月13日 下午5:51:25 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface PmsBaseService<T> {
	
	PageResult<T> findPage(T model, PageParam pageParam);
	
	List<T> findList(T model);
	
	List<T> findAll();
	
	T findByCode(String code);
	
	void save(T model);
	
	void update(T model);
	
	void delete(String code);
	
}

