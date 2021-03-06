package com.yffd.easy.framework.common.dao.support;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.framework.common.mapper.ICommonMapper;

/**
 * @Description  mybatis dao常用操作类，基于 mapper接口方式.
 * @Date		 2018年4月18日 下午5:39:08 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public abstract class MybatisCommonMapperDaoSupport<E> extends MybatisCommonCustomDaoSupport {

	public abstract ICommonMapper<E> getBindMapper();
	
	public abstract Class<?> getBindMapperClass();
	
	protected Integer save_(E entity) {
		if(null==entity) return 0;
		return this.getBindMapper().insertOne(entity);
	}

	protected Integer save_(List<E> entityList) {
		if(null==entityList || entityList.isEmpty()) return 0;
		return this.getBindMapper().insertList(entityList);
	}
	
	protected Integer update_(E entity, E entityOld, Map<String, Object> propsMap) {
		if(null==entity) return 0;
		if(null==entityOld && (null==propsMap || propsMap.isEmpty())) return 0;
		return this.getBindMapper().updateBy(entity, entityOld, propsMap);
	}
	
	protected Integer delete_(E entity, Map<String, Object> propsMap) {
		if(null==entity && (null==propsMap || propsMap.isEmpty())) return 0;
		return this.getBindMapper().deleteBy(entity, propsMap);
	}

	protected Integer findCount_(E entity, Map<String, Object> propsMap) {
		return this.getBindMapper().selectCountBy(entity, propsMap);
	}
	
	protected E findOne_(E entity, Map<String, Object> propsMap) {
		if(null==entity && (null==propsMap || propsMap.isEmpty())) return null;
		return this.getBindMapper().selectOneBy(entity, propsMap);
	}

	protected List<E> findList_(E entity, Map<String, Object> propsMap, String orderBy) {
		return this.getBindMapper().selectListBy(entity, propsMap, orderBy, null);
	}

	protected PageResult<E> findPage_(E entity, Map<String, Object> propsMap, String orderBy, PageParam page) {
		if(null==page) return null;
		Integer totalRecord = this.getBindMapper().selectCountBy(entity, propsMap);
		page.setTotalRecord(totalRecord);
		if(totalRecord==0) return null;
		List<E> recordList = this.getBindMapper().selectListBy(entity, propsMap, orderBy, page);
		return new PageResult<E>(page, recordList);
	}

	protected Boolean exsistAndUnique_(E entity, Map<String, Object> propsMap) {
		Integer count = this.findCount_(entity, propsMap);
		return count==0;
	}

	protected Boolean exsist_(E entity, Map<String, Object> propsMap) {
		Integer count = this.findCount_(entity, propsMap);
		return count>0;
	}

	///////////////属性////////////////////
	
	protected Integer updateByProperty_(E entity, String propertyName, Object value) {
		if(null==entity) return 0;
		if(EasyStringCheckUtils.isEmpty(propertyName) || null==value) return 0;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(propertyName, value);
		return this.getBindMapper().updateBy(entity, null, paramMap);
	}
	
	protected Integer deleteByProperty_(String propertyName, Object value) {
		if(EasyStringCheckUtils.isEmpty(propertyName) || null==value) return 0;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(propertyName, value);
		return this.delete_(null, paramMap);
	}

	protected Integer findCountByProperty_(String propertyName, Object value) {
		if(EasyStringCheckUtils.isEmpty(propertyName) || null==value) return 0;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(propertyName, value);
		return this.findCount_(null, paramMap);
	}

	protected E findOneByProperty_(String propertyName, Object value) {
		if(EasyStringCheckUtils.isEmpty(propertyName) || null==value) return null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(propertyName, value);
		return this.findOne_(null, paramMap);
	}

	protected List<E> findListByProperty_(String propertyName, Object value, String orderBy) {
		if(EasyStringCheckUtils.isEmpty(propertyName) || null==value) return null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(propertyName, value);
		return this.findList_(null, paramMap, orderBy);
	}

	protected PageResult<E> findPageByProperty_(String propertyName, Object value, String orderBy,
			PageParam page) {
		if(EasyStringCheckUtils.isEmpty(propertyName) || null==value) return null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(propertyName, value);
		return this.findPage_(null, paramMap, orderBy, page);
	}

}

