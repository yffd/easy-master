package org.yffd.easy.spring4.shiro.session.dao;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DelegatingSession;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月7日 上午10:54:47 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class MySessionDAO extends CachingSessionDAO {
	Session tmp = null;
	@Override
	protected void doUpdate(Session session) {
		System.out.println("doUpdate sessionId=" + session.getId());
		if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
            return; //如果会话过期/停止 没必要再更新了
        }
	}

	@Override
	protected void doDelete(Session session) {
		System.out.println("doDelete sessionId=" + session.getId());
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        System.out.println("doCreate sessionId=" + sessionId);
        tmp = session;
        return session.getId();
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		System.out.println("doReadSession sessionId=" + sessionId);
		DelegatingSession aa = new DelegatingSession(null, null);
		return tmp;
	}

}

