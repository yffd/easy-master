package org.yffd.easy.app.system.model;

import org.yffd.easy.common.core.model.PersistEntity;

/**
 * @Description  系统管理：权限实体.
 * @Date		 2017年10月12日 下午1:43:44 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class SysPermission extends PersistEntity {
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7+
	 */
	private static final long serialVersionUID = 6683105680845587214L;

	private String code;
	private String name;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}

