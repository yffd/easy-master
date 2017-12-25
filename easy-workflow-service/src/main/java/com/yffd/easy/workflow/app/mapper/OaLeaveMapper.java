package com.yffd.easy.workflow.app.mapper;

import java.util.List;
import java.util.Map;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.app.model.OaLeave;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年12月6日 下午5:09:41 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface OaLeaveMapper {
	
	Long selectCountBy(Map<String, Object> paramMap);
	
	PageResult<Map<String, Object>> selectPage(PageParam paginationInfo, Map<String, Object> paramMap);
	
    List<Map<String, Object>> selectListBy(Map<String, Object> paramMap);
	
    Map<String, Object> selectByPK(String primaryKey);
	
	Map<String, Object> selectOne(Map<String, Object> paramMap);

    List<Map<String, Object>> selectAll();
	
    int insert(OaLeave model);

    int insertBatch(List<OaLeave> list);
    
    int updateBy(OaLeave model);
    
    int updateByPK(OaLeave model);

    int updateBatch(List<OaLeave> list);

    int deleteByPK(String primaryKey);

    int deleteBy(Map<String, Object> paramMap);

    int deleteBatch(List<OaLeave> list);
}

