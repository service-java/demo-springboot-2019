package com.itzcn.service;

import java.util.List;

import com.itzcn.entity.User;

public interface UserService {
	
	
	
	public void addUser(User user);//���Ա����Ϣ
	public void delUser(User user);//ɾ��Ա����Ϣ
	public void upUser(User user);//����Ա����Ϣ
	public User showByUserId(Integer userId);//����Ա������г�Ա����Ϣ
	public List<User> showUsers();//��ʾ���е�Ա����Ϣ
	public boolean isExist(String userName);//Ա�����û����Ƿ����
	public List<User> showByUserName(String userName);//����Ա�����û�����ʾԱ����Ϣ
    public boolean isUpdate(User user);//�Ƿ��ܹ�����Ա����Ϣ
    

}
