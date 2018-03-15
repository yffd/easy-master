package com.yffd.easy.framework.support.code.generator;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import com.yffd.easy.framework.domain.GenericPO;

/**
 * @Description  Mapper代码生成器.
 * @Date		 2018年2月6日 下午3:07:50 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class MapperCodeGenerator extends CodeGenerator {
	
	private SqlCodeGenerator sqlGenerator = new SqlCodeGenerator();
	
	/**
	 * 生成表的列名称
	 * @Date	2018年2月1日 下午4:12:02 <br/>
	 * @author  zhangST
	 * @param modelClazz
	 * @param tableAliasName
	 * @return
	 */
	public String tableColumns(Class<?> modelClazz, String tableAliasName) {
		String tmp = this.sqlGenerator.makeTableColumns(modelClazz, tableAliasName);
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- 列名 -->").append("\r\n");
		sb.append("<sql id=\"table_columns\">").append("\r\n");
		sb.append(this.strFmt(tmp, "\t"));
		sb.append("</sql>");
		return sb.toString();
	}
	
	/**
	 * 生成查询条件，如果名称包含“name”或“Name”，则为like查询，否则为equal查询
	 * @Date	2018年2月1日 下午4:32:38 <br/>
	 * @author  zhangST
	 * @param modelClazz
	 * @param tableAliasName
	 * @return
	 */
	public String conditionsWhere(Class<?> modelClazz, String tableAliasName) {
		String tmp = this.sqlGenerator.makeConditionsWhere(modelClazz, tableAliasName);
		String makeForeach = this.sqlGenerator.makeForeach(modelClazz, null, null, "");
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- 动态组装复合查询条件 -->").append("\r\n");
		sb.append("<sql id=\"conditions_where\">").append("\r\n");
		sb.append(this.strFmt(tmp, "\t"));
		
		sb.append("\r\n");
		sb.append(this.strFmt("<!-- 非model属性：in条件处理拼写，要求List集合中的值能转换成key-value形式，例如map、自定义model对象等 -->", "\t"));
		sb.append(this.strFmt(makeForeach, "\t")).append("\r\n");
		
		sb.append("</sql>");
		return sb.toString();
	}
	
	/**
	 * 生成resultMap
	 * @Date	2018年2月1日 下午5:06:17 <br/>
	 * @author  zhangST
	 * @param modelClazz
	 * @return
	 */
	public String resultMap(Class<?> modelClazz) {
		String className = modelClazz.getName();
		String tmp = this.sqlGenerator.makeResultMap(modelClazz);
		StringBuilder sb = new StringBuilder();
		sb.append("<resultMap id=\"resutlId\" type=\""+className+"\">").append("\r\n");
		sb.append(this.strFmt(tmp, "\t"));
		sb.append("</resultMap>");
		return sb.toString();
	}
	
	/**
	 * 生成单条插入
	 * @Date	2018年2月1日 下午5:13:04 <br/>
	 * @author  zhangST
	 * @param modelClazz
	 * @return
	 */
	public String insertOne(Class<?> modelClazz) {
		String tmp = this.sqlGenerator.makeInsertOne(modelClazz);
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- 单条插入 -->").append("\r\n");
		sb.append("<insert id=\"insertOne\" parameterType=\"java.util.Map\" keyProperty=\"id\" useGeneratedKeys=\"true\">").append("\r\n");
		sb.append("\t").append("insert into").append("\r\n");
		sb.append("\t").append("<include refid=\"table_name\" />").append("\r\n");
		sb.append(this.strFmt(tmp, "\t"));
		sb.append("</insert>");
		return sb.toString();
	}
	
	/**
	 * 生成批量插入
	 * @Date	2018年2月1日 下午5:36:37 <br/>
	 * @author  zhangST
	 * @param modelClazz
	 * @return
	 */
	public String insertBatch(Class<?> modelClazz) {
		String tmp = this.sqlGenerator.makeInsertBatch(modelClazz);
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- 批量插入 -->").append("\r\n");
		sb.append("<insert id=\"insertBatch\" parameterType=\"java.util.Map\" keyProperty=\"id\" useGeneratedKeys=\"true\">").append("\r\n");
		sb.append("\t").append("insert into <include refid=\"table_name\" />").append("\r\n");
		sb.append(this.strFmt(tmp, "\t"));
		sb.append("</insert>");
		return sb.toString();
	}
	
	/**
	 * 生成修改
	 * @Date	2018年2月1日 下午5:18:45 <br/>
	 * @author  zhangST
	 * @param modelClazz
	 * @return
	 */
	public String updateBy(Class<?> modelClazz) {
		return this.sqlGenerator.makeUpdateBy(modelClazz);
	}
	
	/**
	 * 生成单条修改
	 * @Date	2018年2月1日 下午5:18:45 <br/>
	 * @author  zhangST
	 * @param modelClazz
	 * @return
	 */
	public String updateBy123(Class<?> modelClazz) {
		String tmp = this.sqlGenerator.makeUpdateBy123(modelClazz);
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- 更新 -->").append("\r\n");
		sb.append("<update id=\"updateBy\" parameterType=\"java.util.Map\">").append("\r\n");
		sb.append("\t").append("update <include refid=\"table_name\" />").append("\r\n");
		sb.append(this.strFmt(tmp, "\t"));
		sb.append("\t").append("<where>").append("\r\n");
		sb.append("\t\t").append("ID = #{id}").append("\r\n");
		sb.append("\t\t").append("<if test=\"version != null \"> and VERSION = #{version} </if>").append("\r\n");
		sb.append("\t").append("</where>").append("\r\n");
		sb.append("</update>");
		return sb.toString();
	}
	
	/**
	 * 生成批量修改
	 * @Date	2018年2月1日 下午5:38:44 <br/>
	 * @author  zhangST
	 * @param modelClazz
	 * @return
	 */
	public String updateBatch(Class<?> modelClazz) {
		String tmp = this.sqlGenerator.makeUpdateBatch(modelClazz);
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- 批量更新 -->").append("\r\n");
		sb.append("<update id=\"updateBatch\" parameterType=\"java.util.List\">").append("\r\n");
		sb.append(this.strFmt(tmp, "\t"));
		sb.append("</update>");
		return sb.toString();
	}
	
	/**
	 * 生成删除
	 * @Date	2018年2月1日 下午5:28:40 <br/>
	 * @author  zhangST
	 * @param modelClazz
	 * @return
	 */
	public String deleteBy(Class<?> modelClazz) {
		String tmp = this.sqlGenerator.makeDeleteBy(modelClazz);
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- 删除 -->").append("\r\n");
		sb.append("<delete id=\"deleteBy\" parameterType=\"java.util.Map\">").append("\r\n");
		sb.append("\t").append("delete from <include refid=\"table_name\" />").append("\r\n");
		sb.append(this.strFmt(tmp, "\t"));
		sb.append("</delete>");
		return sb.toString();
	}
	
	public String tableName(Class<?> modelClazz) {
		String name = modelClazz.getSimpleName();
		name = this.fmtModelName(modelClazz, null, null);
		name = this.name2column(name);
		name = name.toLowerCase();
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- 表名 -->").append("\r\n");
		sb.append("<sql id=\"table_name\"> "+name+" </sql>");
		return sb.toString();
	}
	
	public String conditionsLimit() {
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- 分页条件 -->").append("\r\n");
		sb.append("<sql id=\"conditions_limit\"><if test=\"pageParam != null\"> limit #{pageParam.pageStartRow}, #{pageParam.pageLimit} </if></sql>");
		return sb.toString();
	}
	
	public String conditionsOrderby(String tableAliasName) {
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- 排序条件 -->").append("\r\n");
		if(null!=tableAliasName) {
			sb.append("<sql id=\"conditions_orderby\"> ORDER BY ").append(tableAliasName).append(".CREATE_TIME desc </sql>");
		} else {
			sb.append("<sql id=\"conditions_orderby\"> ORDER BY CREATE_TIME desc </sql>");
		}
		return sb.toString();
	}
	
	public String selectRangeBy(String tableAliasName) {
		String asTableAliasName = "";
		if(null!=tableAliasName) asTableAliasName = " as " + tableAliasName + " ";
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- 分页查询 -->").append("\r\n");
		sb.append("<select id=\"selectRangeBy\" parameterType=\"java.util.Map\" resultMap=\"resutlId\">").append("\r\n");
		sb.append("\t").append("select <include refid=\"table_columns\" />").append("\r\n");
		sb.append("\t").append("from <include refid=\"table_name\"/> ").append(asTableAliasName).append("\r\n");
		sb.append("\t").append("<where>").append("\r\n");
		sb.append("\t").append("\t").append("<include refid=\"conditions_where\" />").append("\r\n");
		sb.append("\t").append("</where>").append("\r\n");
		sb.append("\t").append("<include refid=\"conditions_orderby\" />").append("\r\n");
		sb.append("\t").append("<include refid=\"conditions_limit\" />").append("\r\n");
		sb.append("</select>");
		return sb.toString();
	}
	
	public String selectListBy(String tableAliasName) {
		String asTableAliasName = "";
		if(null!=tableAliasName) asTableAliasName = " as " + tableAliasName + " ";
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- 条件查询 -->").append("\r\n");
		sb.append("<select id=\"selectListBy\" parameterType=\"java.util.Map\" resultMap=\"resutlId\">").append("\r\n");
		sb.append("\t").append("select <include refid=\"table_columns\" />").append("\r\n");
		sb.append("\t").append("from <include refid=\"table_name\"/> ").append(asTableAliasName).append("\r\n");
		sb.append("\t").append("<where>").append("\r\n");
		sb.append("\t").append("\t").append("<include refid=\"conditions_where\" />").append("\r\n");
		sb.append("\t").append("</where>").append("\r\n");
		sb.append("\t").append("<include refid=\"conditions_orderby\" />").append("\r\n");
		sb.append("</select>");
		return sb.toString();
	}
	
	public String selectCountBy(String tableAliasName) {
		String asTableAliasName = "";
		if(null!=tableAliasName) asTableAliasName = " as " + tableAliasName + " ";
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- 统计查询 -->").append("\r\n");
		sb.append("<select id=\"selectCountBy\" parameterType=\"java.util.Map\" resultType=\"long\">").append("\r\n");
		sb.append("\t").append("select count(1) from ").append("\r\n");
		sb.append("\t").append("<include refid=\"table_name\"/> ").append(asTableAliasName).append("\r\n");
		sb.append("\t").append("<where>").append("\r\n");
		sb.append("\t").append("\t").append("<include refid=\"conditions_where\" />").append("\r\n");
		sb.append("\t").append("</where>").append("\r\n");
		sb.append("</select>");
		return sb.toString();
	}
	
	public String selectListByIds(String tableAliasName) {
		String asTableAliasName = "";
		if(null!=tableAliasName) asTableAliasName = " as " + tableAliasName + " ";
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- in条件查询 -->").append("\r\n");
		sb.append("<select id=\"selectListByIds\" parameterType=\"java.util.List\" resultMap=\"resutlId\">").append("\r\n");
		sb.append("\t").append("select <include refid=\"table_columns\" /> ").append("\r\n");
		sb.append("\t").append("from <include refid=\"table_name\"/> ").append(asTableAliasName).append("\r\n");
		sb.append("\t").append("<where>").append("\r\n");
		if(null!=tableAliasName) {
			sb.append("\t").append("\t").append("t.ID in ").append("\r\n");
			sb.append("\t").append("\t").append("<foreach item=\"item\" index=\"index\" collection=\"list\" open=\"(\" separator=\",\" close=\")\">#{item}</foreach>").append("\r\n");
		} else {
			sb.append("\t").append("\t").append("ID in ").append("\r\n");
			sb.append("\t").append("\t").append("<foreach item=\"item\" index=\"index\" collection=\"list\" open=\"(\" separator=\",\" close=\")\">#{item}</foreach>").append("\r\n");
		}
		sb.append("\t").append("</where>").append("\r\n");
		sb.append("</select>");
		return sb.toString();
	}
	
	public String selectOneBy(String tableAliasName) {
		String asTableAliasName = "";
		if(null!=tableAliasName) asTableAliasName = " as " + tableAliasName + " ";
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- 单条查询 -->").append("\r\n");
		sb.append("<select id=\"selectOneBy\" parameterType=\"java.util.Map\" resultMap=\"resutlId\">").append("\r\n");
		sb.append("\t").append("select <include refid=\"table_columns\" /> ").append("\r\n");
		sb.append("\t").append("from <include refid=\"table_name\"/> ").append(asTableAliasName).append("\r\n");
		sb.append("\t").append("<where>").append("\r\n");
		sb.append("\t").append("\t").append("<include refid=\"conditions_where\" />").append("\r\n");
		sb.append("\t").append("</where>").append("\r\n");
		sb.append("</select>");
		return sb.toString();
	}
	
	public String deleteById() {
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- 主键删除 -->").append("\r\n");
		sb.append("<delete id=\"deleteById\" parameterType=\"java.lang.String\">").append("\r\n");
		sb.append("\t").append("delete from <include refid=\"table_name\" /> where ID = #{_parameter}").append("\r\n");
		sb.append("</delete>");
		return sb.toString();
	}
	
	public String deleteByIds() {
		StringBuilder sb = new StringBuilder();
		sb.append("<!-- 主键批量删除 -->").append("\r\n");
		sb.append("<delete id=\"deleteByIds\" parameterType=\"java.util.List\">").append("\r\n");
		sb.append("\t").append("delete from <include refid=\"table_name\" /> ").append("\r\n");
		sb.append("\t").append("where ID in ").append("\r\n");
		sb.append("\t").append("<foreach item=\"item\" index=\"index\" collection=\"list\" open=\"(\" separator=\",\" close=\")\">#{item}</foreach>").append("\r\n");
		sb.append("</delete>");
		return sb.toString();
	}
	
	public String strFmt(String str, String prefix) {
		if(null==str || "".equals(str)) return null; 
		try {
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(str.getBytes("utf8"))));
			String line;
			while((line = reader.readLine()) !=null) {
				sb.append(prefix).append(line).append("\r\n");
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		MapperCodeGenerator generator = new MapperCodeGenerator();
		String tableAliasName = "t";
		Class<?> modelClass = GenericPO.class;
		
		String tableColumns = generator.tableColumns(modelClass, tableAliasName);
		System.out.println(">>>>>>>>>>>>>>>>>>>start::tableColumns");
		System.out.println(tableColumns);
		System.out.println(">>>>>>>>>>>>>>>>>>>end::tableColumns");
		System.out.println();
		
		String conditionsWhere = generator.conditionsWhere(modelClass, tableAliasName);
		System.out.println(">>>>>>>>>>>>>>>>>>>start::conditionsWhere");
		System.out.println(conditionsWhere);
		System.out.println(">>>>>>>>>>>>>>>>>>>end::conditionsWhere");
		System.out.println();
		
		String resultMap = generator.resultMap(modelClass);
		System.out.println(">>>>>>>>>>>>>>>>>>>start::resultMap");
		System.out.println(resultMap);
		System.out.println(">>>>>>>>>>>>>>>>>>>end::resultMap");
		System.out.println();
		
		String insertOne = generator.insertOne(modelClass);
		System.out.println(">>>>>>>>>>>>>>>>>>>start::insertOne");
		System.out.println(insertOne);
		System.out.println(">>>>>>>>>>>>>>>>>>>end::insertOne");
		System.out.println();
		
		String insertBatch = generator.insertBatch(modelClass);
		System.out.println(">>>>>>>>>>>>>>>>>>>start::insertBatch");
		System.out.println(insertBatch);
		System.out.println(">>>>>>>>>>>>>>>>>>>end::insertBatch");
		System.out.println();
		
		String updateBy = generator.updateBy(modelClass);
		System.out.println(">>>>>>>>>>>>>>>>>>>start::updateBy");
		System.out.println(updateBy);
		System.out.println(">>>>>>>>>>>>>>>>>>>end::updateBy");
		System.out.println();
		
		String updateBy123 = generator.updateBy123(modelClass);
		System.out.println(">>>>>>>>>>>>>>>>>>>start::updateBy123");
		System.out.println(updateBy123);
		System.out.println(">>>>>>>>>>>>>>>>>>>end::updateBy123");
		System.out.println();
		
		String updateBatch = generator.updateBatch(modelClass);
		System.out.println(">>>>>>>>>>>>>>>>>>>start::updateBatch");
		System.out.println(updateBatch);
		System.out.println(">>>>>>>>>>>>>>>>>>>end::updateBatch");
		System.out.println();
		
		String deleteBy = generator.deleteBy(modelClass);
		System.out.println(">>>>>>>>>>>>>>>>>>>start::deleteBy");
		System.out.println(deleteBy);
		System.out.println(">>>>>>>>>>>>>>>>>>>end::deleteBy");
		System.out.println();
		
		String tableName = generator.tableName(modelClass);
		System.out.println(">>>>>>>>>>>>>>>>>>>start::tableName");
		System.out.println(tableName);
		System.out.println(">>>>>>>>>>>>>>>>>>>end::tableName");
		System.out.println();
		
		String conditionsLimit = generator.conditionsLimit();
		System.out.println(">>>>>>>>>>>>>>>>>>>start::conditionsLimit");
		System.out.println(conditionsLimit);
		System.out.println(">>>>>>>>>>>>>>>>>>>end::conditionsLimit");
		System.out.println();
		
		String conditionsOrderby = generator.conditionsOrderby(tableAliasName);
		System.out.println(">>>>>>>>>>>>>>>>>>>start::conditionsOrderby");
		System.out.println(conditionsOrderby);
		System.out.println(">>>>>>>>>>>>>>>>>>>end::conditionsOrderby");
		System.out.println();
		
		String selectListBy = generator.selectListBy(tableAliasName);
		System.out.println(">>>>>>>>>>>>>>>>>>>start::selectListBy");
		System.out.println(selectListBy);
		System.out.println(">>>>>>>>>>>>>>>>>>>end::selectListBy");
		System.out.println();
		
		String selectCountBy = generator.selectCountBy(tableAliasName);
		System.out.println(">>>>>>>>>>>>>>>>>>>start::selectCountBy");
		System.out.println(selectCountBy);
		System.out.println(">>>>>>>>>>>>>>>>>>>end::selectCountBy");
		System.out.println();
		
		String selectListByIds = generator.selectListByIds(tableAliasName);
		System.out.println(">>>>>>>>>>>>>>>>>>>start::selectListByIn");
		System.out.println(selectListByIds);
		System.out.println(">>>>>>>>>>>>>>>>>>>end::selectListByIn");
		System.out.println();
		
		String selectOneBy = generator.selectOneBy(tableAliasName);
		System.out.println(">>>>>>>>>>>>>>>>>>>start::selectOneBy");
		System.out.println(selectOneBy);
		System.out.println(">>>>>>>>>>>>>>>>>>>end::selectOneBy");
		System.out.println();
		
		String deleteById = generator.deleteById();
		System.out.println(">>>>>>>>>>>>>>>>>>>start::deleteById");
		System.out.println(deleteById);
		System.out.println(">>>>>>>>>>>>>>>>>>>end::deleteById");
		System.out.println();
		
		String deleteByIds = generator.deleteByIds();
		System.out.println(">>>>>>>>>>>>>>>>>>>start::deleteByIds");
		System.out.println(deleteByIds);
		System.out.println(">>>>>>>>>>>>>>>>>>>end::deleteByIds");
		System.out.println();
		
	}
}

