package com.yffd.easy.demo.soa.provider;

import com.alibaba.dubbo.rpc.RpcException;
import com.yffd.easy.demo.soa.api.DemoService;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年1月11日 上午11:34:39 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class DemoServiceImpl implements DemoService {

	@Override
	public String sayHello(String name) {
		if(true) {
			throw new RpcException("test exception");
		}
		return "Hello " + name;
	}

}

