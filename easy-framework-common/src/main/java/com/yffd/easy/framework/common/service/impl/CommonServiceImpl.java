package com.yffd.easy.framework.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.framework.common.dao.ICommonDao;
import com.yffd.easy.framework.common.pojo.entity.CommonEntity;
import com.yffd.easy.framework.common.service.ICommonService;

/**
 * @Description  业务逻辑常用操作类，隐藏持久化实体（entity）的方式.
 * @Date		 2018年4月19日 下午2:49:46 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public abstract class CommonServiceImpl<M, E extends CommonEntity> extends CommonBaseServiceImpl<M, E> implements ICommonService<M> {
	
	@Override
	public abstract ICommonDao<E> getBindDao();

	@Override
	public Integer saveOrUpdate(M model) {
		E entity = this.model2entity(model);
		if(null==entity || EasyStringCheckUtils.isEmpty(entity.getId())) return 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", entity.getId());
		E result = this.getBindDao().findOne(map);
		if(null==result) {
			return this.getBindDao().save(entity);
		} else {
			entity.setId(result.getId());
			return this.getBindDao().update(entity);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Integer saveOrUpdate(List<M> modelList) {
		if(null==modelList || modelList.isEmpty()) return 0;
		int num = 0;
		for(M model : modelList) {
			num += this.saveOrUpdate(model);
		}
		return num;
	}

	@Override
	public Integer update(M model) {
		return this.saveOrUpdate(model);
	}

	@Override
	public Integer deleteByPrimaryId(String primaryId) {
		return super.deleteByProperty("id", primaryId);
	}

	@Override
	public Integer deleteByPrimaryIds(String[] primaryIds) {
		return super.deleteByProperty("idList", primaryIds);
	}

	@Override
	public M findOneByPrimaryId(String primaryId) {
		return super.findOneByProperty("id", primaryId);
	}

	@Override
	public List<M> findAllByOrder(String orderBy) {
		return super.findListByOrder(null, null);
	}

	@Override
	public List<M> findAllByOrder(String[] orderPropertyNames, String[] orderByTypes) {
		String orderBy = this.makeOrderBy(orderPropertyNames, orderByTypes);
		return super.findListByOrder(null, orderBy);
	}

	@Override
	public List<M> findListByOrder(M model, String[] orderPropertyNames, String[] orderByTypes) {
		String orderBy = this.makeOrderBy(orderPropertyNames, orderByTypes);
		return super.findListByOrder(model, orderBy);
	}

	@Override
	public List<M> findListByOrder(M model, String orderPropertyName, String orderByType) {
		String[] _orderPropertyNames = null;
		String[] _orderByTypes = null;
		if(!EasyStringCheckUtils.isEmpty(orderPropertyName)) _orderPropertyNames = new String[]{orderPropertyName};
		if(!EasyStringCheckUtils.isEmpty(orderByType)) _orderByTypes = new String[]{orderByType};
		return this.findListByOrder(model, _orderPropertyNames, _orderByTypes);
	}

	@Override
	public List<M> findAll() {
		return this.findList(null);
	}

	@Override
	public List<M> findList(M model) {
		return super.findListByOrder(model, null);
	}

	@Override
	public PageResult<M> findPageByOrder(M model, String[] orderPropertyNames, String[] orderByTypes, PageParam page) {
		String orderBy = this.makeOrderBy(orderPropertyNames, orderByTypes);
		return super.findPageByOrder(model, orderBy, page);
	}

	@Override
	public PageResult<M> findPageByOrder(M model, String orderPropertyName, String orderByType, PageParam page) {
		String[] _orderPropertyNames = null;
		String[] _orderByTypes = null;
		if(!EasyStringCheckUtils.isEmpty(orderPropertyName)) _orderPropertyNames = new String[]{orderPropertyName};
		if(!EasyStringCheckUtils.isEmpty(orderByType)) _orderByTypes = new String[]{orderByType};
		return this.findPageByOrder(model, _orderPropertyNames, _orderByTypes, page);
	}

	@Override
	public PageResult<M> findPage(M model, PageParam page) {
		return super.findPageByOrder(model, null, page);
	}

}

