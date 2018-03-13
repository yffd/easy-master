package com.yffd.easy.framework.base.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yffd.easy.common.core.exception.EasyDaoException;
import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.common.core.util.EasyStringCheckUtils;

/**
 * @Description  持久化常用操作类.
 * @Date		 2018年1月29日 下午5:42:56 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Component
public class CommonDao extends BaseDaoAbstract {
    
	@Autowired
    private SqlSession sqlSession;
	
	@Override
	public SqlSession getSqlSession() {
		return this.sqlSession;
	}
	
	/**
	 * 统计查询.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_COUNT_BY} <br/>
	 * @Date	2018年3月2日 上午9:38:35 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @return
	 */
	public Long selectCountBy(Map<String, Object> paramMap) {
		if(null==paramMap) paramMap = new HashMap<String, Object>();
        return super.selectCountBy_(paramMap);
	}
	
	/**
	 * 分页查询，并返回分页数据、分页信息.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_LIST_BY} <br/>
	 * @sqlcountid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_COUNT_BY} <br/>
	 * @Date	2018年3月2日 上午9:39:21 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @param pageParam
	 * @return
	 */
	public PageResult<?> selectPage(Map<String, Object> paramMap, PageParam pageParam) {
		List<Object> recordList = this.selectList(paramMap, pageParam);
		return new PageResult<Object>(pageParam, recordList);
	}

	/**
	 * 列表查询.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_LIST_BY} <br/>
	 * @Date	2018年3月2日 上午9:39:37 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @return
	 */
	public List<?> selectListBy(Map<String, Object> paramMap) {
		return this.selectList(paramMap, null);
	}
	
	/**
	 * 单条查询.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_ONE_BY} <br/>
	 * @Date	2018年3月2日 上午9:44:57 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @return
	 */
	public <T> T selectOne(Map<String, Object> paramMap) {
		if(null==paramMap) paramMap = new HashMap<String, Object>();
		return super.selectOne_(paramMap);
	}

	/**
	 * 主键查询.<br/>
	 * @Date	2018年3月2日 上午9:41:17 <br/>
	 * @author  zhangST
	 * @param id
	 * @return
	 * @see #selectOne(Map)
	 */
	public <T> T selectById(String id) {
		if(EasyStringCheckUtils.isEmpty(id)) throw EasyDaoException.DB_SELECT_NULL(getStatement(SQL_SELECT_ONE_BY));
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		return this.selectOne(paramMap);
	}
	
	/**
	 * in条件查询.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_LIST_BY_IDS} <br/>
	 * @Date	2018年3月2日 上午9:41:35 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 */
	public List<?> selectListByIds(List<String> list) {
		if(null==list || list.size()==0) throw EasyDaoException.DB_SELECT_NULL(getStatement(SQL_SELECT_LIST_BY_IDS));
        return super.selectListByIds_(list);
	}
	
	/**
	 * 查询所有.<br/>
	 * @Date	2018年3月2日 上午9:43:31 <br/>
	 * @author  zhangST
	 * @return
	 * @see #selectListBy(java.util.Map)
	 */
	public List<?> selectAll() {
		return this.selectListBy(null);
	}
	
	/**
	 * 单条插入.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_INSERT_ONE} <br/>
	 * @Date	2018年3月2日 上午9:45:14 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @return
	 */
	public int insertOne(Map<String, Object> paramMap) {
		if(null==paramMap || paramMap.size()==0) throw EasyDaoException.DB_INSERT_NULL(getStatement(SQL_INSERT_ONE));
		int result = super.insertOne_(paramMap);
//        if(result <= 0) throw EasyDaoException.DB_INSERT_RESULT_0(getStatement(SQL_INSERT_ONE));
        return result;
	}

	/**
	 * 批量插入.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_INSERT_BATCH} <br/>
	 * @Date	2018年3月2日 上午9:45:25 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 */
	public int insertBatch(List<?> list) {
		if(null==list || list.size()==0) throw EasyDaoException.DB_INSERT_NULL(getStatement(SQL_INSERT_BATCH));
        int result = super.insertBatch_(list);
//        if(result <= 0) throw EasyDaoException.DB_INSERT_RESULT_0(getStatement(SQL_INSERT_BATCH));
        return result;
	}

	/**
	 * 更新.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_UPDATE_BY} <br/>
	 * @Date	2018年3月2日 上午9:45:41 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @return
	 */
	public int updateBy(Map<String, Object> paramMap) {
		if(null==paramMap || paramMap.size()==0) throw EasyDaoException.DB_UPDATE_NULL(getStatement(SQL_UPDATE_BY));
        int result = super.updateBy_(paramMap);
//        if(result <= 0) throw EasyDaoException.DB_UPDATE_RESULT_0(getStatement(SQL_UPDATE_BY));
        return result;
	}

	/**
	 * 批量更新.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_UPDATE_BATCH} <br/>
	 * @Date	2018年3月2日 上午9:45:56 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 */
	public int updateBatch(List<?> list) {
		if(null==list || list.size()==0) throw EasyDaoException.DB_UPDATE_NULL(getStatement(SQL_UPDATE_BATCH));
        int result = super.updateBatch_(list);
//        if(result <= 0) throw EasyDaoException.DB_UPDATE_RESULT_0(getStatement(SQL_UPDATE_BATCH));
        return result;
	}

	/**
	 * 删除.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_DELETE_BY} <br/>
	 * @Date	2018年3月2日 上午9:47:23 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @return
	 */
	public int deleteBy(Map<String, Object> paramMap) {
		if(null==paramMap || paramMap.size()==0) throw EasyDaoException.DB_DELETE_NULL(getStatement(SQL_DELETE_BY));
		int result = super.deleteBy_(paramMap);
//		if(result <= 0) throw EasyDaoException.DB_DELETE_RESULT_0(getStatement(SQL_DELETE_BY));
        return result;
	}
	
	/**
	 * 删除-主键.<br/>
	 * @Date	2018年3月2日 上午9:46:08 <br/>
	 * @author  zhangST
	 * @param id
	 * @return
	 * @see #deleteBy(Map)
	 */
	public int deleteById(String id) {
		if(EasyStringCheckUtils.isEmpty(id)) throw EasyDaoException.DB_DELETE_NULL(getStatement(SQL_DELETE_BY));
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		int result = this.deleteBy(paramMap);
//		if(result <= 0) throw EasyDaoException.DB_DELETE_RESULT_0(getStatement(SQL_DELETE_BY));
        return result;
	}
	
	/**
	 * 删除.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_DELETE_WITH_IN_BY} <br/>
	 * @Date	2018年3月12日 下午4:49:38 <br/>
	 * @author  zhangST
	 * @param paramMap			其它条件
	 * @param inName			in条件名称
	 * @param inValueList		in条件值
	 * @return
	 */
	public int deleteWithInBy(Map<String, Object> paramMap, String inName, List<?> inValueList) {
		if(EasyStringCheckUtils.isEmpty(inName) || null==inValueList || inValueList.size()==0) 
			throw EasyDaoException.DB_DELETE_NULL(getStatement(SQL_DELETE_WITH_IN_BY));
		Map<String, Object> inMap = new HashMap<String, Object>();
		inMap.put(inName, inValueList);
		int result = super.deleteWithInBy_(paramMap, inMap);
//		if(result <= 0) throw EasyDaoException.DB_DELETE_RESULT_0(getStatement(SQL_DELETE_WITH_IN_BY));
		return result;
	}
	
	/**
	 * 删除.<br/>
	 * @Date	2018年3月12日 下午4:49:38 <br/>
	 * @author  zhangST
	 * @param inName			in条件名称
	 * @param inValueList		in条件值，list中的类型为能转换成key-value对形式，如 Map、自定义实体类等
	 * @return
	 * @see #deleteWithInBy(Map, String, List)
	 */
	public int deleteWithInBy(String inName, List<?> inValueList) {
		int result = this.deleteWithInBy(null, inName, inValueList);
		return result;
	}
	
	/**
	 * 删除-主键.<br/>
	 * @Date	2018年3月2日 上午9:46:42 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 * @see #deleteBy(String, List)
	 */
	public int deleteByIds(List<String> list) {
		List<Map<String, Object>> paramList = new ArrayList<Map<String, Object>>();
		for(String id : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			paramList.add(map);
		}
		int result = this.deleteWithInBy(null, "id", paramList);
        return result;
	}
	
	private List<Object> selectList(Map<String, Object> paramMap, PageParam pageParam) {
		if (null==paramMap) paramMap = new HashMap<String, Object>();
		paramMap.remove("pageParam"); // 与 listRange区分，保证越过分页拦截器
		
		if(null!=pageParam) {
			// 统计总记录数
	        Long totalRecord = super.selectCountBy_(paramMap);
	        pageParam.setTotalRecord(totalRecord);
	        
			Long pageNum = pageParam.getPageNum();
			Long pageLimit = pageParam.getPageLimit();
			
			// 校验当前页码值的有效范围
			pageNum = pageParam.countPageNum(pageNum);
			pageNum = pageParam.countPageNum(pageNum, pageLimit, totalRecord);
			pageParam.setPageNum(pageNum); // 重新设值
			
			// 校验每页码值的有效范围
			pageLimit = pageParam.countPageLimit(pageLimit);
			pageParam.setPageLimit(pageLimit); // 重新设值
			
			pageParam.setPageStartRow(pageParam.countPageStartRow(pageNum, pageLimit));
			pageParam.setPageEndRow(pageParam.countPageEndRow(pageNum, pageLimit));
			
			// 根据页面传来的分页参数构造SQL分页参数
			paramMap.put("pageParam", pageParam);
		} else {
			paramMap.put("pageParam", null);
		}
		// 获取分页数据集
		List<Object> recordList = super.selectRangeBy_(paramMap);
		return recordList;
	}
	
}

