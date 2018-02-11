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
	
	public abstract String getRangeId();
	
	public Object getModelInstanceForSelect() {
		return this.getModelInstance("select", 0, null);
	}
	
	public Object getModelInstanceForInsertOne() {
		String modelId = this.getIndx() + "";
		return this.getModelInstance("add", 0, modelId);
	}
	
	public List<Object> getModelInstanceForInsertBatch() {
		int startId = this.getStartIndx();
		int endId = this.getEndIndx();
		List<Object> list = new ArrayList<Object>();
		for(int i=startId;i<=endId;i++) {
			list.add(this.getModelInstance("add", i, i+""));
		}
		return list;
	}
	
	public Object getModelInstanceForUpdate() {
		String modelId = this.getIndx() + "";
		return this.getModelInstance("update", 0, modelId);
	}
	
	public List<Object> getModelInstanceForUpdateBatch() {
		int startId = this.getStartIndx();
		int endId = this.getEndIndx();
		List<Object> list = new ArrayList<Object>();
		for(int i=startId;i<=endId;i++) {
			list.add(this.getModelInstance("update", i, i+""));
		}
		return list;
	}
	
	public Object getModelInstanceForDelete() {
		String modelId = this.getIndx() + "";
		return this.getModelInstance("delete", 0, modelId);
	}
	
	public List<Object> getModelInstanceForDeleteBatch() {
		int startId = this.getStartIndx();
		int endId = this.getEndIndx();
		List<Object> list = new ArrayList<Object>();
		for(int i=startId;i<=endId;i++) {
			list.add(this.getModelInstance("delete", i, i+""));
		}
		return list;
	}
	
	private Object getModelInstance(String flag, int suffix, String id) {
		Class<?> modelClass = getModelClass();
		try {
			Object obj = modelClass.newInstance();
			BeanInfo beanInfo = Introspector.getBeanInfo(modelClass);
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			for(PropertyDescriptor pd : pds) {
				Class<?> typeClass = pd.getPropertyType();
				String propName = pd.getName();
				if("select".equals(flag)) {
					if("id".equals(propName)) {
					} else if("version".equals(propName)) {
					} else if("creator".equals(propName)) {
					} else if("createTime".equals(propName)) {
					} else if("editor".equals(propName)) {
					} else if("editTime".equals(propName)) {
					} else if("delFlag".equals(propName)) {
						Method writeMethod = pd.getWriteMethod();
						writeMethod.invoke(obj, "0");
					} else {
						if(String.class.getName().equals(typeClass.getName())) {
							Method writeMethod = pd.getWriteMethod();
							if(propName.contains("name") || propName.contains("Name")) {
								writeMethod.invoke(obj, "a_a_");
							} else if(propName.contains("type") || propName.contains("Type") || propName.contains("status") || propName.contains("Status")) {
								Random random = new Random();
								int pick = random.nextInt(3) + 1;
								writeMethod.invoke(obj, pick + "");
							}
							
						} else if(char.class.getName().equals(typeClass.getName())) {
						} else if(Date.class.getName().equals(typeClass.getName())) {
						} else if(Boolean.class.getName().equals(typeClass.getName()) || boolean.class.getName().equals(typeClass.getName())) {
						} else if(Number.class.isAssignableFrom(typeClass)) {
						}
					}
				} else if("delete".equals(flag)) {
					if("id".equals(propName)) {
						Method writeMethod = pd.getWriteMethod();
						writeMethod.invoke(obj, id);
						break;
					}
				} else if("id".equals(propName)) {
					if(null!=id) {
						Method writeMethod = pd.getWriteMethod();
						writeMethod.invoke(obj, id);
					}
				} else if("version".equals(propName)) {
					Method writeMethod = pd.getWriteMethod();
					writeMethod.invoke(obj, 0);
				} else if("creator".equals(propName)) {
					if("add".endsWith(flag)) {
						Method writeMethod = pd.getWriteMethod();
						writeMethod.invoke(obj, "tester1");
					}
				} else if("createTime".equals(propName)) {
					if("add".endsWith(flag)) {
						Method writeMethod = pd.getWriteMethod();
						writeMethod.invoke(obj, new Date());
					}
				} else if("editor".equals(propName)) {
					if("update".endsWith(flag)) {
						Method writeMethod = pd.getWriteMethod();
						writeMethod.invoke(obj, "tester2");
					}
				} else if("editTime".equals(propName)) {
					if("update".endsWith(flag)) {
						Method writeMethod = pd.getWriteMethod();
						writeMethod.invoke(obj, new Date());
					}
				} else if("delFlag".equals(propName)) {
					Method writeMethod = pd.getWriteMethod();
					writeMethod.invoke(obj, "0");
				} else {
					if(String.class.getName().equals(typeClass.getName())) {
						if("add".equals(flag)) {
							Method writeMethod = pd.getWriteMethod();
							if(propName.contains("type") || propName.contains("Type") || propName.contains("status") || propName.contains("Status")) {
								Random random = new Random();
								int pick = random.nextInt(4);
								writeMethod.invoke(obj, pick + "");
							} else {
								writeMethod.invoke(obj, "a_a_" + suffix);
							}
						} else if("update".equals(flag)) {
							
						} else {
							Method writeMethod = pd.getWriteMethod();
							writeMethod.invoke(obj, "u_u_" + suffix);
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
	
	private int getStartIndx() {
		String range = this.getRangeId();
		String[] nums = range.split(":");
		return Integer.parseInt(nums[0]);
	}
	
	private int getEndIndx() {
		String range = this.getRangeId();
		String[] nums = range.split(":");
		return Integer.parseInt(nums[1]);
	}
	
	private int getIndx() {
		String range = this.getRangeId();
		String[] nums = range.split(":");
		return Integer.parseInt(nums[1]) + 1000;
	}
}

