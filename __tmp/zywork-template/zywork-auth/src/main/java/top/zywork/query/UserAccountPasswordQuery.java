package top.zywork.query;

/**
 * 用户登录时的查询类，通过账号和密码进行登录<br />
 * 创建于2017-09-05
 *
 * @author 王振宇
 * @version 1.0
 */
public class UserAccountPasswordQuery {

    private String account;
    private String password;

    public UserAccountPasswordQuery() {}

    public UserAccountPasswordQuery(String account, String password) {
        this.account = account;
        this.password = password;
    }

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
