package com.songsir.bean.auth;

import java.util.Set;

/**
 * @PackageName com.songsir.bean.auth
 * @ProjectName songsir-demoboot
 * @Author: SongYapeng
 * @Date: Create in 17:13 2019/8/16
 * @Description:
 * @Copyright Copyright (c) 2019, songsir01@163.com All Rights Reserved.
 */
public class Module {

    private int mid;

    private String mname;

    private Set<Role> roles;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
