package com.yffd.easy.framework.common.pojo.bo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.yffd.easy.common.core.pojo.IPOJO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年4月19日 下午4:07:53 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class CommonBO<PO> implements IPOJO, Serializable {
	
	private static final long serialVersionUID = -6701664224305413394L;
	
	private PO bindPo;
	private List<PO> bindListPo;
	
	public PO getBindPo() {
		return bindPo;
	}

	public void setBindPo(PO bindPo) {
		this.bindPo = bindPo;
	}

	public List<PO> getBindListPo() {
		return bindListPo;
	}

	public void setBindListPo(List<PO> bindListPo) {
		this.bindListPo = bindListPo;
	}

	public Map<String, Object> propertyNamesNotInPo() {
		
		return null;
	}
	
	public String[] ignorePropertyNames() {
		return null;
	}
	
	public String[] showPropertyNames() {
		return null;
	}
}

