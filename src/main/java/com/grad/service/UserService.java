package com.grad.service;

import com.grad.dao.UserDao;
import com.grad.eneity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 描述 ：
 * 作者 ：WangYunHe
 * 时间 ：2018/4/12 12:51
 **/
@Service
@Transactional(readOnly = false,rollbackFor = Exception.class)
public class UserService {
    @Autowired
    private UserDao userDao;

    @Transactional(readOnly = true)
    public User findByNameAndPassword(String name,String password){
        User u = userDao.findByNameAndPassword(name,password);
        return u;
    }
    @Transactional(readOnly = true)
    public List<User> findAll() {
        List<User> list = userDao.findAll();
        return list;
    }
    @Transactional(readOnly = true)
    public User findUserById(Integer id) {
        return userDao.findUserById(id);
    }

    public void deleteUserById(Integer id) {
        userDao.deleteUserById(id);
    }
}
