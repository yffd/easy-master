package com.yffd.easy.framework.common.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.framework.common.dao.bak.BakICommonExtDao;
import com.yffd.easy.framework.common.service.ICommonService;

/**
 * @Description  VO与PO一样.
 * @Date		 2018年4月25日 上午10:41:15 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public abstract class CommonSimpleServiceImpl<VO> implements ICommonService<VO> {

	protected abstract BakICommonExtDao<VO> getBindDao();
	
	@Override
	public Integer save(VO vo) {
		if(null==vo) return 0;
		return this.getBindDao().save(vo);
	}

	@Override
	public Integer save(List<VO> voList) {
		if(null==voList || voList.isEmpty()) return 0;
		return this.getBindDao().save(voList);
	}

	@Override
	public Integer update(VO vo, VO oldVo, Map<String, Object> map) {
		if(null==vo || (null==oldVo && (null==map || map.isEmpty()))) return 0;
		return this.getBindDao().update(vo, oldVo, map);
	}

	@Override
	public Integer update(VO vo, VO oldVo) {
		return this.update(vo, oldVo, null);
	}

	@Override
	public Integer delete(VO vo, Map<String, Object> map) {
		if(null==vo || (null==map || map.isEmpty())) return 0;
		return this.getBindDao().delete(vo, map);
	}

	@Override
	public Integer delete(VO vo) {
		return this.delete(vo, null);
	}

	@Override
	public Integer queryCount(VO vo, Map<String, Object> map) {
		return this.getBindDao().findCount(vo, map);
	}

	@Override
	public Integer queryCount(VO vo) {
		return this.queryCount(vo, null);
	}

	@Override
	public VO findOne(VO vo, Map<String, Object> map) {
		return this.getBindDao().findOne(vo, map);
	}

	@Override
	public VO findOne(VO vo) {
		return this.findOne(vo, null);
	}

	@Override
	public List<VO> findListWithOrder(VO vo, Map<String, Object> map, String orderBy) {
		return this.getBindDao().findListWithOrder(vo, map, orderBy);
	}

	@Override
	public List<VO> findListWithOrder(VO vo, String orderBy) {
		return this.findListWithOrder(vo, null, orderBy);
	}

	@Override
	public List<VO> findList(VO vo, Map<String, Object> map) {
		return this.findListWithOrder(vo, map, null);
	}
	
	@Override
	public List<VO> findList(VO vo) {
		return this.findListWithOrder(vo, null, null);
	}
	
	@Override
	public List<VO> findAll() {
		return this.findList(null);
	}
	
	@Override
	public List<VO> findAllWithOrder(String orderBy) {
		return this.findListWithOrder(null, orderBy);
	}
	
	@Override
	public PageResult<VO> findPageWithOrder(VO vo, Map<String, Object> map, String orderBy, PageParam page) {
		return this.getBindDao().findPageWithOrder(vo, map, orderBy, page);
	}

	@Override
	public PageResult<VO> findPageWithOrder(VO vo, String orderBy, PageParam page) {
		return this.findPageWithOrder(vo, null, orderBy, page);
	}

	@Override
	public PageResult<VO> findPage(VO vo, Map<String, Object> map, PageParam page) {
		return this.findPageWithOrder(vo, map, null, page);
	}
	
	@Override
	public PageResult<VO> findPage(VO vo, PageParam page) {
		return this.findPageWithOrder(vo, null, null, page);
	}
	
	@Override
	public Boolean exsistAndUnique(VO vo, Map<String, Object> map) {
		Integer result = this.getBindDao().findCount(vo, map);
		return result == 1;
	}

	@Override
	public Boolean exsistAndUnique(VO vo) {
		return this.exsistAndUnique(vo, null);
	}

	@Override
	public Boolean exsist(VO vo, Map<String, Object> map) {
		Integer result = this.getBindDao().findCount(vo, map);
		return result > 0;
	}

	@Override
	public Boolean exsist(VO vo) {
		return this.exsist(vo, null);
	}
	
	
	
	@Override
	public Integer deleteByProperty(String propertyName, Object value) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(propertyName, value);
		return this.delete(null, map);
	}

	@Override
	public Integer deleteByPrimaryIds(String[] primaryIds) {
		return this.deleteByProperty("idList", primaryIds);
	}

	@Override
	public Integer deleteByPrimaryId(String primaryId) {
		return this.deleteByProperty("id", primaryId);
	}

	@Override
	public Integer queryCountByProperty(String propertyName, Object value) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(propertyName, value);
		return this.queryCount(null, map);
	}

	@Override
	public VO findOneByProperty(String propertyName, Object value) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(propertyName, value);
		return this.findOne(null, map);
	}

	@Override
	public VO findOneByPrimaryId(String primaryId) {
		return this.findOneByProperty("id", primaryId);
	}

	@Override
	public List<VO> findListWithOrderByProperty(String propertyName, Object value, String orderBy) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(propertyName, value);
		return this.findListWithOrder(null, map, orderBy);
	}

	@Override
	public List<VO> findListByProperty(String propertyName, Object value) {
		return this.findListWithOrderByProperty(propertyName, value, null);
	}

	@Override
	public PageResult<VO> findPageWithOrderByProperty(String propertyName, Object value, String orderBy,
			PageParam page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(propertyName, value);
		return this.findPageWithOrder(null, map, orderBy, page);
	}

	@Override
	public PageResult<VO> findPageWithOrderByProperty(String propertyName, Object value, String orderPropertyName,
			String orderByType, PageParam page) {
		String orderBy = this.makeOrderBy(orderPropertyName, orderByType);
		return this.findPageWithOrderByProperty(orderPropertyName, value, orderBy, page);
	}

	@Override
	public PageResult<VO> findPageByProperty(String propertyName, Object value, PageParam page) {
		return this.findPageWithOrderByProperty(propertyName, value, null, null, page);
	}

	protected String makeOrderBy(String[] orderPropertyNames, String[] orderByTypes) {
		if(null==orderPropertyNames) return null;
		String defaultType = "asc";
		StringBuilder sb = new StringBuilder();
		if(null==orderByTypes) {
			for(String name : orderPropertyNames) {
				sb.append(name).append(" ").append(defaultType).append(", ");
			}
		} else {
			Integer len1 = orderPropertyNames.length;
			Integer len2 = orderByTypes.length;
			for(Integer i=0;i<len1;i++) {
				sb.append(orderPropertyNames[i]).append(" ");
				if(i<len2) {
					sb.append(orderByTypes[i]).append(", ");
				} else {
					sb.append(defaultType).append(", ");
				}
			}
		}
		return sb.length()>0 ? sb.substring(0, sb.lastIndexOf(",")) : null;
	}
	
	protected String makeOrderBy(String orderPropertyName, String orderByType) {
		String orderBy = null;
		if(!EasyStringCheckUtils.isEmpty(orderPropertyName)) {
			if(!EasyStringCheckUtils.isEmpty(orderByType)) {
				orderBy = orderPropertyName + " " + orderByType;
			} else {
				orderBy = orderPropertyName + " asc";
			}
		}
		return orderBy;
	}
}

