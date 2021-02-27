package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "/",description = "这是全部的post请求")
@RequestMapping("/v1")
public class PostMethod {
    public static Cookie cookie;


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "这是登录接口",httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value = "userName",required = true) String userName,
                        @RequestParam(value = "passWord",required = true) String passWord){
        if(userName.equals("zhangsan") && passWord.equals("123456")){
            cookie = new Cookie("login","true");
            //cookie = new Cookie("zhangsan","23");
            response.addCookie(cookie);
            return "login success";
        }
        return "登录失败";
    }

    @RequestMapping(value = "/get/user/list",method = RequestMethod.POST)
    @ApiOperation(value = "这是获取用户信息的接口 ",httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                              @RequestBody User u){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies) {
            if(cookie.getName().equals("login") && cookie.getValue().equals("true")
                && u.getUserName().equals("zhangsan") && u.getPassWord().equals("123456")
                ){
                User user = new User();
                user.setName("lisi");
                user.setAge(34);
                user.setSex("女");
                return user.toString();

            }
        }
        return "获取用户列表失败";
    }
}
