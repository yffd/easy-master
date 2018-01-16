package com.yffd.easy.workflow.dao;

import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.cmd.AbstractCustomSqlExecution;
import org.activiti.engine.impl.cmd.CustomSqlExecution;
import org.springframework.stereotype.Repository;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.activiti.service.ActivitiBaseService;
import com.yffd.easy.workflow.mapper.WorkFlowDefinitionMapper;
import com.yffd.easy.workflow.model.dto.WorkFlowDefinitionDTO;

/**
 * @Description  流程定义管理器.
 * @Date		 2017年12月6日 下午5:05:58 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Repository
public class WorkFlowDefinitionDao extends ActivitiBaseService {

	public PageResult<WorkFlowDefinitionDTO> selectPageBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		Long totalRecord = this.selectCountBy(paramMap, pageParam);
		pageParam.setTotalRecord(totalRecord);
		
		List<WorkFlowDefinitionDTO> list = null;
		if(totalRecord>0) {
			list = this.selectListBy(paramMap, pageParam);
		}
		PageResult<WorkFlowDefinitionDTO> paginationResult = new PageResult<WorkFlowDefinitionDTO>(pageParam, list);
		return paginationResult;
	}
	
	public List<WorkFlowDefinitionDTO> selectListBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		
		CustomSqlExecution<WorkFlowDefinitionMapper, List<WorkFlowDefinitionDTO>> customSqlExecution = 
				new AbstractCustomSqlExecution<WorkFlowDefinitionMapper, List<WorkFlowDefinitionDTO>>(WorkFlowDefinitionMapper.class) {
					@Override
					public List<WorkFlowDefinitionDTO> execute(WorkFlowDefinitionMapper mapper) {
						if(null!=pageParam) paramMap.put("pageParam", pageParam);
						return mapper.selectListBy(paramMap);
					}
			
				};
		
		List<WorkFlowDefinitionDTO> list = this.getManagementService().executeCustomSql(customSqlExecution);
		return list;
	}
	
	public Long selectCountBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		
		CustomSqlExecution<WorkFlowDefinitionMapper, Long> customSqlExecution = 
				new AbstractCustomSqlExecution<WorkFlowDefinitionMapper, Long>(WorkFlowDefinitionMapper.class) {
			@Override
			public Long execute(WorkFlowDefinitionMapper mapper) {
				if(null!=pageParam) paramMap.put("pageParam", pageParam);
				return mapper.selectCountBy(paramMap);
			}
			
		};
		
		Long count = this.getManagementService().executeCustomSql(customSqlExecution);
		return count;
	}
	
	public WorkFlowDefinitionDTO selectByPK(final String id) {
		if(null==id || "".equals(id)) return null; 
		CustomSqlExecution<WorkFlowDefinitionMapper, WorkFlowDefinitionDTO> customSqlExecution = 
				new AbstractCustomSqlExecution<WorkFlowDefinitionMapper, WorkFlowDefinitionDTO>(WorkFlowDefinitionMapper.class) {
					@Override
					public WorkFlowDefinitionDTO execute(WorkFlowDefinitionMapper mapper) {
						return mapper.selectByPK(id);
					}
			
				};
		
		WorkFlowDefinitionDTO dto = this.getManagementService().executeCustomSql(customSqlExecution);
		return dto;
	}
}

