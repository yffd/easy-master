package com.yffd.easy.web.workflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yffd.easy.common.core.model.RespModel;
import com.yffd.easy.common.support.web.mvc.BaseController;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月8日 上午11:15:53 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Controller
public class ProcessDefinitionController extends BaseController {

	@RequestMapping(value="/listPage", method=RequestMethod.POST)
	@ResponseBody
	public RespModel listPage() {
		return this.successAjax();
	}
}

