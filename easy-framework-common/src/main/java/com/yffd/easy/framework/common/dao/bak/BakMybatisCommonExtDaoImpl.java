package com.yffd.easy.framework.common.dao.bak;

import java.util.List;
import java.util.Map;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;

/**
 * @Description  持久化常用操作类，以dao的完整类名作为 mapper sql 的命名空间.
 * @Date		 2018年4月19日 下午1:56:21 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public abstract class BakMybatisCommonExtDaoImpl<E> extends BakMybatisCommonGenericDaoImpl<E> implements BakICommonExtDao<E> {

	@Override
	public Integer update(E entity, Map<String, Object> map) {
		return super.update(entity, null, map);
	}

	@Override
	public Integer delete(E entity) {
		return super.delete(entity, null);
	}

	@Override
	public Integer delete(Map<String, Object> map) {
		return super.delete(null, map);
	}

	@Override
	public Integer findCount(E entity) {
		return super.findCount(entity, null);
	}

	@Override
	public Integer findCount(Map<String, Object> map) {
		return super.findCount(null, map);
	}
	
	@Override
	public E findOne(E entity) {
		return super.findOne(entity, null);
	}

	@Override
	public E findOne(Map<String, Object> map) {
		return super.findOne(null, map);
	}

	@Override
	public List<E> findListWithOrder(E entity, Map<String, Object> map, String[] orderPropertyNames,
			String[] orderByTypes) {
		String orderBy = this.makeOrderBy(orderPropertyNames, orderByTypes);
		return super.findListWithOrder(entity, map, orderBy);
	}

	@Override
	public List<E> findListWithOrder(E entity, String orderBy) {
		return super.findListWithOrder(entity, null, orderBy);
	}

	@Override
	public List<E> findListWithOrder(Map<String, Object> map, String orderBy) {
		return super.findListWithOrder(null, map, orderBy);
	}

	@Override
	public List<E> findAllWithOrder(String orderBy) {
		return super.findListWithOrder(null, null, orderBy);
	}

	@Override
	public List<E> findList(E entity, Map<String, Object> map) {
		return super.findListWithOrder(entity, map, null);
	}

	@Override
	public List<E> findList(E entity) {
		return super.findListWithOrder(entity, null, null);
	}

	@Override
	public List<E> findList(Map<String, Object> map) {
		return super.findListWithOrder(null, map, null);
	}

	@Override
	public List<E> findAll() {
		return super.findListWithOrder(null, null, null);
	}

	@Override
	public PageResult<E> findPageWithOrder(E entity, Map<String, Object> map, String[] orderPropertyNames,
			String[] orderByTypes, PageParam page) {
		String orderBy = this.makeOrderBy(orderPropertyNames, orderByTypes);
		return super.findPageWithOrder(entity, map, orderBy, page);
	}

	@Override
	public PageResult<E> findPageWithOrder(E entity, String orderBy, PageParam page) {
		return super.findPageWithOrder(entity, null, orderBy, page);
	}

	@Override
	public PageResult<E> findPageWithOrder(Map<String, Object> map, String orderBy, PageParam page) {
		return super.findPageWithOrder(null, map, orderBy, page);
	}

	@Override
	public PageResult<E> findPage(E entity, Map<String, Object> map, PageParam page) {
		return super.findPageWithOrder(entity, map, null, page);
	}

	@Override
	public PageResult<E> findPage(E entity, PageParam page) {
		return super.findPageWithOrder(entity, null, null, page);
	}

	@Override
	public PageResult<E> findPage(Map<String, Object> map, PageParam page) {
		return super.findPageWithOrder(null, map, null, page);
	}

	@Override
	public Boolean checkUnique(E entity) {
		return super.exsist(entity, null);
	}

	@Override
	public Boolean checkUnique(Map<String, Object> map) {
		return super.exsist(null, map);
	}

	@Override
	public Boolean exsist(E entity) {
		return super.exsist(entity, null);
	}

	@Override
	public Boolean exsist(Map<String, Object> map) {
		return super.exsist(null, map);
	}

}

