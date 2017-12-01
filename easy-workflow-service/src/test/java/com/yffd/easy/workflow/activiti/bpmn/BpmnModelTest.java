package com.yffd.easy.workflow.activiti.bpmn;

import java.io.InputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.junit.Test;

import com.yffd.easy.workflow.activiti.test.AbstractActivitiTest;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月23日 上午11:41:02 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class BpmnModelTest extends AbstractActivitiTest {
	 
	@Test
	public void xmlToBpmnModelTest() throws XMLStreamException {
		RepositoryService repositoryService = this.repositoryService;
		ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
		ProcessDefinition processDefinition = query.deploymentId("37501").singleResult();
		
		String resourceName = processDefinition.getResourceName();
		
		InputStream inputStream = repositoryService.getResourceAsStream("37501", resourceName);
		
		BpmnXMLConverter converter = new BpmnXMLConverter();
		
		XMLInputFactory factory = XMLInputFactory.newFactory();
		XMLStreamReader reader = factory.createXMLStreamReader(inputStream);
		
		BpmnModel bpmnModel = converter.convertToBpmnModel(reader);
		
		org.activiti.bpmn.model.Process process = bpmnModel.getMainProcess();
		
		System.out.println(process.getId());
	}
	
	@Test
	public void bpmnModelToXmlTest() throws XMLStreamException {
		RepositoryService repositoryService = this.repositoryService;
		ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
		ProcessDefinition processDefinition = query.deploymentId("37501").singleResult();
		
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinition.getId());
		
		BpmnXMLConverter converter = new BpmnXMLConverter();
		
		byte[] bytes = converter.convertToXML(bpmnModel);
		String xmlContent = new String(bytes);
		System.out.println(xmlContent);
	}
}

