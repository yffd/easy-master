package com.yffd.easy.common.shiro.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月7日 下午5:35:45 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class AccountInfo implements Serializable {
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = -7048794666856535554L;

	private String id;// 主键ID.
    private Integer version;// 版本号
    private String delFlag;// 删除标记，1：删除、0：未删除
    private String creater;// 创建人
    private Date createTime;// 创建时间
    private String editor;// 修改人
    private Date editTime;// 修改时间
    
    private String accountName; // 用户名
    private String accountPwd; // 密码
    private String nickName; // 昵称
    private String salt; // 加密密码的盐
    private String status = "A"; // 账户状态，A：active、I：inactive
    private String type = "S"; // 账户类型，T：tourist、S：staff
    private String isOnline; // 是否在线
    private String visitIp; // 访问IP
    private Integer visitCount; // 登录次数
    private Date visitFirst; // 第一次登录时间
    private Date visitPrevious; // 上一次登录时间
    private Date visitLast; // 最后一次登录时间
    
	public AccountInfo() {
	}
	
	public AccountInfo(String accountName, String accountPwd) {
		this.accountName = accountName;
		this.accountPwd = accountPwd;
	}

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
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountPwd() {
		return accountPwd;
	}
	public void setAccountPwd(String accountPwd) {
		this.accountPwd = accountPwd;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}
	public String getVisitIp() {
		return visitIp;
	}
	public void setVisitIp(String visitIp) {
		this.visitIp = visitIp;
	}
	public Integer getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}
	public Date getVisitFirst() {
		return visitFirst;
	}
	public void setVisitFirst(Date visitFirst) {
		this.visitFirst = visitFirst;
	}
	public Date getVisitPrevious() {
		return visitPrevious;
	}
	public void setVisitPrevious(Date visitPrevious) {
		this.visitPrevious = visitPrevious;
	}
	public Date getVisitLast() {
		return visitLast;
	}
	public void setVisitLast(Date visitLast) {
		this.visitLast = visitLast;
	}
    
	public String getCredentialsSalt() {
        return accountName + salt;
    }
	
	@Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(obj==null || getClass()!=obj.getClass()) return false;

        AccountInfo info = (AccountInfo) obj;
        if(id!=null?!id.equals(info.id):info.id!=null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return id!=null?id.hashCode():0;
    }
}

