package com.yffd.easy.workflow.mapper;

import java.util.List;
import java.util.Map;

import com.yffd.easy.workflow.model.dto.WfHistoryInstanceDTO;

/**
 * @Description  流程历史实例模块.
 * @Date		 2017年12月6日 下午5:09:41 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface WfHistoryInstanceMapper {

	List<WfHistoryInstanceDTO> selectListBy(Map<String, Object> paramMap);
	
	Long selectCountBy(Map<String, Object> paramMap);
	
}

