package com.yffd.easy.workflow.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.image.ProcessDiagramGenerator;
import org.springframework.stereotype.Service;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年1月17日 下午4:45:27 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class WfTraceService extends WfCommonService {

	public InputStream traceWorkFlowByDiagram(String instanceId) {
		// 获取历史流程实例
		HistoricProcessInstance historicProcessInstance = this.getHistoryService().createHistoricProcessInstanceQuery()
				.processInstanceId(instanceId).singleResult();
		if(null!=historicProcessInstance) {
			// 获取流程历史中已执行节点，并按照节点在流程中执行先后顺序排序
            List<HistoricActivityInstance> historicActivityInstanceList = this.getHistoryService().createHistoricActivityInstanceQuery()
                    .processInstanceId(instanceId).orderByHistoricActivityInstanceId().asc().list();
            // 已执行的节点ID集合
            List<String> executedActivityIdList = new ArrayList<String>();
            for(HistoricActivityInstance activityInstance : historicActivityInstanceList) {
                executedActivityIdList.add(activityInstance.getActivityId());  // 获取已经执行的节点ID              
            }
            // 获取流程定义
            ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity)this.getRepositoryService()
            		.getProcessDefinition(historicProcessInstance.getProcessDefinitionId());
            // 已执行的线集合
            List<String> flowIds = new ArrayList<String>();
            flowIds = this.getHighLightedFlows(definitionEntity, historicActivityInstanceList);	// 获取流程走过的线
            // 获取流程图
    		BpmnModel bpmnModel = this.getRepositoryService().getBpmnModel(historicProcessInstance.getProcessDefinitionId());
    		// 获取流程图图像字符流
            ProcessDiagramGenerator pdg = this.getProcessEngine().getProcessEngineConfiguration().getProcessDiagramGenerator();
            // 配置字体
            InputStream imageStream = pdg.generateDiagram(bpmnModel, "png", executedActivityIdList, flowIds, "宋体", "微软雅黑", "黑体", null, 2.0);
            return imageStream;
		}
		return null;
	}
	
	/**
	 * 获取需要高亮的线
	 * @Date	2017年12月25日 下午1:57:54 <br/>
	 * @author  zhangST
	 * @param processDefinitionEntity
	 * @param historicActivityInstances
	 * @return
	 */
	private List<String> getHighLightedFlows(ProcessDefinitionEntity processDefinitionEntity, List<HistoricActivityInstance> historicActivityInstances) {
		List<String> highFlows = new ArrayList<String>();	// 用以保存高亮的线flowId
		List<ActivityImpl> historyActivityList = new ArrayList<ActivityImpl>();
		for(int i=0;i<historicActivityInstances.size();i++) {	// 对历史流程节点进行遍历
			ActivityImpl activityImpl = processDefinitionEntity.findActivity(historicActivityInstances.get(i)
                            .getActivityId());
			historyActivityList.add(activityImpl);
        }
		for(ActivityImpl activityImpl : historyActivityList) {
			List<PvmTransition> pvmTransitions = activityImpl.getOutgoingTransitions();	// 取出节点的所有出去的线
	        for(PvmTransition pvmTransition : pvmTransitions) {
	            // 对所有的线进行遍历
	            ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition.getDestination();
	            // 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
	            if(historyActivityList.contains(pvmActivityImpl)) {
	                highFlows.add(pvmTransition.getId());
	            }
	        }
		}

		return highFlows;
	}
}

