package com.yffd.easy.framework.base.dao;

import java.util.List;
import java.util.Map;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;

/**
 * @Description  持久化常用操作类.
 * @Date		 2018年1月29日 下午5:42:56 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface ICommonDao {
    
	/**
	 * 统计查询.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_COUNT_BY} <br/>
	 * @Date	2018年3月1日 下午6:09:56 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @return
	 */
	Long selectCountBy(Map<String, Object> paramMap);
	
	/**
	 * 分页查询，并返回分页数据、分页信息.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_LIST_BY} <br/>
	 * @sqlcountid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_COUNT_BY} <br/>
	 * @Date	2018年3月1日 下午6:13:00 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @param pageParam
	 * @return
	 */
	PageResult<?> selectPage(Map<String, Object> paramMap, PageParam pageParam);

	/**
	 * 列表查询.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_LIST_BY} <br/>
	 * @Date	2018年3月1日 下午6:13:05 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @return
	 */
	List<?> selectListBy(Map<String, Object> paramMap);

	/**
	 * 主键查询.<br/>
	 * @Date	2018年3月1日 下午6:13:11 <br/>
	 * @author  zhangST
	 * @param id
	 * @return
	 * @see #selectListBy(java.util.Map)
	 */
	List<?> selectListById(String id);
	
	/**
	 * in条件查询.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_LIST_BY_IN} <br/>
	 * @Date	2018年3月1日 下午6:13:16 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 */
	List<?> selectListByIds(List<String> list);
	
	/**
	 * 查询所有.<br/>
	 * @Date	2018年3月1日 下午6:13:20 <br/>
	 * @author  zhangST
	 * @return
	 * @see #selectListBy(java.util.Map)
	 */
	List<?> selectAll();
	
	/**
	 * 单条查询.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_ONE_BY} <br/>
	 * @Date	2018年3月1日 下午6:13:23 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @return
	 */
	<PO> PO selectOne(Map<String, Object> paramMap);

	/**
	 * 单条插入.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_INSERT_ONE} <br/>
	 * @Date	2018年3月1日 下午6:13:27 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @return
	 */
	int insertOne(Map<String, Object> paramMap);

	/**
	 * 批量插入.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_INSERT_BATCH} <br/>
	 * @Date	2018年3月1日 下午6:13:30 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 */
	int insertBatch(List<?> list);

	/**
	 * 更新.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_UPDATE_BY} <br/>
	 * @Date	2018年3月1日 下午6:13:34 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @return
	 */
	int updateBy(Map<String, Object> paramMap);

	/**
	 * 批量更新.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_UPDATE_BATCH} <br/>
	 * @Date	2018年3月1日 下午6:13:37 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 */
	int updateBatch(List<?> list);

	/**
	 * 删除-主键.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_DELETE_BY_ID} <br/>
	 * @Date	2018年3月1日 下午6:13:41 <br/>
	 * @author  zhangST
	 * @param id
	 * @return
	 */
	int deleteById(String id);

	/**
	 * 删除-主键.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_DELETE_BY_IDS} <br/>
	 * @Date	2018年3月1日 下午6:13:44 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 */
	int deleteByIds(List<String> list);

	/**
	 * 删除.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_DELETE_BY} <br/>
	 * @Date	2018年3月1日 下午6:13:47 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @return
	 */
	int deleteBy(Map<String, Object> paramMap);

	/**
	 * 批量删除.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_DELETE_BATCH} <br/>
	 * @Date	2018年3月1日 下午6:13:50 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 */
	int deleteBatch(List<?> list);
	
}

