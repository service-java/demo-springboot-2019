package com.itzcn.dao;

import java.util.List;

import com.itzcn.entity.Admin;

public interface AdminDao {
	
	public List<Admin> findAllAdmin();//�г����й���Ա��Ϣ
	public List<Admin> findByAdminUserNameAndPwd(String adminUserName,String adminUserPwd);//����adminUserName��adminUserPwd��ѯ
	public Admin findByAdminId(Integer adminId);//����adminId��ѯ
	

}
