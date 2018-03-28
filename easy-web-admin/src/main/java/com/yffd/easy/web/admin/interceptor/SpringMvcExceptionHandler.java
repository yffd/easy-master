package com.yffd.easy.web.admin.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.yffd.easy.common.core.enums.CommonEnum;
import com.yffd.easy.framework.domain.RespModel;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月16日 下午3:22:02 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class SpringMvcExceptionHandler implements HandlerExceptionResolver {

	private static final Logger logger = LoggerFactory.getLogger(SpringMvcExceptionHandler.class);
	
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		response.setContentType("text/html;charset=utf-8"); 
		response.setCharacterEncoding("utf-8"); 
		//判断ajax请求
//		if(request.getHeader("x-requested-with")!=null && request.getHeader("x-requested-with").equals("XMLHttpRequest")) {
//		} else { }
		
		RespModel entity = new RespModel();
		entity.setRespType(CommonEnum.REQUEST_SYNC.getValue());
		entity.setStatusCode(CommonEnum.ERROR.getValue());
		entity.setRespData(ex.getClass().getName());
		
		if(ex instanceof AuthorizationException) { // 授权异常
			entity.setStatusDesc("权限不足，拒绝访问！");
		} else if (ex instanceof AuthenticationException) { // 认证异常
			try {
				response.sendRedirect("/admin/login.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String msg = JSON.toJSONString(entity);
		writeMsg(msg, response);
		return null;
	}

	//打印异步请求的异常信息
	private void writeMsg(String msg, HttpServletResponse response){
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(msg);
		} catch (IOException e) {
			logger.error("[admin]--返回异步异常信息失败！");
		} finally {
			out.flush();
			out.close();
		}
	}

}

