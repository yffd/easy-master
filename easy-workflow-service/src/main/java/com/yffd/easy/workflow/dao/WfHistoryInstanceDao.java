package com.yffd.easy.workflow.dao;

import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.cmd.AbstractCustomSqlExecution;
import org.activiti.engine.impl.cmd.CustomSqlExecution;
import org.springframework.stereotype.Repository;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.activiti.service.ActivitiServiceManager;
import com.yffd.easy.workflow.mapper.WfHistoryInstanceMapper;
import com.yffd.easy.workflow.model.dto.WfHistoryInstanceDTO;

/**
 * @Description  流程历史实例.
 * @Date		 2017年12月6日 下午5:05:58 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Repository
public class WfHistoryInstanceDao extends ActivitiServiceManager {

	public PageResult<WfHistoryInstanceDTO> selectPageBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		Long totalRecord = this.selectCountBy(paramMap, pageParam);
		pageParam.setTotalRecord(totalRecord);
		
		List<WfHistoryInstanceDTO> list = null;
		if(totalRecord>0) {
			list = this.selectListBy(paramMap, pageParam);
		}
		PageResult<WfHistoryInstanceDTO> paginationResult = new PageResult<WfHistoryInstanceDTO>(pageParam, list);
		return paginationResult;
	}
	
	public List<WfHistoryInstanceDTO> selectListBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		
		CustomSqlExecution<WfHistoryInstanceMapper, List<WfHistoryInstanceDTO>> customSqlExecution = 
				new AbstractCustomSqlExecution<WfHistoryInstanceMapper, List<WfHistoryInstanceDTO>>(WfHistoryInstanceMapper.class) {
					@Override
					public List<WfHistoryInstanceDTO> execute(WfHistoryInstanceMapper mapper) {
						if(null!=pageParam) paramMap.put("pageParam", pageParam);
						return mapper.selectListBy(paramMap);
					}
			
				};
		
		List<WfHistoryInstanceDTO> list = this.getManagementService().executeCustomSql(customSqlExecution);
		return list;
	}
	
	public Long selectCountBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		
		CustomSqlExecution<WfHistoryInstanceMapper, Long> customSqlExecution = 
				new AbstractCustomSqlExecution<WfHistoryInstanceMapper, Long>(WfHistoryInstanceMapper.class) {
			@Override
			public Long execute(WfHistoryInstanceMapper mapper) {
				if(null!=pageParam) paramMap.put("pageParam", pageParam);
				return mapper.selectCountBy(paramMap);
			}
			
		};
		
		Long count = this.getManagementService().executeCustomSql(customSqlExecution);
		return count;
	}
	
}

