package com.yffd.easy.web.pmi.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yffd.easy.common.core.model.RespModel;
import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;
import org.yffd.easy.common.core.util.ValidUtils;
import org.yffd.easy.common.core.view.vo.DataGridVO;
import org.yffd.easy.common.core.view.vo.SearchBoxVO;
import org.yffd.easy.common.ssm.web.BaseController;

import com.yffd.easy.pmi.model.PmiUser;
import com.yffd.easy.pmi.service.PmiUserService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月21日 下午1:47:50 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Controller
@RequestMapping("/pmi/user")
public class PmiUserController extends BaseController {

	@Autowired
	private PmiUserService pmiUserService;
	
	@RequestMapping(value="/findPage", method=RequestMethod.POST)
	@ResponseBody
	public RespModel listPage(PmiUser user, SearchBoxVO searchBoxVO) {
		PageParam pageParam = this.getPageParam(searchBoxVO);
		PageResult<PmiUser> pageResult = this.pmiUserService.findList(user, pageParam );
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public RespModel add(PmiUser user) {
		if(ValidUtils.isNull(user) 
				|| ValidUtils.isEmpty(user.getUserCode())
				|| ValidUtils.isEmpty(user.getUserName())) {
			return this.error("参数无效");
		}
		PmiUser _user = this.pmiUserService.findByCode(user.getUserCode());
		if(!ValidUtils.isNull(_user)) {
			return this.errorAjax("编号已存在");
		}
		user.setDefaultAdd("admin", new Date());
		this.pmiUserService.add(user);
		return this.successAjax();
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public RespModel edit(PmiUser user) {
		if(ValidUtils.isNull(user) 
				|| ValidUtils.isEmpty(user.getUserCode())) {
			return this.error("参数无效");
		}
		user.setDefaultUpdate("admin", new Date());
		this.pmiUserService.editByCode(user);
		return this.successAjax();
	}
	
	@RequestMapping(value="/del", method=RequestMethod.POST)
	@ResponseBody
	public RespModel del(HttpServletRequest req) {
		String userCode = req.getParameter("userCode");
		if(ValidUtils.isEmpty(userCode)) {
			return this.errorAjax("参数无效");
		}
		this.pmiUserService.delByCode(userCode);
		return this.successAjax();
	}
	
}

