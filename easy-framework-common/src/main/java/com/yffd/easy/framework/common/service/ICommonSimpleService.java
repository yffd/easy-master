package com.yffd.easy.framework.common.service;

import java.util.List;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.framework.common.dao.ICommonDao;

/**
 * @Description  数据模型对象贯穿全局的方式-对属性操作的扩展.
 * @Date		 2018年4月19日 下午6:14:50 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface ICommonSimpleService<E> extends ICommonDao<E> {

	public Integer saveOrUpdate(E model);
	public Integer saveOrUpdate(List<E> modelList);
	
	
	public Integer deleteByProperty(String propertyName, Object value);
	public Integer deleteByPrimaryId(String primaryId);
	public Integer deleteByPrimaryIds(String[] primaryIds);
	
	
	public E findOneByProperty(String propertyName, Object value);
	public E findOneByPrimaryId(String primaryId);
	
	
	public Integer queryCountByProperty(String propertyName, Object value);
	
	
	public List<E> findListByProperty(String propertyName, Object value, String[] orderPropertyNames, String[] orderByTypes);
	public List<E> findListByProperty(String propertyName, Object value, String orderBy);
	public List<E> findListByProperty(String propertyName, Object value);
	
	
	public PageResult<E> findPageByProperty(String propertyName, Object value, String[] orderPropertyNames, String[] orderByTypes, PageParam page);
	public PageResult<E> findPageByProperty(String propertyName, Object value, String orderBy, PageParam page);
	public PageResult<E> findPageByProperty(String propertyName, Object value, PageParam page);

}

