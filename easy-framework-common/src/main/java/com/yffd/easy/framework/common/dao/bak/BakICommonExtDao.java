package com.yffd.easy.framework.common.dao.bak;

import java.util.List;
import java.util.Map;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;

/**
 * @Description  持久化常用操作接口.
 * @Date		 2018年4月18日 下午5:38:43 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface BakICommonExtDao<E> extends BakICommonGenericDao<E> {

	
	/**************************************** 修改 ****************************************/
	
	/**
	 * 
	 * @Date	2018年4月19日 上午10:33:39 <br/>
	 * @author  zhangST
	 * @param entity			待修改“属性名-值”集合
	 * @param map			条件-指定“属性名-值”对集合
	 * @return
	 */
	Integer update(E entity, Map<String, Object> map);
	
	
	/**************************************** 删除 ****************************************/
	
	/**
	 * @see #update(Object, Object, Map)
	 * @Date	2018年4月19日 上午10:35:52 <br/>
	 * @author  zhangST
	 * @param entity
	 * @return
	 */
	Integer delete(E entity);
	
	/**
	 * 
	 * @Date	2018年4月19日 上午10:36:12 <br/>
	 * @author  zhangST
	 * @param map
	 * @return
	 */
	Integer delete(Map<String, Object> map);
	
	
	/**************************************** 查询（统计） ****************************************/
	
	Integer findCount(E entity);
	
	Integer findCount(Map<String, Object> map);
	
	
	/**************************************** 查询（单条） ****************************************/
	
	E findOne(E entity);
	
	E findOne(Map<String, Object> map);
	
	
	/**************************************** 查询（list） ****************************************/
	
	List<E> findListWithOrder(E entity, Map<String, Object> map, String[] orderPropertyNames, String[] orderByTypes);
	
	List<E> findListWithOrder(E entity, String orderBy);
	
	List<E> findListWithOrder(Map<String, Object> map, String orderBy);
	
	List<E> findAllWithOrder(String orderBy);
	
	List<E> findList(E entity, Map<String, Object> map);
	
	List<E> findList(E entity);
	
	List<E> findList(Map<String, Object> map);
	
	List<E> findAll();
	
	
	/**************************************** 查询（分页） ****************************************/
	
	PageResult<E> findPageWithOrder(E entity, Map<String, Object> map, String[] orderPropertyNames, String[] orderByTypes, PageParam page);
	
	PageResult<E> findPageWithOrder(E entity, String orderBy, PageParam page);
	
	PageResult<E> findPageWithOrder(Map<String, Object> map, String orderBy, PageParam page);
	
	PageResult<E> findPage(E entity, Map<String, Object> map, PageParam page);
	
	PageResult<E> findPage(E entity, PageParam page);
	
	PageResult<E> findPage(Map<String, Object> map, PageParam page);
	
	
	/**************************************** 查询（判断） ****************************************/
	
	/**
	 * 
	 * @Date	2018年4月19日 上午11:13:23 <br/>
	 * @author  zhangST
	 * @param entity
	 * @return
	 */
	Boolean checkUnique(E entity);
	
	/**
	 * 
	 * @Date	2018年4月19日 上午11:13:39 <br/>
	 * @author  zhangST
	 * @param map
	 * @return
	 */
	Boolean checkUnique(Map<String, Object> map);
	
	Boolean exsist(E entity);
	Boolean exsist(Map<String, Object> map);

}

