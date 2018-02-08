package com.yffd.easy.workflow.activiti.test;

import java.util.Date;
import java.util.List;

import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yffd.easy.workflow.activiti.service.ActivitiServiceManager;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年1月4日 下午3:56:39 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "classpath:spring/workflow-spring-beans.xml"
})
public class ActivitiServiceTest extends ActivitiServiceManager {

	@Test
	public void test() {
//		List<IdentityLink> list = this.getTaskService().getIdentityLinksForTask("37505");
//		System.out.println(list);
		
		//select distinct RES.* , DEF.KEY_ as PROC_DEF_KEY_, DEF.NAME_ as PROC_DEF_NAME_, DEF.VERSION_ as PROC_DEF_VERSION_, DEF.DEPLOYMENT_ID_ as DEPLOYMENT_ID_ from ACT_HI_PROCINST RES left outer join ACT_RE_PROCDEF DEF on RES.PROC_DEF_ID_ = DEF.ID_ WHERE RES.END_TIME_ is not NULL order by RES.ID_ asc LIMIT ? OFFSET ?
//		this.getHistoryService().createHistoricProcessInstanceQuery().finished().list();
//		
//		List<HistoricDetail> list = this.getHistoryService().createHistoricDetailQuery().processInstanceId("2501").list();
//		System.out.println(list);
		
		String instanceId_ = "25001";
		List<HistoricVariableInstance> list = this.getHistoryService().createHistoricVariableInstanceQuery().processInstanceId(instanceId_).list();
		System.out.println(list);
		String name = list.get(0).getVariableName();
		String variableTypeName = list.get(0).getVariableTypeName();
		Object value = list.get(0).getValue();
		Date createTime = list.get(0).getCreateTime();
		Date lastUpdateDTime = list.get(0).getLastUpdatedTime();
		String taskId = list.get(0).getTaskId();
		String instanceId = list.get(0).getProcessInstanceId();
	}
	
	@Test
	public void findTodoTask() {
		
//		select distinct RES.* from ACT_RU_TASK RES WHERE RES.ASSIGNEE_ = 'xiaohei'
		String assignee = "xiaohei";
		List<Task> list1 = this.getTaskService().createTaskQuery().taskAssignee(assignee).list();
		System.out.println("______________________");
		
//		select distinct RES.* from ACT_RU_TASK RES inner join ACT_RU_IDENTITYLINK I on I.TASK_ID_ = RES.ID_ WHERE RES.ASSIGNEE_ is null and I.TYPE_ = 'candidate' and ( I.GROUP_ID_ IN ( 'b1' ) )
		String candidateGroup = "b1";
		List<Task> list2 = this.getTaskService().createTaskQuery().taskCandidateGroup(candidateGroup).list();
		System.out.println("______________________");
		
//		select distinct RES.* from ACT_RU_TASK RES inner join ACT_RU_IDENTITYLINK I on I.TASK_ID_ = RES.ID_ WHERE RES.ASSIGNEE_ is null and I.TYPE_ = 'candidate' and ( I.USER_ID_ = 'hr2' )
		String candidateUser = "hr2";
		List<Task> list3 = this.getTaskService().createTaskQuery().taskCandidateUser(candidateUser).list();
		System.out.println("______________________");
		TaskEntity aa = null;
		aa.getIdentityLinks();
		
//		select distinct RES.* from ACT_RU_TASK RES left join ACT_RU_IDENTITYLINK I on I.TASK_ID_ = RES.ID_ WHERE (RES.ASSIGNEE_ = 'xiaohei' or (RES.ASSIGNEE_ is null and I.TYPE_ = 'candidate' and (I.USER_ID_ = 'xiaohei' ))) order by RES.ID_ asc
		String userIdForCandidateAndAssignee = "hr2";
		List<Task> list4 = this.getTaskService().createTaskQuery().taskCandidateOrAssigned(userIdForCandidateAndAssignee).list();
		System.out.println("______________________");
		
	}
}

