package com.yffd.easy.workflow.app.dao;

import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.cmd.AbstractCustomSqlExecution;
import org.activiti.engine.impl.cmd.CustomSqlExecution;
import org.springframework.stereotype.Repository;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.activiti.service.ActivitiBaseService;
import com.yffd.easy.workflow.app.mapper.OaLeaveMapper;
import com.yffd.easy.workflow.app.model.OaLeave;

/**
 * @Description  
 * @Date		 2017年12月6日 下午5:05:58 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Repository
public class OaLeaveDao extends ActivitiBaseService {

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
		
		CustomSqlExecution<OaLeaveMapper, List<Map<String, Object>>> customSqlExecution = 
				new AbstractCustomSqlExecution<OaLeaveMapper, List<Map<String, Object>>>(OaLeaveMapper.class) {
					@Override
					public List<Map<String, Object>> execute(OaLeaveMapper mapper) {
						if(null!=pageParam) paramMap.put("pageParam", pageParam);
						return mapper.selectListBy(paramMap);
					}
			
				};
		
		List<Map<String, Object>> list = this.getManagementService().executeCustomSql(customSqlExecution);
		return list;
	}
	
	public Long selectCountBy(final Map<String, Object> paramMap, final PageParam pageParam) {
		
		CustomSqlExecution<OaLeaveMapper, Long> customSqlExecution = 
				new AbstractCustomSqlExecution<OaLeaveMapper, Long>(OaLeaveMapper.class) {
			@Override
			public Long execute(OaLeaveMapper mapper) {
				if(null!=pageParam) paramMap.put("pageParam", pageParam);
				return mapper.selectCountBy(paramMap);
			}
			
		};
		
		Long count = this.getManagementService().executeCustomSql(customSqlExecution);
		return count;
	}
	
	public Map<String, Object> selectByPK(final String leaveId) {
		if(null==leaveId || "".equals(leaveId)) return null; 
		CustomSqlExecution<OaLeaveMapper, Map<String, Object>> customSqlExecution = 
				new AbstractCustomSqlExecution<OaLeaveMapper, Map<String, Object>>(OaLeaveMapper.class) {
					@Override
					public Map<String, Object> execute(OaLeaveMapper mapper) {
						return mapper.selectByPK(leaveId);
					}
			
				};
		
		Map<String, Object> map = this.getManagementService().executeCustomSql(customSqlExecution);
		return map;
	}
	
	public void insert(final OaLeave model) {
		if(null==model) return; 
		CustomSqlExecution<OaLeaveMapper, Integer> customSqlExecution = 
				new AbstractCustomSqlExecution<OaLeaveMapper, Integer>(OaLeaveMapper.class) {
					@Override
					public Integer execute(OaLeaveMapper mapper) {
						return mapper.insert(model);
					}
			
				};
		
		this.getManagementService().executeCustomSql(customSqlExecution);
	}
	
	public void updateByPK(final OaLeave model) {
		if(null==model) return; 
		CustomSqlExecution<OaLeaveMapper, Integer> customSqlExecution = 
				new AbstractCustomSqlExecution<OaLeaveMapper, Integer>(OaLeaveMapper.class) {
			@Override
			public Integer execute(OaLeaveMapper mapper) {
				return mapper.updateByPK(model);
			}
			
		};
		
		this.getManagementService().executeCustomSql(customSqlExecution);
	}
	
	public void deleteByPK(final String id) {
		if(null==id || "".equals(id)) return; 
		CustomSqlExecution<OaLeaveMapper, Integer> customSqlExecution = 
				new AbstractCustomSqlExecution<OaLeaveMapper, Integer>(OaLeaveMapper.class) {
			@Override
			public Integer execute(OaLeaveMapper mapper) {
				return mapper.deleteByPK(id);
			}
			
		};
		
		this.getManagementService().executeCustomSql(customSqlExecution);
	}
	
}

