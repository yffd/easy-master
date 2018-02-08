package com.yffd.easy.workflow.web.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.common.core.util.EasyDateUtils;
import com.yffd.easy.common.core.view.vo.DataGridVO;
import com.yffd.easy.framework.web.domain.EasyRespModel;
import com.yffd.easy.workflow.model.dto.WfDefinitionDTO;
import com.yffd.easy.workflow.model.dto.WfHistoryInstanceDTO;
import com.yffd.easy.workflow.model.dto.WfHistoryTaskDTO;
import com.yffd.easy.workflow.model.dto.WfInstanceDTO;
import com.yffd.easy.workflow.model.dto.WfTaskDTO;
import com.yffd.easy.workflow.service.WfQueryService;

/**
 * @Description  流程查询模块.
 * @Date		 2018年1月17日 上午11:21:07 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RestController
@RequestMapping("/workflow/query")
public class WfQueryController extends WfBaseController {
	@Autowired
	private WfQueryService wfQueryService;
	
	@RequestMapping(value="/findDefinitionListPage", method=RequestMethod.POST)
	public EasyRespModel findDefinitionListPage(@RequestParam Map<String, Object> paramMap) {
		PageParam pageParam = this.getPageParam(paramMap);
		WfDefinitionDTO dto = this.map2model(paramMap, WfDefinitionDTO.class, null);
		String lastVersion = (String) paramMap.get("versionState");
		if(null==lastVersion || "".equals(lastVersion)) { // 默认
			if(null==dto) dto = new WfDefinitionDTO();
			dto.setLastVersion(true);
		} else if(null!=lastVersion && "last".equals(lastVersion)) {
			if(null==dto) dto = new WfDefinitionDTO();
			dto.setLastVersion(true);
		} else if(null!=lastVersion && "all".equals(lastVersion)) {
			if(null==dto) dto = new WfDefinitionDTO();
			dto.setLastVersion(false);
		}
		PageResult<WfDefinitionDTO> pageResult = this.wfQueryService.findDefinitionListPage(dto, pageParam);
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value="/findInstanceListPage", method=RequestMethod.POST)
	public EasyRespModel findInstanceListPage(@RequestParam Map<String, Object> paramMap) {
		PageParam pageParam = this.getPageParam(paramMap);
		WfInstanceDTO dto = this.map2model(paramMap, WfInstanceDTO.class, null);
		PageResult<WfInstanceDTO> pageResult = this.wfQueryService.findInstanceListPage(dto, pageParam);
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value="/findTaskListPage", method=RequestMethod.POST)
	public EasyRespModel findTaskListPage(@RequestParam Map<String, Object> paramMap) {
		PageParam pageParam = this.getPageParam(paramMap);
		WfTaskDTO dto = this.map2model(paramMap, WfTaskDTO.class, null);
		PageResult<WfTaskDTO> pageResult = this.wfQueryService.findTaskListPage(dto, pageParam);
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value="/findHistoryInstanceListPage", method=RequestMethod.POST)
	public EasyRespModel findHistoryInstanceListPage(@RequestParam Map<String, Object> paramMap) {
		PageParam pageParam = this.getPageParam(paramMap);
		WfHistoryInstanceDTO dto = this.map2model(paramMap, WfHistoryInstanceDTO.class, null);
		PageResult<WfHistoryInstanceDTO> pageResult = this.wfQueryService.findHistoryInstanceListPage(dto, pageParam);
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value="/findHistoryTaskListPage", method=RequestMethod.POST)
	public EasyRespModel findHistoryTaskListPage(@RequestParam Map<String, Object> paramMap) {
		PageParam pageParam = this.getPageParam(paramMap);
		WfHistoryTaskDTO dto = this.map2model(paramMap, WfHistoryTaskDTO.class, null);
		PageResult<WfHistoryTaskDTO> pageResult = this.wfQueryService.findHistoryTaskListPage(dto, pageParam);
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value="/findTodoTaskListPage", method=RequestMethod.POST)
	public EasyRespModel findTodoTaskListPage(@RequestParam Map<String, Object> paramMap) {
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
//		if(userIds.size()==0 && roleIds.size()==0) {
//			return this.successAjax("参数无效");
//		}
		PageParam pageParam = this.getPageParam(paramMap);
		WfTaskDTO dto = this.map2model(paramMap, WfTaskDTO.class, null);
		PageResult<WfTaskDTO> pageResult = this.wfQueryService.findTodoTaskListPage(userIds, roleIds, dto, pageParam);
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
}

