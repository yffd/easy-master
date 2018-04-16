package com.yffd.easy.framework.core.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.framework.core.common.converter.CommonTreeConverter;
import com.yffd.easy.framework.core.exception.BizException;

/**
 * @Description  持久化常用操作类.
 * @Date		 2018年1月29日 下午5:42:56 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class CommonServiceSupport extends CommonTreeConverter {
	
	@Autowired
    private SqlSession sqlSession;
	
	protected SqlSession getSqlSession() {
		return this.sqlSession;
	}
    
	protected Long selectCountBy(String sqlId, Object parameter, boolean shortName) {
		if(EasyStringCheckUtils.isEmpty(sqlId)) throw BizException.newInstance("sqlId 不能为空");
		if(shortName) sqlId = this.getStatement(sqlId);
        return this.getSqlSession().selectOne(sqlId, parameter);
	}

	protected <T> List<T> selectListBy(String sqlId, Object parameter, boolean shortName) {
		if(EasyStringCheckUtils.isEmpty(sqlId)) throw BizException.newInstance("sqlId 不能为空");
		if(shortName) sqlId = this.getStatement(sqlId);
		return this.getSqlSession().selectList(sqlId, parameter);
	}

	protected <T> T selectOneBy(String sqlId, Object parameter, boolean shortName) {
		if(EasyStringCheckUtils.isEmpty(sqlId)) throw BizException.newInstance("sqlId 不能为空");
		if(shortName) sqlId = this.getStatement(sqlId);
		return this.getSqlSession().selectOne(sqlId, parameter);
	}

	protected int insertBy(String sqlId, Object parameter, boolean shortName) {
		if(EasyStringCheckUtils.isEmpty(sqlId)) throw BizException.newInstance("sqlId 不能为空");
		if(shortName) sqlId = this.getStatement(sqlId);
		return this.getSqlSession().insert(sqlId, parameter);
	}

	protected int updateBy(String sqlId, Object parameter, boolean shortName) {
		if(EasyStringCheckUtils.isEmpty(sqlId)) throw BizException.newInstance("sqlId 不能为空");
		if(shortName) sqlId = this.getStatement(sqlId);
		return this.getSqlSession().update(sqlId, parameter);
	}

	protected int deleteBy(String sqlId, Object parameter, boolean shortName) {
		if(EasyStringCheckUtils.isEmpty(sqlId)) throw BizException.newInstance("sqlId 不能为空");
		if(shortName) sqlId = this.getStatement(sqlId);
		return this.getSqlSession().delete(sqlId, parameter);
	}
	
	/*************************************************************************************/
	/*************************************************************************************/
	
	protected <T> PageResult<T> selectPage(Map<String, Object> paramMap, PageParam pageParam, String sqlId, String countSqlId, boolean shortName) {
		if(EasyStringCheckUtils.isEmpty(sqlId) || EasyStringCheckUtils.isEmpty(countSqlId)) throw BizException.newInstance("sqlId或 countSqlId 不能为空");
		List<Object> recordList = this.selectRangeList(paramMap, pageParam, sqlId, countSqlId, shortName); // 获取分页数据集
		return new PageResult(pageParam, recordList);
	}
	
	protected <T> List<T> selectRangeList(Map<String, Object> paramMap, PageParam pageParam, String sqlId, String countSqlId, boolean shortName) {
		if(null==paramMap) paramMap = new HashMap<String, Object>();
		if(null!=pageParam) {
			if(EasyStringCheckUtils.isEmpty(countSqlId)) throw BizException.newInstance("countSqlId 不能为空");
			if(shortName) countSqlId = this.getStatement(countSqlId);
			Long totalRecord = this.getSqlSession().selectOne(countSqlId, paramMap); // 统计总记录数
			pageParam.setTotalRecord(totalRecord);
			paramMap.put("page", pageParam); // 根据页面传来的分页参数构造SQL分页参数
		}
		if(EasyStringCheckUtils.isEmpty(sqlId)) throw BizException.newInstance("sqlId 不能为空");
		if(shortName) sqlId = this.getStatement(sqlId);
		List<T> recordList = this.getSqlSession().selectList(sqlId, paramMap); // 获取分页数据集
		return recordList;
	}
	
	protected String getStatement(String sqlId) {
		String namespace = this.getClass().getName();
        StringBuilder sb = new StringBuilder();
        sb.append(namespace).append(".").append(sqlId);
        return sb.toString();
	}
	

}

