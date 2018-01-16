package com.yffd.easy.workflow.dao;

import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.cmd.AbstractCustomSqlExecution;
import org.activiti.engine.impl.cmd.CustomSqlExecution;
import org.springframework.stereotype.Repository;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.activiti.service.ActivitiBaseService;
import com.yffd.easy.workflow.mapper.WorkFlowHistoryTaskMapper;
import com.yffd.easy.workflow.model.dto.WorkFlowHistoryTaskDTO;

/**
 * @Description  流程实例持久化操作.
 * @Date		 2017年12月6日 下午5:05:58 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Repository
public class WorkFlowHistoryTaskDao extends ActivitiBaseService {

	public PageResult<WorkFlowHistoryTaskDTO> selectPageBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		Long totalRecord = this.selectCountBy(paramMap);
		pageParam.setTotalRecord(totalRecord);
		
		List<WorkFlowHistoryTaskDTO> list = null;
		if(totalRecord>0) {
			list = this.selectListBy(paramMap, pageParam);
		}
		PageResult<WorkFlowHistoryTaskDTO> paginationResult = new PageResult<WorkFlowHistoryTaskDTO>(pageParam, list);
		return paginationResult;
	}
	
	public List<WorkFlowHistoryTaskDTO> selectListBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		
		CustomSqlExecution<WorkFlowHistoryTaskMapper, List<WorkFlowHistoryTaskDTO>> customSqlExecution = 
				new AbstractCustomSqlExecution<WorkFlowHistoryTaskMapper, List<WorkFlowHistoryTaskDTO>>(WorkFlowHistoryTaskMapper.class) {
					@Override
					public List<WorkFlowHistoryTaskDTO> execute(WorkFlowHistoryTaskMapper mapper) {
						if(null!=pageParam) paramMap.put("pageParam", pageParam);
						return mapper.selectListBy(paramMap);
					}
			
				};
		
		List<WorkFlowHistoryTaskDTO> list = this.getManagementService().executeCustomSql(customSqlExecution);
		return list;
	}
	
	public Long selectCountBy(final Map<String, Object> paramMap) {
		
		CustomSqlExecution<WorkFlowHistoryTaskMapper, Long> customSqlExecution = 
				new AbstractCustomSqlExecution<WorkFlowHistoryTaskMapper, Long>(WorkFlowHistoryTaskMapper.class) {
			@Override
			public Long execute(WorkFlowHistoryTaskMapper mapper) {
				return mapper.selectCountBy(paramMap);
			}
			
		};
		
		Long count = this.getManagementService().executeCustomSql(customSqlExecution);
		return count;
	}
	
}

