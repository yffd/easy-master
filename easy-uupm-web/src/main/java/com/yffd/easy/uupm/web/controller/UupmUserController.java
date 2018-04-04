package com.yffd.easy.uupm.web.controller;

import java.util.Arrays;
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
import com.yffd.easy.uupm.api.model.UupmUserModel;
import com.yffd.easy.uupm.service.UupmUserService;
import com.yffd.easy.uupm.web.common.UupmCommonController;


/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年03月20日 15时37分32秒 <br/>
 * @author		 ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RestController
@RequestMapping("/uupm/user")
public class UupmUserController extends UupmCommonController {

	@Autowired
	private UupmUserService uupmUserService;
	
//	@RequestMapping(value="/findPage", method=RequestMethod.POST)
//	public RespModel findPage(@RequestParam Map<String, Object> paramMap) {
//		PageParam pageParam = this.getPageParam(paramMap);
//		if(null==paramMap) paramMap = new HashMap<String, Object>();
//		paramMap.put("accountType", "default");
//		PageResult<Map<String, Object>> pageResult = this.uupmUserService.findPageForUserInfo(paramMap, pageParam, null);
//		DataGridVO dataGridVO = this.toDataGrid(pageResult);
//		return this.successAjax(dataGridVO);
//	}
	
	@RequestMapping(value="/findOne", method=RequestMethod.POST)
	public RespModel findOne(UupmUserModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getId())) return this.error("参数无效");
		UupmUserModel result = this.uupmUserService.findOne(model, null);
		return this.successAjax(result);
	}
	
//	@RequestMapping(value="/add", method=RequestMethod.POST)
//	public RespModel add(UupmUserModel model) {
//		if(null==model || EasyStringCheckUtils.isEmpty(model.getUserCode())) return this.error("参数无效");
//		if(EasyStringCheckUtils.isEmpty(model.getUserCode()) 
//				|| "root".equals(model.getOrgCode())) return this.error("请选择机构");
//		UupmUserModel paramModel = new UupmUserModel();
//		paramModel.setUserCode(model.getUserCode());
//		UupmUserModel result = this.uupmUserService.findOne(paramModel, null);
//		if(null!=result) return this.error("编号已存在");
//		this.uupmUserService.addUserWithAccount(model, null);
//		return this.successAjax();
//	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public RespModel edit(UupmUserModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getId())) { 
			return this.error("参数无效");
		}
		UupmUserModel paramOld = new UupmUserModel();
		paramOld.setId(model.getId());
		this.uupmUserService.update(model, paramOld, null, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/delById", method=RequestMethod.POST)
	public RespModel delById(String id) {
		if(EasyStringCheckUtils.isEmpty(id)) return this.errorAjax("参数无效");
		this.uupmUserService.deleteBy("id", id);
		return this.successAjax();
	}
	
	@RequestMapping(value="/delBatch", method=RequestMethod.POST)
	public RespModel delBatch(HttpServletRequest req) {
		String ids = req.getParameter("ids");
		if(EasyStringCheckUtils.isEmpty(ids)) return this.error("参数无效");
		String[] idsArr = ids.split(",");
		List<String> idsList = Arrays.asList(idsArr);
		int result = this.uupmUserService.delete("id", idsList);
		if(result==0) return this.error("删除失败");
		return this.successAjax();
	}
	
}
