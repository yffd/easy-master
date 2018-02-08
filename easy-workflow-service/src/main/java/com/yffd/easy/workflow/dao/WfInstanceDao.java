package com.yffd.easy.workflow.dao;

import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.cmd.AbstractCustomSqlExecution;
import org.activiti.engine.impl.cmd.CustomSqlExecution;
import org.springframework.stereotype.Repository;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.activiti.service.ActivitiServiceManager;
import com.yffd.easy.workflow.mapper.WfInstanceMapper;
import com.yffd.easy.workflow.model.dto.WfInstanceDTO;

/**
 * @Description  流程实例.
 * @Date		 2017年12月6日 下午5:05:58 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Repository
public class WfInstanceDao extends ActivitiServiceManager {

	public PageResult<WfInstanceDTO> selectPageBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		Long totalRecord = this.selectCountBy(paramMap, pageParam);
		pageParam.setTotalRecord(totalRecord);
		
		List<WfInstanceDTO> list = null;
		if(totalRecord>0) {
			list = this.selectListBy(paramMap, pageParam);
		}
		PageResult<WfInstanceDTO> paginationResult = new PageResult<WfInstanceDTO>(pageParam, list);
		return paginationResult;
	}
	
	public List<WfInstanceDTO> selectListBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		
		CustomSqlExecution<WfInstanceMapper, List<WfInstanceDTO>> customSqlExecution = 
				new AbstractCustomSqlExecution<WfInstanceMapper, List<WfInstanceDTO>>(WfInstanceMapper.class) {
					@Override
					public List<WfInstanceDTO> execute(WfInstanceMapper mapper) {
						if(null!=pageParam) paramMap.put("pageParam", pageParam);
						return mapper.selectListBy(paramMap);
					}
			
				};
		
		List<WfInstanceDTO> list = this.getManagementService().executeCustomSql(customSqlExecution);
		return list;
	}
	
	public Long selectCountBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		
		CustomSqlExecution<WfInstanceMapper, Long> customSqlExecution = 
				new AbstractCustomSqlExecution<WfInstanceMapper, Long>(WfInstanceMapper.class) {
			@Override
			public Long execute(WfInstanceMapper mapper) {
				if(null!=pageParam) paramMap.put("pageParam", pageParam);
				return mapper.selectCountBy(paramMap);
			}
			
		};
		
		Long count = this.getManagementService().executeCustomSql(customSqlExecution);
		return count;
	}
	
	public WfInstanceDTO selectByPK(final String id) {
		if(null==id || "".equals(id)) return null; 
		CustomSqlExecution<WfInstanceMapper, WfInstanceDTO> customSqlExecution = 
				new AbstractCustomSqlExecution<WfInstanceMapper, WfInstanceDTO>(WfInstanceMapper.class) {
					@Override
					public WfInstanceDTO execute(WfInstanceMapper mapper) {
						return mapper.selectByPK(id);
					}
			
				};
		
		WfInstanceDTO result = this.getManagementService().executeCustomSql(customSqlExecution);
		return result;
	}
}

