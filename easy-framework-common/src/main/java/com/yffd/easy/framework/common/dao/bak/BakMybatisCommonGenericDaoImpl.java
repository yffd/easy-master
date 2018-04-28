package com.yffd.easy.framework.common.dao.bak;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;

/**
 * @Description  mybatis dao常用操作类，以dao的完整类名作为 mapper sql 的命名空间.
 * @Date		 2018年4月20日 上午11:53:06 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public abstract class BakMybatisCommonGenericDaoImpl<E> extends BakMybatisCommonCustomDaoSupport implements BakICommonGenericDao<E> {
	/** mapper xml 中的SQL ID，即statement=insertOne */
	public static final String SQL_ID_INSERT_ONE = "insertOne";
	/** mapper xml 中的SQL ID，即statement=insertList */
	public static final String SQL_ID_INSERT_LIST = "insertList";
	/** mapper xml 中的SQL ID，即statement=updateBy */
	public static final String SQL_ID_UPDATE_BY = "updateBy";
	/** mapper xml 中的SQL ID，即statement=deleteBy */
	public static final String SQL_ID_DELETE_BY = "deleteBy";
	/** mapper xml 中的SQL ID，即statement=selectOneBy */
	public static final String SQL_ID_SELECT_ONE_BY = "selectOneBy";
	/** mapper xml 中的SQL ID，即statement=selectCountBy */
	public static final String SQL_ID_SELECT_COUNT_BY = "selectCountBy";
	/** mapper xml 中的SQL ID，即statement=selectListBy */
	public static final String SQL_ID_SELECT_LIST_BY = "selectListBy";
	
	
	/**
	 * sqlid : {@link #SQL_ID_INSERT_ONE}
	 * @Date	2018年4月19日 下午2:20:26 <br/>
	 * @author  zhangST
	 * @param entity
	 * @return
	 */
	@Override
	public Integer save(E entity) {
		if(null==entity) return 0;
		return super.insertByCustom(SQL_ID_INSERT_ONE, entity, true);
	}

	/**
	 * sqlid : {@link #SQL_ID_INSERT_LIST}
	 * @Date	2018年4月19日 下午2:23:07 <br/>
	 * @author  zhangST
	 * @param modelList
	 * @return
	 */
	@Override
	public Integer save(List<E> modelList) {
		if(null==modelList || modelList.isEmpty()) return 0;
		return super.insertByCustom(SQL_ID_INSERT_LIST, modelList, true);
	}

	/**
	 * sqlid : {@link #SQL_ID_UPDATE_BY}
	 * @Date	2018年4月19日 下午2:25:11 <br/>
	 * @author  zhangST
	 * @param entity
	 * @param oldEntity
	 * @param map
	 * @return
	 */
	@Override
	public Integer update(E entity, E oldEntity, Map<String, Object> map) {
		if(null==entity) return 0;
		if(null==oldEntity && (null==map || map.isEmpty())) return 0;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", entity);
		params.put("oldEntity", oldEntity);
		params.put("map", map);
		return super.updateByCustom(SQL_ID_UPDATE_BY, params, true);
	}
	
	/**
	 * sqlid : {@link #SQL_ID_DELETE_BY}
	 * @Date	2018年4月19日 下午2:26:17 <br/>
	 * @author  zhangST
	 * @param entity
	 * @param map
	 * @return
	 */
	@Override
	public Integer delete(E entity, Map<String, Object> map) {
		if(null==entity && (null==map || map.isEmpty())) return 0;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", entity);
		params.put("map", map);
		return super.deleteByCustom(SQL_ID_DELETE_BY, params, true);
	}

	/**
	 * sqlid : {@link #SQL_ID_SELECT_COUNT_BY}
	 * @Date	2018年4月19日 下午2:28:17 <br/>
	 * @author  zhangST
	 * @param entity
	 * @param map
	 * @return
	 */
	@Override
	public Integer findCount(E entity, Map<String, Object> map) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", entity);
		params.put("map", map);
		return super.selectCountByCustom(SQL_ID_SELECT_COUNT_BY, params, true);
	}
	
	/**
	 * sqlid : {@link #SQL_ID_SELECT_ONE_BY}
	 * @Date	2018年4月19日 下午2:27:34 <br/>
	 * @author  zhangST
	 * @param entity
	 * @param map
	 * @return
	 */
	@Override
	public E findOne(E entity, Map<String, Object> map) {
		if(null==entity && (null==map || map.isEmpty())) return null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", entity);
		params.put("map", map);
		return super.selectOneByCustom(SQL_ID_SELECT_ONE_BY, params, true);
	}

	/**
	 * sqlid : {@link #SQL_ID_SELECT_LIST_BY}
	 * @Date	2018年4月19日 下午2:28:59 <br/>
	 * @author  zhangST
	 * @param entity
	 * @param map
	 * @param orderBy
	 * @return
	 */
	@Override
	public List<E> findListWithOrder(E entity, Map<String, Object> map, String orderBy) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", entity);
		params.put("map", map);
		params.put("orderBy", orderBy);
		return super.selectListByCustom(SQL_ID_SELECT_LIST_BY, params, true);
	}

	/**
	 * sqlid : {@link #SQL_ID_SELECT_LIST_BY}
	 * sqlid count : {@link #SQL_ID_SELECT_COUNT_BY}
	 * @Date	2018年4月19日 下午2:31:16 <br/>
	 * @author  zhangST
	 * @param entity
	 * @param map
	 * @param orderBy
	 * @param page
	 * @return
	 */
	@Override
	public PageResult<E> findPageWithOrder(E entity, Map<String, Object> map, String orderBy, PageParam page) {
		if(null==page) return null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", entity);
		params.put("map", map);
		Integer totalRecord = super.selectCountByCustom(SQL_ID_SELECT_COUNT_BY, params, true);
		page.setTotalRecord(totalRecord);
		if(totalRecord==0) return null;
		params.put("orderBy", orderBy);
		params.put("page", page);
		List<E> recordList = super.selectListByCustom(SQL_ID_SELECT_LIST_BY, params, true);
		return new PageResult<E>(page, recordList);
	}

	/**
	 * sqlid : {@link #SQL_ID_SELECT_COUNT_BY}
	 * @Date	2018年4月19日 下午2:35:29 <br/>
	 * @author  zhangST
	 * @param entity
	 * @param map
	 * @return
	 */
	@Override
	public Boolean exsistAndUnique(E entity, Map<String, Object> map) {
		Integer count = this.findCount(entity, map);
		return count==0;
	}

	/**
	 * sqlid : {@link #SQL_ID_SELECT_COUNT_BY}
	 * @Date	2018年4月19日 下午2:36:04 <br/>
	 * @author  zhangST
	 * @param entity
	 * @param map
	 * @return
	 */
	@Override
	public Boolean exsist(E entity, Map<String, Object> map) {
		Integer count = this.findCount(entity, map);
		return count>0;
	}

}

