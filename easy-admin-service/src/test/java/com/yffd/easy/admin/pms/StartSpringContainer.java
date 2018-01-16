package com.yffd.easy.admin.pms;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年1月12日 下午5:35:55 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class StartSpringContainer {
	public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"classpath*:spring/admin-spring-beans.xml"});
        context.start();
        System.in.read(); // 按任意键退出
    }
}

