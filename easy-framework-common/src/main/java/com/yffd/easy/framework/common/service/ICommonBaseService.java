package com.yffd.easy.framework.common.service;

import java.util.List;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;

/**
 * @Description  业务逻辑常用操作类.
 * @Date		 2018年4月19日 上午11:18:46 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface ICommonBaseService<M> {

	/**
	 * 添加：单条
	 * @Date	2018年4月19日 上午10:32:50 <br/>
	 * @author  zhangST
	 * @param model
	 * @return
	 */
	Integer save(M model);
	
	/**
	 * 添加：批量
	 * @Date	2018年4月19日 上午10:33:10 <br/>
	 * @author  zhangST
	 * @param modelList
	 * @return
	 */
	Integer save(List<M> modelList);
	
	/**
	 * 修改
	 * @Date	2018年4月19日 上午10:33:20 <br/>
	 * @author  zhangST
	 * @param model			待修改“属性名-值”集合
	 * @param oldModel		条件-旧对象“属性名-值”集合
	 * @return
	 */
	Integer update(M model, M oldModel);
	
	/**
	 * 删除
	 * @Date	2018年4月19日 上午10:35:20 <br/>
	 * @author  zhangST
	 * @param model
	 * @return
	 */
	Integer delete(M model);
	
	/**
	 * 查询：统计
	 * @Date	2018年4月20日 下午2:55:01 <br/>
	 * @author  zhangST
	 * @param model
	 * @return
	 */
	Integer findCount(M model);
	
	/**
	 * 查询：单条
	 * @Date	2018年4月20日 下午2:55:16 <br/>
	 * @author  zhangST
	 * @param model
	 * @return
	 */
	M findOne(M model);
	
	/**
	 * 查询：列表
	 * @Date	2018年4月20日 下午2:55:27 <br/>
	 * @author  zhangST
	 * @param model
	 * @param orderBy
	 * @return
	 */
	List<M> findListByOrder(M model, String orderBy);
	
	/**
	 * 查询：分页
	 * @Date	2018年4月20日 下午2:55:40 <br/>
	 * @author  zhangST
	 * @param model
	 * @param orderBy
	 * @param page
	 * @return
	 */
	PageResult<M> findPageByOrder(M model, String orderBy, PageParam page);
	
	/**
	 * 查询：存在并且唯一校验
	 * @Date	2018年4月19日 上午11:12:57 <br/>
	 * @author  zhangST
	 * @param model
	 * @return
	 */
	Boolean checkUnique(M model);

	/**
	 * 查询：存在校验
	 * @Date	2018年4月20日 下午2:55:52 <br/>
	 * @author  zhangST
	 * @param model
	 * @return
	 */
	Boolean exsist(M model);
	
	
	/** 属性 */
	Integer deleteByProperty(String propertyName, Object value);
	
	Integer findCountByProperty(String propertyName, Object value);
	
	M findOneByProperty(String propertyName, Object value);
	
	List<M> findListByProperty(String propertyName, Object value);
	
	PageResult<M> findPageByProperty(String propertyName, Object value, PageParam page);
}

