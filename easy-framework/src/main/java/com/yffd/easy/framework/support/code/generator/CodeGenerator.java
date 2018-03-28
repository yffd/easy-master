package com.yffd.easy.framework.support.code.generator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yffd.easy.common.core.converter.EasyModelConverter;

/**
 * @Description  代码生成器.
 * @Date		 2018年2月6日 上午10:07:51 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class CodeGenerator extends EasyModelConverter {
	private static final SimpleDateFormat DATE_FMT = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
	
	private List<String> sortedPropsList = new ArrayList<String>();
	private List<String> skipModelNamesLike = new ArrayList<String>();
	
	{
		sortedPropsList.add("version");
		sortedPropsList.add("delFlag");
		sortedPropsList.add("createBy");
		sortedPropsList.add("createTime");
		sortedPropsList.add("updateBy");
		sortedPropsList.add("updateTime");
		sortedPropsList.add("id");
		sortedPropsList.add("tenantCode");
		
		skipModelNamesLike.add("EasyPersistModel");
		skipModelNamesLike.add("BaseModel");
	}
	
	/**
	 * 属性排序
	 * @Date	2018年2月6日 上午10:09:11 <br/>
	 * @author  zhangST
	 * @return
	 */
	protected List<String> getSortedPropsList() {
		return sortedPropsList;
	}
	
	/**
	 *  跳过的model名称，匹配模式为 like
	 * @Date	2018年2月6日 上午10:08:55 <br/>
	 * @author  zhangST
	 * @return
	 */
	protected List<String> getSkipModelNamesLike() {
		return skipModelNamesLike;
	}
	
	/**
	 * 格式化Mapper的名称
	 * @Date	2018年2月6日 上午10:51:13 <br/>
	 * @author  zhangST
	 * @param modelClazz
	 * @return
	 */
	protected String fmtMapperName(Class<?> modelClazz) {
		return this.fmtModelName(modelClazz, null, "Mapper");
	}
	
	/**
	 * 格式化DaoTest类名称，简短名称，非完整类名
	 * @Date	2018年2月6日 上午11:05:08 <br/>
	 * @author  zhangST
	 * @param modelClazz
	 * @return
	 */
	protected String fmtDaoTestSimpleName(Class<?> modelClazz) {
		return this.fmtModelName(modelClazz, null, "DaoTest");
	}
	
	/**
	 * 格式化Dao类名称，简短名称，非完整类名
	 * @Date	2018年2月6日 上午11:05:08 <br/>
	 * @author  zhangST
	 * @param modelClazz
	 * @return
	 */
	protected String fmtDaoSimpleName(Class<?> modelClazz) {
		return this.fmtModelName(modelClazz, null, "Dao");
	}
	
	/**
	 * 格式化ServiceTest类名称，简短名称，非完整类名
	 * @Date	2018年2月6日 上午11:05:08 <br/>
	 * @author  zhangST
	 * @param modelClazz
	 * @return
	 */
	protected String fmtServiceTestSimpleName(Class<?> modelClazz) {
		return this.fmtModelName(modelClazz, null, "ServiceTest");
	}
	
	protected String fmtControllerSimpleName(Class<?> modelClazz) {
		return this.fmtModelName(modelClazz, null, "Controller");
	}
	
	/**
	 * 格式化Service类名称，简短名称，非完整类名
	 * @Date	2018年2月6日 上午11:05:08 <br/>
	 * @author  zhangST
	 * @param modelClazz
	 * @return
	 */
	protected String fmtServiceSimpleName(Class<?> modelClazz) {
		return this.fmtModelName(modelClazz, null, "Service");
	}
	
	/**
	 * 格式化Dao完整类名，即包名+类名
	 * @Date	2018年2月6日 上午10:41:11 <br/>
	 * @author  zhangST
	 * @param modelClazz
	 * @param daoPackageName
	 * @return
	 */
	protected String fmtDaoFullClassName(Class<?> modelClazz, String daoPackageName) {
		String daoSimpleName = this.fmtModelName(modelClazz, null, "Dao");
		return daoPackageName + "." + daoSimpleName;
	}
	
	/**
	 * 格式化Service完整类名，即包名+类名
	 * @Date	2018年2月6日 上午10:41:11 <br/>
	 * @author  zhangST
	 * @param modelClazz
	 * @param servicePackageName
	 * @return
	 */
	protected String fmtServiceFullClassName(Class<?> modelClazz, String servicePackageName) {
		String daoSimpleName = this.fmtModelName(modelClazz, null, "Service");
		return servicePackageName + "." + daoSimpleName;
	}
	
	/**
	 * 格式化model的名称，简短名称，非完整类名
	 * @Date	2018年2月6日 上午10:33:35 <br/>
	 * @author  zhangST
	 * @param modelClazz
	 * @param prefix
	 * @param suffix
	 * @return
	 */
	protected String fmtModelName(Class<?> modelClazz, String prefix, String suffix) {
		String modelName = modelClazz.getSimpleName();
		if(modelName.lastIndexOf("Model")!=-1)
			modelName = modelName.substring(0, modelName.lastIndexOf("Model"));
		
		StringBuilder sb = new StringBuilder();
		if(null!=prefix && !"".equals(prefix.trim())) {
			sb.append(prefix);
		}
		sb.append(modelName);
		if(null!=suffix && !"".equals(suffix.trim())) {
			sb.append(suffix);
		}
		return sb.toString();
	}
	
	protected String fmtDate(Date date) {
		return DATE_FMT.format(new Date());
	}
	
	protected String fmtLine(String lines, String prefix, String suffix) {
		if(null==lines || "".equals(lines)) return null; 
		try {
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(lines.getBytes("utf8"))));
			String line;
			while((line = reader.readLine()) !=null) {
				sb.append(prefix).append(line).append("\r\n");
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 创建多级目录
	 * @Date	2018年2月6日 上午10:34:03 <br/>
	 * @author  zhangST
	 * @param dirPath
	 */
	protected void makedirs(String dirPath) {
		File file = new File(dirPath);
		if(!file.exists()) {
			file.mkdirs();
		}
	}
	
	/**
	 * 
	 * @Date	2018年2月6日 下午2:27:51 <br/>
	 * @author  zhangST
	 * @param content
	 * @param outRootDirPath
	 * @param covered			是否覆盖旧文件
	 */
	protected void writeToFile(String content, String outRootDirPath, boolean covered) {
		try {
			if(new File(outRootDirPath).exists()) {
				if(covered) {
					System.out.println("覆盖文件：" + outRootDirPath + "， 原因：文件已存在！");
				} else {
					System.out.println("跳过文件：" + outRootDirPath + "， 原因：文件已存在！");
					return;
				}
			}
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(outRootDirPath))));
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(content.getBytes("utf8"))));
			String line = null;
			while((line = reader.readLine()) != null) {
				writer.write(line + "\r\n");
			}
			writer.close();
			reader.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

