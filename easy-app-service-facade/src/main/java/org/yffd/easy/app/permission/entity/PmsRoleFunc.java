package org.yffd.easy.app.permission.entity;

import org.yffd.easy.common.core.entity.PersistEntity;

/**
 * @Description  权限：角色-功能关系表实体.
 * @Date		 2017年9月15日 下午4:09:34 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class PmsRoleFunc extends PersistEntity {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = -135703958072105458L;

	private String roleCode;
	private String funcCode;
	
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getFuncCode() {
		return funcCode;
	}
	public void setFuncCode(String funcCode) {
		this.funcCode = funcCode;
	}
	@Override
	public String toString() {
		return "PmsRoleFunc [roleCode=" + roleCode + ", funcCode=" + funcCode + ", getId()=" + getId()
				+ ", getVersion()=" + getVersion() + ", getStatus()=" + getStatus() + ", getCreater()=" + getCreater()
				+ ", getCreateTime()=" + getCreateTime() + ", getEditor()=" + getEditor() + ", getEditTime()="
				+ getEditTime() + ", getRemark()=" + getRemark() + "]";
	}
	
}

