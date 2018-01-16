package com.yffd.easy.workflow.app.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.WorkFlowBaseTestCase;
import com.yffd.easy.workflow.app.model.OaLeave;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月19日 上午9:46:40 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class OaLeaveDaoTest extends WorkFlowBaseTestCase {

	@Autowired
	private OaLeaveDao oaLeaveDao;
	
	@Test
	public void selectPageByTest() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		PageParam pageParam = new PageParam(1L, 3L);
		PageResult<Map<String, Object>> pageResult = this.oaLeaveDao.selectPageBy(paramMap, pageParam);
		System.out.println(pageResult.getPageParam());
		System.out.println(pageResult.getRecordList());
	}
	
	@Test
	public void selectByPKTest() {
		Map<String, Object> resultMap = this.oaLeaveDao.selectByPK("");
		System.out.println(resultMap);
	}
	
	@Test
	public void insertTest() {
		OaLeave model = new OaLeave();
		model.setApplyTime(new Date());
		model.setUserCode("xiaohei");
		model.setStartTime(new Date());
		model.setEndTime(new Date());
		model.setReason("不知道");
		model.setLeaveType("1");
		this.oaLeaveDao.insert(model);
		System.out.println(model.getId());
	}
	
	@Test
	public void updateByPKTest() {
		OaLeave model = new OaLeave();
		model.setId("6");
		model.setApplyTime(new Date());
		model.setUserCode("xiaohei");
		model.setStartTime(new Date());
		model.setEndTime(new Date());
		model.setReason("不知道__");
		model.setLeaveType("1");
		this.oaLeaveDao.updateByPK(model);
	}
	@Test
	public void deleteByPKTest() {
		this.oaLeaveDao.deleteByPK("5");
	}
}

