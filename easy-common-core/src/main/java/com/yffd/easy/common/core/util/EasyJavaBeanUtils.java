package com.yffd.easy.common.core.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import com.yffd.easy.common.core.exception.EasyCommonException;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月12日 下午2:44:27 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class EasyJavaBeanUtils {

	public static <E> E copyProperties(Object source, Class<?> targetClass) throws ReflectiveOperationException, EasyCommonException {
		if(null==source) {
			throw new EasyCommonException("原始对象未指定"); 
		}
		if(null==targetClass) {
			throw new EasyCommonException("目标对象类未指定"); 
		}
		try {
			Object obj = targetClass.newInstance();
			ConvertUtils.register(new Converter() {
				@Override
				public <T> T convert(Class<T> type, Object value) {
					Date date = EasyDateUtils.parseToDate(value.toString());
                    return (T) date;
				}
	        }, Date.class);
			BeanUtils.copyProperties(obj, source);
			return (E) obj;
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw e;
		}
	}
	
}

