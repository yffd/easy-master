package com.yffd.easy.workflow.app.model;

import java.util.Date;

import com.yffd.easy.common.core.model.PersistModel;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月18日 下午5:49:07 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class OaLeave extends PersistModel {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = -2393895756002618906L;

	private String id;
	private String userCode;
	private Date applyTime;
	private String leaveType;
	private Date startTime;
	private Date endTime;
	private String reason;
	private String processInstanceId;
	private String workFlowKey;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getWorkFlowKey() {
		return workFlowKey;
	}
	public void setWorkFlowKey(String workFlowKey) {
		this.workFlowKey = workFlowKey;
	}
	
}

