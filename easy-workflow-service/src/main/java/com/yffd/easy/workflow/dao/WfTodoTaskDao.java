package com.yffd.easy.workflow.dao;

import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.cmd.AbstractCustomSqlExecution;
import org.activiti.engine.impl.cmd.CustomSqlExecution;
import org.springframework.stereotype.Repository;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.activiti.service.ActivitiServiceManager;
import com.yffd.easy.workflow.mapper.WfTodoTaskMapper;
import com.yffd.easy.workflow.model.dto.WfTaskDTO;

/**
 * @Description  流程待办任务.
 * @Date		 2017年12月6日 下午5:05:58 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Repository
public class WfTodoTaskDao extends ActivitiServiceManager {

	public PageResult<WfTaskDTO> selectPageBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		Long totalRecord = this.selectCountBy(paramMap);
		pageParam.setTotalRecord(totalRecord);
		
		List<WfTaskDTO> list = null;
		if(totalRecord>0) {
			list = this.selectListBy(paramMap, pageParam);
		}
		PageResult<WfTaskDTO> paginationResult = new PageResult<WfTaskDTO>(pageParam, list);
		return paginationResult;
	}
	
	public List<WfTaskDTO> selectListBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		
		CustomSqlExecution<WfTodoTaskMapper, List<WfTaskDTO>> customSqlExecution = 
				new AbstractCustomSqlExecution<WfTodoTaskMapper, List<WfTaskDTO>>(WfTodoTaskMapper.class) {
					@Override
					public List<WfTaskDTO> execute(WfTodoTaskMapper mapper) {
						if(null!=pageParam) paramMap.put("pageParam", pageParam);
						return mapper.selectListBy(paramMap);
					}
			
				};
		
		List<WfTaskDTO> list = this.getManagementService().executeCustomSql(customSqlExecution);
		return list;
	}
	
	public Long selectCountBy(final Map<String, Object> paramMap) {
		
		CustomSqlExecution<WfTodoTaskMapper, Long> customSqlExecution = 
				new AbstractCustomSqlExecution<WfTodoTaskMapper, Long>(WfTodoTaskMapper.class) {
			@Override
			public Long execute(WfTodoTaskMapper mapper) {
				return mapper.selectCountBy(paramMap);
			}
			
		};
		
		Long count = this.getManagementService().executeCustomSql(customSqlExecution);
		return count;
	}
	
}

