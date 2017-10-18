package org.yffd.easy.common.core.model;

import java.io.Serializable;
import java.util.Date;

import org.yffd.easy.common.core.enums.SysConstantsEnum;
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
    private String delFlag;// 删除标记，1：删除、0：未删除
    private String creater;// 创建人
    private Date createTime;// 创建时间
    private String editor;// 修改人
    private Date editTime;// 修改时间
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
    public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
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
    
    /**
     * 持久化操作-添加实体，设置实体的默认属性：
     * 					id:			RandomUtils.get32UUID()
     * 					version:	0
     * 					delFlag：	0
     * 					creater：	admin
     * 					createTime：	当前时间
     * 					editor：		admin
     * 					editTime：	当前时间
     * @Date	2017年10月11日 下午6:01:27 <br/>
     * @author  zhangST
     * @param user
     * @param time
     */
    public void setDefault() {
    	this.setId(RandomUtils.get32UUID());
    	this.setVersion(0);
    	this.setCreater("admin");
    	Date date = new Date();
    	this.setCreateTime(date);
    	this.setEditor("admin");
    	this.setEditTime(date);
    	this.setDelFlag(SysConstantsEnum.DEL_FLAG_UNENABLE.getValue());
    }
    
    /**
     * 持久化操作-添加实体，设置实体的默认属性：
     * 					version：	0
     * 					delFlag：	0
     * 					creater：	user
     * 					createTime：	time
     * 					editor：		user
     * 					editTime：	time
     * @Date	2017年10月11日 下午6:01:27 <br/>
     * @author  zhangST
     * @param user
     * @param time
     */
    public void setDefaultAdd(String user, Date time) {
    	this.setVersion(0);
    	this.setDelFlag("0");
    	this.setCreater(user);
    	this.setCreateTime(time);
    	this.setEditor(user);
    	this.setEditTime(time);
    }
    
    /**
     * 持久化操作-修改实体，设置实体的默认属性：
     * 					editor：		user
     * 					editTime：	time
     * @Date	2017年10月11日 下午6:03:16 <br/>
     * @author  zhangST
     * @param user
     * @param time
     */
    public void setDefaultUpdate(String user, Date time) {
    	this.setEditor(user);
    	this.setEditTime(time);
    }
}

