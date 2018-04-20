package com.yffd.easy.framework.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.framework.common.dao.ICommonBaseDao;
import com.yffd.easy.framework.common.service.ICommonBaseService;
import com.yffd.easy.framework.common.service.support.CommonServiceSupport;

/**
 * @Description  业务逻辑常用操作类，隐藏持久化实体（entity）的方式.
 * @Date		 2018年4月20日 上午11:25:52 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public abstract class CommonBaseServiceImpl<M, E> extends CommonServiceSupport<M, E> implements ICommonBaseService<M> {
	
	public abstract ICommonBaseDao<E> getBindDao();
	
	@Override
	public Integer save(M model) {
		E entity = this.model2entity(model);
		if(null==entity) return 0;
		return this.getBindDao().save(entity);
	}

	@Override
	public Integer save(List<M> modelList) {
		List<E> entityList = this.modelList2entityList(modelList);
		if(null==entityList || entityList.isEmpty()) return 0;
		return this.getBindDao().save(entityList);
	}

	@Override
	public Integer update(M model, M oldModel) {
		E entity = this.model2entity(model);
		E oldEntity = this.model2entity(oldModel);
		Map<String, Object> map = this.noneEntityPropertys(oldModel);
		if(null==entity && (null==oldEntity || map.isEmpty())) return 0;
		return this.getBindDao().update(entity, oldEntity, map);
	}

	@Override
	public Integer delete(M model) {
		E entity = this.model2entity(model);
		Map<String, Object> map = this.noneEntityPropertys(model);
		if(null==entity && (null==map || map.isEmpty())) return 0;
		return this.getBindDao().delete(entity, map);
	}
	
	@Override
	public Integer findCount(M model) {
		E entity = this.model2entity(model);
		Map<String, Object> map = this.noneEntityPropertys(model);
		if(null==entity && (null==map || map.isEmpty())) return 0;
		return this.getBindDao().findCount(entity, map);
	}

	@Override
	public M findOne(M model) {
		E entity = this.model2entity(model);
		Map<String, Object> map = this.noneEntityPropertys(model);
		if(null==entity && (null==map || map.isEmpty())) return null;
		E result = this.getBindDao().findOne(entity, map);
		return this.entity2model(result);
	}

	@Override
	public List<M> findListByOrder(M model, String orderBy) {
		E entity = this.model2entity(model);
		Map<String, Object> map = this.noneEntityPropertys(model);
		if(null==entity && (null==map || map.isEmpty())) return null;
		List<E> result = this.getBindDao().findListByOrder(entity, map, orderBy);
		return this.entityList2modelList(result);
	}

	@Override
	public PageResult<M> findPageByOrder(M model, String orderBy, PageParam page) {
		E entity = this.model2entity(model);
		Map<String, Object> map = this.noneEntityPropertys(model);
		if(null==entity && (null==map || map.isEmpty())) return null;
		PageResult<E> result = this.getBindDao().findPageByOrder(entity, map, orderBy, page);
		return this.entityPage2modelPage(result);
	}

	@Override
	public Boolean checkUnique(M model) {
		E entity = this.model2entity(model);
		Map<String, Object> map = this.noneEntityPropertys(model);
		if(null==entity && (null==map || map.isEmpty())) return null;
		return this.getBindDao().checkUnique(entity, map);
	}

	@Override
	public Boolean exsist(M model) {
		E entity = this.model2entity(model);
		Map<String, Object> map = this.noneEntityPropertys(model);
		if(null==entity && (null==map || map.isEmpty())) return null;
		return this.getBindDao().exsist(entity, map);
	}
	
	
	@Override
	public Integer deleteByProperty(String propertyName, Object value) {
		if(EasyStringCheckUtils.isEmpty(propertyName) || null==value) return null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(propertyName, value);
		return this.getBindDao().delete(null, map);
	}

	@Override
	public Integer findCountByProperty(String propertyName, Object value) {
		if(EasyStringCheckUtils.isEmpty(propertyName) || null==value) return null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(propertyName, value);
		return this.getBindDao().findCount(null, map);
	}

	@Override
	public M findOneByProperty(String propertyName, Object value) {
		if(EasyStringCheckUtils.isEmpty(propertyName) || null==value) return null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(propertyName, value);
		E result = this.getBindDao().findOne(null, map);
		return this.entity2model(result);
	}

	@Override
	public List<M> findListByProperty(String propertyName, Object value) {
		if(EasyStringCheckUtils.isEmpty(propertyName) || null==value) return null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(propertyName, value);
		List<E> result = this.getBindDao().findListByOrder(null, map, null);
		return this.entityList2modelList(result);
	}

	@Override
	public PageResult<M> findPageByProperty(String propertyName, Object value, PageParam page) {
		if(EasyStringCheckUtils.isEmpty(propertyName) || null==value) return null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(propertyName, value);
		PageResult<E> result = this.getBindDao().findPageByOrder(null, map, null, page);
		return this.entityPage2modelPage(result);
	}
	
}

