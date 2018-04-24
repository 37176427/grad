package com.grad.dao;

import com.grad.eneity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述 ：UserDao
 * 作者 ：WangYunHe
 * 时间 ：2018/4/12 11:49
 **/
@Repository
public interface UserDao {

    User findByNameAndPassword(@Param("name") String name, @Param("password") String password);

    List<User> initPaging(@Param("start") Integer start, @Param("pageSize") Integer pageSize);

    Integer total();

    Integer findtotalByName(String name);

    List<User> initQueryPaging(@Param("start") Integer start, @Param("pageSize") Integer pageSize, @Param("name") String name);

    List<User> findByName(String userName);

    void addUser(User user);

    void updateUser(User user);

    /**
     * 根据条件删除跟踪信息
     *
     * @return 影响的记录数
     */
    Integer delByCondition(@Param("id") Integer id, @Param("realName") String realName);

    int batchDelUser(String[] array);
}
