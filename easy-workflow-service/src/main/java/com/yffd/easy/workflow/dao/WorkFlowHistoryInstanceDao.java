package com.yffd.easy.workflow.dao;

import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.cmd.AbstractCustomSqlExecution;
import org.activiti.engine.impl.cmd.CustomSqlExecution;
import org.springframework.stereotype.Repository;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.activiti.service.ActivitiBaseService;
import com.yffd.easy.workflow.mapper.WorkFlowHistoryInstanceMapper;
import com.yffd.easy.workflow.model.dto.WorkFlowHistoryInstanceDTO;

/**
 * @Description  流程实例持久化操作.
 * @Date		 2017年12月6日 下午5:05:58 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Repository
public class WorkFlowHistoryInstanceDao extends ActivitiBaseService {

	public PageResult<WorkFlowHistoryInstanceDTO> selectPageBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		Long totalRecord = this.selectCountBy(paramMap, pageParam);
		pageParam.setTotalRecord(totalRecord);
		
		List<WorkFlowHistoryInstanceDTO> list = null;
		if(totalRecord>0) {
			list = this.selectListBy(paramMap, pageParam);
		}
		PageResult<WorkFlowHistoryInstanceDTO> paginationResult = new PageResult<WorkFlowHistoryInstanceDTO>(pageParam, list);
		return paginationResult;
	}
	
	public List<WorkFlowHistoryInstanceDTO> selectListBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		
		CustomSqlExecution<WorkFlowHistoryInstanceMapper, List<WorkFlowHistoryInstanceDTO>> customSqlExecution = 
				new AbstractCustomSqlExecution<WorkFlowHistoryInstanceMapper, List<WorkFlowHistoryInstanceDTO>>(WorkFlowHistoryInstanceMapper.class) {
					@Override
					public List<WorkFlowHistoryInstanceDTO> execute(WorkFlowHistoryInstanceMapper mapper) {
						if(null!=pageParam) paramMap.put("pageParam", pageParam);
						return mapper.selectListBy(paramMap);
					}
			
				};
		
		List<WorkFlowHistoryInstanceDTO> list = this.getManagementService().executeCustomSql(customSqlExecution);
		return list;
	}
	
	public Long selectCountBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		
		CustomSqlExecution<WorkFlowHistoryInstanceMapper, Long> customSqlExecution = 
				new AbstractCustomSqlExecution<WorkFlowHistoryInstanceMapper, Long>(WorkFlowHistoryInstanceMapper.class) {
			@Override
			public Long execute(WorkFlowHistoryInstanceMapper mapper) {
				if(null!=pageParam) paramMap.put("pageParam", pageParam);
				return mapper.selectCountBy(paramMap);
			}
			
		};
		
		Long count = this.getManagementService().executeCustomSql(customSqlExecution);
		return count;
	}
	
}

