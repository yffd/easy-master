package com.yffd.easy.uupm.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yffd.easy.uupm.api.model.UupmTreeDictionaryModel;
import com.yffd.easy.uupm.base.UupmBaseServiceTest;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年04月02日 10时30分38秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class UupmTreeDictionaryServiceTest extends UupmBaseServiceTest {

	@Autowired
	private UupmTreeDictionaryService uupmTreeDictionaryService;
	
	@Test
	public void addRootNodeTest() {
		UupmTreeDictionaryModel paramModel = new UupmTreeDictionaryModel();
		paramModel.setTenantCode("default");
		paramModel.setNodeLabel("combo");
		paramModel.setNodeCode("combo");
		paramModel.setNodeName("控件");
		int result = this.uupmTreeDictionaryService.addRootNode(paramModel, null);
		System.out.println(result);
		System.out.println(paramModel.getId());
	}
	
	@Test
	public void addChildNodeTest() {
		UupmTreeDictionaryModel paramModel = new UupmTreeDictionaryModel();
		paramModel.setTenantCode("default");
		paramModel.setNodeLabel("combo");
		paramModel.setNodeCode("combo");
		paramModel.setNodeName("控件");
		
		UupmTreeDictionaryModel paramModel1 = new UupmTreeDictionaryModel();
		paramModel1.setTenantCode("default");
		paramModel1.setNodeLabel("combo");
		paramModel1.setNodeCode("active-status");
		paramModel1.setNodeName("功能状态");
		
		UupmTreeDictionaryModel paramModel2 = new UupmTreeDictionaryModel();
		paramModel2.setTenantCode("default");
		paramModel2.setNodeLabel("combo");
		paramModel2.setNodeCode("rs-type");
		paramModel2.setNodeName("资源类型");
		
		UupmTreeDictionaryModel paramModel11 = new UupmTreeDictionaryModel();
		paramModel11.setTenantCode("default");
		paramModel11.setNodeLabel("combo");
		paramModel11.setNodeCode("A");
		paramModel11.setNodeName("有效");
		UupmTreeDictionaryModel paramModel12 = new UupmTreeDictionaryModel();
		paramModel12.setTenantCode("default");
		paramModel12.setNodeLabel("combo");
		paramModel12.setNodeCode("I");
		paramModel12.setNodeName("无效");
		
		UupmTreeDictionaryModel paramModel21 = new UupmTreeDictionaryModel();
		paramModel21.setTenantCode("default");
		paramModel21.setNodeLabel("combo");
		paramModel21.setNodeCode("M");
		paramModel21.setNodeName("菜单");
		UupmTreeDictionaryModel paramModel22 = new UupmTreeDictionaryModel();
		paramModel22.setTenantCode("default");
		paramModel22.setNodeLabel("combo");
		paramModel22.setNodeCode("O");
		paramModel22.setNodeName("操作");
		UupmTreeDictionaryModel paramModel23 = new UupmTreeDictionaryModel();
		paramModel23.setTenantCode("default");
		paramModel23.setNodeLabel("combo");
		paramModel23.setNodeCode("A");
		paramModel23.setNodeName("应用");
		
		String nodeLabel = "combo";
		String parentNodeCode = "rs-type";
		int result = this.uupmTreeDictionaryService.addChildNode(nodeLabel, parentNodeCode, paramModel23, null);
		System.out.println(result);
		System.out.println(paramModel.getId());
	}
	
}
