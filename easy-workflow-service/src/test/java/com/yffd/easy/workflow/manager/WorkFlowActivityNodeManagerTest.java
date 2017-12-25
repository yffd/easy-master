package com.yffd.easy.workflow.manager;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yffd.easy.workflow.activiti.dao.WorkFlowBaseTestCase;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月22日 下午5:38:47 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class WorkFlowActivityNodeManagerTest extends WorkFlowBaseTestCase {

	@Autowired
	private WorkFlowActivityNodeManager workFlowActivityNodeManager;
	
	@Test
	public void getNodeTest() {
		String definitionId = "leave:1:4";
		workFlowActivityNodeManager.getNode(definitionId);
	}
	@Test
	public void getAllNodeTest() {
		String definitionId = "leave:1:4";
		workFlowActivityNodeManager.getAllNode(definitionId);
	}
}

