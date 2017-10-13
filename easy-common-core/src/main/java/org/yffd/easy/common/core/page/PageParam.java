package org.yffd.easy.common.core.page;

import java.io.Serializable;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月8日 下午6:26:53 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class PageParam implements Serializable {
    
    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * @since JDK 1.7+
     */
    private static final long serialVersionUID = -6381874871206323550L;
    /** 默认为第一页 */
    public static final Long DEFAULT_PAGE_NUM = 1L;
    /** 默认每页记录数(15) */
    public static final Long DEFAULT_NUM_PER_PAGE = 15L;
    /** 最大每页记录数(100) */
    public static final Long MAX_PAGE_SIZE = 100L;
    
    private Long currentPage; // 当前页数
    private Long numPerPage; // 每页记录数
    
    private Long startRowNum; // 开始行号
    private Long endRowNum; //结束行号

    private Long totalRecord; // 总记录数
    private Long totalPage; //总页数
    
    /** 是否进行自动count计算总条数，默认为自动计算：true:是，false:否 */
    private boolean autoCount = true;
    
    public PageParam() {
    }
    
    public PageParam(Long currentPage, Long numPerPage) {
        this.currentPage = currentPage;
        this.numPerPage = numPerPage;
    }
    
    public PageParam(Long currentPage, Long numPerPage, Long totalRecord) {
        this.currentPage = currentPage;
        this.numPerPage = numPerPage;
        this.totalRecord = totalRecord;
    }
    
    /**
     * 
     * countStartRowNum:计算开始行号. <br/>
     * @Date	2017年9月11日 上午10:15:23 <br/>
     * @author  zhangST
     * @param currentPage
     * @param numPerPage
     * @return
     */
    public static Long countStartRowNum(Long currentPage, Long numPerPage) {
        return (currentPage - 1) * numPerPage;
    }
    
    /**
     * 
     * countEndRowNum:计算结束行号. <br/>
     * @Date	2017年9月11日 上午10:15:04 <br/>
     * @author  zhangST
     * @param currentPage
     * @param numPerPage
     * @return
     */
    public static Long countEndRowNum(Long currentPage, Long numPerPage) {
        return currentPage * numPerPage;
    }
    
    /**
     * 计算总页数 .
     * 
     * @param totalRecord
     *            总记录数.
     * @param numPerPage
     *            每页记录数.
     * @return totalPage 总页数.
     */
    public static Long countTotalPage(Long totalRecord, Long numPerPage) {
        if (totalRecord % numPerPage == 0) {
            // 刚好整除
            return totalRecord / numPerPage;
        } else {
            // 不能整除则总页数为：商 + 1
            return totalRecord / numPerPage + 1;
        }
    }

    /**
     * 校验当前页数currentPage.<br/>
     * 1、判断currentPage是否小于1，小于则返回1.<br/>
     * 2、其它则直接返回currentPage .
     * 
     * @param currentPage
     *            输入的当前页数 .
     * @return currentPage .
     */
    public static Long checkCurrentPage(Long currentPage) {
        return currentPage < 1 ? 1 : currentPage;
    }
    
    /**
     * 校验当前页数currentPage.<br/>
     * 1、先根据总记录数totalRecord和每页记录数numPerPage，计算出总页数totalPage.<br/>
     * 2、判断页面提交过来的当前页数currentPage是否大于总页数totalPage，大于则返回totalPage.<br/>
     * 3、判断currentPage是否小于1，小于则返回1.<br/>
     * 4、其它则直接返回currentPage .
     * 
     * @param totalRecord
     *            要分页的总记录数 .
     * @param numPerPage
     *            每页记录数大小 .
     * @param currentPage
     *            输入的当前页数 .
     * @return currentPage .
     */
    public static Long checkCurrentPage(Long totalRecord, Long numPerPage, Long currentPage) {
    	Long totalPage = PageParam.countTotalPage(totalRecord, numPerPage); // 最大页数
        if (currentPage > totalPage) {
            // 如果页面提交过来的页数大于总页数，则将当前页设为总页数
            // 此时要求totalPage要大于获等于1
            if (totalPage < 1) {
                return 1L;
            }
            return totalPage;
        } else if (currentPage < 1) {
            return 1L; // 当前页不能小于1（避免页面输入不正确值）
        } else {
            return currentPage;
        }
    }

    /**
     * 校验页面输入的每页记录数numPerPage是否合法 .<br/>
     * 1、当页面输入的每页记录数numPerPage大于允许的最大每页记录数MAX_PAGE_SIZE时，返回MAX_PAGE_SIZE.
     * 2、如果numPerPage小于1，则返回默认的每页记录数DEFAULT_PAGE_SIZE.
     * 
     * @param numPerPage
     *            页面输入的每页记录数 .
     * @return checkNumPerPage .
     */
    public static Long checkNumPerPage(Long numPerPage) {
        if (numPerPage > PageParam.MAX_PAGE_SIZE) {
            return PageParam.MAX_PAGE_SIZE;
        } else if (numPerPage < 1) {
            return PageParam.DEFAULT_NUM_PER_PAGE;
        } else {
            return numPerPage;
        }
    }
    
    /** 当前页数 */
    public Long getCurrentPage() {
        return currentPage;
    }
    /** 当前页数 */
    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }
    /** 每页记录数 */
    public Long getNumPerPage() {
        return numPerPage;
    }
    /** 每页记录数 */
    public void setNumPerPage(Long numPerPage) {
        this.numPerPage = numPerPage;
    }
    /** 开始行号 */
    public Long getStartRowNum() {
        return startRowNum;
    }
    /** 开始行号 */
    public void setStartRowNum(Long startRowNum) {
        this.startRowNum = startRowNum;
    }
    /** 结束行号 */
    public Long getEndRowNum() {
        return endRowNum;
    }
    /** 结束行号 */
    public void setEndRowNum(Long endRowNum) {
        this.endRowNum = endRowNum;
    }
    /** 总记录数 */
    public Long getTotalRecord() {
        return totalRecord;
    }
    /** 总记录数 */
    public void setTotalRecord(Long totalRecord) {
        this.totalRecord = totalRecord;
    }
    /** 总页数 */
    public Long getTotalPage() {
        return totalPage;
    }
    /** 总页数 */
    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isAutoCount() {
        return autoCount;
    }

    public void setAutoCount(boolean autoCount) {
        this.autoCount = autoCount;
    }

    @Override
    public String toString() {
        return "PageParam [currentPage=" + currentPage + ", numPerPage=" + numPerPage + ", startRowNum=" + startRowNum + ", endRowNum="
                + endRowNum + ", totalRecord=" + totalRecord + ", totalPage=" + totalPage + ", autoCount=" + autoCount + "]";
    }

}

