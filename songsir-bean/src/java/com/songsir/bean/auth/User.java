package com.songsir.bean.auth;

import java.util.HashSet;
import java.util.Set;

/**
 * @PackageName com.songsir.bean.auth
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 17:12 2019/8/16
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class User {

    private int uid;

    private String uname;

    private String password;

    private Set<Role> roles = new HashSet<>();

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
