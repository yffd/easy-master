package com.yffd.easy.web.workflow.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yffd.easy.common.core.model.RespModel;
import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.common.core.util.EasyDateUtils;
import com.yffd.easy.common.core.view.vo.DataGridVO;
import com.yffd.easy.workflow.model.dto.WorkFlowTaskCandidatorDTO;
import com.yffd.easy.workflow.model.dto.WorkFlowTaskDTO;
import com.yffd.easy.workflow.model.dto.WorkFlowVariableDTO;
import com.yffd.easy.workflow.service.WorkFlowTaskService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月21日 下午1:47:50 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Controller
@RequestMapping("/workflow/task")
public class WorkflowTaskController extends WorkflowBaseController {
	
	@Autowired
	private WorkFlowTaskService workFlowTaskService;
	
	@RequestMapping(value="/listPage", method=RequestMethod.POST)
	@ResponseBody
	public RespModel listPage(@RequestParam Map<String, Object> paramMap) {
		PageParam pageParam = this.getPageParam(paramMap);
		WorkFlowTaskDTO dto = this.map2model(paramMap, WorkFlowTaskDTO.class, null);
		PageResult<WorkFlowTaskDTO> pageResult = this.workFlowTaskService.findListPage(dto, pageParam);
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value="/findVariables", method=RequestMethod.POST)
	@ResponseBody
	public RespModel findVariables(@RequestParam String taskId) {
		if(null==taskId && "".equals(taskId)) return this.errorAjax("参数无效");
		List<WorkFlowVariableDTO> result = this.workFlowTaskService.findVariables(taskId);
		DataGridVO dataGridVO = this.toDataGrid(result);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value="/findCandidators", method=RequestMethod.POST)
	@ResponseBody
	public RespModel findCandidators(@RequestParam String taskId) {
		if(null==taskId && "".equals(taskId)) return this.errorAjax("参数无效");
		WorkFlowTaskCandidatorDTO result = this.workFlowTaskService.findCandidators(taskId);
		return this.successAjax(result);
	}
	
	@RequestMapping(value="/todoTaskListPage", method=RequestMethod.POST)
	@ResponseBody
	public RespModel todoTaskListPage(@RequestParam Map<String, Object> paramMap) {
		String userCode = (String) paramMap.get("userCode");
		Set<String> userIds = new HashSet<String>();
		if(null!=userCode) {
			String[] userCodes = userCode.split(",");
			userIds.addAll(Arrays.asList(userCodes));
		}
		String roleCode = (String) paramMap.get("roleCode");
		Set<String> roleIds = new HashSet<String>();
		if(null!=roleCode) {
			String[] roleCodes = roleCode.split(",");
			roleIds.addAll(Arrays.asList(roleCodes));
		}
		if(userIds.size()==0 && roleIds.size()==0) {
			return this.successAjax("参数无效");
		}
		String workFlowCategoryCode = (String) paramMap.get("workFlowCategoryCode");
		String workFlowCategoryName = (String) paramMap.get("workFlowCategoryName");
		String workFlowKey = (String) paramMap.get("workFlowKey");
		Date startTime = null;
		String startTimeStr = (String) paramMap.get("searchStartTime");
		if(null!=startTimeStr && !"".equals(startTimeStr)) {
			startTime = EasyDateUtils.parseToDate(startTimeStr);
		}
		Date endTime = null;
		String endTimeStr = (String) paramMap.get("searchEndTime");
		if(null!=endTimeStr && !"".equals(endTimeStr)) {
			endTime = EasyDateUtils.parseToDate(endTimeStr);
		}
		PageParam pageParam = this.getPageParam(paramMap);
		PageResult<WorkFlowTaskDTO> pageResult = this.workFlowTaskService.findTodoTaskListPageBy(userIds, roleIds, 
				workFlowCategoryCode, workFlowCategoryName, workFlowKey, startTime, endTime, pageParam);
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
	
}

