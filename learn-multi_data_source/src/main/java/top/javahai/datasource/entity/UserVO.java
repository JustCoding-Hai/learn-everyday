package top.javahai.datasource.entity;

import java.util.List;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2022/7/31 - 16:23
 **/
public class UserVO {

    private List<Admin> adminList;
    private List<TUserinfo> tUserinfos;
    private List<TUser> tUsers;

    public List<Admin> getAdminList() {
        return adminList;
    }

    public void setAdminList(List<Admin> adminList) {
        this.adminList = adminList;
    }

    public List<TUserinfo> gettUserinfos() {
        return tUserinfos;
    }

    public void settUserinfos(List<TUserinfo> tUserinfos) {
        this.tUserinfos = tUserinfos;
    }

    public List<TUser> gettUsers() {
        return tUsers;
    }

    public void settUsers(List<TUser> tUsers) {
        this.tUsers = tUsers;
    }
}
