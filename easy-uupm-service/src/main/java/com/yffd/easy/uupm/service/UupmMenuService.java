package com.yffd.easy.uupm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.framework.core.common.mapper.ICommonMapper;
import com.yffd.easy.framework.core.common.service.CommonServiceAbstract;
import com.yffd.easy.framework.core.exception.BizException;
import com.yffd.easy.uupm.mapper.IUupmMenuMapper;
import com.yffd.easy.uupm.pojo.entity.UupmMenuEntity;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		2018年04月08日 16时48分28秒 <br/>
 * @author		ZhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@Service
public class UupmMenuService extends CommonServiceAbstract<UupmMenuEntity> {

	@Autowired
	private IUupmMenuMapper uupmMenuMapper;
	
	@Override
	public ICommonMapper<UupmMenuEntity> getMapper() {
		return this.uupmMenuMapper;
	}

	@Override
	public Class<?> getMapperClass() {
		return IUupmMenuMapper.class;
	}

	public List<Map<String, Object>> findMenuList(String tenantCode, String parentCode) {
		if(EasyStringCheckUtils.isEmpty(tenantCode)) throw BizException.BIZ_TENANT_IS_EMPTY();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tenantCode", tenantCode);
		paramMap.put("parentCode", parentCode);
		return this.selectListBy("selectMenu", paramMap, true);
	}
	
	public int addMenuForAdmin(String tenantCode) {
		List<Map<String, Object>> list = this.findPlanMenuForAdmin(tenantCode);
		if(null==list || list.size()==0) return 0;
		List<UupmMenuEntity> modelList = new ArrayList<UupmMenuEntity>();
		for(Map<String, Object> map : list) {
			UupmMenuEntity model = new UupmMenuEntity();
			model.setTenantCode(tenantCode);
			model.setMenuCode((String) map.get("rsCode"));
			model.setMenuName((String) map.get("rsName"));
			model.setMenuSeqNo((int) map.get("seqNo"));
			model.setMenuIcons("icon-sys");
			model.setMenuType("nav_left");
			model.setAccessType("url");
			String nodeLabel = (String) map.get("nodeLabel");
			String parentCode = (String) map.get("parentCode");
			if(nodeLabel.equals(parentCode)) {
				model.setParentCode("root");
			} else {
				model.setParentCode(parentCode);
			}
			modelList.add(model);
		}
		if(modelList.size()==0) return 0;
		return this.addList(modelList);
	}
	
	public int addMenuForOther(String tenantCode) {
		List<Map<String, Object>> list = this.findPlanMenuForOther(tenantCode);
		if(null==list || list.size()==0) return 0;
		List<UupmMenuEntity> modelList = new ArrayList<UupmMenuEntity>();
		for(Map<String, Object> map : list) {
			UupmMenuEntity model = new UupmMenuEntity();
			model.setTenantCode(tenantCode);
			model.setMenuCode((String) map.get("rsCode"));
			model.setMenuName((String) map.get("rsName"));
			model.setMenuSeqNo((int) map.get("seqNo"));
			model.setMenuIcons("icon-sys");
			model.setMenuType("nav_left");
			model.setAccessType("url");
			String nodeLabel = (String) map.get("nodeLabel");
			String parentCode = (String) map.get("parentCode");
			if(nodeLabel.equals(parentCode)) {
				model.setParentCode("root");
			} else {
				model.setParentCode(parentCode);
			}
			modelList.add(model);
		}
		if(modelList.size()==0) return 0;
		return this.addList(modelList);
	}
	
	public List<Map<String, Object>> findPlanMenuForAdmin(String tenantCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tenantCode", tenantCode);
		return this.selectListBy("selectPlanMenuForAdmin", paramMap, true);
	}
	
	public List<Map<String, Object>> findPlanMenuForOther(String tenantCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tenantCode", tenantCode);
		return this.selectListBy("selectPlanMenuForOther", paramMap, true);
	}
}
