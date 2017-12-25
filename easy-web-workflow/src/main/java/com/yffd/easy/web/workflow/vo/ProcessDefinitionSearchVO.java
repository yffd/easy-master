package com.yffd.easy.web.workflow.vo;

import com.yffd.easy.common.core.view.vo.SearchVO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月11日 下午5:22:02 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class ProcessDefinitionSearchVO extends SearchVO {

	private String name;
	private String key;
	private String startDate;
	private String endDate;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}

