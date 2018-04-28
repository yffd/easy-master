package com.yffd.easy.framework.common.dao.bak;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.framework.common.dao.ICommonBaseDao;
import com.yffd.easy.framework.common.dao.support.MybatisCommonMapDaoSupport;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年4月28日 下午2:57:05 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class BakMybatisCommonBaseDao<E> extends MybatisCommonMapDaoSupport<E> implements ICommonBaseDao<E> {

	/**
	 * sqlid : {@link MybatisCommonMapDaoSupport#SQL_ID_INSERT_ONE}
	 * @Date	2018年4月19日 下午2:20:26 <br/>
	 * @author  zhangST
	 * @param entity
	 * @return
	 */
	@Override
	public Integer save(E entity) {
		if(null==entity) return 0;
		return super.customInsertBy(SQL_ID_INSERT_ONE, entity, true);
	}

	/**
	 * sqlid : {@link MybatisCommonMapDaoSupport#SQL_ID_INSERT_LIST}
	 * @Date	2018年4月19日 下午2:23:07 <br/>
	 * @author  zhangST
	 * @param modelList
	 * @return
	 */
	@Override
	public Integer save(List<E> modelList) {
		if(null==modelList || modelList.isEmpty()) return 0;
		return super.customInsertBy(SQL_ID_INSERT_LIST, modelList, true);
	}

	/**
	 * sqlid : {@link MybatisCommonMapDaoSupport#SQL_ID_UPDATE_BY}
	 * @Date	2018年4月19日 下午2:25:11 <br/>
	 * @author  zhangST
	 * @param entity
	 * @param entityOld
	 * @return
	 */
	@Override
	public Integer update(E entity, E entityOld) {
		if(null==entity || null==entityOld) return 0;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(PARAM_NAME_ENTITY, entity);
		params.put(PARAM_NAME_ENTITY_OLD, entityOld);
		return super.customUpdateBy(SQL_ID_UPDATE_BY, params, true);
	}
	
	/**
	 * sqlid : {@link MybatisCommonMapDaoSupport#SQL_ID_DELETE_BY}
	 * @Date	2018年4月19日 下午2:26:17 <br/>
	 * @author  zhangST
	 * @param entity
	 * @return
	 */
	@Override
	public Integer delete(E entity) {
		if(null==entity) return 0;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(PARAM_NAME_ENTITY, entity);
		return super.customDeleteBy(SQL_ID_DELETE_BY, params, true);
	}

	/**
	 * sqlid : {@link MybatisCommonMapDaoSupport#SQL_ID_SELECT_COUNT_BY}
	 * @Date	2018年4月19日 下午2:28:17 <br/>
	 * @author  zhangST
	 * @param entity
	 * @return
	 */
	@Override
	public Integer findCount(E entity) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(PARAM_NAME_ENTITY, entity);
		return super.customSelectCountBy(SQL_ID_SELECT_COUNT_BY, params, true);
	}
	
	/**
	 * sqlid : {@link MybatisCommonMapDaoSupport#SQL_ID_SELECT_ONE_BY}
	 * @Date	2018年4月19日 下午2:27:34 <br/>
	 * @author  zhangST
	 * @param entity
	 * @return
	 */
	@Override
	public E findOne(E entity) {
		if(null==entity) return null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(PARAM_NAME_ENTITY, entity);
		return super.customSelectOneBy(SQL_ID_SELECT_ONE_BY, params, true);
	}

	/**
	 * sqlid : {@link MybatisCommonMapDaoSupport#SQL_ID_SELECT_LIST_BY}
	 * @Date	2018年4月19日 下午2:28:59 <br/>
	 * @author  zhangST
	 * @param entity
	 * @param orderBy
	 * @return
	 */
	@Override
	public List<E> findListWithOrder(E entity, String orderBy) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(PARAM_NAME_ENTITY, entity);
		params.put(PARAM_NAME_ORDER_BY, orderBy);
		return super.customSelectListBy(SQL_ID_SELECT_LIST_BY, params, true);
	}

	/**
	 * sqlid : {@link MybatisCommonMapDaoSupport#SQL_ID_SELECT_LIST_BY}
	 * sqlid count : {@link #SQL_ID_SELECT_COUNT_BY}
	 * @Date	2018年4月19日 下午2:31:16 <br/>
	 * @author  zhangST
	 * @param entity
	 * @param orderBy
	 * @param page
	 * @return
	 */
	@Override
	public PageResult<E> findPageWithOrder(E entity, String orderBy, PageParam page) {
		if(null==page) return null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(PARAM_NAME_ENTITY, entity);
		Integer totalRecord = super.customSelectCountBy(SQL_ID_SELECT_COUNT_BY, params, true);
		page.setTotalRecord(totalRecord);
		if(totalRecord==0) return null;
		params.put(PARAM_NAME_ORDER_BY, orderBy);
		params.put(PARAM_NAME_PAGE, page);
		List<E> recordList = super.customSelectListBy(SQL_ID_SELECT_LIST_BY, params, true);
		return new PageResult<E>(page, recordList);
	}

	/**
	 * sqlid : {@link MybatisCommonMapDaoSupport#SQL_ID_SELECT_COUNT_BY}
	 * @Date	2018年4月19日 下午2:35:29 <br/>
	 * @author  zhangST
	 * @param entity
	 * @return
	 */
	@Override
	public Boolean exsistAndUnique(E entity) {
		Integer count = this.findCount(entity);
		return count==0;
	}

	/**
	 * sqlid : {@link MybatisCommonMapDaoSupport#SQL_ID_SELECT_COUNT_BY}
	 * @Date	2018年4月19日 下午2:36:04 <br/>
	 * @author  zhangST
	 * @param entity
	 * @return
	 */
	@Override
	public Boolean exsist(E entity) {
		Integer count = this.findCount(entity);
		return count>0;
	}

}

