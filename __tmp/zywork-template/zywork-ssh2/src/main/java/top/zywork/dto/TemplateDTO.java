package top.zywork.dto;

/**
 * TempldateDTO类<br />
 * 创建于2017-08-24
 *
 * @author 王振宇
 * @version 1.0
 */
public class TemplateDTO extends BaseDTO {
    private static final long serialVersionUID = 8035834024640187998L;

    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
