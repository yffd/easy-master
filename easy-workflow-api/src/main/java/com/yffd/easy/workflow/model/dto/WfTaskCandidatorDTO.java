package com.yffd.easy.workflow.model.dto;

import java.io.Serializable;
import java.util.Set;

/**
 * @Description  流程任务候选人信息.
 * @Date		 2018年1月4日 下午4:24:01 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class WfTaskCandidatorDTO implements Serializable {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = 7335160077660941434L;

	private Set<String> users;
	private Set<String> roles;
	
	public Set<String> getUsers() {
		return users;
	}
	public void setUsers(Set<String> users) {
		this.users = users;
	}
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	
}

