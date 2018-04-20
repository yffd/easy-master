package com.yffd.easy.framework.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yffd.easy.common.core.page.PageParam;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年3月27日 下午6:30:47 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface ICommonMapper<E> {
	
	public int insertOne(E entity);
	
	public int insertList(List<E> poList);
	
	public int updateBy(@Param("entity") E entity, @Param("oldEntity") E oldEntity, @Param("map") Map<String, Object> map);
	
	public int deleteBy(@Param("entity") E entity, @Param("map") Map<String, Object> map);
	
	public E selectOneBy(@Param("entity") E entity, @Param("map") Map<String, Object> map);
	
	public Integer selectCountBy(@Param("entity") E entity, @Param("map") Map<String, Object> map);
	
	public List<E> selectListBy(@Param("entity") E entity, @Param("map") Map<String, Object> map, @Param("orderBy") String orderBy, @Param("page") PageParam page);
	
}

