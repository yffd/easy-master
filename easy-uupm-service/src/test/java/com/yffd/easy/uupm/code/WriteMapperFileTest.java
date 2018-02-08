package com.yffd.easy.uupm.code;

import org.junit.Test;

import com.yffd.easy.framework.support.code.generator.MapperFileCodeGenerator;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年2月2日 下午6:27:15 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class WriteMapperFileTest extends LocalProjectConfigTest {
	private MapperFileCodeGenerator generator = new MapperFileCodeGenerator();
	
	@Test
	public void writeAllToFile() {
		try {
			generator.writeAllToFile(modelRootDirPath, modelPackageName, daoPackageName, outRootDirPath_mapper, covered);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

