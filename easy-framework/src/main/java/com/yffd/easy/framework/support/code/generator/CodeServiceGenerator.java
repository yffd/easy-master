package com.yffd.easy.framework.support.code.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import com.yffd.easy.framework.domain.CustomPo;

/**
 * @Description  XxxService代码生成器.
 * @Date		 2018年2月6日 下午1:55:18 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class CodeServiceGenerator extends CodeGenerator {
	private static final String FILE_SUFFIX = ".java";
	private static final String TEMPLATE_PATH = "com\\yffd\\easy\\framework\\support\\code\\template\\XxxService.template";
	
	public void writeAllToFile(String modelRootDirPath, String modelPackageName, Class<?> superServiceClazz, String servicePackageName, String mapperPackageName, String author, String outRootDirPath, boolean covered) throws Exception {
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
						this.writeToFile(modelClazz, superServiceClazz, servicePackageName, mapperPackageName, author, outRootDirPath, covered);
					}
				}
			}
		}
	}
	
	public void writeToFile(Class<?> modelClazz, Class<?> superServiceClazz, String servicePackageName, String mapperPackageName, String author, String outRootDirPath, boolean covered) {
		String content = this.makeContent(modelClazz, superServiceClazz, servicePackageName, mapperPackageName, author);
		
		if(!outRootDirPath.endsWith(File.separator)) outRootDirPath += File.separator;
		String packageDirPath = servicePackageName.replace(".", File.separator);
		String fullDirPath = outRootDirPath + packageDirPath;
		this.makedirs(fullDirPath);
		
		String fileName = fullDirPath + File.separator + this.fmtServiceSimpleName(modelClazz) + FILE_SUFFIX;
		this.writeToFile(content, fileName, covered);
	}
	
	public void writeToConsole(Class<?> modelClazz, Class<?> superServiceClazz, String servicePackageName, String mapperPackageName, String author) {
		String content = this.makeContent(modelClazz, superServiceClazz, servicePackageName, mapperPackageName, author);
		System.out.println(content);
	}
	
	protected String makeContent(Class<?> modelClazz, Class<?> superServiceClazz, String servicePackageName, String mapperPackageName, String author) {
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
		
		content = content.replace("##service_package_name##", servicePackageName);
		content = content.replace("##import_model_full_name##", modelClazz.getName());
		content = content.replace("##import_mapper_full_name##", this.fmtMapperFullClassName(modelClazz, mapperPackageName));
		content = content.replace("##date##", this.fmtDate(new Date()));
		content = content.replace("##author##", author);
		content = content.replace("##service_name##", this.fmtServiceSimpleName(modelClazz));
		
		if(null!=superServiceClazz) {
			content = content.replace("##import_super_service_full_name##", superServiceClazz.getName());
			content = content.replace("##super_service_name##", "extends " + superServiceClazz.getSimpleName() +"<"+modelClazz.getSimpleName()+"> ");
		} else {
			content = content.replace("##import_super_service_full_name##", "");
			content = content.replace("##super_service_name##", "");
		}
		
		String mapperSimpleName = this.fmtMapperSimpleName(modelClazz);
		String mapperAliasName = mapperSimpleName.substring(1, 2).toLowerCase() + mapperSimpleName.substring(2);
		
		content = content.replace("##mapper_name##", mapperSimpleName);
		content = content.replace("##mapper_alias_name##", mapperAliasName);
		
		content = content.replace("##model_simple_name##", modelClazz.getSimpleName());
		
		return content;
	}
	
	public static void main(String[] args) {
		CodeServiceGenerator generator = new CodeServiceGenerator();
		String author = "ZhangST";
		Class<?> modelClazz = CustomPo.class;
		Class<?> superServiceClazz = null;
		String servicePackageName = "com.yffd.easy.xxx.service";
		String mapperPackageName = "com.yffd.easy.xxx.mapper";
		generator.writeToConsole(modelClazz, superServiceClazz, servicePackageName, mapperPackageName, author);
		
		String outRootDirPath = "D:\\ddd\\code\\service";
		boolean covered = true;
		
		generator.writeToFile(modelClazz, superServiceClazz, servicePackageName, mapperPackageName, author, outRootDirPath, covered);
	}
}

