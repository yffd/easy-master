package com.yffd.easy.workflow.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yffd.easy.common.core.util.ValidUtils;
import com.yffd.easy.framework.web.domain.EasyRespModel;
import com.yffd.easy.workflow.service.WfStateService;

/**
 * @Description  流程活动图状态.
 * @Date		 2018年1月15日 下午3:06:57 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RestController
@RequestMapping("/workflow/activityState")
public class WfActivityStateController extends WfBaseController {
	@Autowired
	private WfStateService wfStateService;
    
	@RequestMapping(value = "/updateDefinition/{state}/{definitionId}")
    public EasyRespModel updateDefinitionState(@PathVariable("state") String state, @PathVariable("definitionId") String definitionId) {
		if(ValidUtils.isEmpty(state) || ValidUtils.isEmpty(definitionId)) {
			return this.errorAjax("参数无效");
		}
		if(state.equals("active")) {
			int result = this.wfStateService.activateDefinition(definitionId);
			String message = "流程定义已激活，ID：" + definitionId;
			if(result==0) {
			} else if(result==1) {
				message = "流程定义不存在，ID：" + definitionId;
				return this.errorAjax(message);
			}
			return this.successAjax(message);
        } else if(state.equals("suspend")) {
        	int result = this.wfStateService.suspendDefinition(definitionId);
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
	
	@RequestMapping(value = "/updateInstance/{state}/{instanceId}")
    public EasyRespModel updateState(@PathVariable("state") String state, @PathVariable("instanceId") String instanceId) {
		if(ValidUtils.isEmpty(state) || ValidUtils.isEmpty(instanceId)) {
			return this.errorAjax("参数无效");
		}
		if(state.equals("active")) {
			int result = this.wfStateService.activateInstance(instanceId);
			String message = "流程实例已激活，ID：" + instanceId;
			if(result==0) {
			} else if(result==1) {
				message = "流程实例不存在，ID：" + instanceId;
				return this.errorAjax(message);
			}
			return this.successAjax(message);
        } else if(state.equals("suspend")) {
        	int result = this.wfStateService.suspendInstance(instanceId);
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
}

