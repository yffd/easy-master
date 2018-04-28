package com.yffd.easy.framework.common.pojo.entity.factory;

import java.util.List;
import java.util.Map;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年4月27日 下午3:33:10 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface ICommonEntityFactory<E> {

	E createEntityWithSameProperties(Object obj, Class<E> entityClazz);
	
	E createEntityWithSameProperties(Object obj, Map<String, Object> diffPropertiesMap, Class<E> entityClazz);
	
	List<E> createEntityWithSameProperties(List<?> objList, Class<E> entityClazz);
	
}

