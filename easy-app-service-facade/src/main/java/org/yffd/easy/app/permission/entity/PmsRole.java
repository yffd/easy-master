package org.yffd.easy.app.permission.entity;

import org.yffd.easy.common.core.entity.PersistEntity;

/**
 * 
 * @Description  权限管理：角色表实体.
 * @Date		 2017年9月12日 下午4:59:50 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see
 */
public class PmsRole extends PersistEntity {

	private static final long serialVersionUID = -1850274607153125161L;
	private String roleCode; // 角色编码
	private String roleName; // 角色名称

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	/** 角色名称 */
	public String getRoleName() {
		return roleName;
	}

	/** 角色名称 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "PmsRole [roleCode=" + roleCode + ", roleName=" + roleName + ", getId()=" + getId() + ", getVersion()="
				+ getVersion() + ", getStatus()=" + getStatus() + ", getCreater()=" + getCreater()
				+ ", getCreateTime()=" + getCreateTime() + ", getEditor()=" + getEditor() + ", getEditTime()="
				+ getEditTime() + ", getRemark()=" + getRemark() + "]";
	}

}
