package com.yffd.easy.workflow.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.dao.WfDefinitionDao;
import com.yffd.easy.workflow.dao.WfHistoryInstanceDao;
import com.yffd.easy.workflow.dao.WfHistoryTaskDao;
import com.yffd.easy.workflow.dao.WfInstanceDao;
import com.yffd.easy.workflow.dao.WfTaskDao;
import com.yffd.easy.workflow.dao.WfTodoTaskDao;
import com.yffd.easy.workflow.model.dto.WfDefinitionDTO;
import com.yffd.easy.workflow.model.dto.WfHistoryInstanceDTO;
import com.yffd.easy.workflow.model.dto.WfHistoryTaskDTO;
import com.yffd.easy.workflow.model.dto.WfInstanceDTO;
import com.yffd.easy.workflow.model.dto.WfTaskDTO;

/**
 * @Description  流程查询相关.
 * @Date		 2018年1月16日 下午6:00:17 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class WfQueryService extends WfCommonService {
	@Autowired
	private WfDefinitionDao wfDefinitionDao;
	@Autowired
	private WfInstanceDao wfInstanceDao;
	@Autowired
	private WfTaskDao wfTaskDao;
	@Autowired
	private WfTodoTaskDao wfTodoTaskDao;
	@Autowired
	private WfHistoryInstanceDao wfHistoryInstanceDao;
	@Autowired
	private WfHistoryTaskDao wfHistoryTaskDao;

	/**
	 * 分页查询：流程定义
	 * @Date	2018年1月17日 下午3:35:35 <br/>
	 * @author  zhangST
	 * @param model
	 * @param pageParam
	 * @return
	 */
	public PageResult<WfDefinitionDTO> findDefinitionListPage(WfDefinitionDTO model, PageParam pageParam) {
		Map<String, Object> paramMap = this.model2map(model, null);
		PageResult<WfDefinitionDTO> pageResult = this.wfDefinitionDao.selectPageBy(paramMap, pageParam);
		return pageResult;
	}
	
	/**
	 * 单体查询：流程发布
	 * @Date	2018年1月17日 下午3:36:03 <br/>
	 * @author  zhangST
	 * @param model
	 * @param pageParam
	 * @return
	 */
	public WfDefinitionDTO findDeployByPK(String id) {
		return this.wfDefinitionDao.selectByPK(id);
	}
	
	/**
	 * 分页查询：流程实例
	 * @Date	2018年1月17日 下午3:36:21 <br/>
	 * @author  zhangST
	 * @param model
	 * @param pageParam
	 * @return
	 */
	public PageResult<WfInstanceDTO> findInstanceListPage(WfInstanceDTO model, PageParam pageParam) {
		Map<String, Object> paramMap = this.model2map(model, null);
		return this.wfInstanceDao.selectPageBy(paramMap, pageParam);
	}

	/**
	 * 单体查询：流程实例
	 * @Date	2018年1月17日 下午3:36:33 <br/>
	 * @author  zhangST
	 * @param model
	 * @param pageParam
	 * @return
	 */
//	public WfInstanceDTO findInstanceByPK(String id) {
//		return this.wfInstanceDao.selectByPK(id);
//	}
	
	/**
	 * 分页查询：流程任务
	 * @Date	2018年1月17日 下午3:36:47 <br/>
	 * @author  zhangST
	 * @param model
	 * @param pageParam
	 * @return
	 */
	public PageResult<WfTaskDTO> findTaskListPage(WfTaskDTO model, PageParam pageParam) {
		Map<String, Object> paramMap = this.model2map(model, null);
		return this.wfTaskDao.selectPageBy(paramMap, pageParam);
	}
	
	/**
	 * 分页查询：历史实例
	 * @Date	2018年1月17日 下午3:37:00 <br/>
	 * @author  zhangST
	 * @param model
	 * @param pageParam
	 * @return
	 */
	public PageResult<WfHistoryInstanceDTO> findHistoryInstanceListPage(WfHistoryInstanceDTO model, PageParam pageParam) {
		Map<String, Object> paramMap = this.model2map(model, null);
		return this.wfHistoryInstanceDao.selectPageBy(paramMap, pageParam);
	}
	
	/**
	 * 分页查询：流程历史任务
	 * @Date	2018年1月17日 下午3:37:36 <br/>
	 * @author  zhangST
	 * @param model
	 * @param pageParam
	 * @return
	 */
	public PageResult<WfHistoryTaskDTO> findHistoryTaskListPage(WfHistoryTaskDTO model, PageParam pageParam) {
		Map<String, Object> paramMap = this.model2map(model, null);
		return this.wfHistoryTaskDao.selectPageBy(paramMap, pageParam);
	}
	
	/**
	 * 分页查询：流程待办任务
	 * @Date	2018年1月17日 下午3:37:54 <br/>
	 * @author  zhangST
	 * @param userIds
	 * @param roleIds
	 * @param model
	 * @param pageParam
	 * @return
	 */
	public PageResult<WfTaskDTO> findTodoTaskListPage(Set<String> userIds, Set<String> roleIds, 
			WfTaskDTO model, PageParam pageParam) {
		Map<String, Object> paramMap = this.model2map(model, null);
		if(null==paramMap) paramMap = new HashMap<String, Object>();
		if(null!=userIds && userIds.size()>0) paramMap.put("userIds", userIds);
		if(null!=roleIds && roleIds.size()>0) paramMap.put("roleIds", roleIds);
		return this.wfTodoTaskDao.selectPageBy(paramMap, pageParam);
	}
}

