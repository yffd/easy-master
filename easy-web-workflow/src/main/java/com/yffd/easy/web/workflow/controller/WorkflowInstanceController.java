package com.yffd.easy.web.workflow.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yffd.easy.common.core.model.RespModel;
import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.common.core.util.ValidUtils;
import com.yffd.easy.common.core.view.vo.DataGridVO;
import com.yffd.easy.workflow.model.dto.WorkFlowInstanceDTO;
import com.yffd.easy.workflow.service.WorkFlowInstanceService;
import com.yffd.easy.workflow.service.WorkFlowTraceService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月21日 下午1:47:50 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Controller
@RequestMapping("/workflow/instance")
public class WorkflowInstanceController extends WorkflowBaseController {
	
	@Autowired
	private WorkFlowInstanceService workFlowInstanceService;
	@Autowired
	private WorkFlowTraceService workFlowTraceService;
	
	@RequestMapping(value="/listPage", method=RequestMethod.POST)
	@ResponseBody
	public RespModel listPage(@RequestParam Map<String, Object> paramMap) {
		PageParam pageParam = this.getPageParam(paramMap);
		WorkFlowInstanceDTO dto = this.map2model(paramMap, WorkFlowInstanceDTO.class, null);
		PageResult<WorkFlowInstanceDTO> pageResult = this.workFlowInstanceService.findListPage(dto, pageParam);
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value = "/update/{state}/{instanceId}")
	@ResponseBody
    public RespModel updateState(@PathVariable("state") String state, @PathVariable("instanceId") String instanceId) {
		if(ValidUtils.isEmpty(state) || ValidUtils.isEmpty(instanceId)) {
			return this.errorAjax("参数无效");
		}
		if(state.equals("active")) {
			int result = this.workFlowInstanceService.activateInstance(instanceId);
			String message = "流程实例已激活，ID：" + instanceId;
			if(result==0) {
			} else if(result==1) {
				message = "流程实例不存在，ID：" + instanceId;
				return this.errorAjax(message);
			}
			return this.successAjax(message);
        } else if(state.equals("suspend")) {
        	int result = this.workFlowInstanceService.suspendInstance(instanceId);
        	String message = "流程实例已挂起，ID：" + instanceId;
			if(result==0) {
			} else if(result==1) {
				message = "流程实例不存在，ID：" + instanceId;
				return this.errorAjax(message);
			} else if(result==2) {
			}
        	return this.successAjax(message);
        }
		return this.errorAjax("参数不匹配，更新失败。");
	}
	
	@RequestMapping(value = "/tarceWorkFlowByDiagram")
	@ResponseBody
    public RespModel tarceWorkFlowByDiagram(String instanceId, HttpServletResponse response) throws IOException {
		if(ValidUtils.isEmpty(instanceId)) {
			return this.errorAjax("参数无效");
		}
		InputStream inputStream = this.workFlowTraceService.traceWorkFlowByDiagram(instanceId);
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
}

