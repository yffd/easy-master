package com.yffd.easy.uupm.web.controller;

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
import com.yffd.easy.uupm.api.model.UupmAccountModel;
import com.yffd.easy.uupm.service.UupmAccountService;


/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年03月23日 17时47分21秒 <br/>
 * @author		 ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RestController
@RequestMapping("/uupm/account")
public class UupmAccountController extends UupmBaseController {

	@Autowired
	private UupmAccountService uupmAccountService;
	
	@RequestMapping(value="/findPage", method=RequestMethod.POST)
	public RespModel findPage(@RequestParam Map<String, Object> paramMap) {
		if(EasyStringCheckUtils.isEmpty((String) paramMap.get("tenantCode"))) return this.error("参数无效"); 
		PageParam pageParam = this.getPageParam(paramMap);
		paramMap.put("accountType", "admin");
		PageResult<UupmAccountModel> pageResult = this.uupmAccountService.findPage(paramMap, pageParam);
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value="/findOne", method=RequestMethod.POST)
	public RespModel findOne(UupmAccountModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getId())) return this.error("参数无效");
		UupmAccountModel result = this.uupmAccountService.findById(model.getId());
		return this.successAjax(result);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public RespModel add(UupmAccountModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getTenantCode())
				|| EasyStringCheckUtils.isEmpty(model.getAccountId())) return this.error("参数无效");
		// 存在校验
		UupmAccountModel paramModel = new UupmAccountModel();
		paramModel.setTenantCode(model.getTenantCode());
		paramModel.setAccountId(model.getAccountId());
		UupmAccountModel result = this.uupmAccountService.findOne(paramModel);
		if(null!=result) return this.error("账号已存在");
		model.setAccountType("admin");
		this.uupmAccountService.addOne(model, null); // 添加账号
		return this.successAjax();
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	public RespModel edit(UupmAccountModel model) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getId())) {
			return this.error("参数无效");
		}
		this.uupmAccountService.updateById(model, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/delById", method=RequestMethod.POST)
	public RespModel delById(String id) {
		if(EasyStringCheckUtils.isEmpty(id)) return this.errorAjax("参数无效");
		this.uupmAccountService.delById(id, null);
		return this.successAjax();
	}
	
	@RequestMapping(value="/delBatch", method=RequestMethod.POST)
	public RespModel delBatch(HttpServletRequest req) {
		String ids = req.getParameter("ids");
		if(EasyStringCheckUtils.isEmpty(ids)) return this.error("参数无效");
		int result = this.uupmAccountService.delByIds(ids, null);
		if(result==-1) return this.error("参数无效");
		return this.successAjax();
	}
	
	@RequestMapping(value="/updateStatus", method=RequestMethod.POST)
	public RespModel updateStatus(String accountId, String accountStatus) {
		if(EasyStringCheckUtils.isEmpty(accountId) || EasyStringCheckUtils.isEmpty(accountStatus)) return this.errorAjax("参数无效");
		UupmAccountModel model = new UupmAccountModel();
		model.setAccountId(accountId);
		model.setAccountStatus(accountStatus);
		this.uupmAccountService.updateBy(model, "accountId", null);
		return this.successAjax();
	}
	
}
