package com.grad.dao;

import com.grad.eneity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述 ：
 * 作者 ：WangYunHe
 * 时间 ：2018/4/12 11:49
 **/
@Repository
public interface UserDao {
    User findByNameAndPassword(@Param("name") String name, @Param("password") String password);

    List<User> findAll();

    User findUserById(Integer id);

    void deleteUserById(Integer id);

    List<User> initPaging(@Param("start") Integer start, @Param("pageSize") Integer pageSize);

    Integer total();

    Integer findtotalBySampleName(String userName);

    List<User> initQueryPaging(@Param("start") Integer start, @Param("pageSize") Integer pageSize,@Param("userName") String userName);

    List<User> findByName(String userName);

    void addUser(User user);

    void updateUser(User user);
}
