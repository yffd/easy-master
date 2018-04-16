package com.yffd.easy.framework.core.common.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.framework.core.common.CommonConstants;
import com.yffd.easy.framework.core.common.mapper.ICommonMapper;
import com.yffd.easy.framework.domain.CustomPo;
import com.yffd.easy.framework.domain.LoginInfo;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年3月28日 上午11:38:55 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public abstract class CommonServiceAbstract<PO extends CustomPo> extends CommonServiceSupport {

	public abstract ICommonMapper<PO> getMapper();

	public abstract Class<?> getMapperClass();
	
	@Override
	protected String getStatement(String sqlId) {
		String namespace = this.getMapperClass().getName();
		StringBuilder sb = new StringBuilder();
		sb.append(namespace).append(".").append(sqlId);
		return sb.toString();
	}
	
	
	/**************************************** 查询 ****************************************/
	
	/**
	 * 分页查询.<br/>
	 * @Date	2018年3月28日 下午2:23:16 <br/>
	 * @author  zhangST
	 * @param model
	 * @param paramMap
	 * @param paramPage
	 * @param loginInfo
	 * @return
	 */
	public PageResult<PO> findPage(PO model, Map<String, Object> paramMap, PageParam paramPage, LoginInfo loginInfo) {
		if(null==paramPage) return null;
		Long totalRecord = this.findCount(model, paramMap, loginInfo);
		paramPage.setTotalRecord(totalRecord);
		if(totalRecord==0) return null;
		this.checkAndSetForQuery(model, paramMap, loginInfo);
		List<PO> recordList = this.getMapper().selectListBy(model, paramMap, paramPage);
		return new PageResult<PO>(paramPage, recordList);
	}
	
	public PageResult<PO> findPage(PO model, PageParam paramPage, LoginInfo loginInfo) {
		if(null==paramPage) return null;
		return this.findPage(model, null, paramPage, loginInfo);
	}
	
	/**
	 * 统计查询.<br/>
	 * @Date	2018年3月28日 下午2:27:31 <br/>
	 * @author  zhangST
	 * @param model
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	public Long findCount(PO model, Map<String, Object> paramMap, LoginInfo loginInfo) {
		this.checkAndSetForQuery(model, paramMap, loginInfo);
		return this.getMapper().selectCountBy(model, paramMap);
	}
	
	public Long findCount(PO model, LoginInfo loginInfo) {
		return this.findCount(model, null, loginInfo);
	}
	
	/**
	 * 列表查询.<br/>
	 * @Date	2018年3月28日 下午2:27:48 <br/>
	 * @author  zhangST
	 * @param model
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	public List<PO> findList(PO model, Map<String, Object> paramMap, LoginInfo loginInfo) {
		this.checkAndSetForQuery(model, paramMap, loginInfo);
		return this.getMapper().selectListBy(model, paramMap, null);
	}
	
	public List<PO> findList(PO model, LoginInfo loginInfo) {
		return this.findList(model, null, loginInfo);
	}
	
	/**
	 * 单条查询.<br/>
	 * @Date	2018年3月28日 下午2:28:52 <br/>
	 * @author  zhangST
	 * @param model
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	public PO findOne(PO model, Map<String, Object> paramMap, LoginInfo loginInfo) {
		if(null==model && (null==paramMap || paramMap.isEmpty())) return null;
		this.checkAndSetForQuery(model, paramMap, loginInfo);
		return this.getMapper().selectOneBy(model, paramMap);
	}
	
	public PO findOne(PO model, LoginInfo loginInfo) {
		if(null==model) return null;
		return this.findOne(model, null, loginInfo);
	}
	
	public boolean exsist(PO model, LoginInfo loginInfo) {
		List<PO> resultList = this.findList(model, loginInfo);
		return !(null==resultList || resultList.size()==0);
	}
	
	/**************************************** 添加 ****************************************/
	
	public int addOne(PO model, LoginInfo loginInfo) {
		if(null==model) return 0;
		this.checkAndSetForAdd(model, loginInfo);
		return this.getMapper().insertOne(model);
	}
	
	public int addList(List<PO> models, LoginInfo loginInfo) {
		if(null==models || models.isEmpty()) return 0;
		this.checkAndSetForAdd(models, loginInfo);
		return this.getMapper().insertList(models);
	}
	
	
	/**************************************** 修改 ****************************************/
	
	/**
	 * 更新.<br/>
	 * @Date	2018年3月28日 下午1:50:36 <br/>
	 * @author  zhangST
	 * @param model			待更新“属性名-值”集合
	 * @param modelOld		条件-旧对象“属性名-值”集合
	 * @param paramMap			条件-指定“属性名-值”对集合
	 * @return
	 */
	public int update(PO model, PO modelOld, Map<String, Object> paramMap, LoginInfo loginInfo) {
		if(null==model) return 0;
		if(null==modelOld && (null==paramMap || paramMap.isEmpty())) return 0;
		this.checkAndSetForUpdate(model, modelOld, paramMap, loginInfo);
		return this.getMapper().updateBy(model, modelOld, paramMap);
	}
	
	public int update(PO model, Map<String, Object> paramMap, LoginInfo loginInfo) {
		return this.update(model, null, paramMap, loginInfo);
	}
	
	public int updateBy(PO model, String attributeName, Object value, LoginInfo loginInfo) {
		if(null==model) return 0;
		if(EasyStringCheckUtils.isEmpty(attributeName) && null==value) return 0;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(attributeName, value);
		return this.update(model, null, paramMap, loginInfo);
	}
	
	
	/**************************************** 删除 ****************************************/
	
	/**
	 * 删除.<br/>
	 * @Date	2018年3月28日 下午1:55:44 <br/>
	 * @author  zhangST
	 * @param model
	 * @param paramMap
	 * @return
	 */
	public int delete(PO model, Map<String, Object> paramMap) {
		if(null==model && (null==paramMap || paramMap.isEmpty())) return 0;
		return this.getMapper().deleteBy(model, paramMap);
	}
	
	public int delete(PO model) {
		if(null==model) return 0;
		return this.delete(model, null);
	}
	
	public int delete(Map<String, Object> paramMap) {
		if(null==paramMap || paramMap.isEmpty()) return 0;
		return this.delete(null, paramMap);
	}
	
	
	public int delete(String attributeName, List<?> valueList) {
		if(EasyStringCheckUtils.isEmpty(attributeName) && (null==valueList || valueList.isEmpty())) return 0;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(attributeName, valueList);
		return this.delete(null, paramMap);
	}
	
	public int deleteBy(String attributeName, Object value) {
		if(EasyStringCheckUtils.isEmpty(attributeName) && null==value) return 0;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(attributeName, value);
		return this.delete(null, paramMap);
	}
	
	/************************************* 默认属性值设置 ************************************/
	
	private static final Logger LOG = LoggerFactory.getLogger(CommonServiceAbstract.class);
	/**
	 * 检查和设置属性值-添加
	 * @Date	2018年3月28日 下午2:07:54 <br/>
	 * @author  zhangST
	 * @param parameter
	 * @param loginInfo
	 */
	protected void checkAndSetForAdd(CustomPo po, LoginInfo loginInfo) {
		if(null==po) return;
		if(null==po.getVersion()) po.setVersion(0);
		if(null==po.getDelFlag()) po.setDelFlag("0");
		if(null==po.getCreateTime()) po.setCreateTime(new Date());
		if(null==po.getCreateBy()) {
			if(null!=loginInfo) po.setCreateBy(loginInfo.getUserCode());
		}
		if(EasyStringCheckUtils.isEmpty(po.getTenantCode())) {
//			LOG.warn("【添加】未指定【租户】警告，【mapper=" + this.getMapperClass().getName() + "】，【model=" + po.getClass().getName() + "】");
			LOG.warn("【添加】未指定【租户】警告，【service=" + this.getClass().getName() + "】，【model=" + po.getClass().getName() + "】");
		} else {
//			LOG.info("【添加】【租户=" + po.getTenantCode() + "】，【mapper=" + this.getMapperClass().getName() + "】，【model=" + po.getClass().getName() + "】");
			LOG.info("【添加】【租户=" + po.getTenantCode() + "】，【service=" + this.getClass().getName() + "】，【model=" + po.getClass().getName() + "】");
		}
	}
	
	/**
	 * 检查和设置属性值-添加
	 * @Date	2018年3月28日 下午3:05:24 <br/>
	 * @author  zhangST
	 * @param pos
	 * @param loginInfo
	 */
	protected void checkAndSetForAdd(List<? extends CustomPo> pos, LoginInfo loginInfo) {
		if(null==pos || pos.isEmpty()) return;
		for(CustomPo po : pos) {
			this.checkAndSetForAdd(po, loginInfo);
		}
	}
	
	/**
	 * 检查和设置属性值-修改
	 * @Date	2018年3月28日 下午2:08:17 <br/>
	 * @author  zhangST
	 * @param parameter
	 * @param loginInfo
	 */
	protected void checkAndSetForUpdate(PO modelNew, PO modelOld, Map<String, Object> paramMap, LoginInfo loginInfo) {
		if(null==modelNew.getUpdateTime()) modelNew.setUpdateTime(new Date()); 
		if(null==modelNew.getUpdateBy()) {
			if(null!=loginInfo) modelNew.setUpdateBy(loginInfo.getUserCode());
		}
		// 租户检查
		String tenantCode = null;
		if(null!=modelOld && !EasyStringCheckUtils.isEmpty(modelOld.getTenantCode())) {
			tenantCode = modelOld.getTenantCode();
		}
		if(null==tenantCode && null!=paramMap && null!=paramMap.get(CommonConstants.KEY_TENANT_CODE)) {
			tenantCode = (String) paramMap.get(CommonConstants.KEY_TENANT_CODE);
		}
		if(null!=tenantCode) {
//			LOG.warn("【更新条件】未指定【租户】警告，【mapper=" + this.getMapperClass().getName() + "】，【model=" + tenantCode + "】");
			LOG.warn("【更新条件】未指定【租户】警告，【service=" + this.getClass().getName() + "】，【model=" + tenantCode + "】");
		} else {
//			LOG.info("【更新条件】【租户=" + tenantCode + "】，【mapper=" + this.getMapperClass().getName() + "】");
			LOG.info("【更新条件】【租户=" + tenantCode + "】，【service=" + this.getClass().getName() + "】");
		}
	}
	
	/**
	 * 检查和设置属性值-查询
	 * @Date	2018年3月28日 下午2:07:54 <br/>
	 * @author  zhangST
	 * @param parameter
	 * @param loginInfo
	 */
	protected void checkAndSetForQuery(PO model, Map<String, Object> paramMap, LoginInfo loginInfo) {
		// 租户检查
		String tenantCode = null;
		if(null!=model && !EasyStringCheckUtils.isEmpty(model.getTenantCode())) {
			tenantCode = model.getTenantCode();
		}
		if(null==tenantCode && null!=paramMap && null!=paramMap.get(CommonConstants.KEY_TENANT_CODE)) {
			tenantCode = (String) paramMap.get(CommonConstants.KEY_TENANT_CODE);
		}
		if(null!=tenantCode) {
//			LOG.warn("【查询条件】未指定【租户】警告，【mapper=" + this.getMapperClass().getName() + "】，【model=" + tenantCode + "】");
			LOG.warn("【查询条件】未指定【租户】警告，【service=" + this.getClass().getName() + "】，【model=" + tenantCode + "】");
		} else {
//			LOG.info("【查询条件】【租户=" + tenantCode + "】，【mapper=" + this.getMapperClass().getName() + "】");
			LOG.info("【查询条件】【租户=" + tenantCode + "】，【service=" + this.getClass().getName() + "】");
		}
	}
}

