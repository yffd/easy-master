package com.yffd.easy.demo.soa.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yffd.easy.demo.soa.api.DemoService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年1月11日 上午11:53:03 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class DemoConsumer {
	
	public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"classpath:spring/consumer.xml"});
        context.start();
        DemoService demoService = (DemoService)context.getBean("demoService"); // 获取远程服务代理
        String hello = demoService.sayHello("world"); // 执行远程方法
        System.out.println( hello ); // 显示调用结果
        
//        System.out.println("》》》》》》》》》》》");
//        System.in.read();
    }
}

