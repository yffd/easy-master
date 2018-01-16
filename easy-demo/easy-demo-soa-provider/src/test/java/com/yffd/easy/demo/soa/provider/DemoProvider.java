package com.yffd.easy.demo.soa.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年1月11日 上午11:40:30 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class DemoProvider {
	
	public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"classpath*:spring/provider.xml"});
        context.start();
        System.in.read(); // 按任意键退出
    }
}

