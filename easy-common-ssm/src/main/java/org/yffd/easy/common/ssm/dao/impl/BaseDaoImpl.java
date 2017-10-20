package org.yffd.easy.common.ssm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.yffd.easy.common.core.exception.DaoException;
import org.yffd.easy.common.core.model.PersistModel;
import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;
import org.yffd.easy.common.core.util.ValidUtils;
import org.yffd.easy.common.ssm.dao.IBaseDao;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月14日 下午4:22:06 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class BaseDaoImpl<T extends PersistModel> implements IBaseDao<T> {

	/** mapper xml 中的SQL ID，即statement=selectRange */
    public static final String SQL_SELECT_RANGE = "selectRange";
    /** mapper xml 中的SQL ID，即statement=selectPage */
    public static final String SQL_SELECT_PAGE = "selectPage";
    /** mapper xml 中的SQL ID，即statement=selectCountBy */
    public static final String SQL_SELECT_COUNT_BY = "selectCountBy";
    
    /** mapper xml 中的SQL ID，即statement=selectByPK */
    public static final String SQL_SELECT_BY_PK = "selectByPK";
    /** mapper xml 中的SQL ID，即statement=selectListBy */
    public static final String SQL_SELECT_LIST_BY = "selectListBy";
    
    /** mapper xml 中的SQL ID，即statement=insert */
    public static final String SQL_INSERT = "insert";
    /** mapper xml 中的SQL ID，即statement=insert */
    public static final String SQL_INSERT_BATCH = "insertBatch";
    
    /** mapper xml 中的SQL ID，即statement=updateBy */
    public static final String SQL_UPDATE_BY = "updateBy";
    /** mapper xml 中的SQL ID，即statement=updateByPK */
    public static final String SQL_UPDATE_BY_PK = "updateByPK";
    /** mapper xml 中的SQL ID，即statement=updateBatch */
    public static final String SQL_UPDATE_BATCH = "updateBatch";
    
    /** mapper xml 中的SQL ID，即statement=deleteByPK */
    public static final String SQL_DELETE_BY_PK = "deleteByPK";
    /** mapper xml 中的SQL ID，即statement=deleteBy */
    public static final String SQL_DELETE_BY = "deleteBy";
    /** mapper xml 中的SQL ID，即statement=deleteBatch */
    public static final String SQL_DELETE_BATCH = "deleteBatch";
    
	@Autowired
    private SqlSession sqlSession;
	
	/**
	 * sqlid : {@link org.yffd.easy.common.ssm.dao.impl.BaseDaoImpl#SQL_SELECT_RANGE}
	 * @Date	2017年9月14日 下午4:25:23 <br/>
	 * @author  zhangST
	 * @param pageParam
	 * @param paramMap
	 * @return
	 * @see org.yffd.easy.common.ssm.dao.IBaseDao#selectRange(org.yffd.easy.common.core.page.PageParam, java.util.Map)
	 */
//	@Override
//	public PageResult<T> selectRange(PageParam pageParam, Map<String, Object> paramMap) {
//		if (ValidUtils.isNull(paramMap)) {
//            paramMap = new HashMap<String, Object>();
//        }
//        paramMap.put("pageParam", pageParam);
//        List<T> recordList = sqlSession.selectList(getStatement(SQL_SELECT_RANGE), paramMap);
//        return new PageResult<T>(pageParam, recordList);
//	}

	/**
	 * sqlid : {@link org.yffd.easy.common.ssm.dao.impl.BaseDaoImpl#SQL_SELECT_RANGE}
	 * count sqlid : {@link org.yffd.easy.common.ssm.dao.impl.BaseDaoImpl#SQL_SELECT_COUNT_BY}
	 * @Date	2017年9月14日 下午4:29:52 <br/>
	 * @author  zhangST
	 * @param pageParam
	 * @param paramMap
	 * @return
	 * @see org.yffd.easy.common.ssm.dao.IBaseDao#selectPage(org.yffd.easy.common.core.page.PageParam, java.util.Map)
	 */
	@Override
	public PageResult<T> selectPage(PageParam pageParam, Map<String, Object> paramMap) {
		if (ValidUtils.isNull(paramMap)) {
            paramMap = new HashMap<String, Object>();
        }
        paramMap.remove("pageParam"); // 与 listRange区分，保证越过分页拦截器
        // 统计总记录数
        Long totalRecord = sqlSession.selectOne(getStatement(SQL_SELECT_COUNT_BY), paramMap);
        pageParam.setTotalRecord(totalRecord);
        // 校验当前页数
        Long currentPage = PageParam.checkCurrentPage(totalRecord, pageParam.getNumPerPage(), pageParam.getCurrentPage());
        pageParam.setCurrentPage(currentPage); // 为当前页重新设值
        // 校验页面输入的每页记录数numPerPage是否合法
        Long numPerPage = PageParam.checkNumPerPage(pageParam.getNumPerPage()); // 校验每页记录数
        pageParam.setNumPerPage(numPerPage); // 重新设值

        pageParam.setTotalPage(PageParam.countTotalPage(totalRecord, numPerPage));
        pageParam.setStartRowNum(PageParam.countStartRowNum(pageParam.getCurrentPage(), pageParam.getNumPerPage()));
        pageParam.setEndRowNum(PageParam.countEndRowNum(pageParam.getCurrentPage(), pageParam.getNumPerPage()));
        // 根据页面传来的分页参数构造SQL分页参数
        paramMap.put("numPerPage", pageParam.getNumPerPage());
        paramMap.put("startRowNum", pageParam.getStartRowNum());

        // 获取分页数据集
        List<T> recordList = sqlSession.selectList(getStatement(SQL_SELECT_PAGE), paramMap);

        return new PageResult<T>(pageParam, recordList);
	}

	/**
	 * sqlid : {@link org.yffd.easy.common.ssm.dao.impl.BaseDaoImpl#SQL_SELECT_COUNT_BY}
	 * @Date	2017年9月14日 下午4:31:15 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @return
	 * @see org.yffd.easy.common.ssm.dao.IBaseDao#selectCountBy(java.util.Map)
	 */
	@Override
	public Long selectCountBy(Map<String, Object> paramMap) {
		if (ValidUtils.isNull(paramMap)) {
			paramMap = new HashMap<String, Object>();
        }
        return sqlSession.selectOne(getStatement(SQL_SELECT_COUNT_BY), paramMap);
	}

	/**
	 * sqlid : {@link org.yffd.easy.common.ssm.dao.impl.BaseDaoImpl#SQL_SELECT_BY_PK}
	 * @Date	2017年9月14日 下午4:34:37 <br/>
	 * @author  zhangST
	 * @param primaryKey
	 * @return
	 * @see org.yffd.easy.common.ssm.dao.IBaseDao#selectByPK(java.lang.String)
	 */
	@Override
	public T selectByPK(String primaryKey) {
		return sqlSession.selectOne(getStatement(SQL_SELECT_BY_PK), primaryKey);
	}
	
	/**
	 * sqlid : {@link org.yffd.easy.common.ssm.dao.impl.BaseDaoImpl#SQL_SELECT_LIST_BY}
	 * @Date	2017年9月14日 下午4:37:59 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @return
	 * @see org.yffd.easy.common.ssm.dao.IBaseDao#selectListBy(java.util.Map)
	 */
	@Override
	public List<T> selectListBy(Map<String, Object> paramMap) {
		if (ValidUtils.isNull(paramMap)) {
			paramMap = new HashMap<String, Object>();
        }
        return sqlSession.selectList(getStatement(SQL_SELECT_LIST_BY), paramMap);
	}

	/**
	 * sqlid : {@link org.yffd.easy.common.ssm.dao.impl.BaseDaoImpl#SQL_SELECT_LIST_BY} <br/>
	 * 注：{@link org.yffd.easy.common.ssm.dao.impl.BaseDaoImpl#selectListBy}方法的特例
	 * @Date	2017年9月14日 下午4:43:20 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @return
	 * @see org.yffd.easy.common.ssm.dao.IBaseDao#selectBy(java.util.Map)
	 */
	@Override
	public T selectBy(Map<String, Object> paramMap) {
		if (ValidUtils.isNull(paramMap)) {
			paramMap = new HashMap<String, Object>();
        }
        return sqlSession.selectOne(getStatement(SQL_SELECT_LIST_BY), paramMap);
	}

	/**
	 * sqlid : {@link org.yffd.easy.common.ssm.dao.impl.BaseDaoImpl#SQL_SELECT_LIST_BY} <br/>
	 * 注：{@link org.yffd.easy.common.ssm.dao.impl.BaseDaoImpl#selectListBy}方法的特例
	 * @Date	2017年9月14日 下午4:39:52 <br/>
	 * @author  zhangST
	 * @return
	 * @see org.yffd.easy.common.ssm.dao.IBaseDao#selectAll()
	 */
	@Override
	public List<T> selectAll() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return this.selectListBy(paramMap);
	}

	/**
	 * sqlid : {@link org.yffd.easy.common.ssm.dao.impl.BaseDaoImpl#SQL_INSERT} <br/>
	 * @Date	2017年9月14日 下午4:50:11 <br/>
	 * @author  zhangST
	 * @param model
	 * @return
	 * @see org.yffd.easy.common.ssm.dao.IBaseDao#insert(java.lang.Object)
	 */
	@Override
	public int insert(T model) {
		int result = sqlSession.insert(getStatement(SQL_INSERT), model);
        if (result <= 0) {
            throw DaoException.DB_INSERT_RESULT_0(getStatement(SQL_INSERT));
        }
        return result;
	}

	/**
	 * sqlid : {@link org.yffd.easy.common.ssm.dao.impl.BaseDaoImpl#SQL_INSERT_BATCH} <br/>
	 * @Date	2017年9月14日 下午4:51:31 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 * @see org.yffd.easy.common.ssm.dao.IBaseDao#insertBatch(java.util.List)
	 */
	@Override
	public int insertBatch(List<T> list) {
		if (ValidUtils.isEmpty(list)) {
            return 0;
        }
        int result = sqlSession.insert(getStatement(SQL_INSERT_BATCH), list);
        if (result <= 0) {
            throw DaoException.DB_INSERT_RESULT_0(getStatement(SQL_INSERT_BATCH));
        }
        return result;
	}

	/**
	 * sqlid : {@link org.yffd.easy.common.ssm.dao.impl.BaseDaoImpl#SQL_UPDATE_BY} <br/>
	 * @Date	2017年9月14日 下午4:54:08 <br/>
	 * @author  zhangST
	 * @param model
	 * @return
	 * @see org.yffd.easy.common.ssm.dao.IBaseDao#updateBy(java.lang.Object)
	 */
	@Override
	public int updateBy(T model) {
        int result = sqlSession.update(getStatement(SQL_UPDATE_BY), model);
        if (result <= 0) {
            throw DaoException.DB_UPDATE_RESULT_0(getStatement(SQL_UPDATE_BY));
        }
        return result;
	}

	/**
	 * sqlid : {@link org.yffd.easy.common.ssm.dao.impl.BaseDaoImpl#SQL_UPDATE_BY_PK} <br/>
	 * @Date	2017年9月14日 下午4:54:08 <br/>
	 * @author  zhangST
	 * @param model
	 * @return
	 * @see org.yffd.easy.common.ssm.dao.IBaseDao#updateByPK(java.lang.Object)
	 */
	@Override
	public int updateByPK(T model) {
		int result = sqlSession.update(getStatement(SQL_UPDATE_BY_PK), model);
        if (result <= 0) {
            throw DaoException.DB_UPDATE_RESULT_0(getStatement(SQL_UPDATE_BY_PK));
        }
        return result;
	}

	/**
	 * sqlid : {@link org.yffd.easy.common.ssm.dao.impl.BaseDaoImpl#SQL_UPDATE_BATCH} <br/>
	 * @Date	2017年9月14日 下午5:16:46 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 * @see org.yffd.easy.common.ssm.dao.IBaseDao#updateBatch(java.util.List)
	 */
	@Override
	public int updateBatch(List<T> list) {
		if (ValidUtils.isEmpty(list)) {
            return 0;
        }
        int result = sqlSession.update(getStatement(SQL_UPDATE_BATCH), list);
        if (result <= 0) {
            throw DaoException.DB_UPDATE_RESULT_0(getStatement(SQL_UPDATE_BY_PK));
        }
        return result;
	}

	/**
	 * sqlid : {@link org.yffd.easy.common.ssm.dao.impl.BaseDaoImpl#SQL_DELETE_BY_PK} <br/>
	 * @Date	2017年9月14日 下午5:17:04 <br/>
	 * @author  zhangST
	 * @param primaryKey
	 * @return
	 * @see org.yffd.easy.common.ssm.dao.IBaseDao#deleteByPK(java.lang.String)
	 */
	@Override
	public int deleteByPK(String primaryKey) {
		if (ValidUtils.isEmpty(primaryKey)) {
            return 0;
        }
		int result = sqlSession.delete(getStatement(SQL_DELETE_BY_PK), primaryKey);
		if (result <= 0) {
            throw DaoException.DB_DELETE_RESULT_0(getStatement(SQL_UPDATE_BY_PK));
        }
        return result;
	}

	/**
	 * sqlid : {@link org.yffd.easy.common.ssm.dao.impl.BaseDaoImpl#SQL_DELETE_BY} <br/>
	 * @Date	2017年9月14日 下午5:17:21 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @return
	 * @see org.yffd.easy.common.ssm.dao.IBaseDao#deleteBy(java.util.Map)
	 */
	@Override
	public int deleteBy(Map<String, Object> paramMap) {
		if (ValidUtils.isEmpty(paramMap)) {
            return 0;
        }
		int result = sqlSession.delete(getStatement(SQL_DELETE_BY), paramMap);
		if (result <= 0) {
            throw DaoException.DB_DELETE_RESULT_0(getStatement(SQL_UPDATE_BY_PK));
        }
        return result;
	}

	/**
	 * sqlid : {@link org.yffd.easy.common.ssm.dao.impl.BaseDaoImpl#SQL_DELETE_BATCH} <br/>
	 * @Date	2017年9月14日 下午5:17:38 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 * @see org.yffd.easy.common.ssm.dao.IBaseDao#deleteBatch(java.util.List)
	 */
	@Override
	public int deleteBatch(List<T> list) {
		if (ValidUtils.isEmpty(list)) {
            return 0;
        }
		int result = sqlSession.delete(getStatement(SQL_DELETE_BATCH), list);
		if (result <= 0) {
            throw DaoException.DB_DELETE_RESULT_0(getStatement(SQL_UPDATE_BY_PK));
        }
        return result;
	}

	@Override
	public String getStatement(String sqlId) {
		String name = this.getClass().getName();
        // 单线程用StringBuilder，确保速度；多线程用StringBuffer,确保安全
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(".").append(sqlId);
        return sb.toString();
	}

	@Override
	public SqlSession getSqlSession() {
		return this.sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

}

