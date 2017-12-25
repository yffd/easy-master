package com.yffd.easy.workflow.dao;

import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.cmd.AbstractCustomSqlExecution;
import org.activiti.engine.impl.cmd.CustomSqlExecution;
import org.springframework.stereotype.Repository;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.activiti.service.ActivitiBaseService;
import com.yffd.easy.workflow.mapper.WorkFlowInstanceMapper;
import com.yffd.easy.workflow.model.dto.WorkFlowInstanceDTO;

/**
 * @Description  流程实例持久化操作.
 * @Date		 2017年12月6日 下午5:05:58 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Repository
public class WorkFlowInstanceDao extends ActivitiBaseService {

	public PageResult<WorkFlowInstanceDTO> selectPageBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		Long totalRecord = this.selectCountBy(paramMap, pageParam);
		pageParam.setTotalRecord(totalRecord);
		
		List<WorkFlowInstanceDTO> list = null;
		if(totalRecord>0) {
			list = this.selectListBy(paramMap, pageParam);
		}
		PageResult<WorkFlowInstanceDTO> paginationResult = new PageResult<WorkFlowInstanceDTO>(pageParam, list);
		return paginationResult;
	}
	
	public List<WorkFlowInstanceDTO> selectListBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		
		CustomSqlExecution<WorkFlowInstanceMapper, List<WorkFlowInstanceDTO>> customSqlExecution = 
				new AbstractCustomSqlExecution<WorkFlowInstanceMapper, List<WorkFlowInstanceDTO>>(WorkFlowInstanceMapper.class) {
					@Override
					public List<WorkFlowInstanceDTO> execute(WorkFlowInstanceMapper mapper) {
						if(null!=pageParam) paramMap.put("pageParam", pageParam);
						return mapper.selectListBy(paramMap);
					}
			
				};
		
		List<WorkFlowInstanceDTO> list = this.getManagementService().executeCustomSql(customSqlExecution);
		return list;
	}
	
	public Long selectCountBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		
		CustomSqlExecution<WorkFlowInstanceMapper, Long> customSqlExecution = 
				new AbstractCustomSqlExecution<WorkFlowInstanceMapper, Long>(WorkFlowInstanceMapper.class) {
			@Override
			public Long execute(WorkFlowInstanceMapper mapper) {
				if(null!=pageParam) paramMap.put("pageParam", pageParam);
				return mapper.selectCountBy(paramMap);
			}
			
		};
		
		Long count = this.getManagementService().executeCustomSql(customSqlExecution);
		return count;
	}
	
	public WorkFlowInstanceDTO selectByPK(final String id) {
		if(null==id || "".equals(id)) return null; 
		CustomSqlExecution<WorkFlowInstanceMapper, WorkFlowInstanceDTO> customSqlExecution = 
				new AbstractCustomSqlExecution<WorkFlowInstanceMapper, WorkFlowInstanceDTO>(WorkFlowInstanceMapper.class) {
					@Override
					public WorkFlowInstanceDTO execute(WorkFlowInstanceMapper mapper) {
						return mapper.selectByPK(id);
					}
			
				};
		
		WorkFlowInstanceDTO result = this.getManagementService().executeCustomSql(customSqlExecution);
		return result;
	}
}

