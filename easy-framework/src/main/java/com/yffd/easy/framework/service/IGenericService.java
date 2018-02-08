package com.yffd.easy.framework.service;

import java.util.List;
import java.util.Map;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
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
	 * @Date	2018年2月7日 上午10:58:29 <br/>
	 * @author  zhangST
	 * @param parameter
	 * @param pageParam
	 * @return
	 */
	PageResult<?> findPage(Object parameter, PageParam pageParam);
	
	/**
	 * 条件查询
	 * @Date	2018年2月7日 上午10:58:39 <br/>
	 * @author  zhangST
	 * @param parameter
	 * @return
	 */
	List<?> findList(Object parameter);
	
	/**
	 * 查询所有
	 * @Date	2018年2月7日 上午10:58:53 <br/>
	 * @author  zhangST
	 * @return
	 */
	List<?> findAll();
	
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
	 * @Date	2018年2月7日 上午10:59:17 <br/>
	 * @author  zhangST
	 * @param ids
	 * @return
	 */
	List<?> findByIds(String ids);
	
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
	 * 逻辑删除
	 * @Date	2018年2月8日 下午2:57:48 <br/>
	 * @author  zhangST
	 * @param model
	 * @param loginInfo
	 */
	<T extends GenericPO> void delByModel(T model, LoginInfo loginInfo);
	
	/**
	 * 逻辑删除
	 * @Date	2018年2月8日 下午2:54:19 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @param loginInfo
	 */
	void delByMap(Map<String, Object> paramMap, LoginInfo loginInfo);
	
	/**
	 * 逻辑删除-批量
	 * @Date	2018年2月8日 下午2:55:27 <br/>
	 * @author  zhangST
	 * @param list
	 * @param loginInfo
	 */
	void delBatchByModel(List<? extends GenericPO> list, LoginInfo loginInfo);
	
	/**
	 * 逻辑删除-批量
	 * @Date	2018年2月8日 下午2:55:32 <br/>
	 * @author  zhangST
	 * @param list
	 * @param loginInfo
	 */
	void delBatchByMap(List<Map<String, Object>> list, LoginInfo loginInfo);
	
	/**
	 * 逻辑删除
	 * @Date	2018年2月7日 下午4:32:59 <br/>
	 * @author  zhangST
	 * @param id
	 * @param loginInfo
	 */
	void delById(String id, LoginInfo loginInfo);
	
	/**
	 * 逻辑删除-批量
	 * @Date	2018年2月8日 下午2:58:47 <br/>
	 * @author  zhangST
	 * @param ids
	 * @param loginInfo
	 */
	void delBatchByIds(List<String> ids, LoginInfo loginInfo);
	
	/**
	 * 逻辑删除-批量
	 * @Date	2018年2月7日 下午4:33:13 <br/>
	 * @author  zhangST
	 * @param ids
	 * @param loginInfo
	 */
	void delBatchByIds(String ids, LoginInfo loginInfo);
	
	
	/**
	 * 物理删除
	 * @Date	2018年2月8日 下午2:57:48 <br/>
	 * @author  zhangST
	 * @param model
	 * @param loginInfo
	 */
	<T extends GenericPO> void physicalDelByModel(T model, LoginInfo loginInfo);
	
	/**
	 * 物理删除
	 * @Date	2018年2月8日 下午2:54:19 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @param loginInfo
	 */
	void physicalDelByMap(Map<String, Object> paramMap, LoginInfo loginInfo);
	
	/**
	 * 物理删除-批量
	 * @Date	2018年2月8日 下午2:55:27 <br/>
	 * @author  zhangST
	 * @param list
	 * @param loginInfo
	 */
	void physicalDelBatchByModel(List<? extends GenericPO> list, LoginInfo loginInfo);
	
	/**
	 * 物理删除-批量
	 * @Date	2018年2月8日 下午2:55:32 <br/>
	 * @author  zhangST
	 * @param list
	 * @param loginInfo
	 */
	void physicalDelBatchByMap(List<Map<String, Object>> list, LoginInfo loginInfo);
	
	/**
	 * 物理删除
	 * @Date	2018年2月7日 下午4:32:59 <br/>
	 * @author  zhangST
	 * @param id
	 * @param loginInfo
	 */
	void physicalDelById(String id, LoginInfo loginInfo);
	
	/**
	 * 物理删除-批量
	 * @Date	2018年2月8日 下午2:58:47 <br/>
	 * @author  zhangST
	 * @param ids
	 * @param loginInfo
	 */
	void physicalDelBatchByIds(List<String> ids, LoginInfo loginInfo);
	
	/**
	 * 物理删除-批量
	 * @Date	2018年2月7日 下午4:33:13 <br/>
	 * @author  zhangST
	 * @param ids
	 * @param loginInfo
	 */
	void physicalDelBatchByIds(String ids, LoginInfo loginInfo);
	
	
}

