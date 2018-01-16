package com.yffd.easy.web.workflow.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yffd.easy.common.core.model.RespModel;
import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.common.core.view.vo.DataGridVO;
import com.yffd.easy.workflow.model.dto.WorkFlowHistoryInstanceDTO;
import com.yffd.easy.workflow.model.dto.WorkFlowInstanceDTO;
import com.yffd.easy.workflow.model.dto.WorkFlowVariableDTO;
import com.yffd.easy.workflow.service.WorkFlowHistoryInstanceService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月21日 下午1:47:50 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Controller
@RequestMapping("/workflow/historyInstance")
public class WorkflowHistroryInstanceController extends WorkflowBaseController {
	
	@Autowired
	private WorkFlowHistoryInstanceService workFlowHistoryInstanceService;
 	
	@RequestMapping(value="/listPage", method=RequestMethod.POST)
	@ResponseBody
	public RespModel listPage(@RequestParam Map<String, Object> paramMap) {
		PageParam pageParam = this.getPageParam(paramMap);
		WorkFlowInstanceDTO dto = this.map2model(paramMap, WorkFlowInstanceDTO.class, null);
		PageResult<WorkFlowHistoryInstanceDTO> pageResult = this.workFlowHistoryInstanceService.findListPage(dto, pageParam);
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value="/findVariables", method=RequestMethod.POST)
	@ResponseBody
	public RespModel findVariables(@RequestParam String instanceId) {
		if(null==instanceId || "".equals(instanceId)) return this.errorAjax("参数无效");
		List<WorkFlowVariableDTO> result = this.workFlowHistoryInstanceService.findVariables(instanceId);
		DataGridVO dataGridVO = this.toDataGrid(result);
		return this.successAjax(dataGridVO);
	}
	
}

