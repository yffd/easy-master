package com.yffd.easy.uupm.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yffd.easy.uupm.api.model.UupmTreeNodeModel;
import com.yffd.easy.uupm.base.UupmBaseServiceTest;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年03月30日 18时02分49秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class UupmTreeNodeServiceTest extends UupmBaseServiceTest {

	@Autowired
	private UupmTreeNodeService uupmTreeNodeService;
	
	@Test
	public void addRootNodeTest() {
		UupmTreeNodeModel paramModel = new UupmTreeNodeModel();
		paramModel.setTenantCode("default");
		paramModel.setNodeCode("org");
		paramModel.setNodeName("组织机构根节点");
		paramModel.setNodeLabel("default");
		int result = this.uupmTreeNodeService.addRootNode(paramModel, null);
		System.out.println(result);
		System.out.println(paramModel.getId());
	}
	
	@Test
	public void addChildNodeTest() {
		UupmTreeNodeModel paramModel = new UupmTreeNodeModel();
		paramModel.setTenantCode("default");
		paramModel.setNodeCode("org");
		paramModel.setNodeName("组织机构根节点");
		paramModel.setNodeLabel("org");
		
		UupmTreeNodeModel paramModel1 = new UupmTreeNodeModel();
		paramModel1.setTenantCode("default");
		paramModel1.setNodeCode("it2");
		paramModel1.setNodeName("IT二部");
		paramModel1.setNodeLabel("org");
		
		UupmTreeNodeModel paramModel2 = new UupmTreeNodeModel();
		paramModel2.setTenantCode("default");
		paramModel2.setNodeCode("it1");
		paramModel2.setNodeName("IT一部");
		paramModel2.setNodeLabel("org");
		
		UupmTreeNodeModel paramModel11 = new UupmTreeNodeModel();
		paramModel11.setTenantCode("default");
		paramModel11.setNodeCode("it2_test");
		paramModel11.setNodeName("IT二部-测试组");
		
		UupmTreeNodeModel paramModel21 = new UupmTreeNodeModel();
		paramModel21.setTenantCode("default");
		paramModel21.setNodeCode("it1_test");
		paramModel21.setNodeName("IT一部-测试组");
		
		String nodeLabel = "org";
		String parentNodeCode = "it1";
		int result = this.uupmTreeNodeService.addChildNode(nodeLabel, parentNodeCode, paramModel21, null);
		System.out.println(result);
		System.out.println(paramModel.getId());
	}
	
	@Test
	public void updateNodeTest() {
		UupmTreeNodeModel newNode = new UupmTreeNodeModel();
		newNode.setTenantCode("default");
		newNode.setNodeName("IT二部-new123");
		newNode.setNodeValue("www");
		
		UupmTreeNodeModel oldNode = new UupmTreeNodeModel();
		oldNode.setTenantCode("default");
		oldNode.setNodeLabel("org");
		oldNode.setNodeCode("it2");
		
		int result = this.uupmTreeNodeService.updateNode(newNode, oldNode, null);
		System.out.println(result);
	}
	
	@Test
	public void delNodesTest() {
		String nodeLabel = "org";
		String nodeCode = "it2";
		int result = this.uupmTreeNodeService.delNodes(nodeLabel, nodeCode , null);
		System.out.println(result);
	}
	
	@Test
	public void updateStatusTest() {
		String nodeLabel = "org";
		String nodeCode = "it2";
		String nodeStatus = "A";
		int result = this.uupmTreeNodeService.updateStatus(nodeLabel, nodeCode, nodeStatus, null);
		System.out.println(result);
	}
	
	@Test
	public void countLayerTest() {
		String nodeLabel = "org";
		String nodeCode = "it2";
		long result = this.uupmTreeNodeService.countLayer(nodeLabel, nodeCode, null);
		System.out.println(result);
	}
	
	@Test
	public void findParentPathTest() {
		String nodeLabel = "org";
		String nodeCode = "it2";
		String result = this.uupmTreeNodeService.findParentPath(nodeLabel, nodeCode, null);
		System.out.println(result);
	}
	
	@Test
	public void findChildrenNodeListTest() {
		String nodeLabel = "org";
		String nodeCode = "it2";
		List<UupmTreeNodeModel> result = this.uupmTreeNodeService.findChildrenNodeList(nodeLabel, nodeCode, null);
		System.out.println(result);
		for(UupmTreeNodeModel model : result) {
			System.out.println(model.getNodeCode() + ":" + model.getNodeName() + ":" + model.getParentNodeCode() + ":" + model.getParentNodeName());
		}
	}
	
}
