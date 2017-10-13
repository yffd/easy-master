package org.yffd.easy.common.core.view.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description  EasyUI视图实体封装：DataGrid视图模型.
 * @Date		 2017年10月12日 下午5:39:53 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class DataGridVO {

	private Long total = 0L;
	private List<?> rows = new ArrayList();
	
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> list) {
		this.rows = list;
	}
	
}

