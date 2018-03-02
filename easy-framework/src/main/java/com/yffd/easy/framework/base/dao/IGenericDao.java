package com.yffd.easy.framework.base.dao;

import java.util.List;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.framework.domain.DomainModel;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年2月6日 下午4:42:08 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface IGenericDao<PO> extends ICommonDao {
	
	/**
	 * 统计查询.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_COUNT_BY} <br/>
	 * @Date	2017年9月14日 下午4:31:15 <br/>
	 * @author  zhangST
	 * @param model
	 * @return
	 */
	Long selectCountBy(PO model);
	
	/**
	 * 分页查询，并返回分页数据、分页信息.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_LIST_BY} <br/>
	 * @sqlcountid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_COUNT_BY} <br/>
	 * @Date	2017年9月14日 下午4:29:52 <br/>
	 * @author  zhangST
	 * @param model
	 * @param pageParam
	 * @return
	 */
	<T extends DomainModel> PageResult<?> selectPage(T model);
    
	/**
	 * 分页查询，并返回分页数据、分页信息.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_LIST_BY} <br/>
	 * @sqlcountid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_COUNT_BY} <br/>
	 * @Date	2017年9月14日 下午4:29:52 <br/>
	 * @author  zhangST
	 * @param model
	 * @param pageParam
	 * @return
	 */
	PageResult<?> selectPage(PO model, PageParam pageParam);
	
	/**
	 * 列表查询.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_LIST_BY} <br/>
	 * @Date	2017年9月14日 下午4:37:59 <br/>
	 * @author  zhangST
	 * @param model
	 * @return
	 */
	List<?> selectListBy(PO model);

	/**
	 * 单条查询.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_ONE_BY} <br/>
	 * @Date	2017年9月14日 下午4:34:37 <br/>
	 * @author  zhangST
	 * @param model
	 * @return
	 */
	PO selectOne(PO model);
	
	/**
	 * 单条插入.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_INSERT_ONE} <br/>
	 * @Date	2017年9月14日 下午4:50:11 <br/>
	 * @author  zhangST
	 * @param model
	 * @return
	 */
	int insertOne(PO model);

	/**
	 * 更新.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_UPDATE_BY} <br/>
	 * @Date	2017年9月14日 下午4:54:08 <br/>
	 * @author  zhangST
	 * @param model
	 * @return
	 */
	int updateBy(PO model);

	/**
	 * 物理删除.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_DELETE_BY} <br/>
	 * @Date	2017年9月14日 下午5:17:21 <br/>
	 * @author  zhangST
	 * @param model
	 * @return			返回影响的条数
	 */
	int deleteBy(PO model);

}

