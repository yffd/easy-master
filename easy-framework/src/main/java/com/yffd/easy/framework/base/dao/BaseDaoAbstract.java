package com.yffd.easy.framework.base.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yffd.easy.common.core.converter.EasyModelConverter;

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
    /** mapper xml 中的SQL ID，即statement=selectRangeBy */
    public static final String SQL_SELECT_RANGE_BY = "selectRangeBy";
    /** mapper xml 中的SQL ID，即statement=selectListBy */
    public static final String SQL_SELECT_LIST_BY = "selectListBy";
    /** mapper xml 中的SQL ID，即statement=selectListByIds */
    public static final String SQL_SELECT_LIST_BY_IDS = "selectListByIds";

    /** mapper xml 中的SQL ID，即statement=updateBy */
    public static final String SQL_UPDATE_BY = "updateBy";
    /** mapper xml 中的SQL ID，即statement=updateBatch */
    public static final String SQL_UPDATE_BATCH = "updateBatch";
    
    /** mapper xml 中的SQL ID，即statement=deleteById */
    public static final String SQL_DELETE_BY_ID = "deleteById";
    /** mapper xml 中的SQL ID，即statement=deleteByIds */
    public static final String SQL_DELETE_BY_IDS = "deleteByIds";
    /** mapper xml 中的SQL ID，即statement=deleteBy */
    public static final String SQL_DELETE_BY = "deleteBy";
    
    public abstract SqlSession getSqlSession();
    
	protected Long selectCountBy_(Object parameter) {
        return this.getSqlSession().selectOne(getStatement(SQL_SELECT_COUNT_BY), parameter);
	}

	protected <T> List<T> selectRangeBy_(Object parameter) {
		return this.getSqlSession().selectList(getStatement(SQL_SELECT_RANGE_BY), parameter);
	}
	
	protected <T> List<T> selectListBy_(Object parameter) {
		return this.getSqlSession().selectList(getStatement(SQL_SELECT_LIST_BY), parameter);
	}

	protected <T> List<T> selectListByIds_(Object parameter) {
        return this.getSqlSession().selectList(getStatement(SQL_SELECT_LIST_BY_IDS), parameter);
	}

	protected <T> List<T> selectAll() {
		return this.selectListBy_(null);
	}
	
	protected <T> T selectOne_(Object parameter) {
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

	protected int updateBatch_(Object parameter) {
		return this.getSqlSession().update(getStatement(SQL_UPDATE_BATCH), parameter);
	}

	protected int deleteById_(String id) {
		return this.getSqlSession().delete(getStatement(SQL_DELETE_BY_ID), id);
	}

	protected int deleteByIds_(List<String> list) {
		return this.getSqlSession().delete(getStatement(SQL_DELETE_BY_IDS), list);
	}

	protected int deleteBy_(Object parameter) {
		return this.getSqlSession().delete(getStatement(SQL_DELETE_BY), parameter);
	}

	protected String getStatement(String sqlId) {
		String name = this.getClass().getName();
        // 单线程用StringBuilder，确保速度；多线程用StringBuffer,确保安全
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(".").append(sqlId);
        return sb.toString();
	}

}

