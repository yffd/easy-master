package com.yffd.easy.uupm.code;

import org.junit.Test;

import com.yffd.easy.framework.support.code.generator.DaoTestCodeGenerator;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年2月2日 下午6:27:15 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class WriteTestDaoFileTest extends LocalProjectConfigTest {
	private DaoTestCodeGenerator generator = new DaoTestCodeGenerator();
	
	@Test
	public void writeAllToFile() {
		try {
			generator.writeAllToFile(modelRootDirPath, modelPackageName, baseDaoTestClazz, daoTestPackageName, author, outRootDirPath_dao_test, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

