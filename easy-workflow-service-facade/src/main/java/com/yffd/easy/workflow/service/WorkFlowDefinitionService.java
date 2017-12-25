package com.yffd.easy.workflow.service;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

import com.yffd.easy.common.core.page.PageParam;
import com.yffd.easy.common.core.page.PageResult;
import com.yffd.easy.workflow.model.dto.WorkFlowDefinitionDTO;

/**
 * @Description  流程定义业务操作.
 * @Date		 2017年12月13日 下午5:32:57 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public interface WorkFlowDefinitionService {
	
	/**
	 * 流程定义：分页查询
	 * @Date	2017年12月21日 下午2:22:54 <br/>
	 * @author  zhangST
	 * @param model
	 * @param pageParam
	 * @return
	 */
	PageResult<WorkFlowDefinitionDTO> findListPage(WorkFlowDefinitionDTO model, PageParam pageParam);
	
	/**
	 * 流程定义：主键查询
	 * @Date	2017年12月21日 下午2:23:15 <br/>
	 * @author  zhangST
	 * @param id
	 * @return
	 */
	WorkFlowDefinitionDTO findDefinitionByPK(String id);
	
	/**
	 * 流程定义：根据【流程部署】ID加载内部资源（xml、png）
	 * @Date	2017年12月21日 下午2:24:30 <br/>
	 * @author  zhangST
	 * @param deploymentId		流程发布ID
	 * @param resourceName
	 * @return
	 */
	InputStream loadResource(String deploymentId, String resourceName);
	
	/**
	 * 流程定义：激活【流程定义】
	 * @Date	2017年12月13日 下午2:20:37 <br/>
	 * @author  zhangST
	 * @param definitionId
	 * @return  0：更新成功，1：流程实例不存在或已激活
	 */
	int activateDefinition(String definitionId);
	
	/**
	 * 流程定义：挂起【流程定义】，挂起的流程定义，不能创建新的流程实例（会抛出一个异常）。
	 * @Date	2017年12月13日 下午2:21:04 <br/>
	 * @author  zhangST
	 * @param definitionId
	 * @return	0：更新成功，1：流程实例不存在，2：流程实例已挂起
	 */
	int suspendDefinition(String definitionId);
	
	/**
	 * 流程定义：发布-类路径
	 * @Date	2017年12月20日 下午4:38:38 <br/>
	 * @author  zhangST
	 * @param bpmnClasspath
	 * @param pngClasspath
	 * @return			流程发布ID
	 */
	String deployDefinitionByClasspath(String bpmnClasspath, String pngClasspath);
	
	/**
	 * 流程定义：发布-输入流
	 * @Date	2017年12月20日 下午4:38:50 <br/>
	 * @author  zhangST
	 * @param resourceName
	 * @param inputStream
	 * @return			流程发布ID
	 */
	String deployDefinitionByInputStream(String resourceName, InputStream inputStream);
	
	/**
	 * 流程定义：发布-zip输入流
	 * @Date	2017年12月20日 下午4:39:16 <br/>
	 * @author  zhangST
	 * @param ziInputStream
	 * @return			流程发布ID
	 */
	String deployDefinitionByZipInputStream(ZipInputStream ziInputStream);
	
}

