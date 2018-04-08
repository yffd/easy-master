package com.yffd.easy.uupm.code;

import org.junit.Test;

import com.yffd.easy.framework.support.code.generator.CodeMapperFileGenerator;
import com.yffd.easy.framework.support.code.generator.CodeMapperInterfaceGenerator;
import com.yffd.easy.framework.support.code.generator.CodeMapperSqlGenerator;
import com.yffd.easy.framework.support.code.generator.CodeServiceGenerator;
import com.yffd.easy.framework.support.code.generator.CodeServiceTestGenerator;
import com.yffd.easy.uupm.api.model.UupmApplicationModel;
import com.yffd.easy.uupm.api.model.UupmTreeDictionaryModel;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年2月7日 下午2:20:00 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class WriteOneTest extends LocalProjectConfigTest {
	private CodeMapperInterfaceGenerator mapperInterfaceGenerator = new CodeMapperInterfaceGenerator();
	private CodeMapperFileGenerator mapperFileGenerator = new CodeMapperFileGenerator();
	private CodeMapperSqlGenerator mapperSqlGenerator = new CodeMapperSqlGenerator();
	private CodeServiceGenerator serviceGenerator = new CodeServiceGenerator();
	private CodeServiceTestGenerator serviceTestGenerator = new CodeServiceTestGenerator();
	
	private Class<?> modelClazz = UupmApplicationModel.class;
	
	/**
	 * 所有--文件
	 * @Date	2018年2月8日 下午5:45:00 <br/>
	 * @author  zhangST
	 */
	@Test
	public void writeAllOfOneToFileTest() {
		mapperInterfaceGenerator.writeToFile(modelClazz, superMapperClazz, mapperPackageName, author, outRootDirPath_mapper_interface, true);
		mapperFileGenerator.writeToFile(modelClazz, mapperPackageName, outRootDirPath_mapper_xml, covered);
		serviceGenerator.writeToFile(modelClazz, superServiceClazz, servicePackageName, mapperPackageName, author, outRootDirPath_service, true);
		serviceTestGenerator.writeToFile(modelClazz, superServiceTestClazz, serviceTestPackageName, author, outRootDirPath_service_test, true);
	}
	
	/**
	 * service--test--文件
	 * @Date	2018年2月8日 下午5:46:11 <br/>
	 * @author  zhangST
	 */
	@Test
	public void writeServiceTestToFile() {
		serviceTestGenerator.writeToFile(modelClazz, superServiceTestClazz, serviceTestPackageName, author, outRootDirPath_service_test, true);
	}
	
	/**
	 * service--文件
	 * @Date	2018年2月8日 下午5:45:49 <br/>
	 * @author  zhangST
	 */
	@Test
	public void writeServiceToFile() {
		serviceGenerator.writeToFile(modelClazz, superServiceClazz, servicePackageName, mapperPackageName, author, outRootDirPath_service, true);
	}
	
	/**
	 * mapper interface--test--文件
	 * @Date	2018年2月8日 下午5:47:35 <br/>
	 * @author  zhangST
	 */
	@Test
	public void writeMapperInterfaceToFile() {
		mapperInterfaceGenerator.writeToFile(modelClazz, superMapperClazz, mapperPackageName, author, outRootDirPath_mapper_interface, true);
	}
	
	/**
	 * mapper--文件
	 * @Date	2018年2月8日 下午5:46:37 <br/>
	 * @author  zhangST
	 */
	@Test
	public void writeMapperToFile() {
		mapperFileGenerator.writeToFile(modelClazz, mapperPackageName, outRootDirPath_mapper_xml, covered);
	}
	
	/*****************************************************************************/
	/*****************************************************************************/
	
	/**
	 * service--test--控制台
	 * @Date	2018年2月8日 下午5:45:58 <br/>
	 * @author  zhangST
	 */
	@Test
	public void writeServiceTestToConsole() {
		serviceTestGenerator.writeToConsole(modelClazz, superServiceTestClazz, serviceTestPackageName, author);
	}
	
	/**
	 * service--控制台
	 * @Date	2018年2月8日 下午5:45:15 <br/>
	 * @author  zhangST
	 */
	@Test
	public void writeServiceToConsole() {
		serviceGenerator.writeToConsole(modelClazz, superServiceClazz, servicePackageName, mapperPackageName, author);
	}
	
	/**
	 * mapper interface--test--文件
	 * @Date	2018年2月8日 下午5:47:35 <br/>
	 * @author  zhangST
	 */
	@Test
	public void writeMapperInterfaceToConsoleTest() {
		mapperInterfaceGenerator.writeToConsole(modelClazz, superMapperClazz, mapperPackageName, author);
	}
	
	/**
	 * mapper--控制台
	 * @Date	2018年2月8日 下午5:46:21 <br/>
	 * @author  zhangST
	 */
	@Test
	public void writeMapperToConsoleTest() {
		mapperFileGenerator.writeToConsole(modelClazz, mapperPackageName);
	}
	
	/**
	 * mapper sql--控制台
	 * @Date	2018年2月8日 下午5:46:21 <br/>
	 * @author  zhangST
	 */
	@Test
	public void writeMapperSqlToConsoleTest() {
//		String tableAliasName = "";
//		String modelAliasName = "";
//		String oldAliasName = "";
//		String mapAliasName = "";
		
		String tableAliasName = "t";
		String modelAliasName = "model";
		String oldAliasName = "old";
		String mapAliasName = "map";
		
		Class<?> modelClazz = UupmTreeDictionaryModel.class;
		mapperSqlGenerator.writeToConsole(tableAliasName, modelAliasName, oldAliasName, mapAliasName, modelClazz);
	}
	
	
}

