package com.yffd.easy.framework.web.mvc;

import com.yffd.easy.common.core.enums.CommonEnum;
import com.yffd.easy.framework.domain.RespModel;
import com.yffd.easy.framework.web.view.ViewModelConverter;

/**
 * @Description  web控制转发器基类.
 * @Date		 2017年9月18日 下午5:43:59 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public abstract class WebController extends ViewModelConverter {
	
	/**
	 * 同步请求：成功
	 * @Date	2017年9月22日 下午2:48:40 <br/>
	 * @author  zhangST
	 * @return
	 */
	protected RespModel success() {
		RespModel entity = new RespModel();
		entity.setType(CommonEnum.REQUEST_SYNC.getValue());
		entity.setStatus(CommonEnum.SUCCESS.getValue());
		entity.setMsg(CommonEnum.SUCCESS.getDesc());
		return entity;
	}
	
	/**
	 * 同步请求：成功
	 * @Date	2017年9月22日 下午2:49:14 <br/>
	 * @author  zhangST
	 * @param statusDesc		提示信息
	 * @param objects
	 * @return
	 */
	protected RespModel success(String statusDesc, Object...objects) {
		RespModel entity = new RespModel();
		entity.setType(CommonEnum.REQUEST_SYNC.getValue());
		entity.setStatus(CommonEnum.SUCCESS.getValue());
		entity.setMsg(statusDesc);
		entity.setData(objects);
		return entity;
	}
	
	/**
	 * 异步请求：成功
	 * @Date	2017年9月22日 下午2:50:00 <br/>
	 * @author  zhangST
	 * @return
	 */
	protected RespModel successAjax() {
		RespModel entity = new RespModel();
		entity.setType(CommonEnum.REQUEST_ASYNC.getValue());
		entity.setStatus(CommonEnum.SUCCESS.getValue());
		entity.setMsg(CommonEnum.SUCCESS.getDesc());
		return entity;
	}
	
	/**
	 * 异步请求：成功
	 * @Date	2017年9月22日 下午2:50:00 <br/>
	 * @author  zhangST
	 * @param respData		响应数据
	 * @return
	 */
	protected RespModel successAjax(Object respData) {
		RespModel entity = new RespModel();
		entity.setType(CommonEnum.REQUEST_ASYNC.getValue());
		entity.setStatus(CommonEnum.SUCCESS.getValue());
		entity.setMsg(CommonEnum.SUCCESS.getDesc());
		entity.setData(respData);
		return entity;
	}
	
	/**
	 * 异步请求：成功
	 * @Date	2017年9月22日 下午2:50:28 <br/>
	 * @author  zhangST
	 * @param statusDesc		提示信息
	 * @param objects
	 * @return
	 */
	protected RespModel successAjax(String statusDesc, Object...objects) {
		RespModel entity = new RespModel();
		entity.setType(CommonEnum.REQUEST_ASYNC.getValue());
		entity.setStatus(CommonEnum.SUCCESS.getValue());
		entity.setMsg(statusDesc);
		entity.setData(objects);
		return entity;
	}
	
	/**
	 * 同步请求：失败
	 * @Date	2017年9月22日 下午2:49:01 <br/>
	 * @author  zhangST
	 * @return
	 */
	protected RespModel error() {
		RespModel entity = new RespModel();
		entity.setType(CommonEnum.REQUEST_SYNC.getValue());
		entity.setStatus(CommonEnum.ERROR.getValue());
		entity.setMsg(CommonEnum.ERROR.getDesc());
		return entity;
	}
	
	/**
	 * 同步请求：失败
	 * @Date	2017年9月22日 下午2:49:43 <br/>
	 * @author  zhangST
	 * @param statusDesc		提示信息
	 * @param objects
	 * @return
	 */
	protected RespModel error(String statusDesc, Object...objects) {
		RespModel entity = new RespModel();
		entity.setType(CommonEnum.REQUEST_SYNC.getValue());
		entity.setStatus(CommonEnum.ERROR.getValue());
		entity.setMsg(statusDesc);
		entity.setData(objects);
		return entity;
	}
	
	/**
	 * 异步请求：失败
	 * @Date	2017年9月22日 下午2:50:16 <br/>
	 * @author  zhangST
	 * @return
	 */
	protected RespModel errorAjax() {
		RespModel entity = new RespModel();
		entity.setType(CommonEnum.REQUEST_ASYNC.getValue());
		entity.setStatus(CommonEnum.ERROR.getValue());
		entity.setMsg(CommonEnum.ERROR.getDesc());
		return entity;
	}
	
	/**
	 * 异步请求：失败
	 * @Date	2017年9月22日 下午2:50:41 <br/>
	 * @author  zhangST
	 * @param statusDesc		提示信息
	 * @param objects
	 * @return
	 */
	protected RespModel errorAjax(String statusDesc, Object...objects) {
		RespModel entity = new RespModel();
		entity.setType(CommonEnum.REQUEST_ASYNC.getValue());
		entity.setStatus(CommonEnum.ERROR.getValue());
		entity.setMsg(statusDesc);
		entity.setData(objects);
		return entity;
	}
	
}

