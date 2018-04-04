package com.yffd.easy.framework.support.code.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import com.yffd.easy.framework.domain.CustomPo;

/**
 * @Description  XxxServiceTest代码生成器.
 * @Date		 2018年2月6日 下午1:55:18 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class CodeServiceTestGenerator extends CodeGenerator {
	private static final String FILE_SUFFIX = ".java";
	private static final String TEMPLATE_PATH = "com\\yffd\\easy\\framework\\support\\code\\template\\XxxServiceTest.template";
	
	public void writeAllToFile(String modelRootDirPath, String modelPackageName, Class<?> superServiceTestClazz, String serviceTestPackageName, String author, String outRootDirPath, boolean covered) throws Exception {
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
						this.writeToFile(modelClazz, superServiceTestClazz, serviceTestPackageName, author, outRootDirPath, covered);
					}
				}
			}
		}
	}
	
	public void writeToFile(Class<?> modelClazz, Class<?> superServiceTestClazz, String serviceTestPackageName, String author, String outRootDirPath, boolean covered) {
		String content = this.makeContent(modelClazz, superServiceTestClazz, serviceTestPackageName, author);
		
		if(!outRootDirPath.endsWith(File.separator)) outRootDirPath += File.separator;
		String packageDirPath = serviceTestPackageName.replace(".", File.separator);
		String fullDirPath = outRootDirPath + packageDirPath;
		this.makedirs(fullDirPath);
		
		String fileName = fullDirPath + File.separator + this.fmtServiceTestSimpleName(modelClazz) + FILE_SUFFIX;
		this.writeToFile(content, fileName, covered);
	}
	
	public void writeToConsole(Class<?> modelClazz, Class<?> superServiceTestClazz, String serviceTestPackageName, String author) {
		String content = this.makeContent(modelClazz, superServiceTestClazz, serviceTestPackageName, author);
		System.out.println(content);
	}
	
	protected String makeContent(Class<?> modelClazz, Class<?> superServiceTestClazz, String serviceTestPackageName, String author) {
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
		
		content = content.replace("##service_package_name##", serviceTestPackageName);
		content = content.replace("##import_model_full_name##", modelClazz.getName());
		content = content.replace("##date##", this.fmtDate(new Date()));
		content = content.replace("##author##", author);
		content = content.replace("##test_service_name##", this.fmtServiceTestSimpleName(modelClazz));
		
		if(null!=superServiceTestClazz) {
			content = content.replace("##import_test_super_service_full_name##", superServiceTestClazz.getName());
			content = content.replace("##test_base_service_name##", "extends " + superServiceTestClazz.getSimpleName() + " ");
		} else {
			content = content.replace("##import_test_super_service_full_name##", "");
			content = content.replace("##test_base_service_name##", "");
		}
		
		String serviceSimpleName = this.fmtServiceSimpleName(modelClazz);
		String serviceAliasName = serviceSimpleName.substring(0, 1).toLowerCase() + serviceSimpleName.substring(1);
		
		content = content.replace("##service_name##", serviceSimpleName);
		content = content.replace("##service_alias_name##", serviceAliasName);
		
		content = content.replace("##model_simple_name##", modelClazz.getSimpleName());
		
		return content;
	}
	
	public static void main(String[] args) {
		CodeServiceTestGenerator generator = new CodeServiceTestGenerator();
		String author = "ZhangST";
		Class<?> modelClazz = CustomPo.class;
		Class<?> superServiceTestClazz = null;
		String serviceTestPackageName = "com.yffd.easy.xxx.service";
		generator.writeToConsole(modelClazz, superServiceTestClazz, serviceTestPackageName, author);
		
		String outRootDirPath = "D:\\ddd\\code\\serviceTest";
		boolean covered = true;
		
		generator.writeToFile(modelClazz, superServiceTestClazz, serviceTestPackageName, author, outRootDirPath, covered);
	}
}

