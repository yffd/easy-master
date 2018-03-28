package com.yffd.easy.framework.base.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.yffd.easy.common.core.converter.EasyModelConverter;
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
public abstract class BaseDaoAbstract extends EasyModelConverter {
	
	/** mapper xml 中的SQL ID，即statement=insertOne */
    public static final String SQL_INSERT_ONE = "insertOne";
    /** mapper xml 中的SQL ID，即statement=insert */
    public static final String SQL_INSERT_BATCH = "insertBatch";
    
    /** mapper xml 中的SQL ID，即statement=selectCountBy */
    public static final String SQL_SELECT_COUNT_BY = "selectCountBy";

    /** mapper xml 中的SQL ID，即statement=selectByPK */
    public static final String SQL_SELECT_ONE_BY = "selectOneBy";
    /** mapper xml 中的SQL ID，即statement=selectListBy */
    public static final String SQL_SELECT_LIST_BY = "selectListBy";
    /** mapper xml 中的SQL ID，即statement=selectListByIds */
//    public static final String SQL_SELECT_LIST_BY_IDS = "selectListByIds";

    /** mapper xml 中的SQL ID，即statement=updateBy */
    public static final String SQL_UPDATE_BY = "updateBy";
    /** mapper xml 中的SQL ID，即statement=updateBatch */
//    public static final String SQL_UPDATE_BATCH = "updateBatch";
    
    /** mapper xml 中的SQL ID，即statement=deleteBy */
    public static final String SQL_DELETE_BY = "deleteBy";
    
    public abstract SqlSession getSqlSession();
    
	protected Long selectCountBy_(Object parameter) {
        return this.getSqlSession().selectOne(getStatement(SQL_SELECT_COUNT_BY), parameter);
	}

	protected <T> List<T> selectListBy_(Object parameter) {
		return this.getSqlSession().selectList(getStatement(SQL_SELECT_LIST_BY), parameter);
	}

	protected <T> T selectOne_(Object parameter) {
		Object o =this.getSqlSession().selectOne(getStatement(SQL_SELECT_ONE_BY), parameter);
		return this.getSqlSession().selectOne(getStatement(SQL_SELECT_ONE_BY), parameter);
	}

	protected int insertOne_(Object parameter) {
		return this.getSqlSession().insert(getStatement(SQL_INSERT_ONE), parameter);
	}

	protected int insertBatch_(Object parameter) {
		return this.getSqlSession().insert(getStatement(SQL_INSERT_BATCH), parameter);
	}

	protected int updateBy_(Object parameter) {
		return this.getSqlSession().update(getStatement(SQL_UPDATE_BY), parameter);
	}

//	protected int updateBatch_(Object parameter) {
//		return this.getSqlSession().update(getStatement(SQL_UPDATE_BATCH), parameter);
//	}

	protected int deleteBy_(Object parameter) {
		return this.getSqlSession().delete(getStatement(SQL_DELETE_BY), parameter);
	}
	
	/*************************************************************************************/
	/*************************************************************************************/
	
	protected <T> PageResult<T> selectPage(Map<String, Object> paramMap, PageParam pageParam, String sqlId, String countSqlId) {
		List<Object> recordList = this.selectRangeList(paramMap, pageParam, sqlId, countSqlId); // 获取分页数据集
		return new PageResult(pageParam, recordList);
	}
	
	protected <T> List<T> selectRangeList(Map<String, Object> paramMap, PageParam pageParam, String sqlId, String countSqlId) {
		if(null==paramMap) paramMap = new HashMap<String, Object>();
		if(null!=pageParam) {
			Long totalRecord = this.getSqlSession().selectOne(countSqlId, paramMap); // 统计总记录数
			pageParam.setTotalRecord(totalRecord);
			paramMap.put("pageParam", pageParam); // 根据页面传来的分页参数构造SQL分页参数
		} else {
			paramMap.put("pageParam", null);
		}
		List<T> recordList = this.getSqlSession().selectList(sqlId, paramMap); // 获取分页数据集
		return recordList;
	}
	
	protected String getStatement(String sqlId) {
		String name = this.getClass().getName();
        // 单线程用StringBuilder，确保速度；多线程用StringBuffer,确保安全
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(".").append(sqlId);
        return sb.toString();
	}
	

}

