package com.yffd.easy.workflow.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yffd.easy.workflow.WorkFlowBaseTestCase;
import com.yffd.easy.workflow.model.dto.WfActivityNodeDTO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年1月17日 下午4:53:04 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class WfModelerServiceTest extends WorkFlowBaseTestCase {

	@Autowired
	private WfModelerService wfModelerService;
	
	@Test
	public void getActivityNodeTest() {
		String id = "leave:1:4";
		List<WfActivityNodeDTO> nodeList = this.wfModelerService.getActivityNode(id);
		System.out.println(nodeList.size());
		for(WfActivityNodeDTO node : nodeList) {
			System.out.println(node);
		}
	}
}

