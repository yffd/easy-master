package com.yffd.easy.common.shiro.spring;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.yffd.easy.common.shiro.model.AccountInfo;
import com.yffd.easy.common.shiro.service.AccountService;
import com.yffd.easy.common.shiro.support.PasswordSupport;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月8日 上午10:32:12 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-context-test.xml"})
@TransactionConfiguration(defaultRollback = false)
public class ShiroTest {
	
	@Autowired
	private AccountService accountService;
	
	@Test
    public void test() {
		
		AccountInfo info = this.accountService.findAccount("admin");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(info.getAccountName(), "admin");
        subject.login(token);

        Assert.assertTrue(subject.isAuthenticated());
        subject.checkRole("admin");
        subject.checkPermission("user:create");

        System.out.println(">>>>>>>>>>>>>>>>>>>>>");
//        subject.login(token);
        
        subject.logout();

    }
	
	@After
    public void tearDown() throws Exception {
        ThreadContext.unbindSubject();//退出时请解除绑定Subject到线程 否则对下次测试造成影响
    }
}

