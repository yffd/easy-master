package com.yffd.easy.framework.common.dao.impl;

import java.util.List;
import java.util.Map;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.framework.common.dao.ICommonGenericDao;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年4月28日 下午5:42:39 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class MybatisCommonGenericDaoImpl<E> extends MybatisCommonBaseDaoImpl<E> implements ICommonGenericDao<E> {

	@Override
	public List<E> findListWithOrder(E entity, Map<String, Object> map, String orderBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> findListWithOrder(E entity, Map<String, Object> map, String[] orderPropertyNames,
			String[] orderByTypes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> findList(E entity, Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult<E> findPageWithOrder(E entity, Map<String, Object> map, String orderBy, PageParam page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult<E> findPageWithOrder(E entity, Map<String, Object> map, String[] orderPropertyNames,
			String[] orderByTypes, PageParam page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult<E> findPageWithOrder(E entity, Map<String, Object> map, PageParam page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer queryCountByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> findListWithOrderByProperty(String propertyName, Object value, String orderBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> findListWithOrderByProperty(String propertyName, Object value, String orderPropertyName,
			String orderByType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> findListByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult<E> findPageWithOrderByProperty(String propertyName, Object value, String orderBy,
			PageParam page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult<E> findPageWithOrderByProperty(String propertyName, Object value, String orderPropertyName,
			String orderByType, PageParam page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult<E> findPageByProperty(String propertyName, Object value, PageParam page) {
		// TODO Auto-generated method stub
		return null;
	}

}

