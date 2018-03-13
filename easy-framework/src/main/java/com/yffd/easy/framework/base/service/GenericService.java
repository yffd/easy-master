package com.yffd.easy.framework.base.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.framework.base.dao.GenericDao;
import com.yffd.easy.framework.domain.LoginInfo;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年2月6日 下午4:55:34 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@SuppressWarnings("unchecked")
public abstract class GenericService<PO> extends BaseServiceAbstract {

	public abstract GenericDao<PO> getBindDao();

	/**
	 * 查询-分页.<br/>
	 * @Date	2018年3月2日 上午11:16:08 <br/>
	 * @author  zhangST
	 * @param model
	 * @param pageParam
	 * @return
	 */
	public PageResult<PO> findPage(PO model, PageParam pageParam) {
		return (PageResult<PO>) this.getBindDao().selectPage(model, pageParam);
	}
	
	/**
	 * 查询-列表.<br/>
	 * @Date	2018年3月2日 上午11:16:38 <br/>
	 * @author  zhangST
	 * @param model
	 * @return
	 */
	public List<PO> findList(PO model) {
		return (List<PO>) this.getBindDao().selectListBy(model);
	}
	
	/**
	 * 查询-单条.<br/>
	 * @Date	2018年3月2日 上午11:17:02 <br/>
	 * @author  zhangST
	 * @param model
	 * @return
	 */
	public PO findOne(PO model) {
		if(null==model) return null;
		return (PO) this.getBindDao().selectOne(model);
	}
	
	/**
	 * 添加-单条.<br/>
	 * @Date	2018年3月2日 上午11:17:16 <br/>
	 * @author  zhangST
	 * @param model
	 * @param loginInfo
	 */
	public void addOne(PO model, LoginInfo loginInfo) {
		if(null==model) return;
		this.setAddDefault(model, loginInfo);
		this.getBindDao().insertOne(model);
	}
	
	/**
	 * 修改.<br/>
	 * @Date	2018年3月2日 上午11:17:27 <br/>
	 * @author  zhangST
	 * @param model
	 * @param loginInfo
	 */
	public void updateBy(PO model, LoginInfo loginInfo) {
		if(null==model) return;
		this.setEditDefault(model, loginInfo, false);
		this.getBindDao().updateBy(model);
	}
	
	/**
	 * 删除-物理.<br/>
	 * @Date	2018年3月2日 上午11:18:01 <br/>
	 * @author  zhangST
	 * @param model
	 * @param loginInfo
	 */
	public void delBy(PO model, LoginInfo loginInfo) {
		if(null==model) return;
		this.getBindDao().deleteBy(model);
	}
	
	/**
	 * 删除-逻辑.<br/>
	 * @Date	2018年3月2日 上午11:18:18 <br/>
	 * @author  zhangST
	 * @param model
	 * @param loginInfo
	 */
	public void removeBy(PO model, LoginInfo loginInfo) {
		if(null==model) return;
		this.setEditDefault(model, loginInfo, true);
		this.getBindDao().updateBy(model);
	}
	
	/**
	 * 删除-逻辑.<br/>
	 * @Date	2018年3月2日 上午11:24:10 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @param loginInfo
	 */
	public void removeBy(Map<String, Object> paramMap, LoginInfo loginInfo) {
		if(null==paramMap || paramMap.size()==0) return;
		this.setEditDefault(paramMap, loginInfo, true);
		this.getBindDao().updateBy(paramMap);
	}

	/**
	 * 删除-逻辑.<br/>
	 * @Date	2018年3月2日 上午11:25:07 <br/>
	 * @author  zhangST
	 * @param id
	 * @param loginInfo
	 */
	public void removeById(String id, LoginInfo loginInfo) {
		if(EasyStringCheckUtils.isEmpty(id)) return;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		this.setEditDefault(paramMap, loginInfo, true);
		this.getBindDao().updateBy(paramMap);
	}

	/**
	 * 删除-逻辑.<br/>
	 * @Date	2018年3月2日 上午11:25:11 <br/>
	 * @author  zhangST
	 * @param list
	 * @param loginInfo
	 */
	public void removeByIds(List<String> list, LoginInfo loginInfo) {
		if(null==list || list.size()==0) return;
		if(list.size()==1) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", list.get(0));
			this.setEditDefault(paramMap, loginInfo, true);
			this.getBindDao().updateBy(paramMap);
			return;
		}
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
		for(String id : list) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("id", id);
			this.setEditDefault(paramMap, loginInfo, true);
			params.add(paramMap);
		}
		this.getBindDao().updateBatch(params);
	}
	
	/**
	 * 删除-逻辑.<br/>
	 * @Date	2018年3月2日 上午11:25:15 <br/>
	 * @author  zhangST
	 * @param ids
	 * @param loginInfo
	 */
	public void removeByIds(String ids, LoginInfo loginInfo) {
		if(EasyStringCheckUtils.isEmpty(ids)) return;
		if(ids.indexOf(",")==-1) {
			this.removeById(ids, loginInfo);
		} else {
			List<String> list = Arrays.asList(ids.split(","));
			this.removeByIds(list, loginInfo);
		}
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
	 */
//	public int removeByIn(String arributeName, String values, String delimiter, LoginInfo loginInfo) {
//		if(EasyStringCheckUtils.isEmpty(arributeName) || EasyStringCheckUtils.isEmpty(values)) return -1; 
//		if(EasyStringCheckUtils.isEmpty(delimiter)) delimiter = ",";
//		if(values.indexOf(delimiter)!=-1) {
//			String[] arr = values.split(delimiter);
//			if(arr.length==0) return -1;
//			List<Map<String, Object>> paramList = new ArrayList<Map<String, Object>>();
//			for(String tmp : arr) {
//				if(EasyStringCheckUtils.isEmpty(tmp)) continue;
//				Map<String, Object> map = new HashMap<String, Object>();
//				map.put(arributeName, tmp);
//				this.setEditDefault(map, loginInfo, true);
//				paramList.add(map);
//			}
//			return this.getBindDao().deleteByIn(arributeName, paramList);
//		} else {
//			Map<String, Object> paramMap = new HashMap<String, Object>();
//			paramMap.put(arributeName, values);
//			this.setEditDefault(paramMap, loginInfo, true);
//			return this.getBindDao().deleteBy(paramMap);
//		}
//		
//	}
}

