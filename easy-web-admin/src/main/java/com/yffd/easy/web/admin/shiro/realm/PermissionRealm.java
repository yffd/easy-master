package com.yffd.easy.web.admin.shiro.realm;

import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yffd.easy.admin.login.model.LoginInfo;
import com.yffd.easy.admin.login.model.UsernamePasswordInfo;
import com.yffd.easy.admin.login.service.LoginService;
import com.yffd.easy.common.core.util.ValidUtils;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月8日 上午9:52:35 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class PermissionRealm extends AuthorizingRealm {
	private static final Logger LOG = LoggerFactory.getLogger(PermissionRealm.class);

	@Autowired
	private LoginService loginService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		LOG.info("=========shiro PermissionRealm [doGetAuthorizationInfo]");
		String userCode = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        
        Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		LoginInfo info = (LoginInfo) session.getAttribute(LoginService.SESSION_LOGIN_INFO_KEY);
		if(null==info) return null;
		// 设置角色
//		Set<String> roles = info.getRoles();
//		if(null==roles) {
//			roles = this.loginService.findRoles(userCode);
//		}
//		authorizationInfo.setRoles(roles);
		
		// 设置权限
		Set<String> permissions = info.getPermissions();
		if(null==permissions) {
			permissions = this.loginService.findPermissions(userCode);
		}
		authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		LOG.info("=========shiro PermissionRealm [doGetAuthenticationInfo]");
		String userCode = (String)token.getPrincipal();
		if(ValidUtils.isBlank(userCode)) {
			throw new UnknownAccountException();// 没找到帐号
		}
		
		UsernamePasswordInfo info = this.loginService.findAccount(userCode);
		if(null==info) {
			throw new UnknownAccountException();//没找到帐号
		}
		if("I".equals(info.getStatus())) {
			throw new LockedAccountException(); //帐号锁定
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				info.getUserName(), //用户名
				info.getUserPwd(), //密码
                ByteSource.Util.bytes(info.getCredentialsSalt()),//salt=accountName+salt
                getName()  //realm name
        );
        return authenticationInfo;
	}
	
}

