package com.yffd.easy.workflow.app.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yffd.easy.common.core.model.support.EasyModelConverter;
import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.app.dao.OaLeaveDao;
import com.yffd.easy.workflow.app.model.OaLeave;
import com.yffd.easy.workflow.app.service.OaLeaveService;
import com.yffd.easy.workflow.model.WorkFlowInfo;
import com.yffd.easy.workflow.service.WorkFlowTaskService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月19日 上午10:37:13 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service("oaLeaveService")
public class OaLeaveServiceImpl extends EasyModelConverter implements OaLeaveService {
	
	@Autowired
	private OaLeaveDao oaLeaveDao;
	@Autowired
	private WorkFlowTaskService workFlowTaskService;
	
	@Override
	public PageResult<OaLeave> findPage(Map<String, Object> paramMap, PageParam pageParam) {
		if(null==paramMap) paramMap = new HashMap<String, Object>();
		PageResult<Map<String, Object>> pageResult = this.oaLeaveDao.selectPageBy(paramMap, pageParam);
		return this.map2model(pageResult, OaLeave.class, true);
	}

	@Override
	public void add(OaLeave model) {
		if(null==model) return;
		this.oaLeaveDao.insert(model);;
	}

	@Override
	public void update(OaLeave model) {
		if(null==model) return;
		this.oaLeaveDao.updateByPK(model);
	}

	@Override
	public void delete(String id) {
		if(null==id || "".equals(id)) return;
		this.oaLeaveDao.deleteByPK(id);
	}
	
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void apply(OaLeave model) {
		if(null==model) return;
		WorkFlowInfo workFlowInfo = new WorkFlowInfo();
		workFlowInfo.setId(model.getWorkFlowKey());
//		WorkFlowResult result = this.workFlowTaskService.startTask(workFlowInfo);
//		model.setProcessInstanceId(result.getProcessInstanceId());
		this.add(model);
	}

}

