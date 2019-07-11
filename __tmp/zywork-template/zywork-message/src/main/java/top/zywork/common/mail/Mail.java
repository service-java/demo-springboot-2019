package top.zywork.common.mail;

import top.zywork.common.ExceptionUtils;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 邮件对象类，封装邮件属性，如发送者，接收者，主题，内容，类型等<br />
 * 创建于2017-09-14
 *
 * @author 王振宇
 * @version 1.0
 */
public class Mail {
	
	private MailAccount from;
	private List<MailAccount> recipients;
	private List<MailAccount> ccRecipients;
	private List<MailAccount> bccRecipients;
	private String subject;
	private String content;
	private String contentType;
	private List<String> files;
	private Multipart multipart;

	public MailAccount getFrom() {
		return from;
	}

	public void setFrom(MailAccount from) {
		this.from = from;
	}

	public List<MailAccount> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<MailAccount> recipients) {
		this.recipients = recipients;
	}

	public List<MailAccount> getCcRecipients() {
		return ccRecipients;
	}

	public void setCcRecipients(List<MailAccount> ccRecipients) {
		this.ccRecipients = ccRecipients;
	}

	public List<MailAccount> getBccRecipients() {
		return bccRecipients;
	}

	public void setBccRecipients(List<MailAccount> bccRecipients) {
		this.bccRecipients = bccRecipients;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentType() {
		return contentType;
	}

	public List<String> getFiles() {
		return files;
	}

	public void setFiles(List<String> files) {
		this.files = files;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Multipart getMultipart() {
		Multipart multipart = new MimeMultipart();
		try {
			BodyPart contentPart = new MimeBodyPart();
			contentPart.setContent(content, contentType);
			multipart.addBodyPart(contentPart);
			for (String path : files) {
				BodyPart attachmentPart = new MimeBodyPart();
				File file = new File(path);
				attachmentPart.setDataHandler(new DataHandler(new FileDataSource(file)));
				attachmentPart.setFileName(MimeUtility.encodeText(file.getName()));
				multipart.addBodyPart(attachmentPart);
			}
		} catch (MessagingException e) {
			throw ExceptionUtils.appException(e);
		} catch (UnsupportedEncodingException e) {
			throw ExceptionUtils.appException(e);
		}
		return multipart;
	}

}
