package com.yffd.easy.framework.base.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.yffd.easy.common.core.converter.EasyModelConverter;
import com.yffd.easy.common.core.exception.EasyDaoException;
import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.framework.base.dao.GenericDao;
import com.yffd.easy.framework.domain.GenericPO;
import com.yffd.easy.framework.domain.LoginInfo;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年3月2日 上午10:40:18 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@SuppressWarnings("unchecked")
public abstract class BaseServiceAbstract extends EasyModelConverter {

	public abstract GenericDao<?> getBindDao();
	
	/**
	 * 查询-分页.<br/>
	 * @Date	2018年3月2日 上午11:19:50 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @param pageParam
	 * @return
	 */
	public <M> PageResult<M> findPage(Map<String, Object> paramMap, PageParam pageParam) {
		return (PageResult<M>) this.getBindDao().selectPage(paramMap, pageParam);
	}

	/**
	 * 查询-分页.<br/>
	 * @Date	2018年3月2日 上午11:20:11 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @return
	 */
	public <M> List<M> findList(Map<String, Object> paramMap) {
		return (List<M>) this.getBindDao().selectListBy(paramMap);
	}

	/**
	 * 查询-列表.<br/>
	 * @Date	2018年3月2日 上午11:20:16 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @return
	 */
	public <M> M findOne(Map<String, Object> paramMap) {
		if(null==paramMap || paramMap.size()==0) return null;
		return (M) this.getBindDao().selectOne(paramMap);
	}

	/**
	 * 查询-主键-单条.<br/>
	 * @Date	2018年3月2日 上午11:20:30 <br/>
	 * @author  zhangST
	 * @param id
	 * @return
	 */
	public <M> M findById(String id) {
		if(null==id || "".equals(id.trim())) return null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		return this.getBindDao().selectOne(paramMap);
	}
	
	/**
	 * 查询-主键-列表.<br/>
	 * @Date	2018年3月2日 上午11:20:47 <br/>
	 * @author  zhangST
	 * @param idsList
	 * @return
	 */
	public <M> List<M> findByIds(List<String> idsList) {
		if(null==idsList || idsList.size()==0) return null;
		return (List<M>) this.getBindDao().selectListByIds(idsList);
	}
	
	/**
	 * 查询-主键-列表.<br/>
	 * @Date	2018年3月2日 上午11:21:37 <br/>
	 * @author  zhangST
	 * @param ids
	 * @return
	 */
	public <M> List<M> findByIds(String ids) {
		if(null==ids || "".equals(ids.trim())) return null;
		List<String> params = Arrays.asList(ids.split(","));
		return this.findByIds(params);
	}

	/**
	 * 查询-所有.<br/>
	 * @Date	2018年3月2日 上午11:21:45 <br/>
	 * @author  zhangST
	 * @return
	 */
	public <M> List<M> findAll() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return (List<M>) this.getBindDao().selectListBy(paramMap);
	}
	
	/**
	 * 添加-单条.<br/>
	 * @Date	2018年3月2日 上午11:21:58 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @param loginInfo
	 */
	public void addOne(Map<String, Object> paramMap, LoginInfo loginInfo) {
		if(null==paramMap || paramMap.size()==0) return;
		this.setAddDefault(paramMap, loginInfo);
		this.getBindDao().insertOne(paramMap);
	}

	/**
	 * 添加-批量.<br/>
	 * @Date	2018年3月2日 上午11:22:17 <br/>
	 * @author  zhangST
	 * @param list
	 * @param loginInfo
	 */
	public void addBatch(List<?> list, LoginInfo loginInfo) {
		if(null==list || list.size()==0) return;
		this.setAddDefault(list, loginInfo);
		this.getBindDao().insertBatch(list);
	}

	/**
	 * 更新.<br/>
	 * @Date	2018年3月14日 下午4:03:29 <br/>
	 * @author  zhangST
	 * @param newMap
	 * @param oldMap
	 * @param loginInfo
	 * @return
	 */
	public int updateBy(Map<String, Object> newMap, Map<String, Object> oldMap, LoginInfo loginInfo) {
		if(null==newMap || newMap.size()==0 || null==oldMap || oldMap.size()==0) return -1;
		this.setEditDefault(newMap, loginInfo, false);
		int result = this.getBindDao().updateBy(newMap, oldMap);
		return result;
	}
	
	/**
	 * 更新.<br/>
	 * @Date	2018年3月14日 下午4:03:43 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @param loginInfo
	 * @param attributeNames	条件属性名称集合
	 * @return
	 */
	public int updateBy(Map<String, Object> paramMap, LoginInfo loginInfo, String... attributeNames) {
		if(null==paramMap || paramMap.size()==0 || null==attributeNames || attributeNames.length==0) return -1;
		this.setEditDefault(paramMap, loginInfo, false);
		int result = this.getBindDao().updateBy(paramMap, attributeNames);
		return result;
	}
	
	/**
	 * 更新.<br/>
	 * @Date	2018年3月14日 下午4:04:55 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @param attributeName		条件属性名称集合
	 * @param loginInfo
	 * @return
	 * @see #updateBy(Map, String...)
	 */
	public int updateBy(Map<String, Object> paramMap, String attributeName, LoginInfo loginInfo) {
		String[] arr = {attributeName};
		int result = this.updateBy(paramMap, loginInfo, arr);
		return result;
	}
	
	/**
	 * 更新.<br/>
	 * @Date	2018年3月14日 下午4:05:42 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @param loginInfo
	 * @return						-1：没有找到key为“id”所对应的有效值
	 * @see #updateBy(Map, String)
	 */
	public int updateById(Map<String, Object> paramMap, LoginInfo loginInfo) {
		if(null==paramMap || paramMap.size()==0) return -1;
		String attributeName = "id";
		int result = this.updateBy(paramMap, attributeName, loginInfo);
		return result;
	}
	
	/**
	 * 更新.<br/>
	 * @Date	2018年3月14日 下午4:06:20 <br/>
	 * @author  zhangST
	 * @param newMap
	 * @param idsList
	 * @param loginInfo
	 * @return
	 * @see #updateWithInBy(Map, String, List, LoginInfo)
	 */
	public int updateByIds(Map<String, Object> newMap, List<String> idsList, LoginInfo loginInfo) {
		if(null==newMap || newMap.size()==0 || null==idsList || idsList.size()==0) return -1;
		int result = this.updateWithInBy(newMap, "id", idsList, loginInfo);
        return result;
	}
	
	/**
	 * 更新.<br/>
	 * @Date	2018年3月13日 下午3:52:34 <br/>
	 * @author  zhangST
	 * @param newMap		新值集合
	 * @param inName		in条件名称
	 * @param inValueList	in条件值
	 * @return
	 * @see #updateWithInBy(Map, Map, String, List, LoginInfo)
	 */
	public int updateWithInBy(Map<String, Object> newMap, String inName, List<?> inValueList, LoginInfo loginInfo) {
		if(null==newMap || newMap.size()==0 || EasyStringCheckUtils.isEmpty(inName) 
				|| null==inValueList || inValueList.size()==0) return -1;
		int result = this.updateWithInBy(newMap, null, inName, inValueList, loginInfo);
		return result;
	}
	
	/**
	 * 更新.<br/>
	 * @Date	2018年3月13日 下午3:50:19 <br/>
	 * @author  zhangST
	 * @param newMap		新值集合
	 * @param oldMap		旧值集合
	 * @param inName		in条件名称
	 * @param inValueList	in值集合
	 * @return
	 * @see #updateWithInBy(Map, Map, Map, LoginInfo)
	 */
	public int updateWithInBy(Map<String, Object> newMap, Map<String, Object> oldMap, String inName, List<?> inValueList, LoginInfo loginInfo) {
		Map<String, Object> oldInMap = new HashMap<String, Object>();
		oldInMap.put(inName, inValueList);
		int result = this.updateWithInBy(newMap, oldMap, oldInMap, loginInfo);
		return result;
	}
	
	/**
	 * 更新.<br/>
	 * @Date	2018年3月13日 下午4:13:33 <br/>
	 * @author  zhangST
	 * @param newMap		新值集合
	 * @param oldMap		旧值集合
	 * @param oldInMap		in值集合，数据格式：inName：inValueList，如：Map<String,List<String>> == Map<"id", List<String>> == " ID in ( 1,2,3 ) "
	 * @return
	 */
	public int updateWithInBy(Map<String, Object> newMap, Map<String, Object> oldMap, Map<String, Object> oldInMap, LoginInfo loginInfo) {
		if((null==oldMap || oldMap.size()==0) && (null==oldInMap || oldInMap.size()==0)) return -1;
		this.setEditDefault(newMap, loginInfo, false);
		int result = this.getBindDao().updateWithInBy(newMap, oldMap, oldInMap);
		return result;
	}

	/**
	 * 删除-物理.<br/>
	 * @Date	2018年3月2日 上午11:23:01 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @param loginInfo
	 */
	public int delBy(Map<String, Object> paramMap, LoginInfo loginInfo) {
		if(null==paramMap || paramMap.size()==0) return -1;
		return this.getBindDao().deleteBy(paramMap);
	}

	/**
	 * 删除-物理.<br/>
	 * @Date	2018年3月2日 上午11:23:32 <br/>
	 * @author  zhangST
	 * @param id
	 * @param loginInfo
	 */
	public int delById(String id, LoginInfo loginInfo) {
		if(EasyStringCheckUtils.isEmpty(id)) return -1;
		return this.getBindDao().deleteById(id);
	}
	
	/**
	 * 删除-物理.<br/>
	 * @Date	2018年3月2日 上午11:23:45 <br/>
	 * @author  zhangST
	 * @param list
	 * @param loginInfo
	 */
	public int delByIds(List<String> list, LoginInfo loginInfo) {
		if(null==list || list.size()==0) return -1;
		if(list.size()==1) {
			return this.getBindDao().deleteById(list.get(0));
		} else {
			return this.getBindDao().deleteByIds(list);
		}
	}

	/**
	 * 删除-物理.<br/>
	 * @Date	2018年3月2日 上午11:24:03 <br/>
	 * @author  zhangST
	 * @param ids			逗号分隔的字符串值
	 * @param loginInfo
	 * @see #deleteWithInBy(String, String, String, LoginInfo)
	 */
	public int delByIds(String ids, LoginInfo loginInfo) {
		return this.delWithInBy("id", ids, ",", loginInfo);
	}
	
	/**
	 * 删除-物理.<br/>
	 * @Date	2018年3月12日 下午3:18:53 <br/>
	 * @author  zhangST
	 * @param arributeName	属性名称
	 * @param values		带定界符的字符串（逗号）
	 * @param loginInfo
	 * @return				返回-1代表参数（values）为空，即values转换成数组后，数组为空
	 * @see #deleteWithInBy(String, String, String, LoginInfo)
	 */
	public int delWithInBy(String arributeName, String values, LoginInfo loginInfo) {
		return this.delWithInBy(arributeName, values, null, loginInfo);
	}
	
	/**
	 * 删除-物理.<br/>
	 * @Date	2018年3月12日 下午3:18:53 <br/>
	 * @author  zhangST
	 * @param arributeName	属性名称
	 * @param values		带定界符的字符串
	 * @param delimiter		values字符串的定界符，默认为逗号（,）
	 * @param loginInfo
	 * @return				返回-1代表参数（values）为空，即values转换成数组后，数组为空
	 * @see #deleteWithInBy(Map, Map, LoginInfo)
	 */
	public int delWithInBy(String arributeName, String values, String delimiter, LoginInfo loginInfo) {
		if(EasyStringCheckUtils.isEmpty(arributeName) || EasyStringCheckUtils.isEmpty(values)) return -1; 
		if(EasyStringCheckUtils.isEmpty(delimiter)) delimiter = ",";
		List<String> inValueList = new ArrayList<String>();
		if(values.indexOf(delimiter)!=-1) {
			String[] arr = values.split(delimiter);
			if(arr.length==0) return -1;
			for(String tmp : arr) {
				if(EasyStringCheckUtils.isEmpty(tmp)) continue;
				inValueList.add(tmp);
			}
		} else {
			inValueList.add(values);
		}
		
		Map<String, List<?>> inMap = new HashMap<String, List<?>>();
		inMap.put(arributeName, inValueList);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(null!=loginInfo) paramMap.put("tenantCode", loginInfo.getTenantCode());
		
		return this.delWithInBy(paramMap, inMap, loginInfo);
		
	}
	
	/**
	 * 删除-物理.<br/>
	 * @Date	2018年3月12日 下午3:18:53 <br/>
	 * @author  zhangST
	 * @param arributeName	属性名称
	 * @param inValueList	in条件值集合
	 * @param loginInfo
	 * @return				-1：参数错误
	 * @see #deleteWithInBy(Map, Map, LoginInfo)
	 */
	public int delWithInBy(String arributeName, List<?> inValueList, LoginInfo loginInfo) {
		if(EasyStringCheckUtils.isEmpty(arributeName) || inValueList==null || inValueList.size()==0) return -1; 
		
		Map<String, List<?>> inMap = new HashMap<String, List<?>>();
		inMap.put(arributeName, inValueList);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(null!=loginInfo) paramMap.put("tenantCode", loginInfo.getTenantCode());
		
		return this.delWithInBy(paramMap, inMap, loginInfo);
	}
	
	/**
	 * 删除.<br/>
	 * @Date	2018年3月12日 下午4:41:04 <br/>
	 * @author  zhangST
	 * @param paramMap		普通条件
	 * @param inMap			in条件，格式：Map<String, List<String>> == Map<"id", List<String>>
	 * @param loginInfo
	 * @return				-1：参数错误
	 */
	public int delWithInBy(Map<String, Object> paramMap, Map<String, List<?>> inMap, LoginInfo loginInfo) {
		if((null==paramMap || paramMap.size()==0) && (null==inMap || inMap.size()==0)) return -1;
		return this.getBindDao().deleteWithInBy(paramMap, inMap);
	}
	
	
	/**
	 * 设置数据模型的默认值-新增
	 * @Date	2018年2月7日 下午5:07:47 <br/>
	 * @author  zhangST
	 * @param parameter
	 * @param loginInfo
	 */
	protected void setAddDefault(Object parameter, LoginInfo loginInfo) {
		if(parameter instanceof GenericPO) {
			GenericPO model = (GenericPO) parameter;
			if(null==model.getVersion()) model.setVersion(0);
			if(null==model.getDelFlag()) model.setDelFlag("0");
			if(null==model.getCreateTime()) model.setCreateTime(new Date());
			if(null==model.getCreateBy()) {
				if(null!=loginInfo) 
					model.setCreateBy(loginInfo.getUserCode());
				else 
					model.setCreateBy("sys");
			}
		} else if(parameter instanceof Map) {
			Map<String, Object> paramMap = (Map<String, Object>) parameter;
			if(null==paramMap.get("version")) paramMap.put("version", 0);
			if(null==paramMap.get("delFlag")) paramMap.put("delFlag", "0");
			if(null==paramMap.get("createTime")) paramMap.put("createTime", new Date());
			if(null==paramMap.get("creator")) {
				if(null!=loginInfo) 
					paramMap.put("creator", loginInfo.getUserCode());
				else 
					paramMap.put("creator", "sys");
			}
		} else if(parameter instanceof List) {
			List<Object> list = (List<Object>) parameter;
			for(Object obj : list) {
				this.setAddDefault(obj, loginInfo);
			}
		}
	}
	
	/**
	 * 设置数据模型的默认值-修改
	 * @Date	2018年2月7日 下午5:07:58 <br/>
	 * @author  zhangST
	 * @param parameter
	 * @param loginInfo
	 */
	protected void setEditDefault(Object parameter, LoginInfo loginInfo, boolean delFlag) {
		if(parameter instanceof GenericPO) {
			GenericPO model = (GenericPO) parameter;
			if(null==model.getUpdateTime()) model.setUpdateTime(new Date()); 
			if(null==model.getUpdateBy()) {
				if(null!=loginInfo) 
					model.setUpdateBy(loginInfo.getUserCode());
				else 
					model.setUpdateBy("sys");
			}
			if(delFlag) model.setDelFlag("1");
		} else if(parameter instanceof Map) {
			Map<String, Object> paramMap = (Map<String, Object>) parameter;
			if(null==paramMap.get("editTime")) paramMap.put("editTime", new Date());
			if(null==paramMap.get("editor")) {
				if(null!=loginInfo) 
					paramMap.put("editor", loginInfo.getUserCode());
				else 
					paramMap.put("editor", "sys");
			}
			if(delFlag) paramMap.put("delFlag", "1");
		} else if(parameter instanceof List) {
			List<Object> list = (List<Object>) parameter;
			for(Object obj : list) {
				this.setEditDefault(obj, loginInfo, delFlag);
			}
		}
	}
}

