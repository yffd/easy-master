package com.yffd.easy.web.workflow.app.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yffd.easy.common.core.model.RespModel;
import com.yffd.easy.common.core.util.ValidUtils;
import com.yffd.easy.web.workflow.controller.WorkflowBaseController;
import com.yffd.easy.workflow.app.model.OaLeave;
import com.yffd.easy.workflow.app.service.OaLeaveService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月13日 下午5:13:04 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Controller
@RequestMapping("/workflow/app/leave")
public class LeaveController extends WorkflowBaseController {
	private static final Logger LOG = LoggerFactory.getLogger(LeaveController.class);
	
	@Autowired
	private OaLeaveService oaLeaveService;
	
	@RequestMapping(value = "apply", method = RequestMethod.POST)
	@ResponseBody
	public RespModel apply(@RequestParam Map<String, Object> paramMap) {
		OaLeave leave = this.map2model(paramMap, OaLeave.class, null);
		if(ValidUtils.isNull(leave)) {
			return this.errorAjax("参数无效");
		}
		leave.setWorkFlowKey("leave");
		this.oaLeaveService.apply(leave);
		LOG.info("流程已启动，流程ID：" + leave.getProcessInstanceId());
		return this.successAjax();
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
    public RespModel add(@RequestParam Map<String, Object> paramMap) {
		OaLeave leave = this.map2model(paramMap, OaLeave.class, null);
		leave.setUserCode("xiaobai");
		this.oaLeaveService.add(leave);
		return this.successAjax();
	}
	
}

