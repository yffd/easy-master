package com.yffd.easy.framework.common.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.framework.common.mapper.ICommonMapper;
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
public abstract class CommonServiceAbstract<PO extends CustomPo> {

	public abstract ICommonMapper<PO> getMapper();

	/**************************************** 查询 ****************************************/
	
	/**
	 * 分页查询.<br/>
	 * @Date	2018年3月28日 下午2:23:16 <br/>
	 * @author  zhangST
	 * @param paramPo
	 * @param paramMap
	 * @param paramPage
	 * @param loginInfo
	 * @return
	 */
	public PageResult<PO> findPage(PO paramPo, Map<String, Object> paramMap, PageParam paramPage, LoginInfo loginInfo) {
		if(null==paramPage) return null;
		Long totalRecord = this.findCount(paramPo, paramMap, loginInfo);
		paramPage.setTotalRecord(totalRecord);
		if(totalRecord==0) return null;
		this.checkAndSetForQuery(paramPo, paramMap, loginInfo, true); // 检查和设置默认属性
		List<PO> recordList = this.getMapper().selectListBy(paramPo, paramMap, paramPage);
		return new PageResult<PO>(paramPage, recordList);
	}
	
	public PageResult<PO> findPage(PO paramPo, PageParam paramPage, LoginInfo loginInfo) {
		if(null==paramPage) return null;
		return this.findPage(paramPo, null, paramPage, loginInfo);
	}
	
	/**
	 * 统计查询.<br/>
	 * @Date	2018年3月28日 下午2:27:31 <br/>
	 * @author  zhangST
	 * @param paramPo
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	public Long findCount(PO paramPo, Map<String, Object> paramMap, LoginInfo loginInfo) {
		this.checkAndSetForQuery(paramPo, paramMap, loginInfo, true); // 检查和设置默认属性
		return this.getMapper().selectCountBy(paramPo, paramMap);
	}
	
	public Long findCount(PO paramPo, LoginInfo loginInfo) {
		return this.findCount(paramPo, null, loginInfo);
	}
	
	/**
	 * 列表查询.<br/>
	 * @Date	2018年3月28日 下午2:27:48 <br/>
	 * @author  zhangST
	 * @param paramPo
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	public List<PO> findList(PO paramPo, Map<String, Object> paramMap, LoginInfo loginInfo) {
		this.checkAndSetForQuery(paramPo, paramMap, loginInfo, true); // 检查和设置默认属性
		return this.getMapper().selectListBy(paramPo, paramMap, null);
	}
	
	public List<PO> findList(PO paramPo, LoginInfo loginInfo) {
		return this.findList(paramPo, null, loginInfo);
	}
	
	/**
	 * 单条查询.<br/>
	 * @Date	2018年3月28日 下午2:28:52 <br/>
	 * @author  zhangST
	 * @param paramPo
	 * @param paramMap
	 * @param loginInfo
	 * @return
	 */
	public PO findOne(PO paramPo, Map<String, Object> paramMap, LoginInfo loginInfo) {
		if(null==paramPo && (null==paramMap || paramMap.isEmpty())) return null;
		this.checkAndSetForQuery(paramPo, paramMap, loginInfo, true); // 检查和设置默认属性
		return this.getMapper().selectOneBy(paramPo, paramMap);
	}
	
	public PO findOne(PO paramPo, LoginInfo loginInfo) {
		if(null==paramPo) return null;
		return this.findOne(paramPo, null, loginInfo);
	}
	
	
	/**************************************** 添加 ****************************************/
	
	public int addOne(PO paramPo, LoginInfo loginInfo) {
		if(null==paramPo) return 0;
		this.checkAndSetForAdd(paramPo, loginInfo, true); // 检查和设置默认属性
		return this.getMapper().insertOne(paramPo);
	}
	
	public int addList(List<PO> paramPos, LoginInfo loginInfo) {
		if(null==paramPos || paramPos.isEmpty()) return 0;
		this.checkAndSetForAdd(paramPos, loginInfo, true); // 检查和设置默认属性
		return this.getMapper().insertList(paramPos);
	}
	
	
	/**************************************** 修改 ****************************************/
	
	/**
	 * 更新.<br/>
	 * @Date	2018年3月28日 下午1:50:36 <br/>
	 * @author  zhangST
	 * @param paramPoNew		待更新“属性名-值”集合
	 * @param paramPoOld		条件-旧对象“属性名-值”集合
	 * @param paramMap			条件-指定“属性名-值”对集合
	 * @return
	 */
	public int update(PO paramPoNew, PO paramPoOld, Map<String, Object> paramMap, LoginInfo loginInfo) {
		if(null==paramPoNew) return 0;
		if(null==paramPoOld && (null==paramMap || paramMap.isEmpty())) return 0;
		this.checkAndSetForUpdate(paramPoNew, paramPoOld, paramMap, loginInfo, true); // 检查和设置默认属性
		return this.getMapper().updateBy(paramPoNew, paramPoOld, paramMap);
	}
	
	public int update(PO paramPoNew, Map<String, Object> paramMap, LoginInfo loginInfo) {
		return this.update(paramPoNew, null, paramMap, loginInfo);
	}
	
	public int updateBy(PO paramPoNew, String attributeName, Object value, LoginInfo loginInfo) {
		if(null==paramPoNew) return 0;
		if(EasyStringCheckUtils.isEmpty(attributeName) && null==value) return 0;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put(attributeName, value);
		return this.update(paramPoNew, null, paramMap, loginInfo);
	}
	
	
	/**************************************** 删除 ****************************************/
	
	/**
	 * 删除.<br/>
	 * @Date	2018年3月28日 下午1:55:44 <br/>
	 * @author  zhangST
	 * @param paramPo
	 * @param paramMap
	 * @return
	 */
	public int delete(PO paramPo, Map<String, Object> paramMap) {
		if(null==paramPo && (null==paramMap || paramMap.isEmpty())) return 0;
		return this.getMapper().deleteBy(paramPo, paramMap);
	}
	
	public int delete(PO paramPo) {
		if(null==paramPo) return 0;
		return this.delete(paramPo, null);
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
	
	
	/************************************* 默认属性值设置 ************************************/
	
	private static final Logger LOG = LoggerFactory.getLogger(CommonServiceAbstract.class);
	private static final String KEY_NAME_TENANT_CODE = "tenantCode";
	/**
	 * 检查和设置属性值-添加
	 * @Date	2018年3月28日 下午2:07:54 <br/>
	 * @author  zhangST
	 * @param parameter
	 * @param loginInfo
	 * @param setDefaultTenant
	 */
	protected void checkAndSetForAdd(CustomPo po, LoginInfo loginInfo, boolean setDefaultTenant) {
		if(null==po) return;
		if(null==po.getVersion()) po.setVersion(0);
		if(null==po.getDelFlag()) po.setDelFlag("0");
		if(null==po.getCreateTime()) po.setCreateTime(new Date());
		if(null==po.getCreateBy()) {
			if(null!=loginInfo) po.setCreateBy(loginInfo.getUserCode());
		}
		if(EasyStringCheckUtils.isEmpty(po.getTenantCode())) return;
		if(setDefaultTenant && null!=loginInfo) po.setTenantCode(loginInfo.getTenantCode());
		if(EasyStringCheckUtils.isEmpty(po.getTenantCode())) {
			LOG.warn("【添加】未指定【租户】警告，mapper为：【" + this.getMapper().getClass() + "】， 持久化对象为：【" + po.getClass() + "】");
		}
	}
	
	/**
	 * 检查和设置属性值-添加
	 * @Date	2018年3月28日 下午3:05:24 <br/>
	 * @author  zhangST
	 * @param pos
	 * @param loginInfo
	 * @param setDefaultTenant
	 */
	protected void checkAndSetForAdd(List<? extends CustomPo> pos, LoginInfo loginInfo, boolean setDefaultTenant) {
		if(null==pos || pos.isEmpty()) return;
		for(CustomPo po : pos) {
			this.checkAndSetForAdd(po, loginInfo, setDefaultTenant);
		}
	}
	
	/**
	 * 检查和设置属性值-修改
	 * @Date	2018年3月28日 下午2:08:17 <br/>
	 * @author  zhangST
	 * @param parameter
	 * @param loginInfo
	 * @param setDefaultTenant
	 */
	protected void checkAndSetForUpdate(PO paramPoNew, PO paramPoOld, Map<String, Object> paramMap, LoginInfo loginInfo, boolean setDefaultTenant) {
		if(null==paramPoNew.getUpdateTime()) paramPoNew.setUpdateTime(new Date()); 
		if(null==paramPoNew.getUpdateBy()) {
			if(null!=loginInfo) paramPoNew.setUpdateBy(loginInfo.getUserCode());
		}
		// 租户检查
		boolean exist = false;
		if(!exist && null!=paramPoOld && !EasyStringCheckUtils.isEmpty(paramPoOld.getTenantCode())) exist = true;
		if(!exist && null!=paramMap && null!=paramMap.get(KEY_NAME_TENANT_CODE)) exist = true;
		
		if(!exist && setDefaultTenant) {
			if(null!=loginInfo && null!=paramPoOld) {
				paramPoOld.setTenantCode(loginInfo.getTenantCode());
			} else if(null!=loginInfo && null!=paramMap) {
				paramMap.put(KEY_NAME_TENANT_CODE, loginInfo.getTenantCode());
			}
		}
		if((null==paramPoOld || EasyStringCheckUtils.isEmpty(paramPoOld.getTenantCode()))
				&& (null==paramMap || null==paramMap.get(KEY_NAME_TENANT_CODE))) {
			LOG.warn("【更新条件】未指定【租户】警告，mapper为：【" + this.getMapper().getClass() + "】， 持久化对象为：【" + paramPoNew.getClass() + "】");
		}
	}
	
	/**
	 * 检查和设置属性值-查询
	 * @Date	2018年3月28日 下午2:07:54 <br/>
	 * @author  zhangST
	 * @param parameter
	 * @param loginInfo
	 */
	protected void checkAndSetForQuery(PO paramPo, Map<String, Object> paramMap, LoginInfo loginInfo, boolean setDefaultTenant) {
		// 租户检查
		boolean exist = false;
		if(!exist && null!=paramPo && !EasyStringCheckUtils.isEmpty(paramPo.getTenantCode())) exist = true;
		if(!exist && null!=paramMap && null!=paramMap.get(KEY_NAME_TENANT_CODE)) exist = true;
		
		if(!exist && setDefaultTenant) {
			if(null!=loginInfo && null!=paramPo) {
				paramPo.setTenantCode(loginInfo.getTenantCode());
			} else if(null!=loginInfo && null!=paramMap) {
				paramMap.put(KEY_NAME_TENANT_CODE, loginInfo.getTenantCode());
			}
		}
		if((null==paramPo || EasyStringCheckUtils.isEmpty(paramPo.getTenantCode()))
				&& (null==paramMap || null==paramMap.get(KEY_NAME_TENANT_CODE))) {
			LOG.warn("【查询条件】未指定【租户】警告，mapper为：【" + this.getMapper().getClass() + "】");
		}
	}
}

