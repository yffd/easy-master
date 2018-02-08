package com.yffd.easy.workflow.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yffd.easy.common.core.view.vo.DataGridVO;
import com.yffd.easy.framework.web.domain.EasyRespModel;
import com.yffd.easy.workflow.model.dto.WfTaskCandidatorDTO;
import com.yffd.easy.workflow.model.dto.WfVariableDTO;
import com.yffd.easy.workflow.service.WfCandidatorsService;
import com.yffd.easy.workflow.service.WfVariablesService;

/**
 * @Description  流程历史活动节点.
 * @Date		 2018年1月17日 上午11:21:07 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RestController
@RequestMapping("/workflow/historyActivityNode")
public class WfHistoryActivityNodeController extends WfBaseController {
	@Autowired
	private WfCandidatorsService wfCandidatorsService;
	@Autowired
	private WfVariablesService wfVariablesService;
	
	@RequestMapping(value="/findVariables", method=RequestMethod.POST)
	public EasyRespModel findVariables(@RequestParam String instanceId) {
		if(null==instanceId && "".equals(instanceId)) return this.errorAjax("参数无效");
		List<WfVariableDTO> result = this.wfVariablesService.findHistoryTaskVariables(instanceId);
		DataGridVO dataGridVO = this.toDataGrid(result);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value="/findCandidators", method=RequestMethod.POST)
	public EasyRespModel findCandidators(@RequestParam String taskId) {
		if(null==taskId && "".equals(taskId)) return this.errorAjax("参数无效");
		WfTaskCandidatorDTO result = this.wfCandidatorsService.findHistoryTaskCandidators(taskId);
		return this.successAjax(result);
	}
}

