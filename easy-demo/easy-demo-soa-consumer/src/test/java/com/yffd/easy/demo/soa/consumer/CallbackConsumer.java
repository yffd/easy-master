package com.yffd.easy.demo.soa.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yffd.easy.demo.soa.api.CallbackListener;
import com.yffd.easy.demo.soa.api.CallbackService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年1月11日 下午4:46:28 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class CallbackConsumer {
	
	public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"classpath:spring/consumer.xml"});
        context.start();
        
        CallbackService callbackService = (CallbackService) context.getBean("callbackService");

        callbackService.addListener("http://localhsot/display/dubbo/foo.bar", new CallbackListener(){
            public void changed(String msg) {
                System.out.println("callback1:" + msg);
            }
        });        
//        System.out.println("》》》》》》》》》》》");
//        System.in.read();
    }
}

