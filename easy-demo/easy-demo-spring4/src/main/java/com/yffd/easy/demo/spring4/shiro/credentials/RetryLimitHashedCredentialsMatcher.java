package com.yffd.easy.demo.spring4.shiro.credentials;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月7日 下午3:29:01 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
	
	@Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        boolean matches = super.doCredentialsMatch(token, info);
        return matches;
    }
}

