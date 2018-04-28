package com.yffd.easy.framework.common.pojo.entity.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yffd.easy.common.core.exception.EasyCommonException;
import com.yffd.easy.common.core.util.EasyJavaBeanUtils;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年4月27日 下午3:36:35 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class CommonEntityFactory<E> implements ICommonEntityFactory<E> {

	@Override
	public E createEntityWithSameProperties(Object obj, Class<E> entityClazz) {
		if(null==obj || null==entityClazz) return null;
		if(entityClazz.isInstance(obj)) return (E) obj;
		
		try {
			return EasyJavaBeanUtils.copyProperties(obj, entityClazz);
		} catch (Exception e) {
			throw new EasyCommonException("copy the same properties to entity error!", e);
		}
	}

	@Override
	public E createEntityWithSameProperties(Object obj, Map<String, Object> diffPropertiesMap, Class<E> entityClazz) {
		if(null==obj || null==entityClazz) return null;
		if(entityClazz.isInstance(obj)) return (E) obj;
		
		Map<String, Object> diffProperties = this.createMapWithDiffProperties(obj, entityClazz);
		if(null!=diffProperties && !diffProperties.isEmpty()) {
			if(null==diffPropertiesMap) diffPropertiesMap = new HashMap<String, Object>();
			diffPropertiesMap.putAll(diffProperties);
		}
		return this.createEntityWithSameProperties(obj, entityClazz);
	}

	@Override
	public List<E> createEntityWithSameProperties(List<?> objList, Class<E> entityClazz) {
		if(null==objList || objList.isEmpty()) return null;
		Object firstObj = objList.get(0);
		if(null==firstObj || null==entityClazz) return null;
		if(entityClazz.isInstance(firstObj)) return (List<E>) objList;
		
		List<E> entityList = new ArrayList<E>();
		for(Object tmpObj : objList) {
			E obj = this.createEntityWithSameProperties(tmpObj, entityClazz);
			entityList.add(obj);
		}
		return entityList;
	}

	protected <T> Map<String, Object> createMapWithDiffProperties(Object obj, Class<E> entityClazz) {
		try {
			return EasyJavaBeanUtils.copyDiffProperties(obj, entityClazz);
		} catch (Exception e) {
			throw new EasyCommonException("copy the different properties to map error!", e);
		}
	}
	
}

