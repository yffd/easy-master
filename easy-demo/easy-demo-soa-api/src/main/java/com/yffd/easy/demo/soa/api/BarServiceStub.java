package com.yffd.easy.demo.soa.api;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年1月11日 下午5:26:15 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class BarServiceStub implements BarService {
	private final BarService barService;
	
	// 构造函数传入真正的远程代理对象
    public BarServiceStub(BarService barService) {
        this.barService = barService;
    }

    public String sayHello(String name) {
        // 此代码在客户端执行, 你可以在客户端做ThreadLocal本地缓存，或预先验证参数是否合法，等等
    	System.out.println(">>>>>>>>>>>>>>>>>");
        try {
            return barService.sayHello(name);
        } catch (Exception e) {
            // 你可以容错，可以做任何AOP拦截事项
            return "容错数据";
        }
    }

}

