package org.yffd.easy.app.controller.system;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yffd.easy.app.system.model.SysUser;
import org.yffd.easy.app.system.service.SysUserService;
import org.yffd.easy.common.core.model.RespModel;
import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;
import org.yffd.easy.common.core.util.ValidUtils;
import org.yffd.easy.common.core.view.vo.DataGridVO;
import org.yffd.easy.common.core.view.vo.SearchBoxVO;
import org.yffd.easy.common.ssm.web.BaseController;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月21日 下午1:47:50 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Controller
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {

	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping(value="/listPage", method=RequestMethod.POST)
	@ResponseBody
	public RespModel listPage(SysUser sysUser, SearchBoxVO searchBoxVO) {
		PageParam pageParam = this.getPageParam(searchBoxVO);
		PageResult<SysUser> pageResult = this.sysUserService.findList(sysUser, pageParam );
		DataGridVO dataGridVO = this.toDataGrid(pageResult);
		return this.successAjax(dataGridVO);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public RespModel add(SysUser sysUser) {
		if(ValidUtils.isNull(sysUser) 
				|| ValidUtils.isEmpty(sysUser.getUserCode())
				|| ValidUtils.isEmpty(sysUser.getUserName())) {
			return this.error("参数无效");
		}
		SysUser org = this.sysUserService.findByCode(sysUser.getUserCode());
		if(!ValidUtils.isNull(org)) {
			return this.errorAjax("编号已存在");
		}
		sysUser.setDefaultAdd("admin", new Date());
		this.sysUserService.add(sysUser);
		return this.successAjax();
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public RespModel edit(SysUser sysUser) {
		if(ValidUtils.isNull(sysUser) 
				|| ValidUtils.isEmpty(sysUser.getUserCode())) {
			return this.error("参数无效");
		}
		sysUser.setDefaultUpdate("admin", new Date());
		this.sysUserService.editByCode(sysUser);
		return this.successAjax();
	}
	
	@RequestMapping(value="/del", method=RequestMethod.POST)
	@ResponseBody
	public RespModel del(HttpServletRequest req) {
		String userCode = req.getParameter("userCode");
		if(ValidUtils.isEmpty(userCode)) {
			return this.errorAjax("参数无效");
		}
		this.sysUserService.delByCode(userCode);
		return this.successAjax();
	}
	
}

