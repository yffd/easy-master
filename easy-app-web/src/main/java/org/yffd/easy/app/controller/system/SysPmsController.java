package org.yffd.easy.app.controller.system;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yffd.easy.app.system.model.SysPermission;
import org.yffd.easy.app.system.service.SysPermissionService;
import org.yffd.easy.common.core.model.RespEntity;
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
@RequestMapping("/sys/pms")
public class SysPmsController extends BaseController {
	@Autowired
	private SysPermissionService sysPermissionService;
	
	@RequestMapping(value="/list", method=RequestMethod.POST)
	@ResponseBody
	public RespEntity list(HttpServletRequest req, SearchBoxVO searchBoxVO) {
		String name = req.getParameter("name");
		String code = req.getParameter("code");
		PageParam pageParam = this.getPageParam(searchBoxVO); //获取分页参数
		PageResult<SysPermission> pageResult = this.sysPermissionService.findList(name, code, pageParam);
		DataGridVO dataGrid = this.toDataGrid(pageResult); //转换成datagrid数据格式
		return this.successAjax(dataGrid);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public RespEntity add(HttpServletRequest req, SysPermission sysPermission) {
		if(ValidUtils.isNull(sysPermission) || ValidUtils.isEmpty(sysPermission.getCode())) {
			return this.error("参数无效");
		}
		SysPermission pms = this.sysPermissionService.findByCode(sysPermission.getCode());
		if(!ValidUtils.isNull(pms)) {
			return this.errorAjax("编号已存在");
		}
		sysPermission.setDefaultAdd("admin", new Date());
		this.sysPermissionService.add(sysPermission);
		return this.successAjax();
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public RespEntity edit(HttpServletRequest req, SysPermission sysPermission) {
		if(ValidUtils.isNull(sysPermission) || ValidUtils.isEmpty(sysPermission.getCode())) {
			return this.error("参数无效");
		}
		sysPermission.setDefaultUpdate("admin", new Date());
		this.sysPermissionService.editByCode(sysPermission);
		return this.successAjax();
	}
	
	@RequestMapping(value="/del", method=RequestMethod.POST)
	@ResponseBody
	public RespEntity del(HttpServletRequest req) {
		String code = req.getParameter("code");
		if(ValidUtils.isEmpty(code)) {
			return this.errorAjax("参数无效");
		}
		this.sysPermissionService.delByCode(code);
		return this.successAjax();
	}
}

