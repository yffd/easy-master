package com.yffd.easy.framework.common.code.generator;

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
public class CodeTemplateServiceGenerator extends CodeGenerator {
	private static final String FILE_SUFFIX = ".java";
	private static final String TEMPLATE_PATH = "com\\yffd\\easy\\framework\\core\\code\\template\\XxxService2.template";
	private static final String SERVICE_SUFFIX = "Service";
	private static final String DAO_SUFFIX = "Dao";
	private static final String VO_SUFFIX = "Vo";
	
	public void writeAllToFile(String modelRootDirPath, Class<?> serviceSuperClazz, String modelPackageName, String servicePackageName, String daoPackageName, boolean withVo, String author, String outRootDirPath, boolean covered) throws Exception {
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
						this.writeToFile(modelClazz, serviceSuperClazz, servicePackageName, daoPackageName, withVo, author, outRootDirPath, covered);
					}
				}
			}
		}
	}
	
	public void writeToFile(Class<?> modelClazz, Class<?> serviceSuperClazz, String servicePackageName, String daoPackageName, boolean withVo, String author, String outRootDirPath, boolean covered) {
		String content = this.makeContent(modelClazz, serviceSuperClazz, servicePackageName, daoPackageName, withVo, author);
		
		if(!outRootDirPath.endsWith(File.separator)) outRootDirPath += File.separator;
		String packageDirPath = servicePackageName.replace(".", File.separator);
		String fullDirPath = outRootDirPath + packageDirPath;
		this.makedirs(fullDirPath);
		
		String shortFileName = this.fmtModelName(modelClazz, null, SERVICE_SUFFIX);	// 文件名
		String fileName = fullDirPath + File.separator + shortFileName + FILE_SUFFIX;
		this.writeToFile(content, fileName, covered);
	}
	
	public void writeToConsole(Class<?> modelClazz, Class<?> serviceSuperClazz, String servicePackageName, String daoPackageName, boolean withVo, String author) {
		String content = this.makeContent(modelClazz, serviceSuperClazz, servicePackageName, daoPackageName, withVo, author);
		System.out.println(content);
	}
	
	protected String makeContent(Class<?> modelClazz, Class<?> serviceSuperClazz, String servicePackageName, String daoPackageName, boolean withVo, String author) {
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
		
		content = content.replace("##PACKAGE_SERVICE##", servicePackageName);
		content = content.replace("##IMPORT_MODEL##", modelClazz.getName());
		// import dao
		String daoFullClassName = daoPackageName + "." + this.fmtModelName(modelClazz, "", DAO_SUFFIX);
		content = content.replace("##IMPORT_DAO##", daoFullClassName);
		content = content.replace("##DATE##", this.fmtDate(new Date()));
		content = content.replace("##AUTHOR##", author);
		// dao
		String daoSimpleName = this.fmtDaoSimpleName(modelClazz);
		String daoAliasName = daoSimpleName.substring(1, 2).toLowerCase() + daoSimpleName.substring(2);
		content = content.replace("##SIMPLE_DAO##", daoSimpleName);
		content = content.replace("##ALIAS_DAO##", daoAliasName);
		// super service
		if(null!=serviceSuperClazz) {
			content = content.replace("##IMPORT_SERVICE_SUPER##", serviceSuperClazz.getName());
			// extends
			StringBuilder tmp = new StringBuilder();
			tmp.append("extends ").append(serviceSuperClazz.getSimpleName())
			.append("<").append(modelClazz.getSimpleName()).append(">");
			
			if(withVo) {
				String voSimpleName = this.fmtModelName(modelClazz, null, VO_SUFFIX);
				tmp.append(", ").append("<").append(voSimpleName).append(">");
				// model vo
				content = content.replace("##SIMPLE_CLASS_NAME_MODEL_VO##", voSimpleName);
			}
			
			tmp.append(" ");
			content = content.replace("##SIMPLE_SERVICE_SUPER##", tmp.toString());
		} else {
			content = content.replace("##IMPORT_SERVICE_SUPER##", "");
			content = content.replace("##SIMPLE_SERVICE_SUPER##", "");
		}
		// name
		content = content.replace("##SIMPLE_SERVICE##", this.fmtModelName(modelClazz, null, SERVICE_SUFFIX));
		// model
		content = content.replace("##SIMPLE_CLASS_NAME_MODEL_PO##", modelClazz.getSimpleName());
		
		return content;
	}
	
	public static void main(String[] args) {
		CodeTemplateServiceGenerator generator = new CodeTemplateServiceGenerator();
		String author = "ZhangST";
		Class<?> modelClazz = CommonTreeEntity.class;
		Class<?> serviceSuperClazz = CommonDefaultServiceImpl.class;
		String servicePackageName = "com.yffd.easy.xxx.service";
		String daoPackageName = "com.yffd.easy.xxx.dao";
		boolean withVo = false;
		generator.writeToConsole(modelClazz, serviceSuperClazz, servicePackageName, daoPackageName, withVo, author);
		
		String outRootDirPath = "D:\\ddd\\code\\service";
		boolean covered = true;
		
//		generator.writeToFile(modelClazz, servicePackageName, daoPackageName, author, outRootDirPath, covered);
	}
}

