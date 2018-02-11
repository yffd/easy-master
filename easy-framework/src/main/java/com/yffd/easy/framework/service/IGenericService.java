package com.yffd.easy.framework.service;

import java.util.List;
import java.util.Map;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.framework.domain.DomainModel;
import com.yffd.easy.framework.domain.GenericPO;
import com.yffd.easy.framework.domain.LoginInfo;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年2月6日 下午4:53:39 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface IGenericService {
	
	/**
	 * 分页查询
	 * @Date	2018年2月9日 下午2:26:13 <br/>
	 * @author  zhangST
	 * @param model
	 * @param pageParam
	 * @return
	 */
	<T> PageResult<T> findPage(DomainModel model, PageParam pageParam);
	
	/**
	 * 分页查询
	 * @Date	2018年2月9日 下午2:26:03 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @param pageParam
	 * @return
	 */
	<T> PageResult<T> findPage(Map<String, Object> paramMap, PageParam pageParam);
	
	/**
	 * 条件查询
	 * @Date	2018年2月9日 下午2:25:28 <br/>
	 * @author  zhangST
	 * @param model
	 * @return
	 */
	<T> List<T> findList(DomainModel model);
	
	/**
	 * 条件查询
	 * @Date	2018年2月9日 下午2:25:11 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @return
	 */
	<T> List<T> findList(Map<String, Object> paramMap);
	
	/**
	 * 查询所有
	 * @Date	2018年2月7日 上午10:58:53 <br/>
	 * @author  zhangST
	 * @return
	 */
	<T> List<T> findAll();
	
	/**
	 * 单条查询
	 * @Date	2018年2月9日 上午11:36:35 <br/>
	 * @author  zhangST
	 * @param parameter
	 * @return
	 */
	<T> T findOne(DomainModel model);
	
	/**
	 * 单条查询
	 * @Date	2018年2月9日 上午11:44:06 <br/>
	 * @author  zhangST
	 * @param parameter
	 * @return
	 */
	<T> T findOne(Map<String, Object> paramMap);
	
	/**
	 * 主键查询
	 * @Date	2018年2月7日 上午10:59:04 <br/>
	 * @author  zhangST
	 * @param id
	 * @return
	 */
	<T> T findById(String id);
	
	/**
	 * 多主键查询
	 * @Date	2018年2月9日 上午11:08:24 <br/>
	 * @author  zhangST
	 * @param ids
	 * @return
	 */
	<T> List<T> findByIds(String ids);
	
	/**
	 * 多主键查询
	 * @Date	2018年2月9日 上午11:08:37 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 */
	<T> List<T> findByIds(List<String> list);
	
	/**
	 * 添加
	 * @Date	2018年2月8日 下午2:52:44 <br/>
	 * @author  zhangST
	 * @param model
	 * @param loginInfo
	 */
	<T extends GenericPO> void addByModel(T model, LoginInfo loginInfo);
	
	/**
	 * 添加
	 * @Date	2018年2月8日 下午2:52:49 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @param loginInfo
	 */
	void addByMap(Map<String, Object> paramMap, LoginInfo loginInfo);
	
	/**
	 * 批量添加
	 * @Date	2018年2月8日 下午2:49:40 <br/>
	 * @author  zhangST
	 * @param list
	 * @param loginInfo
	 */
	void addBatchByModel(List<? extends GenericPO> list, LoginInfo loginInfo);
	
	/**
	 * 批量添加
	 * @Date	2018年2月8日 下午2:49:46 <br/>
	 * @author  zhangST
	 * @param list
	 * @param loginInfo
	 */
	void addBatchByMap(List<Map<String, Object>> list, LoginInfo loginInfo);
	
	/**
	 * 修改
	 * @Date	2018年2月8日 下午2:54:06 <br/>
	 * @author  zhangST
	 * @param model
	 * @param loginInfo
	 */
	<T extends GenericPO> void updateByModel(T model, LoginInfo loginInfo);
	
	/**
	 * 修改
	 * @Date	2018年2月8日 下午2:54:19 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @param loginInfo
	 */
	void updateByMap(Map<String, Object> paramMap, LoginInfo loginInfo);
	
	/**
	 * 修改-批量
	 * @Date	2018年2月8日 下午2:55:27 <br/>
	 * @author  zhangST
	 * @param list
	 * @param loginInfo
	 */
	void updateBatchByModel(List<? extends GenericPO> list, LoginInfo loginInfo);
	
	/**
	 * 修改-批量
	 * @Date	2018年2月8日 下午2:55:32 <br/>
	 * @author  zhangST
	 * @param list
	 * @param loginInfo
	 */
	void updateBatchByMap(List<Map<String, Object>> list, LoginInfo loginInfo);
	
	/**
	 * 物理删除
	 * @Date	2018年2月8日 下午2:57:48 <br/>
	 * @author  zhangST
	 * @param model
	 */
	<T extends GenericPO> void delByModel(T model);
	
	/**
	 * 物理删除
	 * @Date	2018年2月8日 下午2:54:19 <br/>
	 * @author  zhangST
	 * @param paramMap
	 */
	void delByMap(Map<String, Object> paramMap);
	
	/**
	 * 物理删除-批量
	 * @Date	2018年2月8日 下午2:55:27 <br/>
	 * @author  zhangST
	 * @param list
	 */
	void delBatchByModel(List<? extends GenericPO> list);
	
	/**
	 * 物理删除-批量
	 * @Date	2018年2月8日 下午2:55:32 <br/>
	 * @author  zhangST
	 * @param list
	 */
	void delBatchByMap(List<Map<String, Object>> list);
	
	/**
	 * 物理删除
	 * @Date	2018年2月7日 下午4:32:59 <br/>
	 * @author  zhangST
	 * @param id
	 */
	void delById(String id);
	
	/**
	 * 物理删除-批量
	 * @Date	2018年2月8日 下午2:58:47 <br/>
	 * @author  zhangST
	 * @param ids
	 */
	void delBatchByIds(List<String> ids);
	
	/**
	 * 物理删除-批量
	 * @Date	2018年2月7日 下午4:33:13 <br/>
	 * @author  zhangST
	 * @param ids
	 */
	void delBatchByIds(String ids);
	
	
	/**
	 * 逻辑删除
	 * @Date	2018年2月8日 下午2:57:48 <br/>
	 * @author  zhangST
	 * @param model
	 * @param loginInfo
	 */
	<T extends GenericPO> void removeByModel(T model, LoginInfo loginInfo);
	
	/**
	 * 逻辑删除
	 * @Date	2018年2月8日 下午2:54:19 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @param loginInfo
	 */
	void removeByMap(Map<String, Object> paramMap, LoginInfo loginInfo);
	
	/**
	 * 逻辑删除-批量
	 * @Date	2018年2月8日 下午2:55:27 <br/>
	 * @author  zhangST
	 * @param list
	 * @param loginInfo
	 */
	void removeBatchByModel(List<? extends GenericPO> list, LoginInfo loginInfo);
	
	/**
	 * 逻辑删除-批量
	 * @Date	2018年2月8日 下午2:55:32 <br/>
	 * @author  zhangST
	 * @param list
	 * @param loginInfo
	 */
	void removeBatchByMap(List<Map<String, Object>> list, LoginInfo loginInfo);
	
	/**
	 * 逻辑删除
	 * @Date	2018年2月7日 下午4:32:59 <br/>
	 * @author  zhangST
	 * @param id
	 * @param loginInfo
	 */
	void removeById(String id, LoginInfo loginInfo);
	
	/**
	 * 逻辑删除-批量
	 * @Date	2018年2月8日 下午2:58:47 <br/>
	 * @author  zhangST
	 * @param ids
	 * @param loginInfo
	 */
	void removeBatchByIds(List<String> ids, LoginInfo loginInfo);
	
	/**
	 * 逻辑删除-批量
	 * @Date	2018年2月7日 下午4:33:13 <br/>
	 * @author  zhangST
	 * @param ids
	 * @param loginInfo
	 */
	void removeBatchByIds(String ids, LoginInfo loginInfo);
	
}

