package com.yffd.easy.framework.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description  持久化模型基类.
 * @Date		 2018年1月29日 下午3:03:16 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class GenericPO extends DomainModel implements Serializable {
	
	private static final long serialVersionUID = 1569029662385519388L;
	
	private String id;
	private Integer version;	// 版本号
	private String creator;		// 创建人
	private Date createTime;	// 创建时间
	private String editor;		// 最后编辑人
	private Date editTime;		// 最后编辑时间
	private String delFlag;		//逻辑删除标识：0=未删除、1=已删除
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public Date getEditTime() {
		return editTime;
	}
	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
}

