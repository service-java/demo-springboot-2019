package top.zywork.common.mail;

import org.apache.commons.lang3.StringUtils;
import top.zywork.common.ExceptionUtils;
import top.zywork.enums.CharsetEnum;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 把邮箱账号对象转化成InternetAddress的工具类<br />
 * 创建于2017-09-14
 *
 * @author 王振宇
 * @version 1.0
 */
public class MailAddressUtils {

    /**
     * 把单个MailAccount转成单个InternetAddress
     * @param mailAccount 需要转换的邮箱账号
     * @return 指定邮箱账号对应的InternetAddress
     */
    public static InternetAddress toAddress(MailAccount mailAccount) {
        try {
            if (StringUtils.isEmpty(mailAccount.getPersonal())) {
                return new InternetAddress(mailAccount.getAddress());
            }
            return new InternetAddress(mailAccount.getAddress(), mailAccount.getPersonal(), CharsetEnum.UTF8.getValue());
        } catch (AddressException | UnsupportedEncodingException e) {
            throw ExceptionUtils.appException(e);
        }
    }

    /**
     * 把MailAccount列表转成InternetAddress数组
     * @param mailAccountList 需要转换的邮箱账号列表
     * @return 邮箱账号列表对应的InternetAddress数组
     */
    public static InternetAddress[] toAddressArray(List<MailAccount> mailAccountList) {
        InternetAddress[] addressArray = new InternetAddress[mailAccountList.size()];
        for (int i = 0, size = mailAccountList.size(); i < size; i++) {
            MailAccount mailAccount = mailAccountList.get(i);
            try {
                if (StringUtils.isEmpty(mailAccount.getPersonal())) {
                    addressArray[i] = new InternetAddress(mailAccount.getAddress());
                } else {
                    addressArray[i] = new InternetAddress(mailAccount.getAddress(), mailAccount.getPersonal(), CharsetEnum.UTF8.getValue());
                }
            } catch (AddressException | UnsupportedEncodingException e) {
                throw ExceptionUtils.appException(e);
            }
        }
        return addressArray;
    }

}
