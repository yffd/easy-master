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
	
	protected Integer save(E entity) {
		if(null==entity) return 0;
		return this.getBindMapper().insertOne(entity);
	}

	protected Integer save(List<E> entityList) {
		if(null==entityList || entityList.isEmpty()) return 0;
		return this.getBindMapper().insertList(entityList);
	}
	
	protected Integer update(E entity, E entityOld, Map<String, Object> propsMap) {
		if(null==entity) return 0;
		if(null==entityOld && (null==propsMap || propsMap.isEmpty())) return 0;
		return this.getBindMapper().updateBy(entity, entityOld, propsMap);
	}
	
	protected Integer delete(E entity, Map<String, Object> propsMap) {
		if(null==entity && (null==propsMap || propsMap.isEmpty())) return 0;
		return this.getBindMapper().deleteBy(entity, propsMap);
	}

	protected Integer findCount(E entity, Map<String, Object> propsMap) {
		return this.getBindMapper().selectCountBy(entity, propsMap);
	}
	
	protected E findOne(E entity, Map<String, Object> propsMap) {
		if(null==entity && (null==propsMap || propsMap.isEmpty())) return null;
		return this.getBindMapper().selectOneBy(entity, propsMap);
	}

	protected List<E> findList(E entity, Map<String, Object> propsMap, String orderBy) {
		return this.getBindMapper().selectListBy(entity, propsMap, orderBy, null);
	}

	protected PageResult<E> findPage(E entity, Map<String, Object> propsMap, String orderBy, PageParam page) {
		if(null==page) return null;
		Integer totalRecord = this.getBindMapper().selectCountBy(entity, propsMap);
		page.setTotalRecord(totalRecord);
		if(totalRecord==0) return null;
		List<E> recordList = this.getBindMapper().selectListBy(entity, propsMap, orderBy, page);
		return new PageResult<E>(page, recordList);
	}

	protected Boolean exsistAndUnique(E entity, Map<String, Object> propsMap) {
		Integer count = this.findCount(entity, propsMap);
		return count==0;
	}

	protected Boolean exsist(E entity, Map<String, Object> propsMap) {
		Integer count = this.findCount(entity, propsMap);
		return count>0;
	}

	///////////////属性////////////////////
	
	protected Integer updateByProperty(E entity, String propertyName, Object value) {
		if(null==entity) return 0;
		if(EasyStringCheckUtils.isEmpty(propertyName) || null==value) return 0;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(propertyName, value);
		return this.getBindMapper().updateBy(entity, null, paramMap);
	}
	
	protected Integer deleteByProperty(String propertyName, Object value) {
		if(EasyStringCheckUtils.isEmpty(propertyName) || null==value) return 0;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(propertyName, value);
		return this.delete(null, paramMap);
	}

	protected Integer findCountByProperty(String propertyName, Object value) {
		if(EasyStringCheckUtils.isEmpty(propertyName) || null==value) return 0;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(propertyName, value);
		return this.findCount(null, paramMap);
	}

	protected E findOneByProperty(String propertyName, Object value) {
		if(EasyStringCheckUtils.isEmpty(propertyName) || null==value) return null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(propertyName, value);
		return this.findOne(null, paramMap);
	}

	protected List<E> findListByProperty(String propertyName, Object value, String orderBy) {
		if(EasyStringCheckUtils.isEmpty(propertyName) || null==value) return null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(propertyName, value);
		return this.findList(null, paramMap, orderBy);
	}

	protected PageResult<E> findPageByProperty(String propertyName, Object value, String orderBy,
			PageParam page) {
		if(EasyStringCheckUtils.isEmpty(propertyName) || null==value) return null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(propertyName, value);
		return this.findPage(null, paramMap, orderBy, page);
	}

}

