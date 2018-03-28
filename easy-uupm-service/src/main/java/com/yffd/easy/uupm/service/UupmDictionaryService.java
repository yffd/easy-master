package com.yffd.easy.uupm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yffd.easy.common.core.util.EasyStringCheckUtils;
import com.yffd.easy.framework.base.dao.GenericDao;
import com.yffd.easy.framework.base.service.GenericService;
import com.yffd.easy.framework.domain.LoginInfo;
import com.yffd.easy.uupm.api.model.UupmDictionaryModel;
import com.yffd.easy.uupm.dao.UupmDictionaryDao;

/**
 * @Description	简单描述该类的功能（可选）.
 * @Date		2018年03月02日 17时50分41秒 <br/>
 * @author		ZhangST
 * @version		1.0
 * @since		JDK 1.7+
 * @see
 */

@Service
public class UupmDictionaryService extends GenericService<UupmDictionaryModel> {

	@Autowired
	private UupmDictionaryDao uupmDictionaryDao;
	
	@Override
	public GenericDao<UupmDictionaryModel> getBindDao() {
		return this.uupmDictionaryDao;
	}

	public void addCategory(UupmDictionaryModel model, LoginInfo loginInfo) {
		if(null==model) return;
		if("".equals(model.getParentCode()) || "-1".equals(model.getParentCode())) {
			model.setParentCode("-1");
			model.setDataPath("-1." + model.getItemCode() + ".");
			model.setDataLabel(model.getItemCode());	//
		} else {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("itemCode", model.getParentCode());
			UupmDictionaryModel parent = this.uupmDictionaryDao.selectOne(paramMap);
			
			model.setDataPath(parent.getDataPath() + model.getItemCode() + ".");
			model.setDataLabel(parent.getDataLabel());	//
		}
		this.setAddDefault(model, loginInfo);
		this.uupmDictionaryDao.insertOne(model);
	}
	
	public void addDict(UupmDictionaryModel model, LoginInfo loginInfo) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getItemCode())) return;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("itemCode", model.getParentCode());
		UupmDictionaryModel parent = this.uupmDictionaryDao.selectOne(paramMap);
		
		model.setDataPath(parent.getDataPath() + model.getItemCode() + ".");
		model.setDataLabel(parent.getItemCode());	//
		
		this.setAddDefault(model, loginInfo);
		this.uupmDictionaryDao.insertOne(model);
	}
	
	public void addChildDict(UupmDictionaryModel model, LoginInfo loginInfo) {
		if(null==model || EasyStringCheckUtils.isEmpty(model.getItemCode())) return;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("itemCode", model.getParentCode());
		UupmDictionaryModel parent = this.uupmDictionaryDao.selectOne(paramMap);
		
		model.setDataPath(parent.getDataPath() + model.getItemCode() + ".");
		model.setDataLabel(parent.getDataLabel());	//
		
		this.setAddDefault(model, loginInfo);
		this.uupmDictionaryDao.insertOne(model);
	}

	/**
	 * 根据父编号获取其子节点-包括父节点
	 * @Date	2018年3月7日 上午10:00:32 <br/>
	 * @author  zhangST
	 * @param parentCode
	 * @return
	 */
	public List<UupmDictionaryModel> findChildrenList(String parentCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dataLabel", parentCode);
		List<UupmDictionaryModel> result = (List<UupmDictionaryModel>) this.uupmDictionaryDao.selectListBy(paramMap);
		return result;
	}
	
	/**
	 * 获取字典集合（根据父编号获取其子节点-不包括父节点）
	 * @Date	2018年3月7日 上午10:00:32 <br/>
	 * @author  zhangST
	 * @param parentCode
	 * @return
	 */
	public List<UupmDictionaryModel> findChildrenListForDict(String parentCode) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dataScope", "DICT");
		paramMap.put("dataLabel", parentCode);
		List<UupmDictionaryModel> result = (List<UupmDictionaryModel>) this.uupmDictionaryDao.selectListBy(paramMap);
		return result;
	}
	
	/**
	 * 获取分类集合
	 * @Date	2018年3月7日 上午10:02:18 <br/>
	 * @author  zhangST
	 * @param dataScope
	 * @return
	 */
	public List<UupmDictionaryModel> findChildrenListForCategory(String dataScope) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dataScope", dataScope);
		List<UupmDictionaryModel> result = (List<UupmDictionaryModel>) this.uupmDictionaryDao.selectListBy(paramMap);
		return result;
	}
	
	public UupmDictionaryModel findByItemCode(String itemCode, LoginInfo loginInfo) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("itemCode", itemCode);
		return this.uupmDictionaryDao.selectOne(paramMap);
	}
	
	public int delByItemCodes(String itermCodes, LoginInfo loginInfo) {
		return this.delWithInBy("itemCode", itermCodes, ",", loginInfo);
	}
}
