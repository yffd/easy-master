package com.yffd.easy.framework.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.framework.common.dao.impl.MybatisCommonDaoImpl;
import com.yffd.easy.framework.common.pojo.entity.CommonEntity;
import com.yffd.easy.framework.common.service.ICommonSimpleService;

/**
 * @Description  entity对象贯穿全局的方式，以entity的完整类名作为mapper sql 的命名空间.
 * @Date		 2018年4月19日 上午11:22:22 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public abstract class CommonSimpleServiceImpl<E extends CommonEntity> extends MybatisCommonDaoImpl<E> implements ICommonSimpleService<E> {

	/**
	 * 根据主键更新其它属性
	 * @Date	2018年4月20日 上午10:45:51 <br/>
	 * @author  zhangST
	 * @param model
	 * @return
	 */
	@Override
	public Integer saveOrUpdate(E model) {
		if(null==model) return 0;
		E old = null;
		if(!EasyStringCheckUtils.isEmpty(model.getId())) old = this.findOneByPrimaryId(model.getId());
		Integer num = 0;
		if(null==old) {
			num = super.save(model);
		} else {
			model.setId(old.getId());
			num = super.update(model);
		} 
		return num;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public Integer saveOrUpdate(List<E> modelList) {
		if(null==modelList || modelList.size()==0) return 0;
		Integer num = 0;
		for(E model : modelList) {
			num += this.saveOrUpdate(model);
		}
		return num;
	}

	@Override
	public Integer deleteByProperty(String propertyName, Object value) {
		if(EasyStringCheckUtils.isEmpty(propertyName) || null==value) return 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(propertyName, value);
		return super.delete(null, map);
	}

	@Override
	public Integer deleteByPrimaryId(String primaryId) {
		return this.deleteByProperty("id", primaryId);
	}

	@Override
	public Integer deleteByPrimaryIds(String[] primaryIds) {
		return this.deleteByProperty("idList", primaryIds);
	}

	@Override
	public E findOneByProperty(String propertyName, Object value) {
		if(EasyStringCheckUtils.isEmpty(propertyName) || null==value) return null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(propertyName, value);
		return super.findOne(null, map);
	}

	@Override
	public E findOneByPrimaryId(String primaryId) {
		return this.findOneByProperty("id", primaryId);
	}

	@Override
	public Integer queryCountByProperty(String propertyName, Object value) {
		if(EasyStringCheckUtils.isEmpty(propertyName) || null==value) return 0;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(propertyName, value);
		return super.findCount(null, map);
	}

	@Override
	public List<E> findListByProperty(String propertyName, Object value, String[] orderPropertyNames,
			String[] orderByTypes) {
		if(EasyStringCheckUtils.isEmpty(propertyName) || null==value) return null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(propertyName, value);
		String orderBy = this.makeOrderBy(orderPropertyNames, orderByTypes);
		return super.findListByOrder(null, map, orderBy);
	}

	@Override
	public List<E> findListByProperty(String propertyName, Object value, String orderBy) {
		if(EasyStringCheckUtils.isEmpty(propertyName) || null==value) return null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(propertyName, value);
		return super.findListByOrder(null, map, orderBy);
	}

	@Override
	public List<E> findListByProperty(String propertyName, Object value) {
		if(EasyStringCheckUtils.isEmpty(propertyName) || null==value) return null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(propertyName, value);
		return super.findList(null, map);
	}
	
	@Override
	public PageResult<E> findPageByProperty(String propertyName, Object value, String[] orderPropertyNames,
			String[] orderByTypes, PageParam page) {
		if(EasyStringCheckUtils.isEmpty(propertyName) || null==value) return null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(propertyName, value);
		String orderBy = this.makeOrderBy(orderPropertyNames, orderByTypes);
		return super.findPageByOrder(map, orderBy, page);
	}
	
	@Override
	public PageResult<E> findPageByProperty(String propertyName, Object value, String orderBy, PageParam page) {
		if(EasyStringCheckUtils.isEmpty(propertyName) || null==value) return null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(propertyName, value);
		return super.findPageByOrder(map, orderBy, page);
	}

	@Override
	public PageResult<E> findPageByProperty(String propertyName, Object value, PageParam page) {
		if(EasyStringCheckUtils.isEmpty(propertyName) || null==value) return null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(propertyName, value);
		return super.findPage(map, page);
	}


}

