package com.grad.dao;

import com.grad.eneity.Project;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述 ：ProjectDao
 * 作者 ：WangYunHe
 * 时间 ：2018/4/18 14:37
 **/
@Repository
public interface ProjectDao {

    List<Project> initQueryPaging(@Param("start") Integer start, @Param("pageSize") Integer pageSize, @Param("name") String name);

    List<Project> initPaging(@Param("start") Integer start, @Param("pageSize") Integer pageSize);

    Integer total();

    void addProject(Project project);

    Project findByNumber(Integer number);

    void updateProject(Project projectResult);

    Integer findtotalByName(String projectName);

    Integer findtotalByUserName(@Param("createUser") String createUser);

    List<Project> initQueryPagingByUserName(@Param("start") Integer start, @Param("pageSize") Integer pageSize, @Param("createUser") String createUser);

    Integer delById(Integer id);

    Project findById(Integer id);

    int batchDel(String[] array);

    List<Project> fuzzyQueryPaging(@Param("start") Integer start, @Param("pageSize") Integer pageSize, @Param("name") String name);
}
