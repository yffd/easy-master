package com.yffd.easy.web.admin;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.yffd.easy.admin.login.model.LoginInfo;
import com.yffd.easy.admin.login.service.LoginService;
import com.yffd.easy.common.support.web.mvc.BaseController;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月15日 上午11:48:56 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class AdminBaseController extends BaseController {

	/**
	 * 获取当前session对象
	 * @Date	2017年11月15日 上午11:50:41 <br/>
	 * @author  zhangST
	 * @return
	 */
	protected Session getSession() {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		return session;
	}
	
	/**
	 * 获取当前登录信息
	 * @Date	2017年11月15日 上午11:51:01 <br/>
	 * @author  zhangST
	 * @return
	 */
	protected LoginInfo currentLoginInfo() {
		LoginInfo info = (LoginInfo) this.getSession().getAttribute(LoginService.SESSION_LOGIN_INFO_KEY);
		return info;
	}
	
	/**
	 * 设置数据模型的默认值-新增
	 * @Date	2017年11月15日 下午2:05:35 <br/>
	 * @author  zhangST
	 * @param model
	 */
	protected void setAddDefault(PersistModel model) {
		Date time = new Date();
		String user = "sys";
		if(null!=this.currentLoginInfo()) user = this.currentLoginInfo().getUserCode();
		model.setVersion(0);
		model.setDelFlag("0");
		model.setCreater(user);
		model.setCreateTime(time);
		model.setEditor(user);
		model.setEditTime(time);
	}
	
	/**
	 * 设置数据模型的默认值-修改
	 * @Date	2017年11月15日 下午2:04:32 <br/>
	 * @author  zhangST
	 * @param model
	 */
	protected void setUpdateDefault(PersistModel model) {
		Date time = new Date();
		String user = "sys";
		if(null!=this.currentLoginInfo()) user = this.currentLoginInfo().getUserCode();
		model.setEditor(user);
		model.setEditTime(time);
	}
}

