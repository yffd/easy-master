package com.yffd.easy.web.workflow.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yffd.easy.common.core.model.RespModel;
import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.common.core.util.ValidUtils;
import com.yffd.easy.common.core.view.vo.DataGridVO;
import com.yffd.easy.workflow.model.dto.WorkFlowDefinitionDTO;
import com.yffd.easy.workflow.service.WorkFlowDefinitionService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月21日 下午1:47:50 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Controller
@RequestMapping("/workflow/definition")
public class WorkflowDefinitionController extends WorkflowBaseController {
	private static final Logger LOG = LoggerFactory.getLogger(WorkflowDefinitionController.class);
	
	@Autowired
	private WorkFlowDefinitionService workFlowDefinitionService;
	
	@RequestMapping(value="/listPage", method=RequestMethod.POST)
	@ResponseBody
	public RespModel listPage(@RequestParam Map<String, Object> paramMap) {
		PageParam pageParam = this.getPageParam(paramMap);
		WorkFlowDefinitionDTO dto = this.map2model(paramMap, WorkFlowDefinitionDTO.class, null);
		String lastVersion = (String) paramMap.get("versionState");
		if(null==lastVersion || "".equals(lastVersion)) { // 默认
			if(null==dto) dto = new WorkFlowDefinitionDTO();
			dto.setLastVersion(true);
		} else if(null!=lastVersion && "last".equals(lastVersion)) {
			if(null==dto) dto = new WorkFlowDefinitionDTO();
			dto.setLastVersion(true);
		} else if(null!=lastVersion && "all".equals(lastVersion)) {
			if(null==dto) dto = new WorkFlowDefinitionDTO();
			dto.setLastVersion(false);
		}
		PageResult<WorkFlowDefinitionDTO> pageResult = this.workFlowDefinitionService.findListPage(dto, pageParam);
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value="/loadResource", method=RequestMethod.GET)
	@ResponseBody
	public RespModel loadResource(@RequestParam Map<String, Object> mapVO, HttpServletResponse response) throws IOException {
		String definitionId = (String) mapVO.get("definitionId");
		String resourceType = (String) mapVO.get("resourceType");
		if(ValidUtils.isEmpty(definitionId) || ValidUtils.isEmpty(definitionId)) {
			return this.errorAjax("参数无效");
		}
		WorkFlowDefinitionDTO definition = this.workFlowDefinitionService.findDefinitionByPK(definitionId);
		if(null==definition) return this.errorAjax("未搜寻到有效数据");
		String resourceName = "";
		if("image".equals(resourceType)) {
			resourceName = definition.getDgrmResourceName();
		} else if("xml".equals(resourceType)) {
			resourceName = definition.getResourceName();
		}
		InputStream inputStream = this.workFlowDefinitionService.loadResource(definition.getDeploymentId(), resourceName);
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
	
	@RequestMapping(value = "/update/{state}/{definitionId}")
	@ResponseBody
    public RespModel updateState(@PathVariable("state") String state, @PathVariable("definitionId") String definitionId) {
		if(ValidUtils.isEmpty(state) || ValidUtils.isEmpty(definitionId)) {
			return this.errorAjax("参数无效");
		}
		if(state.equals("active")) {
			int result = this.workFlowDefinitionService.activateDefinition(definitionId);
			String message = "流程定义已激活，ID：" + definitionId;
			if(result==0) {
			} else if(result==1) {
				message = "流程定义不存在，ID：" + definitionId;
				return this.errorAjax(message);
			}
			return this.successAjax(message);
        } else if(state.equals("suspend")) {
        	int result = this.workFlowDefinitionService.suspendDefinition(definitionId);
        	String message = "流程定义已挂起，ID：" + definitionId;
			if(result==0) {
			} else if(result==1) {
				message = "流程定义不存在，ID：" + definitionId;
				return this.errorAjax(message);
			} else if(result==2) {
			}
        	return this.successAjax(message);
        }
		return this.errorAjax("参数不匹配，更新失败。");
	}
	
	@RequestMapping(value = "/deploy")
	@ResponseBody
    public RespModel deploy(HttpServletRequest request) {
		MultipartHttpServletRequest mul=(MultipartHttpServletRequest)request;
		Map<String,MultipartFile> files=mul.getFileMap();
		for(MultipartFile file : files.values()) {
			String resourceName = file.getOriginalFilename();
			String extension = FilenameUtils.getExtension(resourceName);
			try {
				if(extension.equals("zip") || extension.equals("bar")) {
					InputStream inputStream = file.getInputStream();
					ZipInputStream zipInputStream = new ZipInputStream(inputStream);
					String id = this.workFlowDefinitionService.deployDefinitionByZipInputStream(zipInputStream);
					LOG.info("流程部署成功，流程部署ID：" + id);
				} else if(extension.equals("bpmn") || extension.equals("bpmn20.xml")) {
					InputStream inputStream = file.getInputStream();
					String id = this.workFlowDefinitionService.deployDefinitionByInputStream(resourceName, inputStream);
					LOG.info("流程部署成功，流程部署ID：" + id);
				} else {
					return this.errorAjax("文件类型不支持");
				}
			} catch (IOException e) {
				return this.errorAjax("流程发布失败");
			}
	    }
		return this.successAjax();
	}
}

