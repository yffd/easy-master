package org.yffd.easy.app.controller.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yffd.easy.app.system.model.SysUser;
import org.yffd.easy.app.system.service.SysUserService;
import org.yffd.easy.common.core.model.RespModel;
import org.yffd.easy.common.core.util.EncryptUtils;
import org.yffd.easy.common.core.util.ValidUtils;
import org.yffd.easy.common.ssm.web.BaseController;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年10月25日 下午4:41:43 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Controller
@RequestMapping("/sys")
public class SysLoginController extends BaseController {

	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 列出所有菜单
	 * @Date	2017年10月20日 下午5:30:02 <br/>
	 * @author  zhangST
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public RespModel login(HttpServletRequest req) {
		String userCode = req.getParameter("userName");
		String password = req.getParameter("password");
		
		if(ValidUtils.isEmpty(userCode) || ValidUtils.isEmpty(password)) {
			return this.errorAjax("用户名或密码为空");
		}
		password = EncryptUtils.encodeMD5String(password);
		SysUser user = this.sysUserService.findByAccount(userCode, password);
		if(null==user)
			return this.errorAjax("该用户不存在");
		return this.successAjax();
	}
	
}

