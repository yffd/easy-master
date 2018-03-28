package com.yffd.easy.uupm.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yffd.easy.framework.domain.LoginInfo;
import com.yffd.easy.uupm.api.model.UupmSysTreeModel;
import com.yffd.easy.uupm.base.UupmBaseServiceTest;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年03月19日 13时57分51秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class UupmSysTreeServiceTest extends UupmBaseServiceTest {

	@Autowired
	private UupmSysTreeService uupmSysTreeService;
	
	private LoginInfo loginInfo = null;
	
	{
		loginInfo = new LoginInfo();
		loginInfo.setUserCode("tester");
		loginInfo.setUserName("测试");
	}
	
	@Test
	public void addRootNodeTest() {
		UupmSysTreeModel node = new UupmSysTreeModel();
		node.setNodeCode("b");
		this.uupmSysTreeService.addRootNode(node);

	}
	
	@Test
	public void addNodeTest() {
		UupmSysTreeModel node = new UupmSysTreeModel();
		node.setNodeCode("b111");
		this.uupmSysTreeService.addNode("b11", node);
	}
	
	@Test
	public void countLayerTest() {
		int result = this.uupmSysTreeService.countLayer("b11");
		System.out.println(result);
	}
	
	
	@Test
	public void findParentPathTest() {
		List<UupmSysTreeModel> resultList = this.uupmSysTreeService.findParentNodeListBy("b12");
		for(UupmSysTreeModel model : resultList) {
			System.out.println(model.getNodeCode());
		}
		
//		String result = this.uupmSysTreeService.findParentPath("b12");
//		System.out.println(result);
	}
	
	@Test
	public void findChildrenNodeListByTest() {
		List<UupmSysTreeModel> resultList = this.uupmSysTreeService.findChildrenNodeListBy("b1");
		for(UupmSysTreeModel model : resultList) {
			System.out.println(model.getNodeCode());
		}
	}
	
	
}
