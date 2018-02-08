package com.yffd.easy.workflow.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yffd.easy.common.core.util.ValidUtils;
import com.yffd.easy.framework.web.domain.EasyRespModel;
import com.yffd.easy.workflow.model.dto.WfBaseDTO;
import com.yffd.easy.workflow.model.dto.WfDefinitionDTO;
import com.yffd.easy.workflow.service.WfModelerService;
import com.yffd.easy.workflow.service.WfQueryService;
import com.yffd.easy.workflow.service.WfTraceService;

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
public class WfModelerController extends WfBaseController {
	@Autowired
	private WfModelerService wfModelerService;
	@Autowired
	private WfQueryService wfQueryService;
	@Autowired
	private WfTraceService wfTraceService;
    
	@RequestMapping(value="/loadResource", method=RequestMethod.GET)
	public EasyRespModel loadResource(@RequestParam Map<String, Object> mapVO, HttpServletResponse response) throws IOException {
		String definitionId = (String) mapVO.get("definitionId");
		String resourceType = (String) mapVO.get("resourceType");
		if(ValidUtils.isEmpty(definitionId) || ValidUtils.isEmpty(definitionId)) {
			return this.errorAjax("参数无效");
		}
		WfDefinitionDTO definition = this.wfQueryService.findDeployByPK(definitionId);
		if(null==definition) return this.errorAjax("未搜寻到有效数据");
		String resourceName = "";
		if("image".equals(resourceType)) {
			resourceName = definition.getDgrmResourceName();
		} else if("xml".equals(resourceType)) {
			resourceName = definition.getResourceName();
		}
		InputStream inputStream = this.wfModelerService.loadResource(definition.getDeploymentId(), resourceName);
		if(null!=inputStream) {
			byte[] b = new byte[1024];
	        int len = -1;
	        while ((len = inputStream.read(b, 0, 1024)) != -1) {
	            response.getOutputStream().write(b, 0, len);
	        }
	        return null;
		}
		return this.successAjax();
	}
	
	@RequestMapping(value = "/tarceWorkFlowByDiagram")
    public EasyRespModel tarceWorkFlowByDiagram(String instanceId, HttpServletResponse response) throws IOException {
		if(ValidUtils.isEmpty(instanceId)) {
			return this.errorAjax("参数无效");
		}
		InputStream inputStream = this.wfTraceService.traceWorkFlowByDiagram(instanceId);
		if(null!=inputStream) {
			byte[] b = new byte[4096];
	        int len = -1;
	        while ((len = inputStream.read(b, 0, 4096)) != -1) {
	            response.getOutputStream().write(b, 0, len);
	        }
	        return null;
		}
		return this.errorAjax();
	}
	
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
	public EasyRespModel newModel(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	WfBaseDTO dto = new WfBaseDTO();
    	dto.setWfKey("process");
    	dto.setWfName("new-process");
    	dto.setWfDesc("");
    	dto.setWfCategory("default");
		String modelId = this.wfModelerService.addOne(dto);
        response.sendRedirect(request.getContextPath() + "/views/workflow/modeler/modeler.html?modelId="+modelId);
        return this.successAjax();
	}
}

