package com.yffd.easy.uupm.code;

import org.junit.Test;

import com.yffd.easy.framework.support.code.generator.DaoCodeGenerator;
import com.yffd.easy.framework.support.code.generator.DaoTestCodeGenerator;
import com.yffd.easy.framework.support.code.generator.MapperFileCodeGenerator;
import com.yffd.easy.framework.support.code.generator.ServiceCodeGenerator;
import com.yffd.easy.framework.support.code.generator.ServiceTestCodeGenerator;
import com.yffd.easy.uupm.api.model.UupmDictionaryModel;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年2月7日 下午2:20:00 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class WriteOneTest extends LocalProjectConfigTest {
	private MapperFileCodeGenerator mapperGenerator = new MapperFileCodeGenerator();
	private DaoCodeGenerator daoGenerator = new DaoCodeGenerator();
	private DaoTestCodeGenerator daoTestGenerator = new DaoTestCodeGenerator();
	private ServiceCodeGenerator serviceGenerator = new ServiceCodeGenerator();
	private ServiceTestCodeGenerator serviceTestGenerator = new ServiceTestCodeGenerator();
	
	private Class<?> modelClazz = UupmDictionaryModel.class;
	
	/**
	 * 所有--文件
	 * @Date	2018年2月8日 下午5:45:00 <br/>
	 * @author  zhangST
	 */
	@Test
	public void writeAllOfOneToFileTest() {
		mapperGenerator.writeToFile(modelClazz, daoPackageName, outRootDirPath_mapper, covered);
		daoGenerator.writeToFile(modelClazz, baseDaoClazz, daoPackageName, author, outRootDirPath_dao, true);
		daoTestGenerator.writeToFile(modelClazz, baseDaoTestClazz, daoTestPackageName, author, outRootDirPath_dao_test, true);
		serviceGenerator.writeToFile(modelClazz, baseServiceClazz, servicePackageName, daoPackageName, author, outRootDirPath_service, true);
		serviceTestGenerator.writeToFile(modelClazz, baseServiceTestClazz, serviceTestPackageName, author, outRootDirPath_service_test, true);
	}
	
	/**
	 * service--test--文件
	 * @Date	2018年2月8日 下午5:46:11 <br/>
	 * @author  zhangST
	 */
	@Test
	public void writeServiceTestToFile() {
		serviceTestGenerator.writeToFile(modelClazz, baseServiceTestClazz, serviceTestPackageName, author, outRootDirPath_service_test, true);
	}
	
	/**
	 * dao--test--文件
	 * @Date	2018年2月8日 下午5:47:35 <br/>
	 * @author  zhangST
	 */
	@Test
	public void writeDaoTestToFile() {
		daoTestGenerator.writeToFile(modelClazz, baseDaoTestClazz, daoTestPackageName, author, outRootDirPath_dao_test, true);
	}
	
	/**
	 * service--文件
	 * @Date	2018年2月8日 下午5:45:49 <br/>
	 * @author  zhangST
	 */
	@Test
	public void writeServiceToFile() {
		serviceGenerator.writeToFile(modelClazz, baseServiceClazz, servicePackageName, daoPackageName, author, outRootDirPath_service, true);
	}
	
	/**
	 * dao--文件
	 * @Date	2018年2月8日 下午5:46:59 <br/>
	 * @author  zhangST
	 */
	@Test
	public void writeDaoToFile() {
		daoGenerator.writeToFile(modelClazz, baseDaoClazz, daoPackageName, author, outRootDirPath_dao, true);
	}
	
	/**
	 * mapper--文件
	 * @Date	2018年2月8日 下午5:46:37 <br/>
	 * @author  zhangST
	 */
	@Test
	public void writeMapperToFile() {
		mapperGenerator.writeToFile(modelClazz, daoPackageName, outRootDirPath_mapper, covered);
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
		serviceTestGenerator.writeToConsole(modelClazz, baseServiceTestClazz, serviceTestPackageName, author);
	}
	
	/**
	 * dao--test--控制台
	 * @Date	2018年2月8日 下午5:47:21 <br/>
	 * @author  zhangST
	 */
	@Test
	public void writeDaoTestToConsole() {
		daoTestGenerator.writeToConsole(modelClazz, baseDaoTestClazz, daoTestPackageName, author);
	}
	
	/**
	 * service--控制台
	 * @Date	2018年2月8日 下午5:45:15 <br/>
	 * @author  zhangST
	 */
	@Test
	public void writeServiceToConsole() {
		serviceGenerator.writeToConsole(modelClazz, baseServiceClazz, servicePackageName, daoPackageName, author);
	}
	
	/**
	 * dao--控制台
	 * @Date	2018年2月8日 下午5:46:48 <br/>
	 * @author  zhangST
	 */
	@Test
	public void writeDaoToConsoleTest() {
		daoGenerator.writeToConsole(modelClazz, baseDaoClazz, daoPackageName, author);
	}
	
	/**
	 * mapper--控制台
	 * @Date	2018年2月8日 下午5:46:21 <br/>
	 * @author  zhangST
	 */
	@Test
	public void writeMapperToConsoleTest() {
		mapperGenerator.writeToConsole(modelClazz, daoPackageName);
	}
	
	
}

