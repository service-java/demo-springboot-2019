package org.pan.web.book;


public class shopuser {
	private long Id;			//ID���к�
	private String UserName;	//�����û���
	private String PassWord;    //�û�����
	private String Names;		//�û���ϵ������
	private String Sex;			//�û��Ա�
	private String Address;		//�û���ϵ��ַ
	private String Phone;		//�û���ϵ�绰
	private String Post;		//�û���ϵ�ʱ�
	private String Email;   	//�û������ʼ�
	private String RegTime; 	//�û�ע��ʱ��
	private String RegIpAddress; //�û�ע��ʱIP��ַ	

	public shopuser() {
		Id = 0;
		UserName = "";
		PassWord = "";
		Names = "";
		Sex = "";
		Address = "";
		Phone = "";
		Post = "";
		Email = "";
		RegTime = "";
		RegIpAddress = "";
	}

    public long getId() {
		return Id;
	}

	public void setId(long newId) {
		this.Id = newId;
	}

	public String getUserName() {
		return UserName;
	}
	
	public void setUserName(String newUserName) {
		this.UserName = newUserName;
	}

	public String getPassWord() {
		return PassWord;
	}
	
	public void setPassWord(String newPassWord) {
		this.PassWord = newPassWord;
	}

	public String getNames() {
		return Names;
	}
	
	public void setNames(String newNames) {
		this.Names = newNames;
	}

	public String getSex() {
		return Sex;
	}
	
	public void setSex(String newSex) {
		this.Sex = newSex;
	}

	public String getAddress() {
		return Address;
	}
	
	public void setAddress(String newAddress) {
		this.Address = newAddress;
	}

	public String getPhone() {
		return Phone;
	}
	
	public void setPhone(String newPhone) {
		this.Phone = newPhone;
	}

	public String getPost() {
		return Post;
	}
	
	public void setPost(String newPost) {
		this.Post = newPost;
	}

	public String getEmail() {
		return Email;
	}
	
	public void setEmail(String newEmail) {
		this.Email = newEmail;
	}

	public String getRegTime() {
		return RegTime;
	}
	
	public void setRegTime(String newRegTime) {
		this.RegTime = newRegTime;
	}

	public String getRegIpAddress() {
		return RegIpAddress;
	}
	
	public void setRegIpAddress(String newRegIpAddress) {
		this.RegIpAddress = newRegIpAddress;
	}


}