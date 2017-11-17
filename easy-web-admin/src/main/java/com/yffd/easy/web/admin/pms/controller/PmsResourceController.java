package com.yffd.easy.web.admin.pms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yffd.easy.common.core.model.RespModel;
import org.yffd.easy.common.core.util.ValidUtils;

import com.yffd.easy.admin.login.model.LoginInfo;
import com.yffd.easy.admin.pms.model.PmsResource;
import com.yffd.easy.admin.pms.service.PmsResourceService;
import com.yffd.easy.web.admin.AdminBaseController;
import com.yffd.easy.web.admin.pms.support.PmsResourceSupport;
import com.yffd.easy.web.admin.pms.vo.PmsResourceTreeGridVO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月21日 下午1:47:50 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Controller
@RequestMapping("/pms/resource")
public class PmsResourceController extends AdminBaseController {
	@Autowired
	private PmsResourceSupport pmsResourceSupport;
	@Autowired
	private PmsResourceService pmsResourceService;
	
	/**
	 * 列出所有资源
	 * @Date	2017年10月20日 下午5:30:02 <br/>
	 * @author  zhangST
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/listAll", method=RequestMethod.POST)
	@ResponseBody
	public RespModel listAll(HttpServletRequest req) {
		List<PmsResource> list = this.pmsResourceService.findAll();
		if(!ValidUtils.isEmpty(list)) {
			List<PmsResourceTreeGridVO> treeList = this.pmsResourceSupport.toSyncTreeGridVO(list);
			return this.successAjax(treeList);
		}
		return this.errorAjax("未找到有效数据");
	}
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
		List<PmsResource> list = this.pmsResourceService.findAllMenu();
		if(!ValidUtils.isEmpty(list)) {
			List<PmsResourceTreeGridVO> treeList = this.pmsResourceSupport.toSyncTreeGridVO(list);
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
		LoginInfo info = this.currentLoginInfo();
		if(null!=info && !"".equals(info.getUserCode())) {
			List<PmsResource> list = this.pmsResourceService.findMenuByUser(info.getUserCode());
			if(!ValidUtils.isEmpty(list)) {
				List<PmsResourceTreeGridVO> treeList = this.pmsResourceSupport.toSyncTreeGridVO(list);
				return this.successAjax(treeList);
			}
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
	@RequiresPermissions("rs-view")
	@RequestMapping(value="/asyncList", method=RequestMethod.POST)
	@ResponseBody
	public RespModel asyncList(HttpServletRequest req) {
		String parentCode = req.getParameter("id");
		if(ValidUtils.isEmpty(parentCode)) {
			parentCode = "-1";
		}
		List<PmsResource> list = this.pmsResourceService.findByParentCode(parentCode);
		if(!ValidUtils.isEmpty(list)) {
			List<PmsResourceTreeGridVO> voList = this.pmsResourceSupport.toAsyncTreeGridVO(list);
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
	@RequiresPermissions("rs-add")
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public RespModel add(HttpServletRequest req, PmsResource resource) {
		if(ValidUtils.isNull(resource) || ValidUtils.isEmpty(resource.getRsCode())) {
			return this.error("参数无效");
		}
		PmsResource model = this.pmsResourceService.findByCode(resource.getRsCode());
		if(!ValidUtils.isNull(model)) {
			return this.errorAjax("编号已存在");
		}
		if(ValidUtils.isEmpty(resource.getParentCode())) {
			resource.setParentCode("-1");
		}
		this.setAddDefault(resource); //设置添加时默认值
		this.pmsResourceService.add(resource);
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
	@RequiresPermissions("rs-edit")
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@ResponseBody
	public RespModel edit(HttpServletRequest req, PmsResource resource) {
		if(ValidUtils.isNull(resource) || ValidUtils.isEmpty(resource.getRsCode())) {
			return this.error("参数无效");
		}
		if(ValidUtils.isEmpty(resource.getParentCode())) {
			resource.setParentCode("-1");
		}
		this.setUpdateDefault(resource); //设置修改时默认值
		this.pmsResourceService.editByCode(resource);
		return this.successAjax();
	}
	
	/**
	 * 删除
	 * @Date	2017年10月20日 下午5:34:00 <br/>
	 * @author  zhangST
	 * @param req
	 * @return
	 */
	@RequiresPermissions("rs-del")
	@RequestMapping(value="/del", method=RequestMethod.POST)
	@ResponseBody
	public RespModel del(HttpServletRequest req) {
		String funcCode = req.getParameter("rsCode");
		if(ValidUtils.isEmpty(funcCode)) {
			return this.errorAjax("参数无效");
		}
		this.pmsResourceService.delByCode(funcCode);
		return this.successAjax();
	}
}

