package com.yffd.easy.workflow.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricVariableInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.activiti.service.ActivitiBaseService;
import com.yffd.easy.workflow.dao.WorkFlowHistoryInstanceDao;
import com.yffd.easy.workflow.model.dto.WorkFlowHistoryInstanceDTO;
import com.yffd.easy.workflow.model.dto.WorkFlowInstanceDTO;
import com.yffd.easy.workflow.model.dto.WorkFlowVariableDTO;
import com.yffd.easy.workflow.service.WorkFlowHistoryInstanceService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年1月5日 上午9:40:11 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("workFlowHistoryInstanceService")
public class WorkFlowHistoryInstanceServiceImpl extends ActivitiBaseService implements WorkFlowHistoryInstanceService {

	@Autowired
	private WorkFlowHistoryInstanceDao workFlowHistoryInstanceDao;
	
	@Override
	public PageResult<WorkFlowHistoryInstanceDTO> findListPage(WorkFlowInstanceDTO model, PageParam pageParam) {
		Map<String, Object> paramMap = this.model2map(model, null);
		return this.workFlowHistoryInstanceDao.selectPageBy(paramMap, pageParam);
	}

	@Override
	public List<WorkFlowVariableDTO> findVariables(String instanceId) {
		List<WorkFlowVariableDTO> ret = new ArrayList<WorkFlowVariableDTO>();
		List<HistoricVariableInstance> list = this.getHistoryService().createHistoricVariableInstanceQuery().processInstanceId(instanceId).list();
		for(HistoricVariableInstance historicVariableInstance : list) {
			WorkFlowVariableDTO dto = new WorkFlowVariableDTO();
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
	

}

