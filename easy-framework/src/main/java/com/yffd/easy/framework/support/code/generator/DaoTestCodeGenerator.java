package com.yffd.easy.framework.support.code.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import com.yffd.easy.framework.domain.GenericPO;

/**
 * @Description  XxxDaoTest代码生成器.
 * @Date		 2018年2月6日 下午1:55:18 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class DaoTestCodeGenerator extends CodeGenerator {
	private static final String FILE_SUFFIX = ".java";
	private static final String TEMPLATE_PATH = "com\\yffd\\easy\\framework\\support\\code\\template\\XxxDaoTest.template";
	
	public void writeAllToFile(String modelRootDirPath, String modelPackageName, Class<?> baseDaoTestClazz, String daoTestPackageName, String author, String outRootDirPath, boolean covered) throws Exception {
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
						this.writeToFile(modelClazz, baseDaoTestClazz, daoTestPackageName, author, outRootDirPath, covered);
					}
				}
			}
		}
	}
	
	public void writeToFile(Class<?> modelClazz, Class<?> baseDaoTestClazz, String daoTestPackageName, String author, String outRootDirPath, boolean covered) {
		String content = this.makeContent(modelClazz, baseDaoTestClazz, daoTestPackageName, author);
		
		if(!outRootDirPath.endsWith(File.separator)) outRootDirPath += File.separator;
		String packageDirPath = daoTestPackageName.replace(".", File.separator);
		String fullDirPath = outRootDirPath + packageDirPath;
		this.makedirs(fullDirPath);
		
		String fileName = fullDirPath + File.separator + this.fmtDaoTestSimpleName(modelClazz) + FILE_SUFFIX;
		this.writeToFile(content, fileName, covered);
	}
	
	public void writeToConsole(Class<?> modelClazz, Class<?> baseDaoTestClazz, String daoTestPackageName, String author) {
		String content = this.makeContent(modelClazz, baseDaoTestClazz, daoTestPackageName, author);
		System.out.println(content);
	}
	
	protected String makeContent(Class<?> modelClazz, Class<?> baseDaoTestClazz, String daoTestPackageName, String author) {
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
		
		content = content.replace("##dao_package_name##", daoTestPackageName);
		content = content.replace("##import_model_name##", modelClazz.getName());
		content = content.replace("##date##", this.fmtDate(new Date()));
		content = content.replace("##author##", author);
		content = content.replace("##test_dao_name##", this.fmtDaoTestSimpleName(modelClazz));
		if(null!=baseDaoTestClazz) {
			content = content.replace("##import_base_dao_name##", "import "+baseDaoTestClazz.getName()+";");
			content = content.replace("##test_base_dao_name##", "extends " + baseDaoTestClazz.getSimpleName() + " ");
		} else {
			content = content.replace("##import_base_dao_name##", "");
			content = content.replace("##test_base_dao_name##", "");
		}
		content = content.replace("##dao_name##", this.fmtDaoSimpleName(modelClazz));
		content = content.replace("##model_class_name##", modelClazz.getSimpleName() + ".class");
		
		return content;
	}
	
	public static void main(String[] args) {
		DaoTestCodeGenerator generator = new DaoTestCodeGenerator();
		String author = "ZhangST";
		Class<?> modelClazz = GenericPO.class;
		Class<?> baseDaoTestClazz = null;
		String daoTestPackageName = "com.yffd.easy.xxx.dao";
		generator.writeToConsole(modelClazz, baseDaoTestClazz, daoTestPackageName, author);
		
		String outRootDirPath = "D:\\ddd\\code\\dao1235";
		boolean covered = true;
		
//		generator.writeToFile(modelClazz, baseDaoTestClazz, daoTestPackageName, author, outRootDirPath, covered);
	}
}

