package com.itzcn.service;

import java.util.List;

import com.itzcn.entity.Admin;

public interface AdminService {
	
	public boolean islogin(String adminUserName,String adminUserPwd);//����Ա��¼
	public List<Admin> showAdmins();//�г����й���Ա��Ϣ
	public Admin showByAdminId(Integer adminId);//���ݹ���Ա����г�����Ա��Ϣ
	public Admin login(String adminUserName,String adminUserPwd);//�����û����������ȡAdmin
}
