package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@Log
@RestController
@Api(value = "v1",description = "用户管理系统")
@RequestMapping("v1")
public class UserManager {

    @Autowired
    private SqlSessionTemplate template;

    @ApiOperation(value = "登录接口",httpMethod = "POST")
    @RequestMapping(value ="login",method = RequestMethod.POST)
    public boolean login(HttpServletResponse response,
                        @RequestBody User user){
        int i = template.selectOne("login",user);
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        log.info("查询到的结果是："+i);
        if(i==1){
            log.info("登录的用户是："+user.getUserName());
            return true;
        }
        return false;
    }

    @ApiOperation(value = "添加用户接口",httpMethod = "POST")
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public boolean addUser(HttpServletRequest request,@RequestBody User user){
        boolean verify_cookie = verifyCookies(request);
        int result=0;
        if(verify_cookie){
            result = template.insert("addUser",user);
        }
        if(result>0){
            log.info("addUser成功");
            return true;
        }
        return false;
    }
    @ApiOperation(value = "获取用户列表接口",httpMethod = "POST")
    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    public List<User> getUserList(HttpServletRequest request , @RequestBody User user){
        boolean v = verifyCookies(request);
        if(v){
            List<User> userList = template.selectList("getUserList",user);
            log.info("获取用户的数量为："+userList.size());
            return userList;
        }
        return null;
    }
    @ApiOperation(value = "这是更新用户信息的接口",httpMethod = "POST")
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public Integer updateUser(HttpServletRequest request,@RequestBody User user){
        boolean v = verifyCookies(request);
        int count = 0;
        if(v){
            count = template.update("updateUser",user);
            log.info("更新的条数为"+count);
            return count;
        }
        log.info("更新用户信息失败");
        return count;
    }
    private boolean verifyCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            log.info("cookie内容为空");
            return false;
        }
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("login") && cookie.getValue().equals("true")){
                log.info("cookies验证通过");
                return true;
            }
        }
        return false;
    }
}
