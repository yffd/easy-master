package com.yffd.easy.framework.support.code.generator;

import java.io.File;

import com.yffd.easy.framework.domain.GenericPO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年2月6日 下午3:16:17 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class MapperFileCodeGenerator extends CodeGenerator {
	
	private static final String FILE_SUFFIX = ".xml";
	private MapperCodeGenerator mapperGenerator = new MapperCodeGenerator();
	
	public void writeAllToFile(String modelRootDirPath, String modelPackageName, String daoPackageName, String outRootDirPath, boolean covered) throws Exception {
		if(!modelRootDirPath.endsWith(File.separator)) modelRootDirPath += File.separator;
		String modelPackageDirPath = modelPackageName.replace(".", File.separator);
		String fullDirPath = modelRootDirPath + modelPackageDirPath;
		
		File file = new File(fullDirPath);
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			for(File childFile : files) {
				if(childFile.isDirectory()) {
					continue;
				} else {
					boolean skip = false;
					String javaFileName = childFile.getName();	// 不包含路径
					for(String skipName : this.getSkipModelNamesLike()) {
						if(javaFileName.contains(skipName)) {
							skip = true;
							continue;
						}
					}
					if(!skip) {
						int endIndex = javaFileName.lastIndexOf(".java");
						if(endIndex!=-1) {
							javaFileName = javaFileName.substring(0, endIndex);
						}
						String modelFullClassName = modelPackageName + "." + javaFileName;
						Class<?> modelClazz = Class.forName(modelFullClassName);
						this.writeToFile(modelClazz, daoPackageName, outRootDirPath, covered);
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @Date	2018年2月6日 下午3:22:15 <br/>
	 * @author  zhangST
	 * @param modelClazz
	 * @param daoPackageName
	 * @param outRootDirPath
	 * @param covered			是否覆盖旧文件
	 */
	public void writeToFile(Class<?> modelClazz, String daoPackageName, String outRootDirPath, boolean covered) {
		String tableAliasName = "t";
		String namespace = daoPackageName + "." + this.fmtDaoSimpleName(modelClazz);
		String content = this.makeContent(modelClazz, tableAliasName, namespace);
		
		String fileName = outRootDirPath + File.separator + this.fmtMapperName(modelClazz) + FILE_SUFFIX;
		this.writeToFile(content, fileName, covered);
		
	}
	
	public void writeToConsole(Class<?> modelClazz, String daoPackageName) {
		String tableAliasName = "t";
		String namespace = daoPackageName + "." + this.fmtDaoSimpleName(modelClazz);
		String content = this.makeContent(modelClazz, tableAliasName, namespace);
		System.out.println(content);
		
	}
	
	protected String makeContent(Class<?> modelClazz, String tableAliasName, String namespace) {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append("\r\n");
		sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">").append("\r\n");
		sb.append("<mapper namespace=\"" + namespace + "\">").append("\r\n");
		sb.append("\t").append("<!-- ######################################################################### -->").append("\r\n");
		sb.append("\t").append("<!-- ##############################   基础sql开始    ############################## -->").append("\r\n");
		sb.append("\t").append("<!-- ######################################################################### -->").append("\r\n");
		sb.append("\t").append("\r\n");
		
		String resultMapStr = this.mapperGenerator.resultMap(modelClazz);
		sb.append(this.mapperGenerator.strFmt(resultMapStr, "\t")).append("\r\n");
		
		String tableNameStr = this.mapperGenerator.tableName(modelClazz);
		sb.append(this.mapperGenerator.strFmt(tableNameStr, "\t")).append("\r\n");
		
		String tableColumnsStr = this.mapperGenerator.tableColumns(modelClazz, tableAliasName);
		sb.append(this.mapperGenerator.strFmt(tableColumnsStr, "\t")).append("\r\n");
		
		String conditionsLimitStr = this.mapperGenerator.conditionsLimit();
		sb.append(this.mapperGenerator.strFmt(conditionsLimitStr, "\t")).append("\r\n");
		
		String conditionsOrderbyStr = this.mapperGenerator.conditionsOrderby(tableAliasName);
		sb.append(this.mapperGenerator.strFmt(conditionsOrderbyStr, "\t")).append("\r\n");
		
		String conditionsWhereStr = this.mapperGenerator.conditionsWhere(modelClazz, tableAliasName);
		sb.append(this.mapperGenerator.strFmt(conditionsWhereStr, "\t")).append("\r\n");
		
		String selectListByStr = this.mapperGenerator.selectListBy(tableAliasName);
		sb.append(this.mapperGenerator.strFmt(selectListByStr, "\t")).append("\r\n");
		
		String selectCountByStr = this.mapperGenerator.selectCountBy(tableAliasName);
		sb.append(this.mapperGenerator.strFmt(selectCountByStr, "\t")).append("\r\n");
		
		String selectListByIdsStr = this.mapperGenerator.selectListByIds(tableAliasName);
		sb.append(this.mapperGenerator.strFmt(selectListByIdsStr, "\t")).append("\r\n");
		
		String selectOneByStr = this.mapperGenerator.selectOneBy(tableAliasName);
		sb.append(this.mapperGenerator.strFmt(selectOneByStr, "\t")).append("\r\n");
		
		String insertOneStr = this.mapperGenerator.insertOne(modelClazz);
		sb.append(this.mapperGenerator.strFmt(insertOneStr, "\t")).append("\r\n");
		
		String insertBatchStr = this.mapperGenerator.insertBatch(modelClazz);
		sb.append(this.mapperGenerator.strFmt(insertBatchStr, "\t")).append("\r\n");
		
		String updateByStr = this.mapperGenerator.updateBy(modelClazz);
		sb.append(this.mapperGenerator.strFmt(updateByStr, "\t")).append("\r\n");
		
		String updateBatchStr = this.mapperGenerator.updateBatch(modelClazz);
		sb.append(this.mapperGenerator.strFmt(updateBatchStr, "\t")).append("\r\n");
		
		String deleteByPKStr = this.mapperGenerator.deleteByPK();
		sb.append(this.mapperGenerator.strFmt(deleteByPKStr, "\t")).append("\r\n");
		
		String deleteByStr = this.mapperGenerator.deleteBy(modelClazz);
		sb.append(this.mapperGenerator.strFmt(deleteByStr, "\t")).append("\r\n");
		
		String deleteBatchStr = this.mapperGenerator.deleteBatch();
		sb.append(this.mapperGenerator.strFmt(deleteBatchStr, "\t")).append("\r\n");
		
		sb.append("\t").append("<!-- ######################################################################### -->").append("\r\n");
		sb.append("\t").append("<!-- ##############################   基础sql结束    ############################## -->").append("\r\n");
		sb.append("\t").append("<!-- ######################################################################### -->").append("\r\n");
		sb.append("\r\n");
		sb.append("</mapper>");
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		MapperFileCodeGenerator mapperFileGenerator = new MapperFileCodeGenerator();
		Class<?> modelClazz = GenericPO.class;
		String daoPackageName = "com.yffd.easy.uupm.dao.EasyPersistDao";
		String outRootDirPath = "D:\\ddd\\code";
		
//		mapperFileGenerator.writeToConsole(modelClazz, daoPackageName);
		mapperFileGenerator.writeToFile(modelClazz, daoPackageName, outRootDirPath, true);
		System.out.println("已生成XML文件");
	}
}

