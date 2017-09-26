package org.yffd.easy.app.permission.entity;

import org.yffd.easy.common.core.entity.PersistEntity;

/**
 * @Description  权限：角色-用户表实体.
 * @Date		 2017年9月15日 下午4:11:55 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class PmsRoleUser extends PersistEntity {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = -8440275646594651383L;

	private String roleCode;
	private String userCode;
	
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	@Override
	public String toString() {
		return "PmsRoleUser [roleCode=" + roleCode + ", userCode=" + userCode + ", getId()=" + getId()
				+ ", getVersion()=" + getVersion() + ", getStatus()=" + getStatus() + ", getCreater()=" + getCreater()
				+ ", getCreateTime()=" + getCreateTime() + ", getEditor()=" + getEditor() + ", getEditTime()="
				+ getEditTime() + ", getRemark()=" + getRemark() + "]";
	}
	
}

