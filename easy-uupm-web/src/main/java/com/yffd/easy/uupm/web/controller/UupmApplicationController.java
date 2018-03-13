package com.yffd.easy.uupm.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.framework.domain.RespModel;
import com.yffd.easy.framework.web.view.vo.DataGridVO;
import com.yffd.easy.uupm.api.model.UupmApplicationModel;
import com.yffd.easy.uupm.service.UupmApplicationService;


/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年03月08日 13时41分34秒 <br/>
 * @author		 ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RestController
@RequestMapping("/uupm/app")
public class UupmApplicationController extends UupmBaseController {

	@Autowired
	private UupmApplicationService uupmApplicationService;
	
	@RequestMapping(value="/findPage", method=RequestMethod.POST)
	public RespModel findPage(@RequestParam Map<String, Object> paramMap) {
		PageParam pageParam = this.getPageParam(paramMap);
		PageResult<UupmApplicationModel> pageResult = this.uupmApplicationService.findPage(paramMap, pageParam);
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value="/findList", method=RequestMethod.POST)
	public RespModel findList(@RequestParam Map<String, Object> paramMap) {
		List<UupmApplicationModel> listResult = this.uupmApplicationService.findList(paramMap);
		DataGridVO dataGridVO = this.toDataGrid(listResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value="/findOne", method=RequestMethod.POST)
	public RespModel findOne(UupmApplicationModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getId())) return this.error("参数无效");
		UupmApplicationModel result = this.uupmApplicationService.findById(model.getId());
		return this.successAjax(result);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public RespModel add(UupmApplicationModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getAppCode())) return this.error("参数无效");
		// 存在判断
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("appCode", model.getAppCode());
		UupmApplicationModel result = this.uupmApplicationService.findOne(paramMap);
		if(null!=result) return this.error("编号已存在");
				
		this.uupmApplicationService.addOne(model, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public RespModel edit(UupmApplicationModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getId())) {
			return this.error("参数无效");
		}
		this.uupmApplicationService.updateBy(model, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/del", method=RequestMethod.POST)
	public RespModel del(HttpServletRequest req) {
		String id = req.getParameter("id");
		if(EasyStringCheckUtils.isEmpty(id)) return this.errorAjax("参数无效");
		this.uupmApplicationService.delById(id, null);
		return this.successAjax();
	}
	
}
