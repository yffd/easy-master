package org.yffd.easy.common.core.entity;

import java.io.Serializable;
import java.util.Date;

import org.yffd.easy.common.core.util.RandomUtils;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月13日 下午2:16:07 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class PersistEntity implements Serializable {
    
    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * @since JDK 1.7+
     */
    private static final long serialVersionUID = 5449654546724378848L;

    private String id;// 主键ID.
    private Integer version;// 版本号
    private String status;// 状态 PublicStatusEnum
    private String creater;// 创建人.
    private Date createTime;// 创建时间.
    private String editor;// 修改人.
    private Date editTime;// 修改时间.
    private String remark;// 描述
    
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
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getCreater() {
        return creater;
    }
    public void setCreater(String creater) {
        this.creater = creater;
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
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public void setDefault() {
    	this.setId(RandomUtils.get32UUID());
    	this.setVersion(0);
    	this.setCreater("admin");
    	Date date = new Date();
    	this.setCreateTime(date);
    	this.setEditor("admin");
    	this.setEditTime(date);
    	this.setStatus("1");
    	this.setRemark("激活");
    }
}

