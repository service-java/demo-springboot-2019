package com.itzcn.dao;

import java.util.List;

import com.itzcn.entity.Post;

public interface PostDao {
	
	public void insertPost(Post post);//����Post
	public void upPost(Post post);//����Post
	public void delPost(Post post);//ɾ��Post
	
	public List<Post> findAllPost();//�г�Post
	
	public Post findByPostId(Integer postId);//����PostId��ѯ
	
	public List<Post> findByPostName(String postName);//����PostName��ѯ
	
	
}
