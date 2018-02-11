package com.yffd.easy.framework.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yffd.easy.common.core.converter.EasyModelConverter;
import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.framework.dao.IGenericDao;
import com.yffd.easy.framework.domain.DomainModel;
import com.yffd.easy.framework.domain.GenericPO;
import com.yffd.easy.framework.domain.LoginInfo;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年2月6日 下午4:55:34 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public abstract class GenericService extends EasyModelConverter implements IGenericService {

	public abstract IGenericDao getBindDao();

	@Override
	public <T> PageResult<T> findPage(DomainModel model, PageParam pageParam) {
		if(null==model) return null;
		return (PageResult<T>) this.getBindDao().selectPage(model, pageParam);
	}

	@Override
	public <T> PageResult<T> findPage(Map<String, Object> paramMap, PageParam pageParam) {
		if(null==paramMap || paramMap.size()==0) return null;
		return (PageResult<T>) this.getBindDao().selectPage(paramMap, pageParam);
	}

	@Override
	public <T> List<T> findList(DomainModel model) {
		if(null==model) return null;
		return (List<T>) this.getBindDao().selectListBy(model);
	}

	@Override
	public <T> List<T> findList(Map<String, Object> paramMap) {
		if(null==paramMap) return null;
		return (List<T>) this.getBindDao().selectListBy(paramMap);
	}

	@Override
	public <T> List<T> findAll() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return this.findList(paramMap);
	}

	@Override
	public <T> T findOne(DomainModel model) {
		if(null==model) return null;
		return (T) this.getBindDao().selectOne(model);
	}

	@Override
	public <T> T findOne(Map<String, Object> paramMap) {
		if(null==paramMap || paramMap.size()==0) return null;
		return (T) this.getBindDao().selectOne(paramMap);
	}
	
	@Override
	public <T> T findById(String id) {
		if(null==id || "".equals(id.trim())) return null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		return this.getBindDao().selectOne(paramMap);
	}
	
	@Override
	public <T> List<T> findByIds(String ids) {
		if(null==ids || "".equals(ids.trim())) return null;
		if(ids.indexOf(",")==-1) {
			List<T> list = new ArrayList<T>();
			T model = this.findById(ids);
			if(null!=model) list.add(model);
			return list;
		} else {
			List<String> params = Arrays.asList(ids.split(","));
			return this.findByIds(params);
		}
	}

	@Override
	public <T> List<T> findByIds(List<String> list) {
		if(null==list || list.size()==0) return null;
		return (List<T>) this.getBindDao().selectListByIds(list);
	}

	@Override
	public <T extends GenericPO> void addByModel(T model, LoginInfo loginInfo) {
		if(null==model) return;
		this.setAddDefault(model, loginInfo);
		this.getBindDao().insertOne(model);
	}

	@Override
	public void addByMap(Map<String, Object> paramMap, LoginInfo loginInfo) {
		if(null==paramMap || paramMap.size()==0) return;
		this.setAddDefault(paramMap, loginInfo);
		this.getBindDao().insertOne(paramMap);
	}

	@Override
	public void addBatchByModel(List<? extends GenericPO> list, LoginInfo loginInfo) {
		if(null==list || list.size()==0) return;
		this.setAddDefault(list, loginInfo);
		this.getBindDao().insertBatch(list);
	}

	@Override
	public void addBatchByMap(List<Map<String, Object>> list, LoginInfo loginInfo) {
		if(null==list || list.size()==0) return;
		this.setAddDefault(list, loginInfo);
		this.getBindDao().insertBatch(list);
	}

	@Override
	public <T extends GenericPO> void updateByModel(T model, LoginInfo loginInfo) {
		if(null==model) return;
		this.setEditDefault(model, loginInfo, false);
		this.getBindDao().updateBy(model);
	}

	@Override
	public void updateByMap(Map<String, Object> paramMap, LoginInfo loginInfo) {
		if(null==paramMap || paramMap.size()==0) return;
		this.setEditDefault(paramMap, loginInfo, false);
		this.getBindDao().updateBy(paramMap);
	}

	@Override
	public void updateBatchByModel(List<? extends GenericPO> list, LoginInfo loginInfo) {
		if(null==list || list.size()==0) return;
		this.setEditDefault(list, loginInfo, false);
		this.getBindDao().updateBatch(list);
	}

	@Override
	public void updateBatchByMap(List<Map<String, Object>> list, LoginInfo loginInfo) {
		if(null==list || list.size()==0) return;
		this.setEditDefault(list, loginInfo, false);
		this.getBindDao().updateBatch(list);
	}

	@Override
	public <T extends GenericPO> void delByModel(T model) {
		if(null==model) return;
		this.getBindDao().deleteBy(model);
	}

	@Override
	public void delByMap(Map<String, Object> paramMap) {
		if(null==paramMap || paramMap.size()==0) return;
		this.getBindDao().deleteBy(paramMap);
	}

	@Override
	public void delBatchByModel(List<? extends GenericPO> list) {
		if(null==list || list.size()==0) return;
		this.getBindDao().deleteBatch(list);
	}

	@Override
	public void delBatchByMap(List<Map<String, Object>> list) {
		if(null==list || list.size()==0) return;
		this.getBindDao().deleteBatch(list);
	}

	@Override
	public void delById(String id) {
		if(null==id || "".equals(id.trim())) return;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		this.getBindDao().deleteBy(paramMap);
	}

	@Override
	public void delBatchByIds(List<String> list) {
		if(null==list || list.size()==0) return;
		if(list.size()==1) {
			this.delById(list.get(0));
		} else {
			this.getBindDao().deleteBatch(list);
		}
	}

	@Override
	public void delBatchByIds(String ids) {
		if(null==ids || "".equals(ids.trim())) return;
		if(ids.indexOf(",")==-1) {
			this.delById(ids);
		} else {
			List<String> list = Arrays.asList(ids.split(","));
			this.delBatchByIds(list);
		}
	}
	
	@Override
	public <T extends GenericPO> void removeByModel(T model, LoginInfo loginInfo) {
		if(null==model) return;
		this.setEditDefault(model, loginInfo, true);
		this.getBindDao().updateBy(model);
	}

	@Override
	public void removeByMap(Map<String, Object> paramMap, LoginInfo loginInfo) {
		if(null==paramMap || paramMap.size()==0) return;
		this.setEditDefault(paramMap, loginInfo, true);
		this.getBindDao().updateBy(paramMap);
	}

	@Override
	public void removeBatchByModel(List<? extends GenericPO> list, LoginInfo loginInfo) {
		if(null==list || list.size()==0) return;
		this.setEditDefault(list, loginInfo, true);
		this.getBindDao().updateBatch(list);
	}

	@Override
	public void removeBatchByMap(List<Map<String, Object>> list, LoginInfo loginInfo) {
		if(null==list || list.size()==0) return;
		this.setEditDefault(list, loginInfo, true);
		this.getBindDao().updateBatch(list);
	}

	@Override
	public void removeById(String id, LoginInfo loginInfo) {
		if(null==id || "".equals(id.trim())) return;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		this.setEditDefault(paramMap, loginInfo, true);
		this.getBindDao().updateBy(paramMap);
		
	}

	@Override
	public void removeBatchByIds(List<String> list, LoginInfo loginInfo) {
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
	
	@Override
	public void removeBatchByIds(String ids, LoginInfo loginInfo) {
		if(null==ids || "".equals(ids.trim())) return;
		if(ids.indexOf(",")==-1) {
			this.removeById(ids, loginInfo);
		} else {
			List<String> list = Arrays.asList(ids.split(","));
			this.removeBatchByIds(list, loginInfo);
		}
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
			if(null==model.getCreator()) {
				if(null!=loginInfo) 
					model.setCreator(loginInfo.getUserCode());
				else 
					model.setCreator("sys");
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
			if(null==model.getEditTime()) model.setEditTime(new Date()); 
			if(null==model.getEditor()) {
				if(null!=loginInfo) 
					model.setEditor(loginInfo.getUserCode());
				else 
					model.setEditor("sys");
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

