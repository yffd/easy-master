package com.yffd.easy.framework.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.framework.domain.CustomPo;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年3月27日 下午6:30:47 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface ICommonMapper<PO extends CustomPo> {
	
	public List<PO> selectListBy(@Param("model") PO model, @Param("map") Map<String, Object> map, @Param("page") PageParam page);
	
	public Long selectCountBy(@Param("model") PO model, @Param("map") Map<String, Object> map);
	
	public PO selectOneBy(@Param("model") PO model, @Param("map") Map<String, Object> map);
	
	public int insertOne(PO model);
	
	public int insertList(List<PO> modelList);
	
	public int updateBy(@Param("model") PO model, @Param("old") PO old, @Param("map") Map<String, ?> map);
	
	public int deleteBy(@Param("model") PO model, @Param("map") Map<String, Object> map);
	
}

