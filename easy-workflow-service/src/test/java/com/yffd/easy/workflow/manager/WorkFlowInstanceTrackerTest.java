package com.yffd.easy.workflow.manager;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yffd.easy.workflow.WorkFlowBaseTestCase;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月22日 下午3:18:29 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class WorkFlowInstanceTrackerTest extends WorkFlowBaseTestCase {

	@Autowired
	private WorkFlowInstanceTracker workFlowInstanceTracker;
	
	@Test
	public void traceDiagramTest() {
		String instanceId = "15001";
		workFlowInstanceTracker.traceDiagram(instanceId);
	}
}

