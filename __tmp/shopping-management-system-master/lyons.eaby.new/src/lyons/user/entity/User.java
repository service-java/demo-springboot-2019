package lyons.user.entity;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * �û���½ʵ����
 * @author Lyons(zhanglei)
 *
 */

@SuppressWarnings("serial")
public class User implements Serializable
{
	private String username;
	private String userpass;
	private String phone;
	private String address;
	private String realname;
	private String backNews = "��ע��";
	private LinkedList<String> car;      //���ﳵ������
	
	public User()
	{
		car = new LinkedList<String>();
	}
	
	
    public String getUsername()
    {
        if (username == null ||username.trim()=="")
        {
            return "userNull";
        }
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getUserpass()
    {
        return userpass;
    }
    public void setUserpass(String userpass)
    {
        this.userpass = userpass;
    }
    public String getPhone()
    {
        return phone;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public String getRealname()
    {
        return realname;
    }
    public void setRealname(String realname)
    {
        this.realname = realname;
    }
    public String getBackNews()
    {
        return backNews;
    }
    public void setBackNews(String backNews)
    {
        this.backNews = backNews;
    }
    public LinkedList<String> getCar()
    {
        return car;
    }
    public void setCar(LinkedList<String> car)
    {
        this.car = car;
    }
	
}
