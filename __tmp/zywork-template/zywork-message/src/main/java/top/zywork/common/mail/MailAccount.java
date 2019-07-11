package top.zywork.common.mail;

/**
 * 邮箱账户类，包括邮箱地址和邮箱的昵称<br />
 * 创建于2017-09-14
 *
 * @author 王振宇
 * @version 1.0
 */
public class MailAccount {

    private String address;
    /**
     * 昵称
     */
    private String personal;

    public MailAccount() {}

    public MailAccount(String address) {
        this.address = address;
    }

    public MailAccount(String address, String personal) {
        this(address);
        this.personal = personal;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }
}
