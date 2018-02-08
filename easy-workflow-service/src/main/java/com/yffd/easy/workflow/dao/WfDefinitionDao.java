package com.yffd.easy.workflow.dao;

import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.cmd.AbstractCustomSqlExecution;
import org.activiti.engine.impl.cmd.CustomSqlExecution;
import org.springframework.stereotype.Repository;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.activiti.service.ActivitiServiceManager;
import com.yffd.easy.workflow.mapper.WfDefinitionMapper;
import com.yffd.easy.workflow.model.dto.WfDefinitionDTO;

/**
 * @Description  流程定义.
 * @Date		 2017年12月6日 下午5:05:58 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Repository
public class WfDefinitionDao extends ActivitiServiceManager {

	public PageResult<WfDefinitionDTO> selectPageBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		Long totalRecord = this.selectCountBy(paramMap, pageParam);
		pageParam.setTotalRecord(totalRecord);
		
		List<WfDefinitionDTO> list = null;
		if(totalRecord>0) {
			list = this.selectListBy(paramMap, pageParam);
		}
		PageResult<WfDefinitionDTO> paginationResult = new PageResult<WfDefinitionDTO>(pageParam, list);
		return paginationResult;
	}
	
	public List<WfDefinitionDTO> selectListBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		
		CustomSqlExecution<WfDefinitionMapper, List<WfDefinitionDTO>> customSqlExecution = 
				new AbstractCustomSqlExecution<WfDefinitionMapper, List<WfDefinitionDTO>>(WfDefinitionMapper.class) {
					@Override
					public List<WfDefinitionDTO> execute(WfDefinitionMapper mapper) {
						if(null!=pageParam) paramMap.put("pageParam", pageParam);
						return mapper.selectListBy(paramMap);
					}
			
				};
		
		List<WfDefinitionDTO> list = this.getManagementService().executeCustomSql(customSqlExecution);
		return list;
	}
	
	public Long selectCountBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		
		CustomSqlExecution<WfDefinitionMapper, Long> customSqlExecution = 
				new AbstractCustomSqlExecution<WfDefinitionMapper, Long>(WfDefinitionMapper.class) {
			@Override
			public Long execute(WfDefinitionMapper mapper) {
				if(null!=pageParam) paramMap.put("pageParam", pageParam);
				return mapper.selectCountBy(paramMap);
			}
			
		};
		
		Long count = this.getManagementService().executeCustomSql(customSqlExecution);
		return count;
	}
	
	public WfDefinitionDTO selectByPK(final String id) {
		if(null==id || "".equals(id)) return null; 
		CustomSqlExecution<WfDefinitionMapper, WfDefinitionDTO> customSqlExecution = 
				new AbstractCustomSqlExecution<WfDefinitionMapper, WfDefinitionDTO>(WfDefinitionMapper.class) {
					@Override
					public WfDefinitionDTO execute(WfDefinitionMapper mapper) {
						return mapper.selectByPK(id);
					}
			
				};
		
		WfDefinitionDTO dto = this.getManagementService().executeCustomSql(customSqlExecution);
		return dto;
	}
}

