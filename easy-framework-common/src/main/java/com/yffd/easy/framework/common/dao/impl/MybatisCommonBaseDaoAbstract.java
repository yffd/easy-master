package com.yffd.easy.framework.common.dao.impl;

import java.util.List;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.framework.common.dao.ICommonBaseDao;
import com.yffd.easy.framework.common.dao.support.MybatisCommonMapperDaoSupport;

/**
 * @Description  基于 mapper接口方式.
 * @Date		 2018年4月28日 下午2:57:05 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public abstract class MybatisCommonBaseDaoAbstract<E> extends MybatisCommonMapperDaoSupport<E> implements ICommonBaseDao<E> {

	@Override
	public Integer save(E entity) {
		return super.save_(entity);
	}

	@Override
	public Integer save(List<E> modelList) {
		return super.save_(modelList);
	}

	@Override
	public Integer update(E entity, E entityOld) {
		return super.update_(entity, entityOld, null);
	}
	
	@Override
	public Integer delete(E entity) {
		return super.delete_(entity, null);
	}

	@Override
	public Integer findCount(E entity) {
		return super.findCount_(entity, null);
	}
	
	@Override
	public E findOne(E entity) {
		return super.findOne_(entity, null);
	}

	@Override
	public List<E> findListWithOrder(E entity, String orderBy) {
		return super.findList_(entity, null, orderBy);
	}

	@Override
	public PageResult<E> findPageWithOrder(E entity, String orderBy, PageParam page) {
		return super.findPage_(entity, null, orderBy, page);
	}

	@Override
	public Boolean exsistAndUnique(E entity) {
		return super.exsistAndUnique_(entity, null);
	}

	@Override
	public Boolean exsist(E entity) {
		return super.exsist_(entity, null);
	}

}

