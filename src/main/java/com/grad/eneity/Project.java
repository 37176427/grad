package com.grad.eneity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述 ：
 * 作者 ：WangYunHe
 * 时间 ：2018/4/18 14:32
 **/
//项目实体类
public class Project implements Serializable {
    private Integer id;
    private Integer number;
    private String name;
    private String manager;
    private String member;
    private String nature;
    private String desc;
    private String awards;
    //@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    private Integer status;
    private String createUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public Date getcreateTime() {
        return createTime;
    }

    public void setcreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", manager='" + manager + '\'' +
                ", member='" + member + '\'' +
                ", nature='" + nature + '\'' +
                ", desc='" + desc + '\'' +
                ", awards='" + awards + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                ", createUser='" + createUser + '\'' +
                '}';
    }
}
