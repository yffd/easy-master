package com.yffd.easy.framework.core.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;

/**
 * @Description  持久化接口基类.
 * @Date		 2017年9月14日 下午3:58:43 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface IBaseDao<T> {

	/**
	 * 统计总条数
	 * @Date	2017年9月14日 下午4:03:39 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @return
	 */
	Long selectCountBy(Map<String, Object> paramMap);
	
	/**
	 * 分页查询，该方法会调用自己定义的统计SQL，即自定义计算总条数SQL。<br/>
	 * @Date	2017年9月14日 下午4:01:37 <br/>
	 * @author  zhangST
	 * @param paginationInfo
	 * @param paramMap
	 * @return
	 */
	PageResult<T> selectPage(PageParam paginationInfo, Map<String, Object> paramMap);
	
	/**
     * 根据条件查询数据集合
     * @Date	2017年9月14日 下午4:18:00 <br/>
     * @author  zhangST
     * @param paramMap
     * @return
     */
    List<T> selectListBy(Map<String, Object> paramMap);
	
	/**
	 * 根据主键查询数据
	 * @Date	2017年9月14日 下午4:16:46 <br/>
	 * @author  zhangST
	 * @param primaryKey
	 * @return
	 */
	T selectByPK(String primaryKey);
	
	/**
	 * 根据条件查询数据
	 * @Date	2017年10月26日 上午10:33:30 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @return
	 */
	T selectOne(Map<String, Object> paramMap);

    /**
     * 查询所有数据集合
     * @Date	2017年9月14日 下午4:18:21 <br/>
     * @author  zhangST
     * @return
     */
    List<T> selectAll();
	
    /**
     * 插入数据
     * @Date	2017年9月14日 下午4:18:46 <br/>
     * @author  zhangST
     * @param model
     * @return
     */
    int insert(T model);

    /**
     * 批量插入数据
     * @Date	2017年9月14日 下午4:18:58 <br/>
     * @author  zhangST
     * @param list
     * @return
     */
    int insertBatch(List<T> list);
    
    /**
     * 更新数据
     * @Date	2017年9月14日 下午4:19:16 <br/>
     * @author  zhangST
     * @param model
     * @return
     */
    int updateBy(T model);
    
    /**
     * 根据主键更新数据
     * @Date	2017年9月14日 下午4:19:16 <br/>
     * @author  zhangST
     * @param model
     * @return
     */
    int updateByPK(T model);

    /**
     * 批量更新数据
     * @Date	2017年9月14日 下午4:19:33 <br/>
     * @author  zhangST
     * @param list
     * @return
     */
    int updateBatch(List<T> list);

    /**
     * 根据主键删除数据
     * @Date	2017年9月14日 下午4:19:52 <br/>
     * @author  zhangST
     * @param primaryKey
     * @return
     */
    int deleteByPK(String primaryKey);

    /**
     * 根据条件删除数据
     * @Date	2017年9月14日 下午5:08:19 <br/>
     * @author  zhangST
     * @param paramMap
     * @return
     */
    int deleteBy(Map<String, Object> paramMap);
    /**
     * 批量删除数据
     * @Date	2017年9月14日 下午4:20:06 <br/>
     * @author  zhangST
     * @param list
     * @return
     */
    int deleteBatch(List<T> list);
    
    /**
     * 获取Mapper中的SQL ID
     * @Date	2017年9月14日 下午4:14:56 <br/>
     * @author  zhangST
     * @param sqlId
     * @return
     */
    String getStatement(String sqlId);
    
    /**
     * 获取执行持久化操作的对象SqlSession
     * @Date	2017年9月14日 下午4:20:46 <br/>
     * @author  zhangST
     * @return
     */
    SqlSession getSqlSession();
	
}

