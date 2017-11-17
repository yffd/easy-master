package com.yffd.easy.common.shiro.realm;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yffd.easy.common.shiro.model.AccountInfo;
import com.yffd.easy.common.shiro.service.AccountService;
import com.yffd.easy.common.shiro.support.AccountRealmSupport;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月8日 上午9:52:35 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class AccountRealm extends AuthorizingRealm {
	private static final Logger LOG = LoggerFactory.getLogger(AccountRealm.class);
	
	private AccountService accountService;
	
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		LOG.info("=========shiro AccountRealm [doGetAuthorizationInfo]");
		String accountName = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(this.accountService.findRoles(accountName));
        authorizationInfo.setStringPermissions(this.accountService.findPermissions(accountName));
        return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		LOG.info("=========shiro AccountRealm [doGetAuthenticationInfo]");
		String accountName = (String)token.getPrincipal();
		AccountInfo info = this.accountService.findAccount(accountName);
		if(null==info) {
			throw new UnknownAccountException();//没找到帐号
		}
		if("I".equals(info.getStatus())) {
			throw new LockedAccountException(); //帐号锁定
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                info.getAccountName(), //用户名
                info.getAccountPwd(), //密码
                ByteSource.Util.bytes(info.getCredentialsSalt()),//salt=accountName+salt
                getName()  //realm name
        );
        return authenticationInfo;
	}
	
}

