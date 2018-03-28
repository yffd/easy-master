package com.yffd.easy.uupm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yffd.easy.uupm.api.model.UupmSysTreeModel;
import com.yffd.easy.uupm.base.UupmBaseDao;

/**
 * @Description	简单描述该类的功能（可选）.
 * @Date		2018年03月19日 13时57分51秒 <br/>
 * @author		ZhangST
 * @version		1.0
 * @since		JDK 1.7+
 * @see
 */

@Repository
public class UupmSysTreeDao extends UupmBaseDao<UupmSysTreeModel> {

	public int updateLeftForAdd(Map<String, Object> paramMap) {
		return this.getSqlSession().update(this.getStatement("updateLeftForAdd"), paramMap);
	}
	
	public int updateRightForAdd(Map<String, Object> paramMap) {
		return this.getSqlSession().update(this.getStatement("updateRightForAdd"), paramMap);
	}
	
	public int updateLeftForDel(Map<String, Object> paramMap) {
		return this.getSqlSession().update(this.getStatement("updateLeftForDel"), paramMap);
	}
	
	public int updateRightForDel(Map<String, Object> paramMap) {
		return this.getSqlSession().update(this.getStatement("updateRightForDel"), paramMap);
	}
	
	public int delNodes(UupmSysTreeModel model) {
		return this.getSqlSession().update(this.getStatement("delNodes"), model);
	}
	
	public int countLayer(UupmSysTreeModel model) {
		return this.getSqlSession().selectOne(this.getStatement("countLayer"), model);
	}
	
	public List<UupmSysTreeModel> selectChildrenNodeListBy(UupmSysTreeModel model) {
		return this.getSqlSession().selectList(this.getStatement("selectChildrenNodeListBy"), model);
	}
	
	public List<UupmSysTreeModel> selectParentNodeListBy(UupmSysTreeModel model) {
		return this.getSqlSession().selectList(this.getStatement("selectParentNodeListBy"), model);
	}
	
	
}
