package com.yffd.easy.uupm.web.dev;

import java.util.ArrayList;
import java.util.List;

import com.yffd.easy.framework.support.code.generator.CodeControllerGenerator;
import com.yffd.easy.framework.support.code.generator.CodeGenerator;
import com.yffd.easy.uupm.api.model.UupmApplicationModel;
import com.yffd.easy.uupm.service.UupmApplicationService;
import com.yffd.easy.uupm.web.common.UupmCommonController;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年2月7日 下午2:20:00 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class CodeControllerFileHelper extends CodeGenerator {
	private static CodeControllerGenerator controllerCodeGenerator = new CodeControllerGenerator();
	
	private static boolean covered = true;
	private static String author = "ZhangST";
	private static String outRootDirPath = "F:\\easy\\easy-master\\easy-uupm-web\\src\\main\\java";;
	private static Class<?> superControllerClazz = UupmCommonController.class;
	private static String controllerPackageName = "com.yffd.easy.uupm.web.controller";
	private static Class<?> modelClazz = UupmApplicationModel.class;
	private static Class<?> serviceClazz = UupmApplicationService.class;
	
	List<String> skipModelNamesLike = new ArrayList<String>();
	{
		skipModelNamesLike.add("EasyPersistModel");
		skipModelNamesLike.add("BaseModel");
	}
	
	@Override
	protected List<String> getSkipModelNamesLike() {
		return skipModelNamesLike;
	}
	
	public static void main(String[] args) {
		controllerCodeGenerator.writeToFile(modelClazz, superControllerClazz, controllerPackageName, serviceClazz, author, outRootDirPath, covered);
		
//		controllerCodeGenerator.writeToConsole(modelClazz, superControllerClazz, controllerPackageName, serviceClazz, author);
	}
	
}

