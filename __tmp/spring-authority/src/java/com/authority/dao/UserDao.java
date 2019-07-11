package com.authority.dao;

import java.util.List;

import com.authority.dao.impl.Pager;
import com.authority.model.SysUsers;
import com.authority.utils.ListResult;

public interface UserDao extends BaseDao<SysUsers,String>{
    /**
     * ��ȡ�û��б�
     * @param pager
     * @return
     */
    public ListResult getUserDataList(IPager pager);

    /**
     * ����û�
     * @param users
     * @return
     */
    public Boolean addUser(SysUsers users);

    /**
     * ɾ���û�
     * @param userid
     * @return
     */
    public Boolean delUserById(String userid);

    /**
     * ����û���ɫ
     * @param roleIds
     * @param userid
     * @return
     */
    public Boolean addUserRole(String[] roleIds,String userid);

    /**
     * ɾ���û���ɫ
     * @param roleIds
     * @param userid
     * @return
     */
    public Boolean delUserRole(String[] roleIds,String userid);

    /**
     * ��ȡ�û���ɫ�б�
     * @param pager
     * @return
     */
    public ListResult getUserRole(IPager pager);

    /**
     * �޸�����
     * @param oldpassword
     * @param newpassword
     * @return
     */
    public Boolean changePassworld(String oldpassword,String newpassword);

    /**
     * ���û������û��˺�
     * @param userid
     * @return
     */
    public Boolean updateEnableUser(String userid);

    /**
     * ����������û��˺�
     * @param userid
     * @return
     */
    public Boolean updateLockUser(String userid);


    /**
     * �����û����ͻ�ȡ�û�
     * @param type
     * @return
     */
    public List getUserByType(String type);

    boolean accountUniqueValidate(String userid, String account);
}
