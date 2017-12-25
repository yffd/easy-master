package com.yffd.easy.workflow.app.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.activiti.dao.WorkFlowBaseTestCase;
import com.yffd.easy.workflow.app.model.OaLeave;
import com.yffd.easy.workflow.app.service.OaLeaveService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月19日 上午10:37:13 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class OaLeaveServiceImplTest extends WorkFlowBaseTestCase {

	@Autowired
	private OaLeaveService oaLeaveService;
	
	@Test
	public void findPageTest() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		PageParam pageParam = new PageParam(1L, 3L);
		PageResult<OaLeave> pageResult = this.oaLeaveService.findPage(paramMap, pageParam);
		System.out.println(pageResult.getPageParam());
		List<OaLeave> list = pageResult.getRecordList();
		System.out.println(list.size());
	}

	@Test
	public void addTest() {
		OaLeave model = new OaLeave();
		model.setApplyTime(new Date());
		model.setUserCode("xiaohei");
		model.setStartTime(new Date());
		model.setEndTime(new Date());
		model.setReason("不知道");
		model.setLeaveType("1");
		this.oaLeaveService.add(model);
	}

	@Test
	public void updateTest() {
		OaLeave model = new OaLeave();
		model.setId("3");
		model.setApplyTime(new Date());
		model.setUserCode("xiaohei");
		model.setStartTime(new Date());
		model.setEndTime(new Date());
		model.setReason("不知道");
		model.setLeaveType("1");
		this.oaLeaveService.update(model);
	}

	@Test
	public void deleteTest() {
		this.oaLeaveService.delete("3");;
	}

}

