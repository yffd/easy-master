package com.yffd.easy.workflow.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yffd.easy.framework.web.domain.EasyRespModel;
import com.yffd.easy.workflow.service.WfDeployService;

/**
 * @Description  流程发布模块.
 * @Date		 2018年1月17日 上午11:21:07 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RestController
@RequestMapping("/workflow/deploy")
public class WfDeployController extends WfBaseController {
	private static final Logger LOG = LoggerFactory.getLogger(WfDeployController.class);
	@Autowired
	private WfDeployService wfDeployService;
	
	@RequestMapping(value = "/uploadBPMN")
    public EasyRespModel deploy(HttpServletRequest request) {
		MultipartHttpServletRequest mul=(MultipartHttpServletRequest)request;
		Map<String,MultipartFile> files=mul.getFileMap();
		for(MultipartFile file : files.values()) {
			String resourceName = file.getOriginalFilename();
			String extension = FilenameUtils.getExtension(resourceName);
			try {
				if(extension.equals("zip") || extension.equals("bar")) {
					InputStream inputStream = file.getInputStream();
					ZipInputStream zipInputStream = new ZipInputStream(inputStream);
					String id = this.wfDeployService.deployByZipInputStream(zipInputStream);
					LOG.info("流程部署成功，流程部署ID：" + id);
				} else if(extension.equals("bpmn") || extension.equals("bpmn20.xml")) {
					InputStream inputStream = file.getInputStream();
					String id = this.wfDeployService.deployByInputStream(resourceName, inputStream);
					LOG.info("流程部署成功，流程部署ID：" + id);
				} else {
					return this.errorAjax("文件类型不支持");
				}
			} catch (IOException e) {
				return this.errorAjax("流程发布失败");
			}
	    }
		return this.successAjax();
	}
	
}

