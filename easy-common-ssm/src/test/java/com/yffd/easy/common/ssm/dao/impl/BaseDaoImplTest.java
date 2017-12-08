package org.yffd.easy.common.ssm.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.yffd.easy.common.core.page.PageParam;
import org.yffd.easy.common.core.page.PageResult;
import org.yffd.easy.common.core.util.ValidUtils;
import org.yffd.easy.common.ssm.dao.IBaseDao;
import org.yffd.easy.common.ssm.model.TestModel;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年9月11日 下午2:21:26 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "classpath:syscfg/spring-core-cfg.xml"
})
public class BaseDaoImplTest {

    @Autowired
    private IBaseDao<TestModel> baseDao;
    
//    @Test
//    public void listRangeTest() {
//        int currentPage = 2;
//        int numPerPage = 3;
//        PageParam pageParam = new PageParam(currentPage, numPerPage);
//        
//        Map<String, Object> paramsMap = new HashMap<String, Object>();
//        paramsMap.put("userName", "11");
////        paramsMap.put("userCode", "1_1_0");
//        PageResult<TestModel> result = this.baseDao.listRange(pageParam, paramsMap);
//        System.out.println(result.getPageInfo());
//        System.out.println(result.getRecordList());
//        if (!ValidUtils.isEmpty(result.getRecordList())) {
//            for(TestModel model : result.getRecordList()) {
//                System.out.println(model);
//            }
//        } 
//    }
//    @Test
//    public void listPageTest() {
//        int currentPage = 1;
//        int numPerPage = 3;
//        PageParam pageParam = new PageParam(currentPage, numPerPage);
//        
//        Map<String, Object> paramsMap = new HashMap<String, Object>();
//        paramsMap.put("userName", "11");
////        paramsMap.put("userCode", "1_1_0");
//        PageResult<TestModel> result = this.baseDao.listPage(pageParam, paramsMap);
//        System.out.println(result.getPageInfo());
//        System.out.println(result.getRecordList());
//    }
//    @Test
//    public void selectByPrimaryKeyTest() {
//        String id = "14";
//        TestModel result = this.baseDao.getById(id);
//        System.out.println(result);
//    }
//    @Test
//    public void getByColumnOrListByColumnTest() {
//        Map<String, Object> paramsMap = new HashMap<String, Object>();
//        paramsMap.put("userCode", "1_1_0");
//        TestModel result = this.baseDao.getByColumn(paramsMap);
//        System.out.println(result);
//    }
//    @Test
//    public void getByOrListByTest() {
//        Map<String, Object> paramsMap = new HashMap<String, Object>();
//        paramsMap.put("userCode", "1_1_0");
//        TestModel result = this.baseDao.getBy(paramsMap);
//        System.out.println(result);
//    }
//    @Test
//    public void listByTest() {
//        Map<String, Object> paramsMap = new HashMap<String, Object>();
//        paramsMap.put("userName", "11");
////        paramsMap.put("userCode", "1_1_0");
//        List<TestModel> result = this.baseDao.listBy(paramsMap);
//        System.out.println(result);
//    }
//    @Test
//    public void listByColumnTest() {
//        Map<String, Object> paramsMap = new HashMap<String, Object>();
//        paramsMap.put("userName", "11");
////        paramsMap.put("userCode", "1_1_0");
//        List<TestModel> result = this.baseDao.listByColumn(paramsMap);
//        System.out.println(result);
//    }
//    @Test
//    public void getCountByColumnTest() {
//        Map<String, Object> paramsMap = new HashMap<String, Object>();
//        paramsMap.put("userName", "11");
//        paramsMap.put("userCode", "1_1_0");
//        Long result = this.baseDao.getCountByColumn(paramsMap);
//        System.out.println(result);
//    }
//    
//    @Test
//    public void batchDeleteByColumnTest() {
//        Map<String, Object> paramsMap = new HashMap<String, Object>();
//        paramsMap.put("userName", "11");
//        paramsMap.put("userCode", "1_1");
//        int result = this.baseDao.delete(paramsMap);
//        System.out.println(result);
//    }
//    
//    @Test
//    public void batchDeleteByIdsTest() {
//        String userName = "11";
//        String userCode = "1_1";
//        Date createTime = new Date();
//        
//        List<TestModel> list = new ArrayList<TestModel>();
//        for(int i=2;i<12;i++) {
//            TestModel entity = new TestModel(userName+"_"+i, userCode+"_"+i, createTime);
//            entity.setId(i+"");
//            list.add(entity);
//        }
//        int result = this.baseDao.delete(list);
//        System.out.println(result);
//    }
//    
//    @Test
//    public void deleteByPrimaryKeyTest() {
//        String id = "1";
//        int result = this.baseDao.delete(id);
//        System.out.println(result);
//    }
//    
//    @Test
//    public void batchUpdateByColumnTest() {
//        Map<String, Object> paramsMap = new HashMap<String, Object>();
//        paramsMap.put("userName", "11");
//        paramsMap.put("userCode", "1_1");
//        paramsMap.put("createTime", new Date());
//        int result = this.baseDao.update(paramsMap);
//        System.out.println(result);
//    }
//    
//    @Test
//    public void batchUpdateByIdsTest() {
//        String userName = "11";
//        String userCode = "1_1";
//        Date createTime = new Date();
//        
//        List<TestModel> list = new ArrayList<TestModel>();
//        for(int i=2;i<12;i++) {
//            TestModel entity = new TestModel(userName+"_"+i, userCode+"_"+i, createTime);
//            entity.setId(i+"");
//            list.add(entity);
//        }
//        int result = this.baseDao.update(list);
//        System.out.println(result);
//    }
//    
//    @Test
//    public void updateByPrimaryKeyTest() {
//        String id = "1";
//        String userName = "22";
//        String userCode = "2_2";
//        Date createTime = new Date();
//        TestModel entity = new TestModel(userName, userCode, createTime);
//        entity.setId(id);
//        int result = this.baseDao.update(entity);
//        System.out.println(result);
//    }
//    
//    @Test
//    public void batchInsertTest() {
//        String userName = "11";
//        String userCode = "1_1";
//        Date createTime = new Date();
//        
//        List<TestModel> list = new ArrayList<TestModel>();
//        for(int i=0;i<10;i++) {
//            TestModel entity = new TestModel(userName+"_"+i, userCode+"_"+i, createTime);
//            list.add(entity);
//        }
//        int result = this.baseDao.insert(list);
//        System.out.println(result);
//    }
//    
//    @Test
//    public void insertTest() {
//        String userName = "11";
//        String userCode = "1_1";
//        Date createTime = new Date();
//        TestModel entity = new TestModel(userName, userCode, createTime);
//        int result = this.baseDao.insert(entity);
//        System.out.println(result);
//        System.out.println(entity.getId());
//    }
}

