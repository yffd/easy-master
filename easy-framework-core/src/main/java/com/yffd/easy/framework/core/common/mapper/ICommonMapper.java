package com.yffd.easy.framework.core.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.pojo.IPOJO;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年3月27日 下午6:30:47 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface ICommonMapper<PO> {
	
	public List<PO> selectListBy(@Param("po") PO po, @Param("map") Map<String, Object> map, @Param("page") PageParam page);
	
	public Integer selectCountBy(@Param("po") PO po, @Param("map") Map<String, Object> map);
	
	public PO selectOneBy(@Param("po") PO po, @Param("map") Map<String, Object> map);
	
	public Integer insertOne(PO po);
	
	public Integer insertList(List<PO> poList);
	
	public Integer updateBy(@Param("po") PO po, @Param("old") PO old, @Param("map") Map<String, Object> map);
	
	public Integer deleteBy(@Param("po") PO po, @Param("map") Map<String, Object> map);
	
}

