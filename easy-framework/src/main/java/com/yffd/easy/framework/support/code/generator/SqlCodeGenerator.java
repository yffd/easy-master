package com.yffd.easy.framework.support.code.generator;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import com.yffd.easy.framework.domain.GenericPO;

/**
 * @Description  Mapper中的sql片段拼写生成器.
 * @Date		 2018年2月6日 下午2:56:28 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class SqlCodeGenerator extends CodeGenerator {
	
	/**
	 * 生成表的列名称
	 * @Date	2018年2月1日 下午4:12:02 <br/>
	 * @author  zhangST
	 * @param modelClazz
	 * @param tableAliasName
	 * @return
	 */
	public String makeTableColumns(Class<?> modelClazz, String tableAliasName) {
		LinkedHashMap<String, Class<?>> propsMap = this.sortPropsName(modelClazz);
		if(null==propsMap || propsMap.size()==0) return null;
		
		StringBuilder sb = new StringBuilder();
		for(Iterator<Entry<String, Class<?>>> it = propsMap.entrySet().iterator();it.hasNext();) {
			Entry<String, Class<?>> entry = (Entry<String, Class<?>>)it.next();
			String propName = entry.getKey();
			String columnName = this.name2column(propName);
			if(null==columnName || "".equals(columnName)) continue;
			if(null!=tableAliasName && !"".equals(tableAliasName)) {
				columnName = tableAliasName + "." + columnName;
			}
			sb.append(columnName).append(", ");
		}
		
		if(sb.length()==0) return null;
		int endIndex = sb.lastIndexOf(",");
		return sb.toString().substring(0, endIndex);
	}
	
	/**
	 * 生成查询条件，如果名称包含“name”或“Name”，则为like查询，否则为equal查询
	 * @Date	2018年2月1日 下午4:32:38 <br/>
	 * @author  zhangST
	 * @param modelClazz
	 * @param tableAliasName
	 * @return
	 */
	public String makeConditionsWhere(Class<?> modelClazz, String tableAliasName) {
		LinkedHashMap<String, Class<?>> propsMap = this.sortPropsName(modelClazz);
		if(null==propsMap || propsMap.size()==0) return null;
		return this.makeConditionsWhere(modelClazz, tableAliasName, null, true);
	}
	
	/**
	 * 生成resultMap
	 * @Date	2018年2月1日 下午5:06:17 <br/>
	 * @author  zhangST
	 * @param modelClazz
	 * @return
	 */
	public String makeResultMap(Class<?> modelClazz) {
		LinkedHashMap<String, Class<?>> propsMap = this.sortPropsName(modelClazz);
		if(null==propsMap || propsMap.size()==0) return null;
		
		List<String> resultList = new ArrayList<String>();
		String idColumn = null;
		for(Iterator<Entry<String, Class<?>>> it = propsMap.entrySet().iterator();it.hasNext();) {
			Entry<String, Class<?>> entry = (Entry<String, Class<?>>)it.next();
			String propName = entry.getKey();
			String columnName = this.name2column(propName);
			if(null==columnName || "".equals(columnName)) continue;
			if("id".equals(propName)) {
				idColumn = "<id column=\"ID\" property=\"id\" />";
			} else {
				String tmp = "<result column=\""+columnName+"\" property=\""+propName+"\" />";
				resultList.add(tmp);
			}
		}
		
		if(resultList.size()==0) return null;
		
		StringBuilder sb = new StringBuilder();
		if(null!=idColumn) sb.append(idColumn).append("\r\n");
		
		int size = resultList.size();
		if(size>0) {
			for(int i=0;i<size;i++) {
				sb.append(resultList.get(i));
				if(i<size-1) sb.append("\r\n"); 
			}
		}
		return sb.toString();
	}
	
	/**
	 * 生成单条插入
	 * @Date	2018年2月1日 下午5:13:04 <br/>
	 * @author  zhangST
	 * @param modelClazz
	 * @return
	 */
	public String makeInsertOne(Class<?> modelClazz) {
		LinkedHashMap<String, Class<?>> propsMap = this.sortPropsName(modelClazz);
		if(null==propsMap || propsMap.size()==0) return null;
		
		StringBuilder columns = new StringBuilder();
		StringBuilder params = new StringBuilder();
		
		for(Iterator<Entry<String, Class<?>>> it = propsMap.entrySet().iterator();it.hasNext();) {
			Entry<String, Class<?>> entry = (Entry<String, Class<?>>)it.next();
			String propName = entry.getKey();
			if("id".equals(propName)) continue;
			String columnName = this.name2column(propName);
			if(null==columnName || "".equals(columnName)) continue;
			columns.append(columnName).append(", ");
			params.append("#{").append(propName).append("}, ");
		}
		
		StringBuilder sb = new StringBuilder();
		if(columns.length()>0) {
			sb.append("(");
			sb.append(columns.substring(0, columns.lastIndexOf(",")));
			sb.append(")");
			sb.append("\r\n");
			sb.append("values");
			sb.append("\r\n");
			sb.append("(");
			sb.append(params.substring(0, params.lastIndexOf(",")));
			sb.append(")");
		}
		if(sb.length()==0) return null;
		return sb.toString();
	}
	
	/**
	 * 生成单条修改
	 * @Date	2018年2月1日 下午5:18:45 <br/>
	 * @author  zhangST
	 * @param modelClazz
	 * @return
	 */
	public String makeUpdateBy(Class<?> modelClazz) {
		LinkedHashMap<String, Class<?>> propsMap = this.sortPropsName(modelClazz);
		if(null==propsMap || propsMap.size()==0) return null;
		
		List<String> setList = new ArrayList<String>();
		for(Iterator<Entry<String, Class<?>>> it = propsMap.entrySet().iterator();it.hasNext();) {
			Entry<String, Class<?>> entry = (Entry<String, Class<?>>)it.next();
			String typeName = entry.getValue().getName();
			String propName = entry.getKey();
			String columnName = this.name2column(propName);
			if(null==columnName || "".equals(columnName)) continue;
			columnName = columnName + " = " + "#{"+propName+"}, ";
			if("version".equals(propName)) {
				String tmp = "VERSION = VERSION + 1, ";
				setList.add(tmp);
			} else if("id".equals(propName) || "creator".equals(propName) || "createTime".equals(propName)) {
				
			} else if(String.class.getName().equals(typeName)) {
				String tmp = "<if test=\""+propName+" != null and "+propName+" != ''\"> "+columnName+" </if>";
				setList.add(tmp);
			} else {
				String tmp = "<if test=\""+propName+" != null \"> "+columnName+" </if>";
				setList.add(tmp);
			}
		}
		
		if(setList.size()==0) return null;
				
		StringBuilder sb = new StringBuilder();
		sb.append("<set>").append("\r\n");
		
		for(String name : setList) {
			sb.append(name).append("\r\n");
		}
		sb.append("</set>");
		return sb.toString();
	}
	
	/**
	 * 生成删除
	 * @Date	2018年2月1日 下午5:28:40 <br/>
	 * @author  zhangST
	 * @param modelClazz
	 * @return
	 */
	public String makeDeleteBy(Class<?> modelClazz) {
		String conditionsWhere = this.makeConditionsWhere(modelClazz, null, null, false);
		StringBuilder sb = new StringBuilder();
		sb.append("<where>").append("\r\n");
		sb.append(conditionsWhere);
		sb.append("\r\n").append("</where>");
		
		return sb.toString();
	}
	
	/**
	 * 生成批量插入
	 * @Date	2018年2月1日 下午5:36:37 <br/>
	 * @author  zhangST
	 * @param modelClazz
	 * @return
	 */
	public String makeInsertBatch(Class<?> modelClazz) {
		LinkedHashMap<String, Class<?>> propsMap = this.sortPropsName(modelClazz);
		if(null==propsMap || propsMap.size()==0) return null;
		
		StringBuilder columns = new StringBuilder();
		StringBuilder params = new StringBuilder();
		
		for(Iterator<Entry<String, Class<?>>> it = propsMap.entrySet().iterator();it.hasNext();) {
			Entry<String, Class<?>> entry = (Entry<String, Class<?>>)it.next();
			String propName = entry.getKey();
			if("id".equals(propName)) continue;
			String columnName = this.name2column(propName);
			if(null==columnName || "".equals(columnName)) continue;
			columns.append(columnName).append(", ");
			params.append("#{item.").append(propName).append("},\r\n");
		}
		
		StringBuilder sb = new StringBuilder();
		if(columns.length()>0) {
			sb.append("(");
			sb.append(columns.substring(0, columns.lastIndexOf(",")));
			sb.append(")");
			sb.append("\r\n");
			sb.append("values");
			sb.append("\r\n");
			sb.append("<foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">");
			sb.append("\r\n");
			sb.append("(");
			sb.append(params.substring(0, params.lastIndexOf(",")));
			sb.append(")");
			sb.append("\r\n");
			sb.append("</foreach>");
		}
		if(sb.length()==0) return null;
		return sb.toString();
	}
	
	/**
	 * 生成批量修改
	 * @Date	2018年2月1日 下午5:38:44 <br/>
	 * @author  zhangST
	 * @param modelClazz
	 * @return
	 */
	public String makeUpdateBatch(Class<?> modelClazz) {
		LinkedHashMap<String, Class<?>> propsMap = this.sortPropsName(modelClazz);
		if(null==propsMap || propsMap.size()==0) return null;
		
		List<String> setList = new ArrayList<String>();
		for(Iterator<Entry<String, Class<?>>> it = propsMap.entrySet().iterator();it.hasNext();) {
			Entry<String, Class<?>> entry = (Entry<String, Class<?>>)it.next();
			String typeName = entry.getValue().getName();
			String propName = entry.getKey();
			String columnName = this.name2column(propName);
			if(null==columnName || "".equals(columnName)) continue;
			columnName = columnName + " = " + "#{item."+propName+"}, ";
			
			if("version".equals(propName)) {
				String tmp = "VERSION = VERSION + 1, ";
				setList.add(tmp);
			} else if("id".equals(propName) || "creator".equals(propName) || "creatTime".equals(propName)) {
				
			} else if(String.class.getName().equals(typeName)) {
				String tmp = "<if test=\"item."+propName+" != null and item."+propName+" != ''\"> "+columnName+" </if>";
				setList.add(tmp);
			} else {
				String tmp = "<if test=\"item."+propName+" != null \"> "+columnName+" </if>";
				setList.add(tmp);
			}
		}
		
		if(setList.size()==0) return null;
		
//		String whereConditions = this.makeConditionsWhere(modelClazz, null, "item", false);
		
		StringBuilder sb = new StringBuilder();
		sb.append("<foreach collection=\"list\" item=\"item\" index=\"index\" separator=\";\">").append("\r\n");
		sb.append("update <include refid=\"table_name\" />").append("\r\n");
		sb.append("<set>").append("\r\n");
		for(String name : setList) {
			sb.append(name).append("\r\n");
		}
		sb.append("</set>").append("\r\n");
		sb.append("<where>").append("\r\n");
		sb.append("\t").append("ID = #{item.id}").append("\r\n");
		sb.append("\t").append("<if test=\"item.version != null \"> and VERSION = #{item.version} </if>").append("\r\n");
//		sb.append(whereConditions).append("\r\n");
		sb.append("</where>").append("\r\n");
		sb.append("</foreach>");
		return sb.toString();
	}
	
	/**
	 * 
	 * @Date	2018年2月1日 下午5:32:07 <br/>
	 * @author  zhangST
	 * @param modelClazz
	 * @param tableAliasName
	 * @param parameterAliasName
	 * @param autoLikeQuery     true：“name” 或 “Name” 转为 like查询，否则为equals查询
	 * @return
	 */
	public String makeConditionsWhere(Class<?> modelClazz, String tableAliasName, String parameterAliasName, boolean autoLikeQuery) {
		LinkedHashMap<String, Class<?>> propsMap = this.sortPropsName(modelClazz);
		if(null==propsMap || propsMap.size()==0) return null;
		
		List<String> equalsList = new ArrayList<String>();
		List<String> likeList = new ArrayList<String>();
		for(Iterator<Entry<String, Class<?>>> it = propsMap.entrySet().iterator();it.hasNext();) {
			Entry<String, Class<?>> entry = (Entry<String, Class<?>>)it.next();
			String typeName = entry.getValue().getName();
			String propName = entry.getKey();
			String columnName = this.name2column(propName);
			if(null==columnName || "".equals(columnName)) continue;
			
			if(null!=tableAliasName && !"".equals(tableAliasName)) {
				columnName = tableAliasName + "." + columnName;
			}
			String parameterName = propName;
			if(null!=parameterAliasName && !"".equals(parameterAliasName)) {
				parameterName = parameterAliasName + "." + parameterName;
			}
			if(String.class.getName().equals(typeName)) {
				if(autoLikeQuery && (propName.matches(".*name.*") || propName.matches(".*Name.*"))) {
					if(autoLikeQuery) {
						String tmp = "<if test=\""+parameterName+" != null and "+parameterName+" != ''\"> and " + columnName + " like CONCAT(CONCAT('%', #{"+parameterName+"}), '%') </if>";
						likeList.add(tmp);
					}
				} else {
					String tmp = "<if test=\""+parameterName+" != null and "+parameterName+" != ''\"> and " + columnName + " = #{"+parameterName+"} </if>";
					equalsList.add(tmp);
				}
			} else if(Date.class.getName().equals(typeName)) {
				String tmp = "<if test=\""+parameterName+" != null \"><![CDATA[ and " + columnName + " = #{"+parameterName+"}]]> </if>";
				equalsList.add(tmp);
			} else {
				String tmp = "<if test=\""+parameterName+" != null \"> and " + columnName + " = #{"+parameterName+"} </if>";
				equalsList.add(tmp);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int equalsSize = equalsList.size();
		if(equalsSize>0) {
			if(autoLikeQuery) sb.append("<!-- Equal query -->").append("\r\n");
			for(int i=0;i<equalsSize;i++) {
				sb.append(equalsList.get(i));
				if(i<equalsSize-1) sb.append("\r\n"); 
			}
		}
		int likeSize = likeList.size();
		if(likeSize>0) {
			if(sb.length()>0) sb.append("\r\n");
			sb.append("<!-- Like query -->").append("\r\n");
			for(int i=0;i<likeSize;i++) {
				sb.append(likeList.get(i));
				if(i<likeSize-1) sb.append("\r\n"); 
			}
		}
		
		return sb.toString();
	}
	
	protected LinkedHashMap<String, Class<?>> getPropsName(Class<?> modelClazz) {
		try {
			LinkedHashMap<String, Class<?>> propsMap = new LinkedHashMap<String, Class<?>>();
			BeanInfo beanInfo = Introspector.getBeanInfo(modelClazz, Object.class);
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			for(PropertyDescriptor pd : pds) {
				Class<?> typeClazz = pd.getPropertyType();
				String name = pd.getName();
				if(null==name || "".equals(name)) continue;
				propsMap.put(name, typeClazz);
			}
			return propsMap;
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected LinkedHashMap<String, Class<?>> sortPropsName(Class<?> modelClazz) {
		LinkedHashMap<String, Class<?>> propsMap = this.getPropsName(modelClazz);
		if(null==propsMap || propsMap.size()==0) return null;
		
		List<String> sortedPropsList = this.getSortedPropsList();
		LinkedHashMap<String, Class<?>> before = new LinkedHashMap<String, Class<?>>();
		for(String prop : sortedPropsList) {
			if(propsMap.containsKey(prop)) {
				Class<?> value = propsMap.get(prop);
				before.put(prop, value);
				propsMap.remove(prop);
			}
		}
		before.putAll(propsMap);
		return before;
	}
	
	public static void main(String[] args) {
		SqlCodeGenerator generator = new SqlCodeGenerator();
		Class<?> modelClass = GenericPO.class;
//		LinkedHashMap<String, Class<?>> list1 = generator.getPropsName(modelClass);
//		System.out.println(list1);
//		LinkedHashMap<String, Class<?>> list2 = generator.sortPropsName(modelClass);
//		System.out.println(list2);
		
		String result1 = generator.makeTableColumns(modelClass, "t");
		System.out.println(">>>>>>>>>>>>>>>>>>>start::tableColumns");
		System.out.println(result1);
		System.out.println(">>>>>>>>>>>>>>>>>>>end::tableColumns");
		System.out.println();
		
		String result2 = generator.makeConditionsWhere(modelClass, "t");
		System.out.println(">>>>>>>>>>>>>>>>>>>start::makeConditionsWhere");
		System.out.println(result2);
		System.out.println(">>>>>>>>>>>>>>>>>>>end::makeConditionsWhere");
		System.out.println();
		
		String result3 = generator.makeResultMap(modelClass);
		System.out.println(">>>>>>>>>>>>>>>>>>>start::makeResultMap");
		System.out.println(result3);
		System.out.println(">>>>>>>>>>>>>>>>>>>end::makeResultMap");
		System.out.println();
		
		String result4 = generator.makeInsertOne(modelClass);
		System.out.println(">>>>>>>>>>>>>>>>>>>start::makeInsertOne");
		System.out.println(result4);
		System.out.println(">>>>>>>>>>>>>>>>>>>end::makeInsertOne");
		System.out.println();
		
		String result5 = generator.makeUpdateBy(modelClass);
		System.out.println(">>>>>>>>>>>>>>>>>>>start::makeUpdateBy");
		System.out.println(result5);
		System.out.println(">>>>>>>>>>>>>>>>>>>end::makeUpdateBy");
		System.out.println();
		
		String result6 = generator.makeDeleteBy(modelClass);
		System.out.println(">>>>>>>>>>>>>>>>>>>start::makeDeleteBy");
		System.out.println(result6);
		System.out.println(">>>>>>>>>>>>>>>>>>>end::makeDeleteBy");
		System.out.println();
		
		String result7 = generator.makeInsertBatch(modelClass);
		System.out.println(">>>>>>>>>>>>>>>>>>>start::makeInsertBatch");
		System.out.println(result7);
		System.out.println(">>>>>>>>>>>>>>>>>>>end::makeInsertBatch");
		System.out.println();
		
		String result8 = generator.makeUpdateBatch(modelClass);
		System.out.println(">>>>>>>>>>>>>>>>>>>start::makeUpdateBatch");
		System.out.println(result8);
		System.out.println(">>>>>>>>>>>>>>>>>>>end::makeUpdateBatch");
		System.out.println();
		
	}
}

