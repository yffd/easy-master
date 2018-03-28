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
	
	public List<PO> selectListBy(@Param("paramPo") PO paramPo, @Param("paramMap") Map<String, Object> paramMap, 
			@Param("paramPage") PageParam paramPage);
	
	public Long selectCountBy(@Param("paramPo") PO paramPo, @Param("paramMap") Map<String, Object> paramMap);
	
	public PO selectOneBy(@Param("paramPo") PO paramPo, @Param("paramMap") Map<String, Object> paramMap);
	
	public int insertOne(PO paramPo);
	
	public int insertList(List<PO> paramPos);
	
	public int updateBy(@Param("paramPoNew") PO paramPoNew, @Param("paramPoOld") PO paramPoOld, @Param("paramMap") Map<String, ?> paramMap);
	
	public int deleteBy(@Param("paramPo") PO paramPo, @Param("paramMap") Map<String, Object> paramMap);
	
}

