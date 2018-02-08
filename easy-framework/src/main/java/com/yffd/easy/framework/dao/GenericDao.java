package com.yffd.easy.framework.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.yffd.easy.common.core.converter.EasyModelConverter;
import com.yffd.easy.common.core.exception.EasyDaoException;
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
public class GenericDao extends EasyModelConverter implements IGenericDao {
    
	@Autowired
    private SqlSession sqlSession;
	
	public PageResult<Object> selectPage(Object parameter, PageParam pageParam) {
		Map<String, Object> paramMap = null;
		if(parameter instanceof Map) {
			paramMap = (Map<String, Object>) parameter;
		} else {
			paramMap = paramMap = this.model2map(parameter, null);
		}
		return this.selectPage(paramMap, pageParam);
	}
	
	public Long selectCountBy(Object parameter) {
		if (null==parameter) return this.getSqlSession().selectOne(getStatement(SQL_SELECT_COUNT_BY));
        return this.getSqlSession().selectOne(getStatement(SQL_SELECT_COUNT_BY), parameter);
	}
	
	public List<?> selectListBy(Object parameter) {
		if (null==parameter) return this.getSqlSession().selectList(getStatement(SQL_SELECT_LIST_BY));
        return this.getSqlSession().selectList(getStatement(SQL_SELECT_LIST_BY), parameter);
	}

	public List<?> selectListByIn(Object parameter) {
		if (null==parameter) throw EasyDaoException.DB_SELECT_NULL(getStatement(SQL_INSERT_ONE));
        return this.getSqlSession().selectList(getStatement(SQL_SELECT_LIST_BY_IN), parameter);
	}

	public <T> T selectOne(Object parameter) {
		if (null==parameter) return this.getSqlSession().selectOne(getStatement(SQL_SELECT_ONE_BY));
		return this.getSqlSession().selectOne(getStatement(SQL_SELECT_ONE_BY), parameter);
	}
	
	public int insertOne(Object parameter) {
		if (null==parameter) throw EasyDaoException.DB_INSERT_NULL(getStatement(SQL_INSERT_ONE));
		int result = this.getSqlSession().insert(getStatement(SQL_INSERT_ONE), parameter);
        if (result <= 0) throw EasyDaoException.DB_INSERT_RESULT_0(getStatement(SQL_INSERT_ONE));
        return result;
	}

	public int insertBatch(List<?> list) {
		if (null==list || list.size()==0) throw EasyDaoException.DB_INSERT_NULL(getStatement(SQL_INSERT_BATCH));
        int result = this.getSqlSession().insert(getStatement(SQL_INSERT_BATCH), list);
        if (result <= 0) throw EasyDaoException.DB_INSERT_RESULT_0(getStatement(SQL_INSERT_BATCH));
        return result;
	}

	public int updateBy(Object parameter) {
		if (null==parameter) throw EasyDaoException.DB_UPDATE_NULL(getStatement(SQL_UPDATE_BY));
        int result = this.getSqlSession().update(getStatement(SQL_UPDATE_BY), parameter);
        if (result <= 0) throw EasyDaoException.DB_UPDATE_RESULT_0(getStatement(SQL_UPDATE_BY));
        return result;
	}

	public int updateBatch(List<?> list) {
		if (null==list || list.size()==0) throw EasyDaoException.DB_UPDATE_NULL(getStatement(SQL_UPDATE_BATCH));
        int result = this.getSqlSession().update(getStatement(SQL_UPDATE_BATCH), list);
        if (result <= 0) throw EasyDaoException.DB_UPDATE_RESULT_0(getStatement(SQL_UPDATE_BATCH));
        return result;
	}

	public int deleteBy(Object parameter) {
		if (null==parameter) throw EasyDaoException.DB_DELETE_NULL(getStatement(SQL_DELETE_BY));
		int result = this.getSqlSession().delete(getStatement(SQL_DELETE_BY), parameter);
		if (result <= 0) throw EasyDaoException.DB_DELETE_RESULT_0(getStatement(SQL_DELETE_BY));
        return result;
	}

	public int deleteBatch(List<?> list) {
		if (null==list || list.size()==0) throw EasyDaoException.DB_DELETE_NULL(getStatement(SQL_DELETE_BATCH));
		int result = this.getSqlSession().delete(getStatement(SQL_DELETE_BATCH), list);
		if (result <= 0) throw EasyDaoException.DB_DELETE_RESULT_0(getStatement(SQL_DELETE_BATCH));
        return result;
	}
	
	public List<?> selectAll() {
		return this.selectListBy(null);
	}
	
	private PageResult<Object> selectPage(Map<String, Object> paramMap, PageParam pageParam) {
		if (null==paramMap) paramMap = new HashMap<String, Object>();
        paramMap.remove("pageParam"); // 与 listRange区分，保证越过分页拦截器
        // 统计总记录数
        Long totalRecord = this.getSqlSession().selectOne(getStatement(SQL_SELECT_COUNT_BY), paramMap);
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
        paramMap.put("pageLimit", pageParam.getPageLimit());
        paramMap.put("pageStartRow", pageParam.getPageStartRow());

        // 获取分页数据集
        List<Object> recordList = this.getSqlSession().selectList(getStatement(SQL_SELECT_LIST_BY), paramMap);

        return new PageResult<Object>(pageParam, recordList);
	}
	
	protected String getStatement(String sqlId) {
		String name = this.getClass().getName();
        // 单线程用StringBuilder，确保速度；多线程用StringBuffer,确保安全
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(".").append(sqlId);
        return sb.toString();
	}

	protected SqlSession getSqlSession() {
		return this.sqlSession;
	}

}

