package com.yffd.easy.workflow.activiti.engine;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.Picture;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.util.IoUtil;
import org.junit.Test;

import com.yffd.easy.workflow.activiti.test.AbstractActivitiTest;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月30日 下午3:11:08 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class IdentifyServiceTest extends AbstractActivitiTest {
	
	@Test
	public void userAddTest() {
		IdentityService identityService = this.identityService;
		User user = identityService.newUser("zhangsan");
		user.setFirstName("zhang");
		user.setLastName("san");
		user.setEmail("zhangsan@admin.com");
		user.setPassword("zhangsan");
		identityService.saveUser(user);

		byte[] pictureBytes = IoUtil.readInputStream(this.getClass().getClassLoader().getResourceAsStream(""), null);
        Picture picture = new Picture(pictureBytes, "image/jpeg");
        identityService.setUserPicture("zhangsan", picture);
        
		User user_db = identityService.createUserQuery().userId("zhangsan").singleResult();
		System.out.println(user_db.getId());
	}
	
	@Test
	public void userDelTest() {
		IdentityService identityService = this.identityService;
		identityService.deleteUser("zhangsan");
	}
	
	@Test
	public void groupAddTest() {
		IdentityService identityService = this.identityService;
		Group group = identityService.newGroup("superadmin");
		group.setName("超级管理员");
		group.setType("security-role");
		
		identityService.saveGroup(group);
		
		Group group_db = identityService.createGroupQuery().groupId("superadmin").singleResult();
		
		System.out.println(group_db.getId());
	}
	
	@Test
	public void groupDelTest() {
		IdentityService identityService = this.identityService;
		identityService.deleteGroup("superadmin");
	}
	
	@Test
	public void userAndGroupTest() {
		IdentityService identityService = this.identityService;
		// 关联用户和组
		identityService.createMembership("zhangsan", "superadmin");
		
		User userInGroup = identityService.createUserQuery().memberOfGroup("superadmin").singleResult();
		System.out.println("user in group:" + userInGroup.getId());
		
		Group groupWithUser = identityService.createGroupQuery().groupMember("zhangsan").singleResult();
		
		System.out.println("group with user:" + groupWithUser.getId());
	}
	
}

