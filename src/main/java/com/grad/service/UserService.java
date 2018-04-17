package com.grad.service;

import com.grad.common.eneity.QueryResultObject;
import com.grad.dao.UserDao;
import com.grad.eneity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述 ：
 * 作者 ：WangYunHe
 * 时间 ：2018/4/12 12:51
 **/
@Service
@Transactional(readOnly = false,rollbackFor = Exception.class)
public class UserService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    //登录校验
    public User findByNameAndPassword(String name, String password) {
        return userDao.findByNameAndPassword(name, password);
    }

    /**
     * 带查询的分页与不带查询的分页
     * @param pageNumber
     * @param pageSize
     * @param userName
     * @return
     */
    public Map<String,Object> limitQuery(Integer pageNumber, Integer pageSize, String userName) {
        Map<String, Object> map;
        if (userName != null && !"".equals(userName)) {
            map = this.initQueryPaging(pageNumber, pageSize,userName);
        } else {
            map = this.initPaging(pageNumber, pageSize);
        }
        return map;
    }

    /**
     * 不带查询的分页
     * @param pageNumber
     * @param pageSize
     * @return
     */
    private Map<String,Object> initPaging(Integer pageNumber, Integer pageSize) {
        Map<String, Object> map = new HashMap<>(2);
        pageNumber = (pageNumber - 1) * pageSize;
        List<User> rows = userDao.initPaging(pageNumber, pageSize);
        //查询总记录数
        Integer total = userDao.total();
        //告知前端分页的数量
        map.put("rows", rows);
        map.put("total", total);
        return map;
    }

    /**
     * 带查询的分页
     * @param pageNumber
     * @param pageSize
     * @param userName
     * @return
     */
    private Map<String,Object> initQueryPaging(Integer pageNumber, Integer pageSize, String userName) {
        Map<String, Object> map = new HashMap<>(2);
        pageNumber = (pageNumber - 1) * pageSize;
        //查询总记录数
        Integer total = userDao.findtotalBySampleName(userName);
        //根据参数查询数据
        List<User> rows = userDao.initQueryPaging(pageNumber,pageSize,userName);
        map.put("rows", rows);
        map.put("total", total);
        return map;
    }
    /**
     * 根据name查询，存在返回false 不存在返回true
     * @param userName
     */
    public QueryResultObject findByName(String userName) {
        QueryResultObject msg = new QueryResultObject();
        List<User> userResult = userDao.findByName(userName);
        if(userResult.size() != 0){
            msg.setMsg(userName + "已存在");
            msg.setResult(false);
        }else {
            msg.setResult(true);
        }
        return msg;
    }


    //添加用户
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void addUser(User user) {
        userDao.addUser(user);
    }

    public List<User> findByNameList(String sampleName) {
        return userDao.findByName(sampleName);
    }


    /**
     * 校验参数
     * @param user
     * @return
     */
    public String check (User user){
        if(user.getName() != null && user.getName().length() >100){
            return "错误";
        }
        if(user.getRealName() != null && user.getRealName().length() >100){
            return "错误";
        }
        if(user.getPassword() != null && user.getPassword().length() >100){
            return "错误";
        }
        if(user.getPermission() != null && user.getPermission() >2){
            return "错误";
        }
        return "正确";
    }

    /**
     * 校验后添加用户
     */
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public QueryResultObject addUserService(User user) {
        QueryResultObject msg = new QueryResultObject();
        if(user.getName() == null || "".equals(user.getName())){
            msg.setResult(false);
            msg.setMsg("传入值为空");
            return msg;
        }
        //校验SampleName是否存在
        List<User> userResult = this.findByNameList(user.getName());
        if (userResult.size() != 0 ){
            msg.setResult(false);
            msg.setMsg("数据已存在!");
            return msg;
        }
        //校验数据
        String checkResult = this.check(user);
        if ("错误".equals(checkResult)){
            msg.setResult(false);
            msg.setMsg("数据错误!");
            return msg;
        }
        try {
            this.addUser(user);
        }catch (Exception e){
            logger.error("插入User表出错!" + "UserName= " + user.getName() + e.toString());
            msg.setResult(false);
            msg.setMsg("插入ToolManager表出错!" + "UserName= " + user.getName());
            return msg;
        }
        msg.setResult(true);
        msg.setMsg("插入成功!");
        return msg;
    }

    /**
     * 校验数据后更新用户信息
     */
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public QueryResultObject updateUserService(User user) {
        QueryResultObject msg = new QueryResultObject();
        //做更新操作Name不能为空
        if(user.getName() == null ){
            msg.setResult(false);
            msg.setMsg("更新操作id为空!");
            return msg;
        }
        if(user.getRealName() == null ){
            msg.setResult(false);
            msg.setMsg("更新操作真实姓名为空!");
            return msg;
        }
        if(user.getPermission() == null ){
            msg.setResult(false);
            msg.setMsg("更新操作权限为空!");
            return msg;
        }
        if(user.getPassword() == null ){
            msg.setResult(false);
            msg.setMsg("更新操作密码为空!");
            return msg;
        }
        if(user.getPassword().length() <6 || user.getPassword().length() > 20){
            msg.setResult(false);
            msg.setMsg("更新操作密码长度不正确 请输入6到20位!");
            return msg;
        }
        List<User> userResult = userDao.findByName(user.getName());
        String checkResult = this.check(user);
        if (checkResult == null){
            msg.setResult(false);
            msg.setMsg("数据错误!");
            return msg;
        }
        try {
            if (userResult == null){
                msg.setResult(false);
                msg.setMsg("不存在id= " + user.getId() + " 的数据!");
                return msg;
            }
            userResult.get(0).setName(user.getName());
            userResult.get(0).setRealName(user.getRealName());
            userResult.get(0).setPassword(user.getPassword());
            userResult.get(0).setPermission(user.getPermission());
        }catch (Exception e){
            logger.error("用户名创建后不可更改! UserName= " +user.getName() + e.toString());
            msg.setResult(false);
            msg.setMsg("用户名创建后不可更改! UserName= " +user.getName());
            return msg;
        }
        try {
            this.updateUser(userResult.get(0));
            msg.setResult(true);
            msg.setMsg("更新成功!");
            return msg;
        }catch (Exception e){
            logger.error("更新user表出错! UserName= " +user.getName() + e.toString());
            msg.setResult(false);
            msg.setMsg("更新user表出错! UserName= " +user.getName());
            return msg;
        }
    }
    //更新用户操作
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    /**
     * 根据条件删除用户信息
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Integer delByCondition(Integer id, String realName) {
        return userDao.delByCondition(id, realName);
    }

    /**
     * 批量删除
     * @param array id集合
     * @return 删除成功的结果数
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int batchDelUser(String[] array) {
        return userDao.batchDelUser(array);
    }
}
