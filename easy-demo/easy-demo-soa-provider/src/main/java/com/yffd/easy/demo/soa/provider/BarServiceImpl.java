package com.yffd.easy.demo.soa.provider;

import com.yffd.easy.demo.soa.api.BarService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年1月11日 下午5:26:15 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class BarServiceImpl implements BarService {

    public String sayHello(String name) {
    	return "Hello " + name;
    }

}

