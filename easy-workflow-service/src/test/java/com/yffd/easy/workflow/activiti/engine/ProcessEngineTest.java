package com.yffd.easy.workflow.activiti.engine;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.yffd.easy.workflow.activiti.test.AbstractActivitiTest;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月30日 下午3:02:58 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class ProcessEngineTest extends AbstractActivitiTest {
	
	/**
	 * 检测引擎是否能正常工作
	 * @Date	2017年11月30日 下午3:03:53 <br/>
	 * @author  zhangST
	 */
	@Test
	public void testProcessEngines() {
		assertNotNull(repositoryService);
		assertNotNull(runtimeService);
		assertNotNull(formService);
		assertNotNull(identityService);
		assertNotNull(taskService);
		assertNotNull(historyService);
		assertNotNull(managementService);
	}
}

