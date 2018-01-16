package com.yffd.easy.workflow.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.impl.cmd.AbstractCustomSqlExecution;
import org.activiti.engine.impl.cmd.CustomSqlExecution;
import org.springframework.stereotype.Repository;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.activiti.service.ActivitiBaseService;
import com.yffd.easy.workflow.mapper.WorkFlowTaskMapper;
import com.yffd.easy.workflow.model.dto.WorkFlowTaskDTO;

/**
 * @Description  流程实例持久化操作.
 * @Date		 2017年12月6日 下午5:05:58 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Repository
public class WorkFlowTaskDao extends ActivitiBaseService {

	public PageResult<WorkFlowTaskDTO> selectPageBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		Long totalRecord = this.selectCountBy(paramMap);
		pageParam.setTotalRecord(totalRecord);
		
		List<WorkFlowTaskDTO> list = null;
		if(totalRecord>0) {
			list = this.selectListBy(paramMap, pageParam);
		}
		PageResult<WorkFlowTaskDTO> paginationResult = new PageResult<WorkFlowTaskDTO>(pageParam, list);
		return paginationResult;
	}
	
	public List<WorkFlowTaskDTO> selectListBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		
		CustomSqlExecution<WorkFlowTaskMapper, List<WorkFlowTaskDTO>> customSqlExecution = 
				new AbstractCustomSqlExecution<WorkFlowTaskMapper, List<WorkFlowTaskDTO>>(WorkFlowTaskMapper.class) {
					@Override
					public List<WorkFlowTaskDTO> execute(WorkFlowTaskMapper mapper) {
						if(null!=pageParam) paramMap.put("pageParam", pageParam);
						return mapper.selectListBy(paramMap);
					}
			
				};
		
		List<WorkFlowTaskDTO> list = this.getManagementService().executeCustomSql(customSqlExecution);
		return list;
	}
	
	public Long selectCountBy(final Map<String, Object> paramMap) {
		
		CustomSqlExecution<WorkFlowTaskMapper, Long> customSqlExecution = 
				new AbstractCustomSqlExecution<WorkFlowTaskMapper, Long>(WorkFlowTaskMapper.class) {
			@Override
			public Long execute(WorkFlowTaskMapper mapper) {
				return mapper.selectCountBy(paramMap);
			}
			
		};
		
		Long count = this.getManagementService().executeCustomSql(customSqlExecution);
		return count;
	}
	
	public PageResult<WorkFlowTaskDTO> selectTodoTaskPageBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		final Map<String, Object> _paramMap = new HashMap<String, Object>();
		if(null!=paramMap) _paramMap.putAll(paramMap);
		long totalRecord = this.selectTodoTaskCountBy(_paramMap);
		pageParam.setTotalRecord(totalRecord);
		
		List<WorkFlowTaskDTO> list = null;
		if(totalRecord>0) {
			if(null!=pageParam) _paramMap.put("pageParam", pageParam);
			list = this.selectTodoTaskListBy(_paramMap);
		}
		PageResult<WorkFlowTaskDTO> paginationResult = new PageResult<WorkFlowTaskDTO>(pageParam, list);
		return paginationResult;
	}
	
	public List<WorkFlowTaskDTO> selectTodoTaskListBy(final Map<String, Object> paramMap) {
		final Map<String, Object> _paramMap = new HashMap<String, Object>();
		if(null!=paramMap) _paramMap.putAll(paramMap);
		
		CustomSqlExecution<WorkFlowTaskMapper, List<WorkFlowTaskDTO>> customSqlExecution = 
				new AbstractCustomSqlExecution<WorkFlowTaskMapper, List<WorkFlowTaskDTO>>(WorkFlowTaskMapper.class) {
					@Override
					public List<WorkFlowTaskDTO> execute(WorkFlowTaskMapper mapper) {
						return mapper.selectTodoTaskListBy(_paramMap);
					}
			
				};
		
		List<WorkFlowTaskDTO> list = this.getManagementService().executeCustomSql(customSqlExecution);
		return list;
	}
	
	public long selectTodoTaskCountBy(final Map<String, Object> paramMap) {
		CustomSqlExecution<WorkFlowTaskMapper, Long> customSqlExecution = 
				new AbstractCustomSqlExecution<WorkFlowTaskMapper, Long>(WorkFlowTaskMapper.class) {
			@Override
			public Long execute(WorkFlowTaskMapper mapper) {
				return mapper.selectTodoTaskCountBy(paramMap);
			}
			
		};
		
		Long count = this.getManagementService().executeCustomSql(customSqlExecution);
		return count;
	}
}

