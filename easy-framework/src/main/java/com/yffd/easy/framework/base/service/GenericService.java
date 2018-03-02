package com.yffd.easy.framework.base.service;

import java.util.List;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
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
	 */
	public void delBy(PO model) {
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
	
}

