package com.yffd.easy.framework.core.code.generator;

import java.io.File;
import java.util.Date;

import com.yffd.easy.framework.domain.CustomPo;

/**
 * @Description  mapper interface代码生成器.
 * @Date		 2018年2月6日 上午10:54:12 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class CodeMapperInterfaceGenerator extends CodeGenerator {
	private static final String FILE_SUFFIX = ".java";
	
	public void writeAllToFile(String modelRootDirPath, String modelPackageName, Class<?> superClazz, String mapperPackageName, String author, String outRootDirPath, boolean covered) throws Exception {
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
						this.writeToFile(modelClazz, superClazz, mapperPackageName, author, outRootDirPath, covered);
					}
				}
			}
		}
	}
	
	public void writeToFile(Class<?> modelClazz, Class<?> superClazz, String mapperPackageName, String author, String outRootDirPath, boolean covered) {
		String content = this.makeContent(modelClazz, superClazz, mapperPackageName, author);
		
		if(!outRootDirPath.endsWith(File.separator)) outRootDirPath += File.separator;
		String packageDirPath = mapperPackageName.replace(".", File.separator);
		String fullDirPath = outRootDirPath + packageDirPath;
		this.makedirs(fullDirPath);
		
		String fileName = fullDirPath + File.separator + this.fmtMapperSimpleName(modelClazz) + FILE_SUFFIX;
		this.writeToFile(content, fileName, covered);
	}
	
	public void writeToConsole(Class<?> modelClazz, Class<?> superClazz, String mapperPackageName, String author) {
		String content = this.makeContent(modelClazz, superClazz, mapperPackageName, author);
		System.out.println(content);
	}
	
	protected String makeContent(Class<?> modelClazz, Class<?> superClazz, String mapperPackageName, String author) {
		String curDate = this.fmtDate(new Date());
		StringBuilder sb = new StringBuilder();
		sb.append("package "+ mapperPackageName +";").append("\r\n");
		
		// import super class
		String superClassSimpleName = null;
		if(null!=superClazz) {
			superClassSimpleName = superClazz.getSimpleName();
			if(!superClazz.getPackage().getName().equals(mapperPackageName)) {
				String superClassfullClassName = superClazz.getName();
				sb.append("\r\n");
				sb.append("import ").append(superClassfullClassName).append(";").append("\r\n");
			}
		}
		
		// import model
		sb.append("import ").append(modelClazz.getName()).append(";").append("\r\n");
		
		sb.append("\r\n");
		sb.append("/**").append("\r\n");
		sb.append(" * @Description\t简单描述该类的功能（可选）.").append("\r\n");
		sb.append(" * @Date\t\t" + curDate + " <br/>").append("\r\n");
		sb.append(" * @author\t\t").append(author).append("\r\n");
		sb.append(" * @version\t\t").append("1.0").append("\r\n");
		sb.append(" * @since\t\t").append("JDK 1.7+").append("\r\n");
		sb.append(" * @see").append("\r\n");
		sb.append(" */").append("\r\n");
		
		sb.append("\r\n");
		String mapperSimpleName = this.fmtMapperSimpleName(modelClazz);
		if(null==superClassSimpleName) {
			sb.append("public interface ").append(mapperSimpleName).append(" {").append("\r\n");
		} else {
			sb.append("public interface ").append(mapperSimpleName)
			.append(" extends ").append(superClassSimpleName).append("<").append(modelClazz.getSimpleName()).append(">")
			.append(" {").append("\r\n");
		}
		
		sb.append("\r\n");
		sb.append("}");
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		CodeMapperInterfaceGenerator generator = new CodeMapperInterfaceGenerator();
		Class<?> modelClazz = CustomPo.class;
		Class<?> superClazz = null;
		String mapperPackageName = "com.yffd.easy.xxx.mapper";
		String author = "ZhangST";
		generator.writeToConsole(modelClazz, superClazz, mapperPackageName, author);
		
		String outRootDirPath = "D:\\ddd\\code\\mapper";
		boolean covered = true;
		generator.writeToFile(modelClazz, superClazz, mapperPackageName, author, outRootDirPath, covered);
		
	}
}

