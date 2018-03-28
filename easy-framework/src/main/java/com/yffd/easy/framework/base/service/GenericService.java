package com.yffd.easy.framework.base.service;

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
	 * 查询-主键-单条.<br/>
	 * @Date	2018年3月2日 上午11:20:30 <br/>
	 * @author  zhangST
	 * @param id
	 * @return
	 */
	public PO findById(String id) {
		if(null==id || "".equals(id.trim())) return null;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		return this.getBindDao().selectOne(paramMap);
	}
	
	/**
	 * 查询-主键-列表.<br/>
	 * @Date	2018年3月2日 上午11:21:37 <br/>
	 * @author  zhangST
	 * @param ids
	 * @return
	 */
	public List<PO> findByIds(String ids) {
		if(null==ids || "".equals(ids.trim())) return null;
		List<String> params = Arrays.asList(ids.split(","));
		return this.findByIds(params);
	}
	
	/**
	 * 查询所有.<br/>
	 * @Date	2018年3月2日 上午11:21:37 <br/>
	 * @author  zhangST
	 * @return
	 */
	public List<PO> findAll() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return (List<PO>) this.getBindDao().selectListBy(paramMap);
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
	public int updateById(PO model, LoginInfo loginInfo) {
		if(null==model) return -1;
		this.setEditDefault(model, loginInfo);
		return this.getBindDao().updateById(model);
	}
	
	/**
	 * 更新
	 * @Date	2018年3月27日 上午9:44:07 <br/>
	 * @author  zhangST
	 * @param model
	 * @param loginInfo
	 * @param attributeNames
	 * @return
	 */
	public int updateBy(PO model, LoginInfo loginInfo, String... attributeNames) {
		if(null==model || null==attributeNames || attributeNames.length==0) return -1;
		Map<String, Object> paramMap = this.model2map(model, null);
		this.setEditDefault(paramMap, loginInfo);
		int result = this.getBindDao().updateBy(paramMap, attributeNames);
		return result;
	}
	
	/**
	 * 更新
	 * @Date	2018年3月27日 上午9:44:13 <br/>
	 * @author  zhangST
	 * @param model
	 * @param attributeName
	 * @param loginInfo
	 * @return
	 */
	public int updateBy(PO model, String attributeName, LoginInfo loginInfo) {
		String[] arr = {attributeName};
		Map<String, Object> paramMap = this.model2map(model, null);
		int result = this.updateBy(paramMap, loginInfo, arr);
		return result;
	}
	
	/**
	 * 删除-物理.<br/>
	 * @Date	2018年3月2日 上午11:18:01 <br/>
	 * @author  zhangST
	 * @param model
	 * @param loginInfo
	 */
	public int delBy(PO model, LoginInfo loginInfo) {
		if(null==model) return -1;
		return this.getBindDao().deleteBy(model);
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
	
}

