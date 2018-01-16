package com.yffd.easy.workflow.service.impl;

import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yffd.easy.workflow.activiti.service.ActivitiBaseService;
import com.yffd.easy.workflow.model.dto.WorkFlowModelDefinitionDTO;
import com.yffd.easy.workflow.service.WorkFlowModelService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年1月16日 下午1:56:58 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("workFlowModelService")
public class WorkFlowModelServiceImpl extends ActivitiBaseService implements WorkFlowModelService {

	@Override
	public WorkFlowModelDefinitionDTO addOne(WorkFlowModelDefinitionDTO dto) {
		Model modelData = this.getRepositoryService().newModel();
		modelData.setKey(StringUtils.defaultString(dto.getWorkFlowKey()));
		modelData.setName(dto.getWorkFlowCategoryName());
		modelData.setCategory(dto.getWorkFlowCategoryCode());
		
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode modelObjectNode = objectMapper.createObjectNode();
		modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, dto.getWorkFlowCategoryName());
        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, StringUtils.defaultString(dto.getWorkFlowDesc()));
		modelData.setMetaInfo(modelObjectNode.toString());
		
		this.getRepositoryService().saveModel(modelData);
//		this.getRepositoryService().addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
		return null;
	}

}

