package com.yffd.easy.web.admin.shiro.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.yffd.easy.admin.pms.model.PmsUser;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月7日 下午5:33:20 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class PasswordHelper {
	private static final RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	
	private static String algorithmName = "md5";
    private static int hashIterations = 2;
    
	public static void encryptPassword(PmsUser user) {
		user.setSalt(randomNumberGenerator.nextBytes().toHex());
		String newPassword = new SimpleHash(algorithmName,
				user.getPassword(),
				ByteSource.Util.bytes(user.getCredentialsSalt()),
				hashIterations).toHex();
		user.setPassword(newPassword);
	}
//	public static void encryptPassword(PmsAccount account) {
//		account.setSalt(randomNumberGenerator.nextBytes().toHex());
//		String newPassword = new SimpleHash(algorithmName,
//				account.getAccountPwd(),
//				ByteSource.Util.bytes(account.getCredentialsSalt()),
//				hashIterations).toHex();
//		account.setAccountPwd(newPassword);
//	}

	/**
	 * 加密
	 * @Date	2017年11月14日 上午10:25:12 <br/>
	 * @author  zhangST
	 * @param accountPwd	明文
	 * @param salt
	 * @return
	 */
	public static String getPwd(String accountPwd, String salt) {
		String newPassword = new SimpleHash(algorithmName, accountPwd, ByteSource.Util.bytes(salt), hashIterations).toHex();
		return newPassword;
	}
	
}

