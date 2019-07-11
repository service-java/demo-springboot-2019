package top.zywork.common.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 邮件发送认证器，返回用户名及密码的认证器<br />
 * 创建于2017-09-14
 *
 * @author 王振宇
 * @version 1.0
 */
public class MailAuthenticator extends Authenticator {
	
	private String userName;
	private String password;
	
	public MailAuthenticator(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}

}
