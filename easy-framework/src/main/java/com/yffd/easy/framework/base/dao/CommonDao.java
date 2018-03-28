package com.yffd.easy.framework.base.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yffd.easy.common.core.exception.EasyDaoException;
import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.common.core.util.EasyStringCheckUtils;

/**
 * @Description  持久化常用操作类.
 * @Date		 2018年1月29日 下午5:42:56 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Component
public class CommonDao extends BaseDaoAbstract {
    
	@Autowired
    private SqlSession sqlSession;
	
	@Override
	public SqlSession getSqlSession() {
		return this.sqlSession;
	}
	
	/**
	 * 统计查询.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_COUNT_BY} <br/>
	 * @Date	2018年3月2日 上午9:38:35 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @return
	 */
	public Long selectCountBy(Map<String, Object> paramMap) {
		if(null==paramMap) paramMap = new HashMap<String, Object>();
        return super.selectCountBy_(paramMap);
	}
	
	/**
	 * 分页查询，并返回分页数据、分页信息.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_LIST_BY} <br/>
	 * @sqlcountid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_COUNT_BY} <br/>
	 * @Date	2018年3月2日 上午9:39:21 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @param pageParam
	 * @return
	 */
	public PageResult<?> selectPage(Map<String, Object> paramMap, PageParam pageParam) {
		List<Object> recordList = this.selectRangeList(paramMap, pageParam, getStatement(SQL_SELECT_LIST_BY), getStatement(SQL_SELECT_COUNT_BY));
		return new PageResult<Object>(pageParam, recordList);
	}

	/**
	 * 列表查询.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_LIST_BY} <br/>
	 * @Date	2018年3月2日 上午9:39:37 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @return
	 */
	public List<?> selectListBy(Map<String, Object> paramMap) {
		return this.selectRangeList(paramMap, null, getStatement(SQL_SELECT_LIST_BY), null);
	}
	
	/**
	 * 单条查询.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_ONE_BY} <br/>
	 * @Date	2018年3月2日 上午9:44:57 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @return
	 */
	public <T> T selectOne(Map<String, Object> paramMap) {
		if(null==paramMap) paramMap = new HashMap<String, Object>();
		return super.selectOne_(paramMap);
	}

	/**
	 * 主键查询.<br/>
	 * @Date	2018年3月2日 上午9:41:17 <br/>
	 * @author  zhangST
	 * @param id
	 * @return
	 * @see #selectOne(Map)
	 */
	public <T> T selectById(String id) {
		if(EasyStringCheckUtils.isEmpty(id)) throw EasyDaoException.DB_SELECT_NULL(getStatement(SQL_SELECT_ONE_BY));
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		return this.selectOne(paramMap);
	}
	
	/**
	 * in条件查询.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_SELECT_LIST_BY} <br/>
	 * @Date	2018年3月2日 上午9:41:35 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 * @see #selectListBy(Map)
	 */
	public List<?> selectListByIds(List<String> list) {
		if(null==list || list.size()==0) throw EasyDaoException.DB_SELECT_NULL(getStatement(SQL_SELECT_LIST_BY));
		List<Map<String, Object>> paramList = new ArrayList<Map<String, Object>>();
		for(String id : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			paramList.add(map);
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("idList", paramList);
        return this.selectListBy(paramMap);
	}
	
	/**
	 * 查询所有.<br/>
	 * @Date	2018年3月2日 上午9:43:31 <br/>
	 * @author  zhangST
	 * @return
	 * @see #selectListBy(java.util.Map)
	 */
	public List<?> selectAll() {
		return this.selectListBy(null);
	}
	
	/**
	 * 单条插入.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_INSERT_ONE} <br/>
	 * @Date	2018年3月2日 上午9:45:14 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @return
	 */
	public int insertOne(Map<String, Object> paramMap) {
		if(null==paramMap || paramMap.size()==0) throw EasyDaoException.DB_INSERT_NULL(getStatement(SQL_INSERT_ONE));
		int result = super.insertOne_(paramMap);
//        if(result <= 0) throw EasyDaoException.DB_INSERT_RESULT_0(getStatement(SQL_INSERT_ONE));
        return result;
	}

	/**
	 * 批量插入.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_INSERT_BATCH} <br/>
	 * @Date	2018年3月2日 上午9:45:25 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 */
	public int insertBatch(List<?> list) {
		if(null==list || list.size()==0) throw EasyDaoException.DB_INSERT_NULL(getStatement(SQL_INSERT_BATCH));
        int result = super.insertBatch_(list);
//        if(result <= 0) throw EasyDaoException.DB_INSERT_RESULT_0(getStatement(SQL_INSERT_BATCH));
        return result;
	}

	/**
	 * 更新.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_DELETE_BY} <br/>
	 * @Date	2018年3月13日 下午3:41:23 <br/>
	 * @author  zhangST
	 * @param newMap
	 * @param oldMap
	 * @return
	 */
	public int updateBy(Map<String, Object> newMap, Map<String, Object> oldMap) {
		if(null==newMap || newMap.size()==0 || null==oldMap || oldMap.size()==0) 
			throw EasyDaoException.DB_UPDATE_NULL(getStatement(SQL_DELETE_BY));
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("newParam", newMap);
		paramMap.put("oldParam", oldMap);
		int result = super.updateBy_(paramMap);
		return result;
	}
	
	/**
	 * 更新.<br/>
	 * @Date	2018年3月13日 下午4:47:06 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @param attributeNames		条件属性名称集合
	 * @return
	 */
	public int updateBy(Map<String, Object> paramMap, String... attributeNames) {
		if(null==paramMap || paramMap.size()==0 || null==attributeNames || attributeNames.length==0) 
			throw EasyDaoException.DB_UPDATE_NULL(getStatement(SQL_DELETE_BY));
		Map<String, Object> oldMap = new HashMap<String, Object>();
		Map<String, Object> newMap = new HashMap<String, Object>();
		newMap.putAll(paramMap);
		
		for(String attributeName : attributeNames) {
			if(paramMap.containsKey(attributeName)) {
				Object idValue = paramMap.get(attributeName);
				if(null==idValue || EasyStringCheckUtils.isEmpty((String) idValue)) continue;
				oldMap.put(attributeName, paramMap.get(attributeName));
				
				newMap.remove(attributeName);
			}
		}
		int result = this.updateBy(newMap, oldMap);
		return result;
	}
	
	/**
	 * 更新.<br/>
	 * @Date	2018年3月13日 下午4:39:21 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @param attributeName		条件属性名称
	 * @return
	 * @see #updateBy(Map, String...)
	 */
	public int updateBy(Map<String, Object> paramMap, String attributeName) {
		String[] arr = {attributeName};
		int result = this.updateBy(paramMap, arr);
		return result;
	}
	
	/**
	 * 更新.<br/>
	 * @Date	2018年3月13日 下午3:43:51 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @return			-1：没有找到key为“id”所对应的有效值
	 * @see #updateBy(Map, String...)
	 */
	public int updateById(Map<String, Object> paramMap) {
		String[] arr = {"id"};
		int result = this.updateBy(paramMap, arr);
		return result;
	}
	
	/**
	 * 更新.<br/>
	 * @Date	2018年3月13日 下午3:55:25 <br/>
	 * @author  zhangST
	 * @param newMap
	 * @param idsList
	 * @return
	 * @see #updateWithInBy(Map, Map, Map)
	 */
	public int updateByIds(Map<String, Object> newMap, List<String> idsList) {
		if(null==idsList || idsList.size()==0) 
			throw EasyDaoException.DB_DELETE_NULL(getStatement(SQL_DELETE_BY));
		
		Map<String, Object> oldInMap = new HashMap<String, Object>();
		oldInMap.put("id", idsList);
		int result = this.updateWithInBy(newMap, null, oldInMap);
		return result;
	}
	
	/**
	 * 更新.<br/>
	 * @Date	2018年3月13日 下午3:52:34 <br/>
	 * @author  zhangST
	 * @param newMap		新值集合，作为set列
	 * @param inName		in条件名称，作为where列
	 * @param inValueList	in条件值，不可以为空，数据格式：List<String> == " ID in ( 1,2,3 ) "
	 * @return
	 * @see #updateWithInBy(Map, Map, Map)
	 */
	public int updateWithInBy(Map<String, Object> newMap, String inName, List<?> inValueList) {
		if(EasyStringCheckUtils.isEmpty(inName) || null==inValueList || inValueList.size()==0) 
			throw EasyDaoException.DB_DELETE_NULL(getStatement(SQL_DELETE_BY));
		
		Map<String, Object> oldInMap = new HashMap<String, Object>();
		oldInMap.put(inName, inValueList);
		int result = this.updateWithInBy(newMap, null, oldInMap);
		return result;
	}
	
	/**
	 * 更新.<br/>
	 * @Date	2018年3月13日 下午3:50:19 <br/>
	 * @author  zhangST
	 * @param newMap		新值集合，作为set列
	 * @param oldMap		旧值集合，作为where列
	 * @param inName		in条件名称，不可以为空，作为where列
	 * @param inValueList	in条件值，不可以为空，数据格式：List<String> == " ID in ( 1,2,3 ) "
	 * @return
	 * @see #updateWithInBy(Map, Map, Map)
	 */
	public int updateWithInBy(Map<String, Object> newMap, Map<String, Object> oldMap, String inName, List<?> inValueList) {
		if(EasyStringCheckUtils.isEmpty(inName) || null==inValueList || inValueList.size()==0) 
			throw EasyDaoException.DB_DELETE_NULL(getStatement(SQL_DELETE_BY));
		Map<String, Object> oldInMap = new HashMap<String, Object>();
		oldInMap.put(inName, inValueList);
		int result = this.updateWithInBy(newMap, oldMap, oldInMap);
		return result;
	}
	
	/**
	 * 更新.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_DELETE_BY} <br/>
	 * @Date	2018年3月13日 下午4:13:33 <br/>
	 * @author  zhangST
	 * @param newMap		新值集合，作为set列
	 * @param oldMap		旧值集合，作为where列
	 * @param oldInMap		in值集合，作为where列，数据格式：inName：inValueList，如：Map<String,List<String>> == Map<"id", List<String>> == " ID in ( 1,2,3 ) "
	 * @return
	 */
	public int updateWithInBy(Map<String, Object> newMap, Map<String, Object> oldMap, Map<String, Object> oldInMap) {
		if((null==oldMap || oldMap.size()==0)
				&& (null==oldInMap || oldInMap.size()==0)) throw EasyDaoException.DB_DELETE_NULL(getStatement(SQL_DELETE_BY));
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("newParam", newMap);
		paramMap.put("oldParam", oldMap);

		Map<String, Object> _oldInMap = null;
		if(null!=oldInMap && oldInMap.size()>0) {
			_oldInMap = new HashMap<String, Object>();
			for(Entry<String, Object> entry : oldInMap.entrySet()) {
				_oldInMap.put(entry.getKey() + "List", entry.getValue());
			}
		}
		
		paramMap.put("oldInParam", _oldInMap);
		int result = this.updateBy_(paramMap);
		return result;
	}
	
	/**
	 * 删除.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_DELETE_BY} <br/>
	 * @Date	2018年3月2日 上午9:47:23 <br/>
	 * @author  zhangST
	 * @param paramMap
	 * @return
	 */
	public int deleteBy(Map<String, Object> paramMap) {
		if(null==paramMap || paramMap.size()==0) throw EasyDaoException.DB_DELETE_NULL(getStatement(SQL_DELETE_BY));
		int result = super.deleteBy_(paramMap);
        return result;
	}
	
	/**
	 * 删除-主键.<br/>
	 * @Date	2018年3月2日 上午9:46:08 <br/>
	 * @author  zhangST
	 * @param id
	 * @return
	 * @see #deleteBy(Map)
	 */
	public int deleteById(String id) {
		if(EasyStringCheckUtils.isEmpty(id)) throw EasyDaoException.DB_DELETE_NULL(getStatement(SQL_DELETE_BY));
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		int result = this.deleteBy(paramMap);
        return result;
	}
	
	/**
	 * 删除-主键.<br/>
	 * @Date	2018年3月2日 上午9:46:42 <br/>
	 * @author  zhangST
	 * @param list
	 * @return
	 * @see #deleteWithInBy(Map, Map)
	 */
	public int deleteByIds(List<String> list) {
		int result = this.deleteWithInBy(null, "id", list);
		if(null==list || list.size()==0) throw EasyDaoException.DB_DELETE_NULL(getStatement(SQL_DELETE_BY));
		Map<String, Object> inMap = new HashMap<String, Object>();
		inMap.put("id", list);
        return result;
	}
	
	/**
	 * 删除.<br/>
	 * @Date	2018年3月12日 下午4:49:38 <br/>
	 * @author  zhangST
	 * @param inName			in条件名称
	 * @param inValueList		in条件，格式：List<String> == " ID in ( 1,2,3 ) "
	 * @return
	 * @see #deleteWithInBy(Map, Map)
	 */
	public int deleteWithInBy(String inName, List<?> inValueList) {
		if(EasyStringCheckUtils.isEmpty(inName) || null==inValueList || inValueList.size()==0) 
			throw EasyDaoException.DB_DELETE_NULL(getStatement(SQL_DELETE_BY));
		Map<String, Object> inMap = new HashMap<String, Object>();
		inMap.put(inName, inValueList);
		int result = this.deleteWithInBy(null, inMap);
		return result;
	}
	
	/**
	 * 删除.<br/>
	 * @Date	2018年3月12日 下午4:49:38 <br/>
	 * @author  zhangST
	 * @param paramMap			其它条件
	 * @param inName			in条件名称
	 * @param inValueList		in条件，格式：List<String> == " ID in ( 1,2,3 ) "
	 * @return
	 * @see #deleteWithInBy(Map, Map)
	 */
	public int deleteWithInBy(Map<String, Object> paramMap, String inName, List<?> inValueList) {
		if(EasyStringCheckUtils.isEmpty(inName) || null==inValueList || inValueList.size()==0) 
			throw EasyDaoException.DB_DELETE_NULL(getStatement(SQL_DELETE_BY));
		Map<String, Object> inMap = new HashMap<String, Object>();
		inMap.put(inName, inValueList);
		int result = this.deleteWithInBy(paramMap, inMap);
		return result;
	}
	
	/**
	 * 删除.<br/>
	 * @sqlid : {@link com.yffd.easy.framework.base.dao.BaseDaoAbstract#SQL_DELETE_BY} <br/>
	 * @Date	2018年3月12日 下午4:41:04 <br/>
	 * @author  zhangST
	 * @param paramMap		普通条件
	 * @param inMap			in条件，格式：Map<String, List<String>> == Map<"id", List<String>> == " ID in ( 1,2,3 ) "
	 * @return
	 */
	public <T> int deleteWithInBy(Map<String, Object> paramMap, Map<String, T> inMap) {
		if(null!=inMap && inMap.size()>0) {
			if(null==paramMap || paramMap.size()==0) paramMap = new HashMap<String, Object>();
			Set<String> keys = inMap.keySet();
			for(String key : keys) {
				paramMap.put(key + "List", inMap.get(key));
			}
		}
		if(null==paramMap || paramMap.size()==0) throw EasyDaoException.DB_DELETE_NULL(getStatement(SQL_DELETE_BY));
		return super.deleteBy_(paramMap);
	}
	
}

