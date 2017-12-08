package com.yffd.easy.workflow.dao;

import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.cmd.AbstractCustomSqlExecution;
import org.activiti.engine.impl.cmd.CustomSqlExecution;
import org.springframework.stereotype.Component;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.activiti.services.ActivitiBaseService;
import com.yffd.easy.workflow.mapper.ProcessDefinitionMapper;
import com.yffd.easy.workflow.model.ProcessDefinitionInfo;

/**
 * @Description  流程定义管理器.
 * @Date		 2017年12月6日 下午5:05:58 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Component
public class ProcessDefinitionDao extends ActivitiBaseService {

	public PageResult<ProcessDefinitionInfo> selectPageBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		Long totalRecord = this.selectCountBy(paramMap, pageParam);
		pageParam.setTotalRecord(totalRecord);
		
		List<ProcessDefinitionInfo> list = null;
		if(totalRecord>0) {
			list = this.selectListBy(paramMap, pageParam);
		}
		PageResult<ProcessDefinitionInfo> paginationResult = new PageResult<ProcessDefinitionInfo>(pageParam, list);
		return paginationResult;
	}
	
	public List<ProcessDefinitionInfo> selectListBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		
		CustomSqlExecution<ProcessDefinitionMapper, List<ProcessDefinitionInfo>> customSqlExecution = 
				new AbstractCustomSqlExecution<ProcessDefinitionMapper, List<ProcessDefinitionInfo>>(ProcessDefinitionMapper.class) {
					@Override
					public List<ProcessDefinitionInfo> execute(ProcessDefinitionMapper mapper) {
						if(null!=pageParam) paramMap.put("pageParam", pageParam);
						return mapper.selectListBy(paramMap);
					}
			
				};
		
		List<ProcessDefinitionInfo> list = this.getManagementService().executeCustomSql(customSqlExecution);
		return list;
	}
	
	public Long selectCountBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		
		CustomSqlExecution<ProcessDefinitionMapper, Long> customSqlExecution = 
				new AbstractCustomSqlExecution<ProcessDefinitionMapper, Long>(ProcessDefinitionMapper.class) {
			@Override
			public Long execute(ProcessDefinitionMapper mapper) {
				if(null!=pageParam) paramMap.put("pageParam", pageParam);
				return mapper.selectCountBy(paramMap);
			}
			
		};
		
		Long count = this.getManagementService().executeCustomSql(customSqlExecution);
		return count;
	}
}

