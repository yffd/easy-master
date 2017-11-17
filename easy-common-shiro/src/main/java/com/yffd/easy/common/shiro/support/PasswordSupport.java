package com.yffd.easy.common.shiro.support;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.yffd.easy.common.shiro.model.AccountInfo;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2017年11月7日 下午5:33:20 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class PasswordSupport {
	private static final RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	
	private String algorithmName = "md5";
    private int hashIterations = 2;
    
	public void encryptPassword(AccountInfo info) {
		info.setSalt(randomNumberGenerator.nextBytes().toHex());
		String newPassword = new SimpleHash(algorithmName,
				info.getAccountPwd(),
				ByteSource.Util.bytes(info.getCredentialsSalt()),
				hashIterations).toHex();
		info.setAccountPwd(newPassword);
	}

	public String getAlgorithmName() {
		return algorithmName;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public int getHashIterations() {
		return hashIterations;
	}

	public void setHashIterations(int hashIterations) {
		this.hashIterations = hashIterations;
	}
	
}

