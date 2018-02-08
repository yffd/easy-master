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
	public PageResult<?> findPage(Object parameter, PageParam pageParam) {
		return this.getBindDao().selectPage(parameter, pageParam);
	}

	@Override
	public List<?> findList(Object parameter) {
		return this.getBindDao().selectListBy(parameter);
	}

	@Override
	public List<?> findAll() {
		return this.getBindDao().selectAll();
	}

	@Override
	public <T> T findById(String id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		return this.getBindDao().selectOne(paramMap);
	}

	@Override
	public List<?> findByIds(String ids) {
		if(null==ids || "".equals(ids)) return null;
		if(ids.indexOf(",")!=-1) {
			String[] idsArr = ids.split(",");
			if(idsArr.length>1) {
				this.getBindDao().selectListByIn(Arrays.asList(idsArr));
			} else if(idsArr.length==1) {
				this.findById(idsArr[0]);
			}
		} else {
			this.findById(ids);
		}
		return null;
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
	public <T extends GenericPO> void delByModel(T model, LoginInfo loginInfo) {
		if(null==model) return;
		this.setEditDefault(model, loginInfo, true);
		this.getBindDao().updateBy(model);
	}

	@Override
	public void delByMap(Map<String, Object> paramMap, LoginInfo loginInfo) {
		if(null==paramMap || paramMap.size()==0) return;
		this.setEditDefault(paramMap, loginInfo, true);
		this.getBindDao().updateBy(paramMap);
	}

	@Override
	public void delBatchByModel(List<? extends GenericPO> list, LoginInfo loginInfo) {
		if(null==list || list.size()==0) return;
		this.setEditDefault(list, loginInfo, true);
		this.getBindDao().updateBatch(list);
	}

	@Override
	public void delBatchByMap(List<Map<String, Object>> list, LoginInfo loginInfo) {
		if(null==list || list.size()==0) return;
		this.setEditDefault(list, loginInfo, true);
		this.getBindDao().updateBatch(list);
	}

	@Override
	public void delById(String id, LoginInfo loginInfo) {
		if(null==id || "".equals(id.trim())) return;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		this.setEditDefault(paramMap, loginInfo, true);
		this.getBindDao().updateBy(paramMap);
		
	}

	@Override
	public void delBatchByIds(List<String> list, LoginInfo loginInfo) {
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
	public void delBatchByIds(String ids, LoginInfo loginInfo) {
		if(null==ids || "".equals(ids.trim())) return;
		if(ids.indexOf(",")==-1) {
			this.delById(ids, loginInfo);
		} else {
			List<String> list = Arrays.asList(ids.split(","));
			this.delBatchByIds(list, loginInfo);
		}
	}

	@Override
	public <T extends GenericPO> void physicalDelByModel(T model, LoginInfo loginInfo) {
		if(null==model) return;
		this.getBindDao().deleteBy(model);
	}

	@Override
	public void physicalDelByMap(Map<String, Object> paramMap, LoginInfo loginInfo) {
		if(null==paramMap || paramMap.size()==0) return;
		this.getBindDao().deleteBy(paramMap);
	}

	@Override
	public void physicalDelBatchByModel(List<? extends GenericPO> list, LoginInfo loginInfo) {
		if(null==list || list.size()==0) return;
		this.getBindDao().deleteBatch(list);
	}

	@Override
	public void physicalDelBatchByMap(List<Map<String, Object>> list, LoginInfo loginInfo) {
		if(null==list || list.size()==0) return;
		this.getBindDao().deleteBatch(list);
	}

	@Override
	public void physicalDelById(String id, LoginInfo loginInfo) {
		if(null==id || "".equals(id.trim())) return;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		this.getBindDao().deleteBy(paramMap);
	}

	@Override
	public void physicalDelBatchByIds(List<String> list, LoginInfo loginInfo) {
		if(null==list || list.size()==0) return;
		if(list.size()==1) {
			this.physicalDelById(list.get(0), loginInfo);
		} else {
			this.getBindDao().deleteBatch(list);
		}
	}

	@Override
	public void physicalDelBatchByIds(String ids, LoginInfo loginInfo) {
		if(null==ids || "".equals(ids.trim())) return;
		if(ids.indexOf(",")==-1) {
			this.physicalDelById(ids, loginInfo);
		} else {
			List<String> list = Arrays.asList(ids.split(","));
			this.physicalDelBatchByIds(list, loginInfo);
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

