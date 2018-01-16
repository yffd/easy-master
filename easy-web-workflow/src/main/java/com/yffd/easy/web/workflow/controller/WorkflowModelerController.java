package com.yffd.easy.web.workflow.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yffd.easy.common.core.model.RespModel;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年1月15日 下午3:06:57 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RestController
@RequestMapping("/workflow/modeler")
public class WorkflowModelerController extends WorkflowBaseController {
	@Autowired
    RepositoryService repositoryService;
    
    /**
     * 新建一个空模型
     * @Date	2018年1月15日 下午5:17:53 <br/>
     * @author  zhangST
     * @param paramMap
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/newModel", method=RequestMethod.GET)
	public RespModel newModel(@RequestParam Map<String, Object> paramMap, HttpServletResponse response) throws IOException {
		//初始化一个空模型
        Model model = repositoryService.newModel();
        //设置一些默认信息
        String name = "new-process";
        String description = "";
        int revision = 1;
        String key = "process";
        ObjectNode modelNode = new ObjectMapper().createObjectNode();
        modelNode.put(ModelDataJsonConstants.MODEL_NAME, name);
        modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
        modelNode.put(ModelDataJsonConstants.MODEL_REVISION, revision);

        model.setName(name);
        model.setKey(key);
        model.setMetaInfo(modelNode.toString());
        this.repositoryService.saveModel(model);
        
        String id = model.getId();

        //完善ModelEditorSource
        ObjectNode editorNode = new ObjectMapper().createObjectNode();
        editorNode.put("id", "canvas");
        editorNode.put("resourceId", "canvas");
        ObjectNode stencilSetNode = new ObjectMapper().createObjectNode();
        stencilSetNode.put("namespace",
                "http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.put("stencilset", stencilSetNode);
        this.repositoryService.addModelEditorSource(id, editorNode.toString().getBytes("utf-8"));
        response.sendRedirect("/views/workflow/modeler/modeler.html?modelId="+id);
        return this.successAjax();
	}
}

