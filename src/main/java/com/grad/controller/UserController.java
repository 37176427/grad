package com.grad.controller;

import com.grad.common.eneity.QueryResultObject;
import com.grad.eneity.User;
import com.grad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 描述 ：用户管理页controller
 * 作者 ：WangYunHe
 * 时间 ：2018/4/13 14:07
 **/

@Controller
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    private UserService us;

    /**
     * 分页查询
     */
    @RequestMapping("/initPaging")
    @ResponseBody
    public QueryResultObject initPaging(Integer pageNumber, Integer pageSize, String userName) {
        QueryResultObject object = new QueryResultObject();
        Map<String, Object> map = us.limitQuery(pageNumber, pageSize, userName);
        object.setMsg("查询成功");
        object.setData(map);
        object.setResult(true);
        return object;
    }

    /**
     * 根据名称单个查询
     */
    @RequestMapping("queryByName")
    @ResponseBody
    public QueryResultObject queryByName(String userName) {
        return us.findByName(userName);
    }

    //添加用户
    @RequestMapping("/add")
    @ResponseBody
    public QueryResultObject addUser(User user) {
        return us.addUserService(user);
    }

    //修改用户
    @RequestMapping("/update")
    @ResponseBody
    public QueryResultObject updateUser(User user) {
        return us.updateUserService(user);
    }

    /**
     * 根据name删除用户信息
     */
    @ResponseBody
    @PostMapping(value = "/delUser")
    public QueryResultObject delTrace(String name, String realName) {
        QueryResultObject object = new QueryResultObject();
        //查询工具编号
        List<User> byName = us.findByNameList(name);
        if (byName.size() > 0) {
            if (realName != null && !"".equals(realName)) {
                //获取编号
                Integer id = byName.get(0).getId();
                Integer integer = us.delByCondition(id, realName);
                if (integer > 0) {
                    object.setResult(true);
                    object.setMsg("删除成功");
                } else {
                    object.setResult(false);
                    object.setMsg("删除失败，请稍后尝试");
                }
            } else {
                object.setMsg("删除失败,请检查要删除的用户的正确性");
                object.setResult(false);
            }
        } else {
            object.setResult(false);
            object.setMsg("删除失败，请确定用户名称的正确性");
        }
        return object;
    }

    /**
     * 批量删除用户信息
     *
     * @param ids 采用","分隔的字符串
     * @return 删除结果
     */
    @PostMapping(value = "/batchDelUser")
    @ResponseBody
    public QueryResultObject batchDelTrace(String ids) {
        QueryResultObject object = new QueryResultObject();
        if (ids != null && ids.length() > 0) {
            String[] array = ids.split(",");
            int counter = us.batchDelUser(array);
            if (counter > 0 && counter == array.length) {
                object.setMsg("删除成功");
                object.setResult(true);
            } else {
                object.setMsg("部分删除成功");
                object.setResult(true);
            }
        } else {
            object.setResult(false);
            object.setMsg("请检查要删除的数据的准确性");
        }
        return object;
    }
}
