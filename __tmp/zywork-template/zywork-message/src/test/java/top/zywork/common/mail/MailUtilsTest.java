package top.zywork.common.mail;

import org.junit.Test;
import top.zywork.enums.ContentTypeEnum;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 邮件发送器测试类<br />
 * 创建于2017-09-14
 *
 * @author 王振宇
 */
public class MailUtilsTest {

    @Test
    public void testSendMail() throws MessagingException {
        Mail mail = new Mail();
        mail.setSubject("subject");
        List<MailAccount> mailAccountList = new ArrayList<>();
        mailAccountList.add(new MailAccount("wgssmarter@126.com"));
        mailAccountList.add(new MailAccount("847315251@qq.com"));
        mail.setRecipients(mailAccountList);
        mail.setContent("hello");
        mail.setContentType(ContentTypeEnum.HTML.getValue());
        List<String> files = new ArrayList<>();
        files.add("src/main/resources/config/mail.properties");
        mail.setFiles(files);
        MailUtils.sendMail("classpath:/config/mail.properties", mail);
    }
}
