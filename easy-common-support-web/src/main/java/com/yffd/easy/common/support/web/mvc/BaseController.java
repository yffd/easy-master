package com.yffd.easy.common.support.web.mvc;

import com.yffd.easy.common.core.enums.SysConstantsEnum;
import com.yffd.easy.common.core.model.RespModel;
import com.yffd.easy.common.core.view.support.ViewModelSupport;

/**
 * @Description  web控制转发器基类.
 * @Date		 2017年9月18日 下午5:43:59 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public abstract class BaseController extends ViewModelSupport {
	
	/**
	 * 同步请求：成功
	 * @Date	2017年9月22日 下午2:48:40 <br/>
	 * @author  zhangST
	 * @return
	 */
	protected RespModel success() {
		RespModel entity = new RespModel();
		entity.setRespType(SysConstantsEnum.REQUEST_SYNC.getValue());
		entity.setStatusCode(SysConstantsEnum.SUCCESS.getValue());
		entity.setStatusDesc(SysConstantsEnum.SUCCESS.getDesc());
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
		entity.setRespType(SysConstantsEnum.REQUEST_SYNC.getValue());
		entity.setStatusCode(SysConstantsEnum.SUCCESS.getValue());
		entity.setStatusDesc(statusDesc);
		entity.setRespData(objects);
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
		entity.setRespType(SysConstantsEnum.REQUEST_ASYNC.getValue());
		entity.setStatusCode(SysConstantsEnum.SUCCESS.getValue());
		entity.setStatusDesc(SysConstantsEnum.SUCCESS.getDesc());
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
		entity.setRespType(SysConstantsEnum.REQUEST_ASYNC.getValue());
		entity.setStatusCode(SysConstantsEnum.SUCCESS.getValue());
		entity.setStatusDesc(SysConstantsEnum.SUCCESS.getDesc());
		entity.setRespData(respData);
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
		entity.setRespType(SysConstantsEnum.REQUEST_ASYNC.getValue());
		entity.setStatusCode(SysConstantsEnum.SUCCESS.getValue());
		entity.setStatusDesc(statusDesc);
		entity.setRespData(objects);
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
		entity.setRespType(SysConstantsEnum.REQUEST_SYNC.getValue());
		entity.setStatusCode(SysConstantsEnum.ERROR.getValue());
		entity.setStatusDesc(SysConstantsEnum.ERROR.getDesc());
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
		entity.setRespType(SysConstantsEnum.REQUEST_SYNC.getValue());
		entity.setStatusCode(SysConstantsEnum.ERROR.getValue());
		entity.setStatusDesc(statusDesc);
		entity.setRespData(objects);
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
		entity.setRespType(SysConstantsEnum.REQUEST_ASYNC.getValue());
		entity.setStatusCode(SysConstantsEnum.ERROR.getValue());
		entity.setStatusDesc(SysConstantsEnum.ERROR.getDesc());
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
		entity.setRespType(SysConstantsEnum.REQUEST_ASYNC.getValue());
		entity.setStatusCode(SysConstantsEnum.ERROR.getValue());
		entity.setStatusDesc(statusDesc);
		entity.setRespData(objects);
		return entity;
	}
	
}
