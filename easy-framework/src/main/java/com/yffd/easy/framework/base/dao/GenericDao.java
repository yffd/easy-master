package com.yffd.easy.framework.base.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yffd.easy.common.core.exception.EasyDaoException;
import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年3月1日 下午6:32:35 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Component
public class GenericDao<PO> extends CommonDao {
	@Autowired
    private SqlSession sqlSession;
	
	@Override
	public SqlSession getSqlSession() {
		return this.sqlSession;
	}

	/**
	 * 统计查询.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_COUNT_BY} <br/>
	 * @Date	2018年3月2日 上午10:17:44 <br/>
	 * @author  zhangST
	 * @param model
	 * @return
	 */
	public Long selectCountBy(PO model) {
		Map<String, Object> paramMap = this.model2map(model, null);
		return super.selectCountBy(paramMap);
	}
	
	/**
	 * 分页查询，并返回分页数据、分页信息.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_LIST_BY} <br/>
	 * @sqlcountid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_COUNT_BY} <br/>
	 * @Date	2018年3月2日 上午10:18:22 <br/>
	 * @author  zhangST
	 * @param model
	 * @param pageParam
	 * @return
	 */
	public PageResult<?> selectPage(PO model, PageParam pageParam) {
		Map<String, Object> paramMap = this.model2map(model, null);
		return super.selectPage(paramMap, pageParam);
	}

	/**
	 * 列表查询.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_LIST_BY} <br/>
	 * @Date	2018年3月2日 上午10:18:43 <br/>
	 * @author  zhangST
	 * @param model
	 * @return
	 */
	public List<?> selectListBy(PO model) {
		Map<String, Object> paramMap = this.model2map(model, null);
		return super.selectListBy(paramMap);
	}

	/**
	 * 单条查询.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_ONE_BY} <br/>
	 * @Date	2018年3月2日 上午10:19:04 <br/>
	 * @author  zhangST
	 * @param model
	 * @return
	 */
	public PO selectOne(PO model) {
		if(null==model) return null;
		Map<String, Object> paramMap = this.model2map(model, null);
		return super.selectOne(paramMap);
	}

	/**
	 * 单条插入.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_INSERT_ONE} <br/>
	 * @Date	2018年3月2日 上午10:19:17 <br/>
	 * @author  zhangST
	 * @param model
	 * @return
	 */
	public int insertOne(PO model) {
		if(null==model) throw EasyDaoException.DB_INSERT_NULL(getStatement(SQL_INSERT_ONE));
		Map<String, Object> paramMap = this.model2map(model, null);
		int result = super.insertOne(paramMap);
//        if(result <= 0) throw EasyDaoException.DB_INSERT_RESULT_0(getStatement(SQL_INSERT_ONE));
        return result;
	}

	/**
	 * 更新.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_UPDATE_BY} <br/>
	 * @Date	2018年3月2日 上午10:19:31 <br/>
	 * @author  zhangST
	 * @param model
	 * @return
	 */
	public int updateBy(PO model) {
		if(null==model) throw EasyDaoException.DB_UPDATE_NULL(getStatement(SQL_UPDATE_BY));
		Map<String, Object> paramMap = this.model2map(model, null);
        int result = super.updateBy(paramMap);
//        if(result <= 0) throw EasyDaoException.DB_UPDATE_RESULT_0(getStatement(SQL_UPDATE_BY));
        return result;
	}

	/**
	 * 删除.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_DELETE_BY} <br/>
	 * @Date	2018年3月2日 上午10:20:01 <br/>
	 * @author  zhangST
	 * @param model
	 * @return
	 */
	public int deleteBy(PO model) {
		if(null==model) throw EasyDaoException.DB_DELETE_NULL(getStatement(SQL_DELETE_BY));
		Map<String, Object> paramMap = this.model2map(model, null);
		int result = super.deleteBy(paramMap);
//		if(result <= 0) throw EasyDaoException.DB_DELETE_RESULT_0(getStatement(SQL_DELETE_BY));
        return result;
	}
	
}

