package com.yffd.easy.workflow.service;

import java.io.InputStream;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月13日 下午5:38:35 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface WorkFlowTraceService {

	InputStream tarceWorkFlowByDiagram(String instanceId);
}

