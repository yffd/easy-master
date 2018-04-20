package com.yffd.easy.framework.common.dao.impl;

import java.util.List;
import java.util.Map;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.framework.common.dao.ICommonBaseDao;
import com.yffd.easy.framework.common.dao.support.MybatisCommonCustomDaoSupport;
import com.yffd.easy.framework.common.mapper.ICommonMapper;

/**
 * @Description  mybatis dao常用操作类，结合mapper接口的方式.
 * @Date		 2018年4月18日 下午6:22:04 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public abstract class MybatisMapperCommonBaseDaoImpl<E> extends MybatisCommonCustomDaoSupport implements ICommonBaseDao<E> {
	
	public abstract ICommonMapper<E> getBindMapper();
	
	public abstract Class<?> getBindMapperClass();
	
	@Override
	protected String getStatement(String sqlId) {
		String namespace = this.getBindMapperClass().getName();
		StringBuilder sb = new StringBuilder();
		sb.append(namespace).append(".").append(sqlId);
		return sb.toString();
	}

	@Override
	public Integer save(E entity) {
		if(null==entity) return 0;
		return this.getBindMapper().insertOne(entity);
	}

	@Override
	public Integer save(List<E> modelList) {
		if(null==modelList || modelList.isEmpty()) return 0;
		return this.getBindMapper().insertList(modelList);
	}

	@Override
	public Integer update(E entity, E oldEntity, Map<String, Object> map) {
		if(null==entity) return 0;
		if(null==oldEntity && (null==map || map.isEmpty())) return 0;
		return this.getBindMapper().updateBy(entity, oldEntity, map);
	}

	@Override
	public Integer delete(E entity, Map<String, Object> map) {
		if(null==entity && (null==map || map.isEmpty())) return 0;
		return this.getBindMapper().deleteBy(entity, map);
	}

	@Override
	public Integer findCount(E entity, Map<String, Object> map) {
		return this.getBindMapper().selectCountBy(entity, map);
	}

	@Override
	public E findOne(E entity, Map<String, Object> map) {
		if(null==entity && (null==map || map.isEmpty())) return null;
		return this.getBindMapper().selectOneBy(entity, map);
	}

	@Override
	public List<E> findListByOrder(E entity, Map<String, Object> map, String orderBy) {
		return this.getBindMapper().selectListBy(entity, map, orderBy, null);
	}

	@Override
	public PageResult<E> findPageByOrder(E entity, Map<String, Object> map, String orderBy, PageParam page) {
		if(null==page) return null;
		Integer totalRecord = this.findCount(entity, map);
		page.setTotalRecord(totalRecord);
		if(totalRecord==0) return null;
		List<E> recordList = this.getBindMapper().selectListBy(entity, map, orderBy, page);
		return new PageResult<E>(page, recordList);
	}

	@Override
	public Boolean checkUnique(E entity, Map<String, Object> map) {
		Integer count = this.findCount(entity, map);
		return count==0;
	}

	@Override
	public Boolean exsist(E entity, Map<String, Object> map) {
		Integer count = this.findCount(entity, map);
		return count>0;
	}

}

