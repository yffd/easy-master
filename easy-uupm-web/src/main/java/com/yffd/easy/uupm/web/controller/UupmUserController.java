package com.yffd.easy.uupm.web.controller;

import java.util.Arrays;
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
 * @Date		 2018年04月05日 17时25分29秒 <br/>
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
	
	@RequestMapping(value="/findPage", method=RequestMethod.POST)
	public RespModel findPage(@RequestParam Map<String, Object> paramMap) {
		PageParam paramPage = this.getPageParam(paramMap);
		//TODO  租户条件
		PageResult<Map<String, Object>> pageResult = this.uupmUserService.findUserInfo(paramMap, paramPage, null);
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value="/findOne", method=RequestMethod.POST)
	public RespModel findOne(UupmUserModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getId())) return this.error("参数无效");
		UupmUserModel result = this.uupmUserService.findOne(model, null);
		return this.successAjax(result);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public RespModel add(UupmUserModel model) {
		if(null==model) return this.error("参数无效");
		// 存在校验
		UupmUserModel paramModel = new UupmUserModel();
		paramModel.setTenantCode(model.getTenantCode());
		paramModel.setUserCode(model.getUserCode());
		UupmUserModel resultModel = this.uupmUserService.findOne(paramModel, null);
		if(null!=resultModel) return this.error("数据已存在");
		int result = this.uupmUserService.addUserWithAccount(model, null);
		if(result==0) return this.error("添加失败");
		return this.successAjax();
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public RespModel edit(UupmUserModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getId())) return this.error("参数无效");
		UupmUserModel paramOld = new UupmUserModel();
		paramOld.setId(model.getId());
		int result = this.uupmUserService.update(model, paramOld, null, null);
		if(result==0) return this.error("更新失败");
		return this.successAjax();
	}
	
	@RequestMapping(value="/delById", method=RequestMethod.POST)
	public RespModel delById(String id) {
		if(EasyStringCheckUtils.isEmpty(id)) return this.errorAjax("参数无效");
		int result = this.uupmUserService.deleteBy("id", id);
		if(result==0) return this.error("删除失败");
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
