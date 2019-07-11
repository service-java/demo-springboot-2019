package com.itzcn.service;

import java.util.List;

import com.itzcn.entity.Post;

public interface PostService {
	
	public void addPost(Post post);//��Ӳ���
	public void delPost(Post post);//ɾ������
	public void upPost(Post post);//���²�����Ϣ
	public Post showByPostId(Integer postId);//���ݲ��ű�Ų�ѯ������Ϣ
	public List<Post> showPosts();//��ʾ���в�����Ϣ
	public List<Post> showByPostName(String postName);//���ݲ������Ʋ�ѯ������Ϣ
	public boolean isExist(String postName);//���������Ƿ����
	public boolean isUpdate(Post post);//������Ϣ�Ƿ���Ը���
	public boolean isDel(Integer postId);//������Ϣ�Ƿ����ɾ��
	public int getPostUserCount(Integer postId);//���ݲ��ű�Ų�ѯ�ò���Ա������

}
