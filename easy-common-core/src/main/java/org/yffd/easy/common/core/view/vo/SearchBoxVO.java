package org.yffd.easy.common.core.view.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月13日 下午4:24:41 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class SearchBoxVO {
	
	private Long page; //分页参数：当前页码
	private Long rows; //分页参数：每页记录数
	private Map<String, Object> conditions = new HashMap<String, Object>(); // 查询条件选项集合
	
	public Long getPage() {
		return page;
	}
	public void setPage(Long page) {
		this.page = page;
	}
	public Long getRows() {
		return rows;
	}
	public void setRows(Long rows) {
		this.rows = rows;
	}
	public Map<String, Object> getConditions() {
		return conditions;
	}
	public void setConditions(Map<String, Object> conditions) {
		this.conditions = conditions;
	}
	
}

