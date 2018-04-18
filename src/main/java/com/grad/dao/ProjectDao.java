package com.grad.dao;

import com.grad.eneity.Project;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述 ：
 * 作者 ：WangYunHe
 * 时间 ：2018/4/18 14:37
 **/
@Repository
public interface ProjectDao {

    Integer findtotalByNumber(Integer number);

    List<Project> initQueryPaging(@Param("start") Integer start, @Param("pageSize") Integer pageSize, @Param("number") Integer number);

    List<Project> initPaging(@Param("start") Integer start, @Param("pageSize") Integer pageSize);

    Integer total();

    void addProject(Project project);

    Project findByNumber(Integer number);

    void updateProject(Project projectResult);
}
