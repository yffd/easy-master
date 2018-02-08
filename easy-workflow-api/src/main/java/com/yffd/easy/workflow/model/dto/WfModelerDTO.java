package com.yffd.easy.workflow.model.dto;

import java.util.Date;

/**
 * @Description  流程定义信息.
 * @Date		 2017年12月11日 下午5:57:53 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class WfModelerDTO extends WfBaseDTO {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = 7604014292943836517L;
	private String id;
	private Date createTime;
	private Date updateTime;
	private String metaInfo;
	private String deployId;
	private String editorSourceValueId;			// ID：act_ge_bytearray表
	private String editorSourceExtraValueId;	// ID：act_ge_bytearray表，编辑后保存的
	
	private Date searchStartTime;
	private Date searchEndTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getMetaInfo() {
		return metaInfo;
	}
	public void setMetaInfo(String metaInfo) {
		this.metaInfo = metaInfo;
	}
	public String getDeployId() {
		return deployId;
	}
	public void setDeployId(String deployId) {
		this.deployId = deployId;
	}
	public String getEditorSourceValueId() {
		return editorSourceValueId;
	}
	public void setEditorSourceValueId(String editorSourceValueId) {
		this.editorSourceValueId = editorSourceValueId;
	}
	public String getEditorSourceExtraValueId() {
		return editorSourceExtraValueId;
	}
	public void setEditorSourceExtraValueId(String editorSourceExtraValueId) {
		this.editorSourceExtraValueId = editorSourceExtraValueId;
	}
	public Date getSearchStartTime() {
		return searchStartTime;
	}
	public void setSearchStartTime(Date searchStartTime) {
		this.searchStartTime = searchStartTime;
	}
	public Date getSearchEndTime() {
		return searchEndTime;
	}
	public void setSearchEndTime(Date searchEndTime) {
		this.searchEndTime = searchEndTime;
	}
}

