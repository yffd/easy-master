package com.yffd.easy.uupm.code;

import java.util.ArrayList;
import java.util.List;

import com.yffd.easy.framework.base.service.GenericService;
import com.yffd.easy.framework.support.code.generator.CodeGenerator;
import com.yffd.easy.uupm.base.UupmBaseDao;
import com.yffd.easy.uupm.base.UupmBaseDaoTest;
import com.yffd.easy.uupm.base.UupmBaseServiceTest;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年2月5日 下午5:34:58 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class LocalProjectConfigTest extends CodeGenerator {
	
	public static String author = "ZhangST";
	public static boolean covered = true;
	
	public static String modelRootDirPath = "D:\\java\\git-easy\\easy-master\\easy-uupm-api\\src\\main\\java";
	public static String modelPackageName = "com.yffd.easy.uupm.api.model";
	
	public static Class<?> baseDaoClazz = UupmBaseDao.class;
	public static String daoPackageName = "com.yffd.easy.uupm.dao";
	
	public static Class<?> baseDaoTestClazz = UupmBaseDaoTest.class;
	public static String daoTestPackageName = daoPackageName;
	
	public static Class<?> baseServiceClazz = GenericService.class;
	public static String servicePackageName = "com.yffd.easy.uupm.service";
	
	public static Class<?> baseServiceTestClazz = UupmBaseServiceTest.class;
	public static String serviceTestPackageName = servicePackageName;
	
	public static String outRootDirPath_mapper = "D:\\java\\git-easy\\easy-master\\easy-uupm-service\\src\\main\\resources\\mybatis\\mapper\\uupm";
	public static String outRootDirPath_dao = "D:\\java\\git-easy\\easy-master\\easy-uupm-service\\src\\main\\java";
	public static String outRootDirPath_dao_test = "D:\\java\\git-easy\\easy-master\\easy-uupm-service\\src\\test\\java";
	public static String outRootDirPath_service = outRootDirPath_dao;
	public static String outRootDirPath_service_test = outRootDirPath_dao_test;
	
	List<String> skipModelNamesLike = new ArrayList<String>();
	{
		skipModelNamesLike.add("EasyPersistModel");
		skipModelNamesLike.add("BaseModel");
	}
	@Override
	protected List<String> getSkipModelNamesLike() {
		return skipModelNamesLike;
	}
	
	
}

