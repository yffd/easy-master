package org.yffd.easy.spring4.hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 简单描述该类的功能（可选）.
 * @Date 2017年8月25日 上午11:13:48 <br/>
 * @author zhangST
 * @version 1.0
 * @since JDK 1.7+
 * @see
 */
@Configuration
@ComponentScan
public class Application {
	
	@Bean
	MessageService mockMessageService() {
		return new MessageService() {
			public String getMessage() {
				return "Hello World!";
			}
		};
	}

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
		MessagePrinter printer = context.getBean(MessagePrinter.class);
		printer.printMessage();
	}
}
