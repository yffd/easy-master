package com.yffd.easy.framework.common.dao;

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
public interface ICommonBaseDao<E> {

	/**
	 * 添加：单条
	 * @Date	2018年4月19日 上午10:32:50 <br/>
	 * @author  zhangST
	 * @param entity
	 * @return
	 */
	Integer save(E entity);
	
	/**
	 * 添加：批量
	 * @Date	2018年4月19日 上午10:33:10 <br/>
	 * @author  zhangST
	 * @param entityList
	 * @return
	 */
	Integer save(List<E> entityList);
	
	/**
	 * 修改
	 * @Date	2018年4月19日 上午10:33:20 <br/>
	 * @author  zhangST
	 * @param entity			待修改“属性名-值”集合
	 * @param oldEntity			条件-旧对象“属性名-值”集合
	 * @param map				条件-指定“属性名-值”对集合
	 * @return
	 */
	Integer update(E entity, E oldEntity, Map<String, Object> map);
	
	/**
	 * 删除
	 * @Date	2018年4月19日 上午10:35:20 <br/>
	 * @author  zhangST
	 * @param entity
	 * @param map
	 * @return
	 */
	Integer delete(E entity, Map<String, Object> map);
	
	/**
	 * 查询：统计
	 * @Date	2018年4月20日 下午2:55:01 <br/>
	 * @author  zhangST
	 * @param entity
	 * @param map
	 * @return
	 */
	Integer findCount(E entity, Map<String, Object> map);
	
	/**
	 * 查询：单条
	 * @Date	2018年4月20日 下午2:55:16 <br/>
	 * @author  zhangST
	 * @param entity
	 * @param map
	 * @return
	 */
	E findOne(E entity, Map<String, Object> map);
	
	/**
	 * 查询：列表
	 * @Date	2018年4月20日 下午2:55:27 <br/>
	 * @author  zhangST
	 * @param entity
	 * @param map
	 * @param orderBy
	 * @return
	 */
	List<E> findListByOrder(E entity, Map<String, Object> map, String orderBy);
	
	/**
	 * 查询：分页
	 * @Date	2018年4月20日 下午2:55:40 <br/>
	 * @author  zhangST
	 * @param entity
	 * @param map
	 * @param orderBy
	 * @param page
	 * @return
	 */
	PageResult<E> findPageByOrder(E entity, Map<String, Object> map, String orderBy, PageParam page);
	
	/**
	 * 查询：存在并且唯一校验
	 * @Date	2018年4月19日 上午11:12:57 <br/>
	 * @author  zhangST
	 * @param entity
	 * @param map
	 * @return
	 */
	Boolean checkUnique(E entity, Map<String, Object> map);

	/**
	 * 查询：存在校验
	 * @Date	2018年4月20日 下午2:55:52 <br/>
	 * @author  zhangST
	 * @param entity
	 * @param map
	 * @return
	 */
	Boolean exsist(E entity, Map<String, Object> map);
	
}

