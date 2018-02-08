package com.yffd.easy.workflow.dao;

import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.cmd.AbstractCustomSqlExecution;
import org.activiti.engine.impl.cmd.CustomSqlExecution;
import org.springframework.stereotype.Repository;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.activiti.service.ActivitiServiceManager;
import com.yffd.easy.workflow.mapper.WfHistoryTaskMapper;
import com.yffd.easy.workflow.model.dto.WfHistoryTaskDTO;

/**
 * @Description  流程历史任务.
 * @Date		 2017年12月6日 下午5:05:58 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Repository
public class WfHistoryTaskDao extends ActivitiServiceManager {

	public PageResult<WfHistoryTaskDTO> selectPageBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		Long totalRecord = this.selectCountBy(paramMap);
		pageParam.setTotalRecord(totalRecord);
		
		List<WfHistoryTaskDTO> list = null;
		if(totalRecord>0) {
			list = this.selectListBy(paramMap, pageParam);
		}
		PageResult<WfHistoryTaskDTO> paginationResult = new PageResult<WfHistoryTaskDTO>(pageParam, list);
		return paginationResult;
	}
	
	public List<WfHistoryTaskDTO> selectListBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		
		CustomSqlExecution<WfHistoryTaskMapper, List<WfHistoryTaskDTO>> customSqlExecution = 
				new AbstractCustomSqlExecution<WfHistoryTaskMapper, List<WfHistoryTaskDTO>>(WfHistoryTaskMapper.class) {
					@Override
					public List<WfHistoryTaskDTO> execute(WfHistoryTaskMapper mapper) {
						if(null!=pageParam) paramMap.put("pageParam", pageParam);
						return mapper.selectListBy(paramMap);
					}
			
				};
		
		List<WfHistoryTaskDTO> list = this.getManagementService().executeCustomSql(customSqlExecution);
		return list;
	}
	
	public Long selectCountBy(final Map<String, Object> paramMap) {
		
		CustomSqlExecution<WfHistoryTaskMapper, Long> customSqlExecution = 
				new AbstractCustomSqlExecution<WfHistoryTaskMapper, Long>(WfHistoryTaskMapper.class) {
			@Override
			public Long execute(WfHistoryTaskMapper mapper) {
				return mapper.selectCountBy(paramMap);
			}
			
		};
		
		Long count = this.getManagementService().executeCustomSql(customSqlExecution);
		return count;
	}
	
}

