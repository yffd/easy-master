package com.yffd.easy.framework.common.dao.impl;

import java.util.List;
import java.util.Map;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.framework.common.dao.ICommonGenericDao;

/**
 * @Description  dao常用操作类，公开更多的方法.
 * @Date		 2018年4月28日 下午5:42:39 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class MybatisCommonGenericDaoImpl<E> extends MybatisCommonBaseDaoImpl<E> implements ICommonGenericDao<E> {

	@Override
	public Integer update(E entity, E entityOld, Map<String, Object> propsMap) {
		return super.update_(entity, entityOld, propsMap);
	}

	@Override
	public Integer delete(E entity, Map<String, Object> propsMap) {
		return super.delete_(entity, propsMap);
	}

	@Override
	public Integer findCount(E entity, Map<String, Object> propsMap) {
		return super.findCount_(entity, propsMap);
	}

	@Override
	public E findOne(E entity, Map<String, Object> propsMap) {
		return super.findOne_(entity, propsMap);
	}

	@Override
	public List<E> findListWithOrder(E entity, Map<String, Object> propsMap, String orderBy) {
		return super.findList_(entity, propsMap, orderBy);
	}

	@Override
	public List<E> findListWithOrder(E entity, Map<String, Object> propsMap, String[] orderPropertyNames,
			String[] orderByTypes) {
		String orderBy = this.makeOrderBy(orderPropertyNames, orderByTypes);
		return super.findList_(entity, propsMap, orderBy);
	}

	@Override
	public List<E> findListWithOrder(E entity, Map<String, Object> propsMap, String orderPropertyName,
			String orderByType) {
		String orderBy = this.makeOrderBy(orderPropertyName, orderByType);
		return super.findList_(entity, propsMap, orderBy);
	}

	@Override
	public List<E> findList(E entity, Map<String, Object> propsMap) {
		return super.findList_(entity, propsMap, null);
	}

	@Override
	public PageResult<E> findPageWithOrder(E entity, Map<String, Object> propsMap, String orderBy, PageParam page) {
		return super.findPage_(entity, propsMap, orderBy, page);
	}

	@Override
	public PageResult<E> findPageWithOrder(E entity, Map<String, Object> propsMap, String[] orderPropertyNames,
			String[] orderByTypes, PageParam page) {
		String orderBy = this.makeOrderBy(orderPropertyNames, orderByTypes);
		return super.findPage_(entity, propsMap, orderBy, page);
	}

	@Override
	public PageResult<E> findPageWithOrder(E entity, Map<String, Object> propsMap, String orderPropertyName,
			String orderByType, PageParam page) {
		String orderBy = this.makeOrderBy(orderPropertyName, orderByType);
		return super.findPage_(entity, propsMap, orderBy, page);
	}

	@Override
	public PageResult<E> findPageWithOrder(E entity, Map<String, Object> propsMap, PageParam page) {
		return super.findPage_(entity, propsMap, null, page);
	}

	@Override
	public Boolean exsistAndUnique(E entity, Map<String, Object> propsMap) {
		return super.exsistAndUnique_(entity, propsMap);
	}

	@Override
	public Boolean exsist(E entity, Map<String, Object> propsMap) {
		return super.exsist_(entity, propsMap);
	}

	///////////////// 属性 /////////////////////////////////////////
	
	@Override
	public Integer updateByProperty(E entity, String propertyName, Object value) {
		return super.updateByProperty_(entity, propertyName, value);
	}

	@Override
	public Integer deleteByProperty(String propertyName, Object value) {
		return super.deleteByProperty_(propertyName, value);
	}

	@Override
	public Integer findCountByProperty(String propertyName, Object value) {
		return super.findCountByProperty_(propertyName, value);
	}

	@Override
	public E findOneByProperty(String propertyName, Object value) {
		return super.findOneByProperty_(propertyName, value);
	}

	@Override
	public List<E> findListWithOrderByProperty(String propertyName, Object value, String orderBy) {
		return super.findListByProperty_(propertyName, value, orderBy);
	}

	@Override
	public List<E> findListWithOrderByProperty(String propertyName, Object value, String[] orderPropertyNames,
			String[] orderByTypes) {
		String orderBy = this.makeOrderBy(orderPropertyNames, orderByTypes);
		return super.findListByProperty_(propertyName, value, orderBy);
	}

	@Override
	public List<E> findListWithOrderByProperty(String propertyName, Object value, String orderPropertyName,
			String orderByType) {
		String orderBy = this.makeOrderBy(orderPropertyName, orderByType);
		return super.findListByProperty_(propertyName, value, orderBy);
	}

	@Override
	public List<E> findListByProperty(String propertyName, Object value) {
		return super.findListByProperty_(propertyName, value, null);
	}

	@Override
	public PageResult<E> findPageWithOrderByProperty(String propertyName, Object value, String orderBy,
			PageParam page) {
		return super.findPageProperty_(propertyName, value, orderBy, page);
	}

	@Override
	public PageResult<E> findPageWithOrderByProperty(String propertyName, Object value, String[] orderPropertyNames,
			String[] orderByTypes, PageParam page) {
		String orderBy = this.makeOrderBy(orderPropertyNames, orderByTypes);
		return super.findPageProperty_(propertyName, value, orderBy, page);
	}

	@Override
	public PageResult<E> findPageWithOrderByProperty(String propertyName, Object value, String orderPropertyName,
			String orderByType, PageParam page) {
		String orderBy = this.makeOrderBy(orderPropertyName, orderByType);
		return super.findPageProperty_(propertyName, value, orderBy, page);
	}

	@Override
	public PageResult<E> findPageByProperty(String propertyName, Object value, PageParam page) {
		return super.findPageProperty_(propertyName, value, null, page);
	}


}

