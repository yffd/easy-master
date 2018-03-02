package com.yffd.easy.framework.test;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Description  测试用例帮助类，主要完成model的实例化、以及属性赋值.
 * @Date		 2018年2月5日 下午4:00:45 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public abstract class GenericDaoTestCase {
	
	public abstract Class<?> getModelClass();
	
	public Object getRandomModel() {
		Class<?> modelClass = getModelClass();
		try {
			Object obj = modelClass.newInstance();
			BeanInfo beanInfo = Introspector.getBeanInfo(modelClass);
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			for(PropertyDescriptor pd : pds) {
				Class<?> typeClass = pd.getPropertyType();
				String propName = pd.getName();
				if("id".equals(propName)) {
					Method writeMethod = pd.getWriteMethod();
					writeMethod.invoke(obj, "2000");
				} else if("version".equals(propName)) {
					Method writeMethod = pd.getWriteMethod();
					writeMethod.invoke(obj, 0);
				} else if("createBy".equals(propName)) {
					Method writeMethod = pd.getWriteMethod();
					writeMethod.invoke(obj, "tester1");
				} else if("createTime".equals(propName)) {
					Method writeMethod = pd.getWriteMethod();
					writeMethod.invoke(obj, new Date());
				} else if("updateBy".equals(propName)) {
					Method writeMethod = pd.getWriteMethod();
					writeMethod.invoke(obj, "tester2");
				} else if("updateTime".equals(propName)) {
					Method writeMethod = pd.getWriteMethod();
					writeMethod.invoke(obj, new Date());
				} else if("delFlag".equals(propName)) {
					Method writeMethod = pd.getWriteMethod();
					writeMethod.invoke(obj, "0");
				} else {
					if(String.class.getName().equals(typeClass.getName())) {
						Random random = new Random();
						Method writeMethod = pd.getWriteMethod();
						if(propName.contains("name") || propName.contains("Name")) {
							writeMethod.invoke(obj, "name_" + random.nextInt(10));
						} else if(propName.contains("type") || propName.contains("Type") || propName.contains("status") || propName.contains("Status")) {
							int pick = random.nextInt(3) + 1;
							writeMethod.invoke(obj, pick + "");
						} else {
							writeMethod.invoke(obj, "a_a_" + random.nextInt(20));
						}
					} else if(char.class.getName().equals(typeClass.getName())) {
						Method writeMethod = pd.getWriteMethod();
						writeMethod.invoke(obj, "1");
					} else if(Date.class.getName().equals(typeClass.getName())) {
						Method writeMethod = pd.getWriteMethod();
						writeMethod.invoke(obj, new Date());
					} else if(Boolean.class.getName().equals(typeClass.getName()) || boolean.class.getName().equals(typeClass.getName())) {
						Method writeMethod = pd.getWriteMethod();
						writeMethod.invoke(obj, true);
					} else if(Number.class.isAssignableFrom(typeClass)) {
						Method writeMethod = pd.getWriteMethod();
						writeMethod.invoke(obj, 10);
					}
				}
			}
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<?> getRandomModelList() {
		List<Object> list = new ArrayList<Object>();
		for(int i=2000;i<2015;i++) {
			list.add(this.getRandomModel());
		}
		return list;
	}
	
}

