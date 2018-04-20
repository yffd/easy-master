package com.yffd.easy.framework.common.service;

import java.util.List;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;

/**
 * @Description  业务逻辑常用操作类.
 * @Date		 2018年4月19日 上午11:18:46 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface ICommonService<M> extends ICommonBaseService<M> {

	public Integer saveOrUpdate(M model);
	public Integer saveOrUpdate(List<M> modelList);
	
	public Integer update(M model);
	
	public Integer deleteByPrimaryId(String primaryId);
	public Integer deleteByPrimaryIds(String[] primaryIds);
	
	public M findOneByPrimaryId(String primaryId);
	
	
	public List<M> findAllByOrder(String orderBy);
	public List<M> findAllByOrder(String[] orderPropertyNames, String[] orderByTypes);
	
	public List<M> findListByOrder(M model, String[] orderPropertyNames, String[] orderByTypes);
	public List<M> findListByOrder(M model, String orderPropertyName, String orderByType);
	
	
	public List<M> findAll();
	public List<M> findList(M model);

	
	public PageResult<M> findPageByOrder(M model, String[] orderPropertyNames, String[] orderByTypes, PageParam page);
	public PageResult<M> findPageByOrder(M model, String orderPropertyName, String orderByType, PageParam page);
	
	
	public PageResult<M> findPage(M model, PageParam page);
	
}

