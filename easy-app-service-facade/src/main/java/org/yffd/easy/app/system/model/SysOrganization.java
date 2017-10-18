package org.yffd.easy.app.system.model;

import org.yffd.easy.common.core.model.PersistEntity;

/**
 * @Description  系统模块：组织机构.
 * @Date		 2017年9月27日 下午3:41:11 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class SysOrganization extends PersistEntity {
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = 8507597194623600620L;

	private String name;
	private String code;
	private String parentCode;
	private String firstManagerCode;
	private String secondManagerCode;
	private String tel;
	private String fax;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getFirstManagerCode() {
		return firstManagerCode;
	}
	public void setFirstManagerCode(String firstManagerCode) {
		this.firstManagerCode = firstManagerCode;
	}
	public String getSecondManagerCode() {
		return secondManagerCode;
	}
	public void setSecondManagerCode(String secondManagerCode) {
		this.secondManagerCode = secondManagerCode;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	
}

