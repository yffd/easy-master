package com.yffd.easy.framework.support.code.generator;

import java.io.File;
import java.util.Date;

import com.yffd.easy.framework.domain.CustomPo;

/**
 * @Description  service代码生成器.
 * @Date		 2018年2月6日 上午10:54:12 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class ServiceCodeGenerator extends CodeGenerator {
	private static final String FILE_SUFFIX = ".java";
	
	public void writeAllToFile(String modelRootDirPath, String modelPackageName, Class<?> baseServiceClazz, String servicePackageName, String daoPackageName, String author, String outRootDirPath, boolean covered) throws Exception {
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
						this.writeToFile(modelClazz, baseServiceClazz, servicePackageName, daoPackageName, author, outRootDirPath, covered);
					}
				}
			}
		}
	}
	
	/**
	 * 生成service的java文件
	 * @Date	2018年2月6日 上午11:31:13 <br/>
	 * @author  zhangST
	 * @param modelClazz
	 * @param baseServiceClazz
	 * @param servicePackageName
	 * @param daoPackageName
	 * @param author
	 * @param outRootDirPath
	 * @param covered			是否覆盖旧文件
	 */
	public void writeToFile(Class<?> modelClazz, Class<?> baseServiceClazz, String servicePackageName, String daoPackageName, String author, String outRootDirPath, boolean covered) {
		String content = this.makeContent(modelClazz, baseServiceClazz, servicePackageName, daoPackageName, author);
		
		if(!outRootDirPath.endsWith(File.separator)) outRootDirPath += File.separator;
		String packageDirPath = servicePackageName.replace(".", File.separator);
		String fullDirPath = outRootDirPath + packageDirPath;
		this.makedirs(fullDirPath);
		
		String fileName = fullDirPath + File.separator + this.fmtServiceSimpleName(modelClazz) + FILE_SUFFIX;
		this.writeToFile(content, fileName, covered);
	}
	
	public void writeToConsole(Class<?> modelClazz, Class<?> baseDaoClazz, String servicePackageName, String daoPackageName, String author) {
		String content = this.makeContent(modelClazz, baseDaoClazz, servicePackageName, daoPackageName, author);
		System.out.println(content);
	}
	
	protected String makeContent(Class<?> modelClazz, Class<?> baseServiceClazz, String servicePackageName, String daoPackageName, String author) {
		String curDate = this.fmtDate(new Date());
		StringBuilder sb = new StringBuilder();
		sb.append("package "+ servicePackageName +";").append("\r\n");
		
		sb.append("\r\n");
		sb.append("import org.springframework.beans.factory.annotation.Autowired;").append("\r\n");
		sb.append("import org.springframework.stereotype.Service;").append("\r\n");
		sb.append("\r\n");
		sb.append("import com.yffd.easy.framework.base.dao.GenericDao;").append("\r\n");
		
		// import base service
		String baseServiceSimpleName = null;
		if(null!=baseServiceClazz) {
			baseServiceSimpleName = baseServiceClazz.getSimpleName();
			if(!baseServiceClazz.getPackage().getName().equals(servicePackageName)) {
				String baseServicefullClassName = baseServiceClazz.getName();
				sb.append("import ").append(baseServicefullClassName).append(";").append("\r\n");
			}
		}
		
		// import dao
		String daoFullClassName = this.fmtDaoFullClassName(modelClazz, daoPackageName);
		sb.append("import ").append(daoFullClassName).append(";").append("\r\n");
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
		sb.append("@Service").append("\r\n");
		
		String serviceSimpleName = this.fmtServiceSimpleName(modelClazz);
		if(null==baseServiceSimpleName) {
			sb.append("public class ").append(serviceSimpleName).append(" {").append("\r\n");
		} else {
			sb.append("public class ").append(serviceSimpleName)
			.append(" extends ").append(baseServiceSimpleName).append("<").append(modelClazz.getSimpleName()).append(">")
			.append(" {").append("\r\n");
		}
		
		String daoSimpleName = this.fmtDaoSimpleName(modelClazz);
		String aliasName = daoSimpleName.substring(0, 1).toLowerCase() + daoSimpleName.substring(1);
		sb.append("\r\n");
		sb.append("\t").append("@Autowired").append("\r\n");
		sb.append("\t").append("private ").append(daoSimpleName).append(" ").append(aliasName).append(";").append("\r\n");
		
		sb.append("\t").append("\r\n");
		sb.append("\t").append("@Override").append("\r\n");
		sb.append("\t").append("public GenericDao<"+modelClazz.getSimpleName()+"> getBindDao() {").append("\r\n");
		sb.append("\t").append("\t").append("return this.").append(aliasName).append(";").append("\r\n");
		sb.append("\t").append("}").append("\r\n");
		
		sb.append("\r\n");
		sb.append("}");
		
		return sb.toString();
	}
	
	public static void main(String[] args) throws Exception {
		ServiceCodeGenerator generator = new ServiceCodeGenerator();
		Class<?> modelClazz = CustomPo.class;
		Class<?> baseServiceClazz = null;
		String servicePackageName = "com.yffd.easy.xxx.service";
		String daoPackageName = "com.yffd.easy.xxx.dao";
		String author = "ZhangST";
		generator.writeToConsole(modelClazz, baseServiceClazz, servicePackageName, daoPackageName, author);
		
		String outRootDirPath = "D:\\ddd\\code\\dao124";
		boolean covered = true;
//		generator.writeToFile(modelClazz, baseServiceClazz, servicePackageName, daoPackageName, author, outRootDirPath, covered);
		
	}
}

