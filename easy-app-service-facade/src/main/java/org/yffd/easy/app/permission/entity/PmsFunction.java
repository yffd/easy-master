package org.yffd.easy.app.permission.entity;

import org.yffd.easy.common.core.entity.PersistEntity;

/**
 * 
 * @Description  权限：功能表实体.
 * @Date		 2017年9月12日 下午4:59:04 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see
 */
public class PmsFunction extends PersistEntity {

	private static final long serialVersionUID = -2175597348886393330L;
	private String funcName; // 权限名称
	private String funcCode; // 权限编码
	private String menuCode; // 菜单编码
	
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	public String getFuncCode() {
		return funcCode;
	}
	public void setFuncCode(String funcCode) {
		this.funcCode = funcCode;
	}
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	
	@Override
	public String toString() {
		return "PmsFunction [funcName=" + funcName + ", funcCode=" + funcCode + ", menuCode=" + menuCode + ", getId()="
				+ getId() + ", getVersion()=" + getVersion() + ", getStatus()=" + getStatus() + ", getCreater()="
				+ getCreater() + ", getCreateTime()=" + getCreateTime() + ", getEditor()=" + getEditor()
				+ ", getEditTime()=" + getEditTime() + ", getRemark()=" + getRemark() + "]";
	}
	

}
