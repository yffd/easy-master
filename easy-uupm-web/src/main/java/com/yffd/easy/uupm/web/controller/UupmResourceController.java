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
import com.yffd.easy.uupm.api.model.UupmResourceModel;
import com.yffd.easy.uupm.service.UupmResourceService;
import com.yffd.easy.uupm.web.support.UupmComboTreeSupport;
import com.yffd.easy.uupm.web.vo.UupmResourceComboTreeVO;


/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年03月08日 15时17分43秒 <br/>
 * @author		 ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RestController
@RequestMapping("/uupm/resource")
public class UupmResourceController extends UupmBaseController {

	@Autowired
	private UupmComboTreeSupport uupmComboTreeSupport;
	@Autowired
	private UupmResourceService uupmResourceService;
	
	@RequestMapping(value="/findResWithAppTree", method=RequestMethod.POST)
	public RespModel findResWithAppTree() {
		List<UupmResourceModel> listResult = this.uupmResourceService.findResWithAppList();
		if(null!=listResult && !listResult.isEmpty()) {
			List<UupmResourceComboTreeVO> treeList = this.uupmComboTreeSupport.toSyncTreeVO(listResult, "-1");
			return this.successAjax(treeList);
		}
		return this.successAjax();
	}
	
	@RequestMapping(value="/findTree", method=RequestMethod.POST)
	public RespModel findList(@RequestParam Map<String, Object> paramMap) {
		String appCode = (String) paramMap.get("appCode");
		if(null==appCode || EasyStringCheckUtils.isEmpty(appCode)) return this.error("参数无效");
		List<UupmResourceModel> listResult = this.uupmResourceService.findList(paramMap);
		if(null!=listResult && !listResult.isEmpty()) {
			List<UupmResourceComboTreeVO> treeList = this.uupmComboTreeSupport.toSyncTreeVO(listResult, appCode);
			return this.successAjax(treeList);
		}
		return this.successAjax();
	}
	
	@RequestMapping(value="/findOne", method=RequestMethod.POST)
	public RespModel findOne(UupmResourceModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getId())) return this.error("参数无效");
		UupmResourceModel result = this.uupmResourceService.findById(model.getId());
		return this.successAjax(result);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public RespModel add(UupmResourceModel model) {
		if(null==model) return this.error("参数无效");
		String appCode = model.getAppCode();
		String rsCode = model.getRsCode();
		if(EasyStringCheckUtils.isEmpty(appCode) || EasyStringCheckUtils.isEmpty(model.getRsCode())) return this.error("参数无效");
		// 存在判断
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("appCode", appCode);
		paramMap.put("rsCode", rsCode);
		UupmResourceModel result = this.uupmResourceService.findOne(paramMap);
		if(null!=result) return this.error("编号已存在");
		// 如果parentCode为空，则设置为appCode
		String parentCode = model.getParentCode();
		if(EasyStringCheckUtils.isEmpty(parentCode)) {
			model.setParentCode(appCode);
		}
		this.uupmResourceService.addOne(model, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public RespModel edit(UupmResourceModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getId())) {
			return this.error("参数无效");
		}
		this.uupmResourceService.updateById(model, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/delById", method=RequestMethod.POST)
	public RespModel delById(String id) {
		if(EasyStringCheckUtils.isEmpty(id)) return this.error("参数无效");
		this.uupmResourceService.delById(id, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/delBatch", method=RequestMethod.POST)
	public RespModel delBatch(HttpServletRequest req) {
		String rsCodes = req.getParameter("rsCodes");
		if(EasyStringCheckUtils.isEmpty(rsCodes)) return this.error("参数无效");
		int result = this.uupmResourceService.delWithInBy("rsCode", rsCodes, null);
		if(result==-1) return this.error("参数无效");
		return this.successAjax();
	}
	
}
