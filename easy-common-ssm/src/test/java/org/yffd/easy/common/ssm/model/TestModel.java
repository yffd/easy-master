package org.yffd.easy.common.ssm.model;

import java.util.Date;

import org.yffd.easy.common.core.model.PersistModel;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月11日 下午2:24:44 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class TestModel extends PersistModel {
    
    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * @since JDK 1.7+
     */
    private static final long serialVersionUID = 244794192478091246L;

    private String userName;
    private String userCode;
    private Date createTime;
    
    
    public TestModel() {
    }

    public TestModel(String userName, String userCode, Date createTime) {
        this.userName = userName;
        this.userCode = userCode;
        this.createTime = createTime;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TestModel [userName=" + userName + ", userCode=" + userCode + ", createTime=" + createTime + "]";
    }
    
}

