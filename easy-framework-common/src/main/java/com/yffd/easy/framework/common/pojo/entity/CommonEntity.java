package com.yffd.easy.framework.common.pojo.entity;

import java.io.Serializable;

import com.yffd.easy.common.core.pojo.IPOJO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年4月19日 下午3:13:49 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class CommonEntity implements IPOJO, Serializable {
	private static final long serialVersionUID = -1704662533989601602L;
	
	private String id;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}

