package org.yffd.easy.common.core.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月8日 下午2:20:10 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class PageResult<T> implements Serializable {
    
    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * @since JDK 1.7+
     */
    private static final long serialVersionUID = -8363059681015630815L;

    private PageParam pageInfo; // 分页信息
    private List<T> recordList = new ArrayList<T>(0); // 本页的数据列表
    private Map<String, Object> countResultMap; // 当前分页条件下的统计结果
    
    
    public PageResult() {
    }
    
    public PageResult(PageParam pageInfo, List<T> recordList) {
        this.pageInfo = pageInfo;
        this.recordList = recordList;
    }

    public PageResult(PageParam pageInfo, List<T> recordList, Map<String, Object> countResultMap) {
        this.pageInfo = pageInfo;
        this.recordList = recordList;
        this.countResultMap = countResultMap;
    }

    /** 分页信息 */
    public PageParam getPageInfo() {
        return pageInfo;
    }
    /** 分页信息 */
    public void setPageInfo(PageParam pageInfo) {
        this.pageInfo = pageInfo;
    }
    /** 当前页数据列表 */
    public List<T> getRecordList() {
        return recordList;
    }
    /** 当前页数据列表 */
    public void setRecordList(List<T> recordList) {
        this.recordList = recordList;
    }
    /** 当前分页条件下的统计结果 */
    public Map<String, Object> getCountResultMap() {
        return countResultMap;
    }
    /** 当前分页条件下的统计结果 */
    public void setCountResultMap(Map<String, Object> countResultMap) {
        this.countResultMap = countResultMap;
    }
    
}

