package com.yffd.easy.workflow.service;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.common.core.converter.EasyModelConverter;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年1月16日 下午3:36:22 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class WfCommonService extends EasyModelConverter {
	@Autowired
	private ProcessEngine processEngine;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private IdentityService identityService;
	@Autowired
	private ManagementService managementService;
	@Autowired
	private FormService formService;
	
	public ProcessEngine getProcessEngine() {
		return processEngine;
	}
	public RepositoryService getRepositoryService() {
		return repositoryService;
	}
	public RuntimeService getRuntimeService() {
		return runtimeService;
	}
	public TaskService getTaskService() {
		return taskService;
	}
	public HistoryService getHistoryService() {
		return historyService;
	}
	public IdentityService getIdentityService() {
		return identityService;
	}
	public ManagementService getManagementService() {
		return managementService;
	}
	public FormService getFormService() {
		return formService;
	}
}

