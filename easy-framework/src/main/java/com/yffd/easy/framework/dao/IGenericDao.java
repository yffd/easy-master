package com.yffd.easy.framework.dao;

import java.util.List;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年2月6日 下午4:42:08 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface IGenericDao {
	
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
    /** mapper xml 中的SQL ID，即statement=selectListByIn */
    public static final String SQL_SELECT_LIST_BY_IN = "selectListByIn";

    /** mapper xml 中的SQL ID，即statement=updateBy */
    public static final String SQL_UPDATE_BY = "updateBy";
    /** mapper xml 中的SQL ID，即statement=updateBatch */
    public static final String SQL_UPDATE_BATCH = "updateBatch";
    
    /** mapper xml 中的SQL ID，即statement=deleteByPK */
    public static final String SQL_DELETE_BY_PK = "deleteByPK";
    /** mapper xml 中的SQL ID，即statement=deleteBy */
    public static final String SQL_DELETE_BY = "deleteBy";
    /** mapper xml 中的SQL ID，即statement=deleteBatch */
    public static final String SQL_DELETE_BATCH = "deleteBatch";
    
	/**
	 * 分页查询，并返回分页数据、分页信息。<br/>
	 * @sqlid : {@link #SQL_SELECT_LIST_BY}
	 * @sqlcountid : {@link #SQL_SELECT_COUNT_BY}
	 * @Date	2017年9月14日 下午4:29:52 <br/>
	 * @author  zhangST
	 * @param parameter
	 * @param pageParam
	 * @return
	 */
	public PageResult<Object> selectPage(Object parameter, PageParam pageParam);
	
	/**
	 * 统计总条数。<br/>
	 * @sqlid : {@link #SQL_SELECT_COUNT_BY}
	 * @Date	2017年9月14日 下午4:31:15 <br/>
	 * @author  zhangST
	 * @param parameter
	 * @return
	 */
	public Long selectCountBy(Object parameter);
	
	/**
	 * 列表查询。<br/>
	 * @sqlid : {@link #SQL_SELECT_LIST_BY}
	 * @Date	2017年9月14日 下午4:37:59 <br/>
	 * @author  zhangST
	 * @param parameter
	 * @return
	 */
	public List<?> selectListBy(Object parameter);

	/**
	 * in条件查询
	 * @sqlid : {@link #SQL_SELECT_LIST_BY_IN}
	 * @Date	2018年2月6日 下午6:06:16 <br/>
	 * @author  zhangST
	 * @param parameter
	 * @return
	 */
	public List<?> selectListByIn(Object parameter);

	/**
	 * 单条查询。<br/>
	 * @sqlid : {@link #SQL_SELECT_ONE_BY}
	 * @Date	2017年9月14日 下午4:34:37 <br/>
	 * @author  zhangST
	 * @param parameter
	 * @return
	 */
	public <T> T selectOne(Object parameter);
	
	/**
	 * 单条插入。<br/>
	 * @sqlid : {@link #SQL_INSERT_ONE} <br/>
	 * @Date	2017年9月14日 下午4:50:11 <br/>
	 * @author  zhangST
	 * @param parameter
	 * @return
	 */
	public int insertOne(Object parameter);

	/**
	 * 批量插入。<br/>
	 * @sqlid : {@link #SQL_INSERT_BATCH} <br/>
	 * @Date	2017年9月14日 下午4:51:31 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 */
	public int insertBatch(List<?> list);

	/**
	 * 更新。<br/>
	 * @sqlid : {@link #SQL_UPDATE_BY} <br/>
	 * @Date	2017年9月14日 下午4:54:08 <br/>
	 * @author  zhangST
	 * @param parameter
	 * @return
	 */
	public int updateBy(Object parameter);

	/**
	 * 批量更新。<br/>
	 * @sqlid : {@link #SQL_UPDATE_BATCH} <br/>
	 * @Date	2017年9月14日 下午5:16:46 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 */
	public int updateBatch(List<?> list);

	/**
	 * 物理删除。<br/>
	 * @sqlid : {@link #SQL_DELETE_BY} <br/>
	 * @Date	2017年9月14日 下午5:17:21 <br/>
	 * @author  zhangST
	 * @param parameter
	 * @return			返回影响的条数
	 */
	public int deleteBy(Object parameter);

	/**
	 * 物理批量删除。<br/>
	 * @sqlid : {@link #SQL_DELETE_BATCH} <br/>
	 * @Date	2017年9月14日 下午5:17:38 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 */
	public int deleteBatch(List<?> list);
	
	/**
	 * 查询所有。<br/>
	 * 注：{@link #selectListBy}方法的特例
	 * @sqlid : {@link #SQL_SELECT_LIST_BY} <br/>
	 * @Date	2017年9月14日 下午4:39:52 <br/>
	 * @author  zhangST
	 * @return
	 */
	public List<?> selectAll();
	
}

