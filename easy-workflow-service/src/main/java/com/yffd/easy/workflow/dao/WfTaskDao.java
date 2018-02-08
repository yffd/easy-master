package com.yffd.easy.workflow.dao;

import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.cmd.AbstractCustomSqlExecution;
import org.activiti.engine.impl.cmd.CustomSqlExecution;
import org.springframework.stereotype.Repository;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.activiti.service.ActivitiServiceManager;
import com.yffd.easy.workflow.mapper.WfTaskMapper;
import com.yffd.easy.workflow.model.dto.WfTaskDTO;

/**
 * @Description  流程任务.
 * @Date		 2017年12月6日 下午5:05:58 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Repository
public class WfTaskDao extends ActivitiServiceManager {

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
		
		CustomSqlExecution<WfTaskMapper, List<WfTaskDTO>> customSqlExecution = 
				new AbstractCustomSqlExecution<WfTaskMapper, List<WfTaskDTO>>(WfTaskMapper.class) {
					@Override
					public List<WfTaskDTO> execute(WfTaskMapper mapper) {
						if(null!=pageParam) paramMap.put("pageParam", pageParam);
						return mapper.selectListBy(paramMap);
					}
			
				};
		
		List<WfTaskDTO> list = this.getManagementService().executeCustomSql(customSqlExecution);
		return list;
	}
	
	public Long selectCountBy(final Map<String, Object> paramMap) {
		
		CustomSqlExecution<WfTaskMapper, Long> customSqlExecution = 
				new AbstractCustomSqlExecution<WfTaskMapper, Long>(WfTaskMapper.class) {
			@Override
			public Long execute(WfTaskMapper mapper) {
				return mapper.selectCountBy(paramMap);
			}
			
		};
		
		Long count = this.getManagementService().executeCustomSql(customSqlExecution);
		return count;
	}
	
}

