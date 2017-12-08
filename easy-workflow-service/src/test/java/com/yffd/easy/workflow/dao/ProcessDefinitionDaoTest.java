package com.yffd.easy.workflow.dao;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.model.ProcessDefinitionInfo;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月7日 上午10:54:32 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "classpath:spring/spring-context-service-workflow.xml"
})
public class ProcessDefinitionDaoTest {

	@Autowired
	private ProcessDefinitionDao processDefinitionDao;
	
	@Test
	public void selectListByTest() {
		String processDefinitionNameLike = "请假流程";
		String processDefinitionKey = null;
		boolean latestVersion = true;
		PageParam pageParam = new PageParam(1L, 10L);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("key", processDefinitionKey);
		paramMap.put("name", processDefinitionNameLike);
		paramMap.put("latestVersion", latestVersion);
		
		PageResult<ProcessDefinitionInfo> pageResult = this.processDefinitionDao.selectPageBy(paramMap, pageParam);
		Assert.assertNotNull(pageResult);
		System.out.println("总记录数：" + pageParam.getTotalRecord());
		System.out.println("总页数：" + pageParam.getPageTotal());
		System.out.println("当前页总条数：" + pageResult.getRecordList().size());
		System.out.println("当前页数据：" + pageResult.getRecordList());
		StringBuilder builder = new StringBuilder();
		builder.append("流程定义ID").append(" >>>>>>>>>>>>> ")
		.append("流程发布ID").append(" >>>>>>>>>>>>> ")
		.append("流程名称").append(" >>>>>>>>>>>>> ")
		.append("流程关键字").append(" >>>>>>>>>>>>> ")
		.append("版本号").append(" >>>>>>>>>>>>> ")
		.append("XML").append(" >>>>>>>>>>>>> ")
		.append("图片").append(" >>>>>>>>>>>>> ")
		.append("部署时间");
		System.out.println(builder);
		for(ProcessDefinitionInfo processDefinition : pageResult.getRecordList()) {
			StringBuilder builder1 = new StringBuilder();
			builder1.append(processDefinition.getId()).append(" >>>>>>>>>>>>> ")
			.append(processDefinition.getDeploymentId()).append(" >>>>>>>>>>>>> ")
			.append(processDefinition.getName()).append(" >>>>>>>>>>>>> ")
			.append(processDefinition.getKey()).append(" >>>>>>>>>>>>> ")
			.append(processDefinition.getVersion()).append(" >>>>>>>>>>>>> ")
			.append(processDefinition.getResourceName()).append(" >>>>>>>>>>>>> ")
			.append(processDefinition.getDiagramResourceName()).append(" >>>>>>>>>>>>> ")
			.append(processDefinition.getDeploymentTime());
			System.out.println(builder1);
		}
	}
}

