package com.itzcn.dao;

import java.util.List;

import com.itzcn.entity.User;

public interface UserDao {
	
	public void insertUser(User user);//����User
	public void delUser(User user);//ɾ��
	public void upUser(User user);//����
	public User findByUserId(Integer userId);//����userId����
	public List<User> findByUserName(String userName);//����userName����
	public List<User> findByPostId(Integer postId);//���ݲ���postId��ѯ
	public List<User> findAllUser();//�г�����User��Ϣ
	public List<User> findAllUserByPage(int pageNo,int pageSize);//��ҳ�г�User��Ϣ
	public int getUserCount();//��ȡUser������
	public int getPostUserCount(Integer postId);//��ȡĳPost�µ�User����
	
}
