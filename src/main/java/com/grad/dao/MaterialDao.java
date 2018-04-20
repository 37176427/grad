package com.grad.dao;

import com.grad.eneity.Project;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述 ：
 * 作者 ：WangYunHe
 * 时间 ：2018/4/20 13:52
 **/
@Repository
public interface MaterialDao {
    List<Project> fuzzyQueryPaging(@Param("start") Integer start, @Param("pageSize") Integer pageSize, @Param("name") String name);

    Integer totalByName(@Param("name") String name);

    List<Project> initPaging(@Param("start") Integer start, @Param("pageSize") Integer pageSize);

    Integer total();

    List<Project> findByNameLike(@Param(value = "name") String name, @Param(value = "limit") Integer limit);

    List<Project> findByName(String name);

    Project findById(Integer id);

    void updateProject(Project p);
}
