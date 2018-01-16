package com.yffd.easy.demo.soa.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yffd.easy.demo.soa.api.BarService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年1月11日 下午5:29:29 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class BarConsumer {
	
	public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"classpath:spring/consumer.xml"});
        context.start();
        
        BarService barService = (BarService) context.getBean("barService");
        String hello = barService.sayHello("world"); // 执行远程方法
        System.out.println( hello ); // 显示调用结果
//        System.out.println("》》》》》》》》》》》");
//        System.in.read();
    }
}

