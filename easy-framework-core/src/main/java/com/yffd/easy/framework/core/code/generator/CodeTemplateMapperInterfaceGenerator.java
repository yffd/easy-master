package com.yffd.easy.framework.core.code.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import com.yffd.easy.framework.core.common.pojo.entity.CommonTreeEntity;

/**
 * @Description  XxxService代码生成器.
 * @Date		 2018年2月6日 下午1:55:18 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class CodeTemplateMapperInterfaceGenerator extends CodeGenerator {
	private static final String FILE_SUFFIX = ".java";
	private static final String TEMPLATE_PATH = "com\\yffd\\easy\\framework\\core\\code\\template\\XxxMapperInterface.template";
	
	public void writeAllToFile(String modelRootDirPath, String modelPackageName, String mapperPackageName, String author, String outRootDirPath, boolean covered) throws Exception {
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
						String modelFullClassName = modelPackageName + "." + javaFileName.substring(0, javaFileName.lastIndexOf(FILE_SUFFIX));
						Class<?> modelClazz = Class.forName(modelFullClassName);
						this.writeToFile(modelClazz, mapperPackageName, author, outRootDirPath, covered);
					}
				}
			}
		}
	}
	
	public void writeToFile(Class<?> modelClazz, String mapperPackageName, String author, String outRootDirPath, boolean covered) {
		String content = this.makeContent(modelClazz, mapperPackageName, author);
		
		if(!outRootDirPath.endsWith(File.separator)) outRootDirPath += File.separator;
		String packageDirPath = mapperPackageName.replace(".", File.separator);
		String fullDirPath = outRootDirPath + packageDirPath;
		this.makedirs(fullDirPath);
		
		String fileName = fullDirPath + File.separator + this.fmtMapperSimpleName(modelClazz) + FILE_SUFFIX;
		System.out.println("fileName:" + fileName);
		this.writeToFile(content, fileName, covered);
	}
	
	public void writeToConsole(Class<?> modelClazz, String mapperPackageName, String author) {
		String content = this.makeContent(modelClazz, mapperPackageName, author);
		System.out.println(content);
	}
	
	protected String makeContent(Class<?> modelClazz, String mapperPackageName, String author) {
		StringBuilder sb = new StringBuilder();
		try {
			InputStream inStream = this.getClass().getClassLoader().getResourceAsStream(TEMPLATE_PATH);
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
			String line = null;
			while((line=reader.readLine())!=null) {
				sb.append(line).append("\r\n");
			}
			reader.close();
			inStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String content = sb.toString();
		
		content = content.replace("##PACKAGE_NAME_MAPPER_INTERFACE##", mapperPackageName);
		content = content.replace("##IMPORT_CLASS_FULL_NAME_MODEL##", modelClazz.getName());
		content = content.replace("##date##", this.fmtDate(new Date()));
		content = content.replace("##author##", author);
		content = content.replace("##SIMPLE_CLASS_NAME_MAPPER_INTERFACE##", this.fmtMapperSimpleName(modelClazz));
		
		content = content.replace("##SIMPLE_CLASS_NAME_MODEL##", modelClazz.getSimpleName());
		
		return content;
	}
	
	public static void main(String[] args) {
		CodeTemplateMapperInterfaceGenerator generator = new CodeTemplateMapperInterfaceGenerator();
		String author = "ZhangST";
		Class<?> modelClazz = CommonTreeEntity.class;
		String mapperPackageName = "com.yffd.easy.xxx.mapper";
		generator.writeToConsole(modelClazz, mapperPackageName, author);
		
		String outRootDirPath = "D:\\ddd\\code\\root";
		boolean covered = true;
		
		generator.writeToFile(modelClazz, mapperPackageName, author, outRootDirPath, covered);
	}
}

