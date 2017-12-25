package com.yffd.easy.workflow.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.image.ProcessDiagramGenerator;
import org.springframework.stereotype.Service;

import com.yffd.easy.workflow.activiti.service.ActivitiBaseService;
import com.yffd.easy.workflow.service.WorkFlowTraceService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月21日 下午3:40:51 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("workFlowTraceService")
public class WorkFlowTraceServiceImpl extends ActivitiBaseService implements WorkFlowTraceService {

	@Override
	public InputStream tarceWorkFlowByDiagram(String instanceId) {
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
		for(int i=0;i<historicActivityInstances.size()-1;i++) {	// 对历史流程节点进行遍历
			List<ActivityImpl> historyActivityList = new ArrayList<ActivityImpl>();	// 用以保存后续开始时间相同的节点
			ActivityImpl sameActivityImpl = processDefinitionEntity.findActivity(historicActivityInstances.get(i + 1)
                            .getActivityId());
			historyActivityList.add(sameActivityImpl);
			
            ActivityImpl activityImpl = processDefinitionEntity.findActivity(historicActivityInstances.get(i)
            		.getActivityId());	// 得到节点定义的详细信息
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

