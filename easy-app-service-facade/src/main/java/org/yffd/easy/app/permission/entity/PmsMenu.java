package org.yffd.easy.app.permission.entity;

import java.util.List;

import org.yffd.easy.common.core.entity.PersistEntity;

/**
 * @Description  权限：菜单实体.
 * @Date		 2017年9月12日 下午4:50:19 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class PmsMenu extends PersistEntity {
    
    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * @since JDK 1.7+
     */
    private static final long serialVersionUID = -2813569366654142770L;
    
    /** 菜单名称 **/
    private String menuName;
    
    /** 菜单编号 **/
    private String menuCode;

    /** 菜单地址 **/
    private String menuUrl;

    /** 父菜单编号 **/
    private String parentCode;
    
    /** 菜单编号排序号 **/
    private Float menuNum;
    
    /** 菜单图标 **/
    private String menuIcon;

    /** 父菜单 **/
    private PmsMenu parent;

    /** 子菜单集合 **/
    private List<PmsMenu> children;

    public PmsMenu() {
    }

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public Float getMenuNum() {
		return menuNum;
	}

	public void setMenuNum(Float menuNum) {
		this.menuNum = menuNum;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public PmsMenu getParent() {
		return parent;
	}

	public void setParent(PmsMenu parent) {
		this.parent = parent;
	}

	public List<PmsMenu> getChildren() {
		return children;
	}

	public void setChildren(List<PmsMenu> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "PmsMenu [menuName=" + menuName + ", menuCode=" + menuCode + ", menuUrl=" + menuUrl + ", parentCode="
				+ parentCode + ", menuNum=" + menuNum + ", menuIcon=" + menuIcon + ", parent=" + parent + ", children="
				+ children + ", getId()=" + getId() + ", getVersion()=" + getVersion() + ", getStatus()=" + getStatus()
				+ ", getCreater()=" + getCreater() + ", getCreateTime()=" + getCreateTime() + ", getEditor()="
				+ getEditor() + ", getEditTime()=" + getEditTime() + ", getRemark()=" + getRemark() + "]";
	}

}

