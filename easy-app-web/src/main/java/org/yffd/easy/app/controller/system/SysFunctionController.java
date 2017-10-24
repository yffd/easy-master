package org.yffd.easy.app.controller.system;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yffd.easy.app.controller.system.support.SysFunctionSupport;
import org.yffd.easy.app.controller.system.vo.SysFunctionTreeGridVO;
import org.yffd.easy.app.system.model.SysFunction;
import org.yffd.easy.app.system.service.SysFunctionService;
import org.yffd.easy.common.core.model.RespModel;
import org.yffd.easy.common.core.util.ValidUtils;
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
@RequestMapping("/sys/func")
public class SysFunctionController extends BaseController {
	@Autowired
	private SysFunctionSupport sysFunctionSupport;
	@Autowired
	private SysFunctionService sysFunctionService;
	
	/**
	 * 列出所有菜单
	 * @Date	2017年10月20日 下午5:30:02 <br/>
	 * @author  zhangST
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/listAllMenu", method=RequestMethod.POST)
	@ResponseBody
	public RespModel listAllMenu(HttpServletRequest req) {
		List<SysFunction> list = this.sysFunctionService.findAllMenu();
		if(!ValidUtils.isEmpty(list)) {
			List<SysFunctionTreeGridVO> treeList = this.sysFunctionSupport.toSyncTreeGridVO(list);
			return this.successAjax(treeList);
		}
		return this.errorAjax("未找到有效数据");
	}
	
	/**
	 * 列出菜单
	 * @Date	2017年10月20日 下午5:30:02 <br/>
	 * @author  zhangST
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/listLeftMenu", method=RequestMethod.POST)
	@ResponseBody
	public RespModel listMenu(HttpServletRequest req) {
		//TODO
		List<SysFunction> list = this.sysFunctionService.findAllMenu();
		if(!ValidUtils.isEmpty(list)) {
			List<SysFunctionTreeGridVO> treeList = this.sysFunctionSupport.toSyncTreeGridVO(list);
			return this.successAjax(treeList);
		}
		return this.errorAjax("未找到有效数据");
	}
	
	/**
	 * 列表查询：异步加载easyui tree列表
	 * @Date	2017年10月20日 下午5:31:08 <br/>
	 * @author  zhangST
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/asyncList", method=RequestMethod.POST)
	@ResponseBody
	public RespModel asyncList(HttpServletRequest req) {
		String parentCode = req.getParameter("id");
		if(ValidUtils.isEmpty(parentCode)) {
			parentCode = "-1";
		}
		List<SysFunction> list = this.sysFunctionService.findByParentCode(parentCode);
		if(!ValidUtils.isEmpty(list)) {
			List<SysFunctionTreeGridVO> voList = this.sysFunctionSupport.toAsyncTreeGridVO(list);
			return this.successAjax(voList);
		}
		return this.errorAjax("未找到有效数据");
	}
	
	/**
	 * 添加
	 * @Date	2017年10月20日 下午5:33:38 <br/>
	 * @author  zhangST
	 * @param req
	 * @param sysFunction
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public RespModel add(HttpServletRequest req, SysFunction sysFunction) {
		if(ValidUtils.isNull(sysFunction) || ValidUtils.isEmpty(sysFunction.getFuncCode())) {
			return this.error("参数无效");
		}
		SysFunction model = this.sysFunctionService.findByCode(sysFunction.getFuncCode());
		if(!ValidUtils.isNull(model)) {
			return this.errorAjax("编号已存在");
		}
		if(ValidUtils.isEmpty(sysFunction.getParentCode())) {
			sysFunction.setParentCode("-1");
		}
		sysFunction.setDefaultAdd("admin", new Date());
		this.sysFunctionService.add(sysFunction);
		return this.successAjax();
	}
	
	/**
	 * 修改
	 * @Date	2017年10月20日 下午5:33:51 <br/>
	 * @author  zhangST
	 * @param req
	 * @param sysFunction
	 * @return
	 */
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public RespModel edit(HttpServletRequest req, SysFunction sysFunction) {
		if(ValidUtils.isNull(sysFunction) || ValidUtils.isEmpty(sysFunction.getFuncCode())) {
			return this.error("参数无效");
		}
		if(ValidUtils.isEmpty(sysFunction.getParentCode())) {
			sysFunction.setParentCode("-1");
		}
		sysFunction.setDefaultUpdate("admin", new Date());
		this.sysFunctionService.editByCode(sysFunction);
		return this.successAjax();
	}
	
	/**
	 * 删除
	 * @Date	2017年10月20日 下午5:34:00 <br/>
	 * @author  zhangST
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/del", method=RequestMethod.POST)
	@ResponseBody
	public RespModel del(HttpServletRequest req) {
		String funcCode = req.getParameter("funcCode");
		if(ValidUtils.isEmpty(funcCode)) {
			return this.errorAjax("参数无效");
		}
		this.sysFunctionService.delByCode(funcCode);
		return this.successAjax();
	}
}

