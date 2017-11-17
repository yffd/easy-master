package org.yffd.easy.spring4.shiro.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月6日 下午5:39:06 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@WebServlet(name = "authenticatedServlet", urlPatterns = "/authenticated")
public class AuthenticatedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()) {
            req.getRequestDispatcher("/shiro/authenticated.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/shiro/login.jsp").forward(req, resp);
        }
    }

}

