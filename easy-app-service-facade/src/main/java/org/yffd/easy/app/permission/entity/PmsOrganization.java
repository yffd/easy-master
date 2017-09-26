package org.yffd.easy.app.permission.entity;

import java.util.List;

import org.yffd.easy.common.core.entity.PersistEntity;

/**
 * @Description  权限：组织机构表实体.
 * @Date		 2017年9月15日 下午4:03:43 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class PmsOrganization extends PersistEntity {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = 4317507595967496887L;
	/** 组织机构名称 **/
    private String orgName;
    
    /** 组织机构编号 **/
    private String orgCode;

    /** 父组织机构编号 **/
    private String parentCode;
    
    /** 组织机构排序号 **/
    private Float orgNum;

    /** 子菜单集合 **/
    private List<PmsOrganization> children;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public Float getOrgNum() {
		return orgNum;
	}

	public void setOrgNum(Float orgNum) {
		this.orgNum = orgNum;
	}

	public List<PmsOrganization> getChildren() {
		return children;
	}

	public void setChildren(List<PmsOrganization> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "PmsOrganization [orgName=" + orgName + ", orgCode=" + orgCode + ", parentCode=" + parentCode
				+ ", orgNum=" + orgNum + ", children=" + children + ", getId()=" + getId() + ", getVersion()="
				+ getVersion() + ", getStatus()=" + getStatus() + ", getCreater()=" + getCreater()
				+ ", getCreateTime()=" + getCreateTime() + ", getEditor()=" + getEditor() + ", getEditTime()="
				+ getEditTime() + ", getRemark()=" + getRemark() + "]";
	}
    
}

