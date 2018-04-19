package com.grad.dao;

import com.grad.eneity.Project;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述 ：
 * 作者 ：WangYunHe
 * 时间 ：2018/4/19 15:01
 **/
@Repository
public interface ProjectManagerDao {

    List<Project> initQueryPaging(@Param("start") Integer start, @Param("pageSize") Integer pageSize, @Param("name") String name);

    List<Project> initPaging(@Param("start") Integer start, @Param("pageSize") Integer pageSize);

    Integer total();

    Integer findtotalByName(String projectName);


    void updateProject(Project projectResult);

    Project findByNumber(Integer number);

    Integer findtotalByStatus(Integer status);

    List<Project> initQueryPagingByStatus(@Param("start") Integer start, @Param("pageSize") Integer pageSize, @Param("status") Integer status);
}
