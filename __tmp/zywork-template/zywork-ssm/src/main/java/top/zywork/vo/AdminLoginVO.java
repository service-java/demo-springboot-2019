package top.zywork.vo;

/**
 * 接收管理员登录数据的VO对象<br />
 * 创建于2017-09-12
 *
 * @author 王振宇
 * @version 1.0
 */
public class AdminLoginVO extends BaseVO {

    private static final long serialVersionUID = -6223154645503096888L;

    private String account;
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
