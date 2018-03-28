package com.yffd.easy.framework.support.code.generator;

import java.io.File;
import java.util.Date;

import com.yffd.easy.framework.domain.CustomPo;

/**
 * @Description  DAO代码生成器.
 * @Date		 2018年2月6日 上午10:54:12 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class DaoCodeGenerator extends CodeGenerator {
	private static final String FILE_SUFFIX = ".java";
	
	public void writeAllToFile(String modelRootDirPath, String modelPackageName, Class<?> baseDaoClazz, String daoPackageName, String author, String outRootDirPath, boolean covered) throws Exception {
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
						this.writeToFile(modelClazz, baseDaoClazz, daoPackageName, author, outRootDirPath, covered);
					}
				}
			}
		}
	}
	
	/**
	 * 生成dao的java文件
	 * @Date	2018年2月6日 上午11:31:13 <br/>
	 * @author  zhangST
	 * @param modelClazz
	 * @param baseDaoClazz
	 * @param daoPackageName
	 * @param author
	 * @param outRootDirPath
	 * @param covered			是否覆盖旧文件
	 */
	public void writeToFile(Class<?> modelClazz, Class<?> baseDaoClazz, String daoPackageName, String author, String outRootDirPath, boolean covered) {
		String content = this.makeContent(modelClazz, baseDaoClazz, daoPackageName, author);
		
		if(!outRootDirPath.endsWith(File.separator)) outRootDirPath += File.separator;
		String packageDirPath = daoPackageName.replace(".", File.separator);
		String fullDirPath = outRootDirPath + packageDirPath;
		this.makedirs(fullDirPath);
		
		String fileName = fullDirPath + File.separator + this.fmtDaoSimpleName(modelClazz) + FILE_SUFFIX;
		this.writeToFile(content, fileName, covered);
	}
	
	public void writeToConsole(Class<?> modelClazz, Class<?> baseDaoClazz, String daoPackageName, String author) {
		String content = this.makeContent(modelClazz, baseDaoClazz, daoPackageName, author);
		System.out.println(content);
	}
	
	protected String makeContent(Class<?> modelClazz, Class<?> baseDaoClazz, String daoPackageName, String author) {
		String curDate = this.fmtDate(new Date());
		StringBuilder sb = new StringBuilder();
		sb.append("package "+ daoPackageName +";").append("\r\n");
		sb.append("\r\n");
		sb.append("import org.springframework.stereotype.Repository;").append("\r\n");
		
		// import model
		sb.append("import ").append(modelClazz.getName()).append(";").append("\r\n");
				
		// import
		String baseDaoSimpleName = null;
		if(null!=baseDaoClazz) {
			baseDaoSimpleName = baseDaoClazz.getSimpleName();
			if(!baseDaoClazz.getPackage().getName().equals(daoPackageName)) {
				String baseDaofullClassName = baseDaoClazz.getName();
				sb.append("\r\n");
				sb.append("import ").append(baseDaofullClassName).append(";").append("\r\n");
			}
		}
		
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
		sb.append("@Repository").append("\r\n");
		
		String daoSimpleName = this.fmtDaoSimpleName(modelClazz);
		if(null==baseDaoSimpleName) {
			sb.append("public class ").append(daoSimpleName).append(" {").append("\r\n");
		} else {
			sb.append("public class ").append(daoSimpleName)
			.append(" extends ").append(baseDaoSimpleName).append("<").append(modelClazz.getSimpleName()).append(">")
			.append(" {").append("\r\n");
		}
		
		sb.append("\r\n");
		sb.append("}");
		
		return sb.toString();
	}
	
	public static void main(String[] args) throws Exception {
		DaoCodeGenerator generator = new DaoCodeGenerator();
		Class<?> modelClazz = CustomPo.class;
		Class<?> baseDaoClazz = null;
		String daoPackageName = "com.yffd.easy.xxx.dao";
		String author = "ZhangST";
//		generator.writeToConsole(modelClazz, baseDaoClazz, daoPackageName, author);
		
		String outRootDirPath = "D:\\ddd\\code\\dao1234";
		boolean covered = true;
//		generator.writeToFile(modelClazz, baseDaoClazz, daoPackageName, author, outRootDirPath, covered);
		
	}
}

