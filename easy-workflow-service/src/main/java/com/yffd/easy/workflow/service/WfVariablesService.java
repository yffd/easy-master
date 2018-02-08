package com.yffd.easy.workflow.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.history.HistoricVariableInstance;
import org.springframework.stereotype.Service;

import com.yffd.easy.workflow.model.dto.WfVariableDTO;

/**
 * @Description  流程变量相关.
 * @Date		 2018年1月17日 下午3:03:40 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class WfVariablesService extends WfCommonService {
	
	/**
	 * 根据任务ID，查询与该任务节点相关的变量
	 * @Date	2018年1月17日 下午3:12:12 <br/>
	 * @author  zhangST
	 * @param taskId
	 * @return
	 */
	public List<WfVariableDTO> findTaskVariables(String taskId) {
		Map<String, Object> params = this.getTaskService().getVariables(taskId);
		Map<String, Object> localParams = this.getTaskService().getVariablesLocal(taskId);
		List<WfVariableDTO> ret = new ArrayList<WfVariableDTO>();
		if(null!=params && params.size()>0) {
			Set<String> keys = params.keySet();
			for(String key : keys) {
				WfVariableDTO dto = new WfVariableDTO();
				dto.setVariableName(key);
				dto.setValue(params.get(key));
				dto.setLocal(false);
				ret.add(dto);
			}
		}
		if(null!=localParams && localParams.size()>0) {
			Set<String> keys = localParams.keySet();
			for(String key : keys) {
				WfVariableDTO dto = new WfVariableDTO();
				dto.setVariableName(key);
				dto.setValue(localParams.get(key));
				dto.setLocal(true);
				ret.add(dto);
			}
		}
		return ret;
	}
	
	/**
	 * 根据历史流程实例ID，查询与该历史流程实例相关的变量
	 * @Date	2018年1月17日 下午3:08:36 <br/>
	 * @author  zhangST
	 * @param instanceId
	 * @return
	 */
	public List<WfVariableDTO> findHistoryTaskVariables(String instanceId) {
		List<WfVariableDTO> ret = new ArrayList<WfVariableDTO>();
		List<HistoricVariableInstance> list = this.getHistoryService().createHistoricVariableInstanceQuery().processInstanceId(instanceId).list();
		for(HistoricVariableInstance historicVariableInstance : list) {
			WfVariableDTO dto = new WfVariableDTO();
			dto.setVariableName(historicVariableInstance.getVariableName());
			dto.setValue(historicVariableInstance.getValue());
			dto.setVariableTypeName(historicVariableInstance.getVariableTypeName());
			dto.setCreateTime(historicVariableInstance.getCreateTime());
			dto.setLastUpdatedTime(historicVariableInstance.getLastUpdatedTime());
			dto.setTaskId(historicVariableInstance.getTaskId());
			dto.setInstanceId(historicVariableInstance.getProcessInstanceId());
			if(null==historicVariableInstance.getTaskId()) {
				dto.setLocal(false);
			} else {
				dto.setLocal(true);
			}
			ret.add(dto);
		}
		return ret;
	}
	
	/**
	 * 设置任务变量
	 * @Date	2018年1月17日 下午3:41:15 <br/>
	 * @author  zhangST
	 * @param taskId
	 * @param paramName
	 * @param value
	 * @param isLocal		true:本地范围作用域，即只有当前任务节点可见，false:实例范围作用域，即所有节点可见.
	 */
	public void setParam(String taskId, String paramName, Object value, boolean isLocal) {
		this.getTaskService().setVariable(taskId, paramName, value);
	}
	
	/**
	 * 设置任务变量
	 * @Date	2018年1月17日 下午3:41:37 <br/>
	 * @author  zhangST
	 * @param taskId
	 * @param variables
	 * @param isLocal		true:本地范围作用域，即只有当前任务节点可见，false:实例范围作用域，即所有节点可见.
	 */
	public void setParams(String taskId, Map<String, ? extends Object> variables, boolean isLocal) {
		this.getTaskService().setVariables(taskId, variables);
	}
}

