package org.yffd.easy.app.permission.entity;

import org.yffd.easy.common.core.entity.PersistEntity;

/**
 * 
 * @Description  权限：用户表实体.
 * @Date		 2017年9月12日 下午4:54:32 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see
 */
public class PmsUser extends PersistEntity {

    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * @since JDK 1.7+
     */
    private static final long serialVersionUID = 4729611185709900465L;
    private String userName;// 登录名
    private String userCode;// 登录名
	private String userPwd; // 登录密码
	private String realName; // 姓名
	private String mobileNo; // 手机号
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	@Override
	public String toString() {
		return "PmsUser [userName=" + userName + ", userCode=" + userCode + ", userPwd=" + userPwd + ", realName="
				+ realName + ", mobileNo=" + mobileNo + ", getId()=" + getId() + ", getVersion()=" + getVersion()
				+ ", getStatus()=" + getStatus() + ", getCreater()=" + getCreater() + ", getCreateTime()="
				+ getCreateTime() + ", getEditor()=" + getEditor() + ", getEditTime()=" + getEditTime()
				+ ", getRemark()=" + getRemark() + "]";
	}

}
