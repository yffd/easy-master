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

	public PageResult<Map<String, Object>> selectPageBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		Long totalRecord = this.selectCountBy(paramMap, pageParam);
		pageParam.setTotalRecord(totalRecord);
		
		List<Map<String, Object>> list = null;
		if(totalRecord>0) {
			list = this.selectListBy(paramMap, pageParam);
		}
		PageResult<Map<String, Object>> paginationResult = new PageResult<Map<String, Object>>(pageParam, list);
		return paginationResult;
	}
	
	public List<Map<String, Object>> selectListBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		
		CustomSqlExecution<WorkFlowDefinitionMapper, List<Map<String, Object>>> customSqlExecution = 
				new AbstractCustomSqlExecution<WorkFlowDefinitionMapper, List<Map<String, Object>>>(WorkFlowDefinitionMapper.class) {
					@Override
					public List<Map<String, Object>> execute(WorkFlowDefinitionMapper mapper) {
						if(null!=pageParam) paramMap.put("pageParam", pageParam);
						return mapper.selectListBy(paramMap);
					}
			
				};
		
		List<Map<String, Object>> list = this.getManagementService().executeCustomSql(customSqlExecution);
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
	
	public Map<String, Object> selectByPK(final String id) {
		if(null==id || "".equals(id)) return null; 
		CustomSqlExecution<WorkFlowDefinitionMapper, Map<String, Object>> customSqlExecution = 
				new AbstractCustomSqlExecution<WorkFlowDefinitionMapper, Map<String, Object>>(WorkFlowDefinitionMapper.class) {
					@Override
					public Map<String, Object> execute(WorkFlowDefinitionMapper mapper) {
						return mapper.selectByPK(id);
					}
			
				};
		
		Map<String, Object> map = this.getManagementService().executeCustomSql(customSqlExecution);
		return map;
	}
}

