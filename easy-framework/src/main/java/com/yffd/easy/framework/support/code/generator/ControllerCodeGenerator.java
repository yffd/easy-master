package com.yffd.easy.framework.support.code.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import com.yffd.easy.common.core.util.EasyNamingFormatUtils;

/**
 * @Description  XxxController代码生成器.
 * @Date		 2018年2月6日 下午1:55:18 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class ControllerCodeGenerator extends CodeGenerator {
	private static final String FILE_SUFFIX = ".java";
	private static final String TEMPLATE_PATH = "com\\yffd\\easy\\framework\\support\\code\\template\\XxxController.template";
	
	public void writeAllToFile(String modelRootDirPath, String modelPackageName, Class<?> baseControllerClazz, String controllerPackageName, Class<?> serviceClazz, String author, String outRootDirPath, boolean covered) throws Exception {
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
						this.writeToFile(modelClazz, baseControllerClazz, controllerPackageName, serviceClazz, author, outRootDirPath, covered);
					}
				}
			}
		}
	}
	
	public void writeToFile(Class<?> modelClazz, Class<?> baseControllerClazz, String controllerPackageName, Class<?> serviceClazz, String author, String outRootDirPath, boolean covered) {
		String content = this.makeContent(modelClazz, baseControllerClazz, controllerPackageName, serviceClazz, author);
		
		if(!outRootDirPath.endsWith(File.separator)) outRootDirPath += File.separator;
		String packageDirPath = controllerPackageName.replace(".", File.separator);
		String fullDirPath = outRootDirPath + packageDirPath;
		this.makedirs(fullDirPath);
		
		String fileName = fullDirPath + File.separator + this.fmtControllerSimpleName(modelClazz) + FILE_SUFFIX;
		System.out.println("file name:" + fileName);
		this.writeToFile(content, fileName, covered);
	}
	
	public void writeToConsole(Class<?> modelClazz, Class<?> baseControllerClazz, String controllerPackageName, Class<?> serviceClazz, String author) {
		String content = this.makeContent(modelClazz, baseControllerClazz, controllerPackageName, serviceClazz, author);
		System.out.println(content);
	}
	
	protected String makeContent(Class<?> modelClazz, Class<?> baseControllerClazz, String controllerPackageName, Class<?> serviceClazz, String author) {
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
		
		content = content.replace("##controller_package_name##", controllerPackageName);
		content = content.replace("##import_model_full_name##", modelClazz.getName());
		content = content.replace("##date##", this.fmtDate(new Date()));
		content = content.replace("##author##", author);
		content = content.replace("##controller_name##", this.fmtControllerSimpleName(modelClazz));
		
		if(null!=baseControllerClazz) {
			if(!baseControllerClazz.getPackage().getName().equals(controllerPackageName)) {
				content = content.replace("##import_base_controller_full_name##", "import " + baseControllerClazz.getName() + ";");
			} else {
				content = content.replace("##import_base_controller_full_name##", "");
			}
			content = content.replace("##base_controller_name##", "extends " + baseControllerClazz.getSimpleName() + " ");
		} else {
			content = content.replace("##import_base_controller_full_name##", "");
			content = content.replace("##base_controller_name##", "");
		}

		content = content.replace("##import_service_full_name##", serviceClazz.getName());
		String serviceSimpleName = serviceClazz.getSimpleName();
		String serviceAliasName = serviceSimpleName.substring(0, 1).toLowerCase() + serviceSimpleName.substring(1);
		
		content = content.replace("##service_name##", serviceSimpleName);
		content = content.replace("##service_alias_name##", serviceAliasName);
		
		content = content.replace("##model_simple_name##", modelClazz.getSimpleName());
		
		String requestMapping = "/";
		String tmp = this.fmtModelName(modelClazz, null, null);
		tmp = EasyNamingFormatUtils.camel2underline(tmp, false, null, null);
		tmp = tmp.replace("_", "/");
		requestMapping += tmp;
		content = content.replace("##requestMapping##", requestMapping);
		
		return content;
	}
	
}

