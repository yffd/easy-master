package com.yffd.easy.workflow.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yffd.easy.workflow.WorkFlowBaseTestCase;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年1月17日 下午4:59:53 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class WfTraceServiceTest extends WorkFlowBaseTestCase {
	@Autowired
	private WfTraceService wfTraceService;
	
	@Test
	public void tarceWorkFlowByDiagramTest() throws IOException {
		String instanceId = "2501";
		InputStream inputStream = wfTraceService.traceWorkFlowByDiagram(instanceId);
		File file = new File("D:\\ddd\\aa.png");
		FileOutputStream out = new FileOutputStream(file);
		int bytesRead = 0;
		byte[] buffer = new byte[8192];
        while((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
        	out.write(buffer, 0, bytesRead);
        }
        out.close();
        inputStream.close();
	}
}

