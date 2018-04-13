package com.grad.eneity;

import java.io.Serializable;

/**
 * 描述 ：
 * 作者 ：WangYunHe
 * 时间 ：2018/4/12 11:50
 **/
public class User implements Serializable{
    private Integer id;
    private String name;
    private String password;
    private String realName;
    private Integer permission;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", permission=" + permission +
                '}';
    }
}
