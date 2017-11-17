package org.yffd.easy.spring4.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月7日 上午10:32:47 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class MySessionListener2 extends SessionListenerAdapter {

	@Override
	public void onStart(Session session) {
		System.out.println("1会话创建：" + session.getId());
	}

	@Override
	public void onStop(Session session) {
		System.out.println("1会话期停止：" + session.getId());
	}

	@Override
	public void onExpiration(Session session) {
		System.out.println("1会话过过期：" + session.getId());
	}

}

