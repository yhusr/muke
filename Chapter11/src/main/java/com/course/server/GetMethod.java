package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "所有get请求")
public class GetMethod {
    @RequestMapping(value = "/getMethod",method = RequestMethod.GET)
    @ApiOperation(value = "这是最基本的get请求",httpMethod = "GET")
    public String getString(){
        return "this is get method";
    }

    @RequestMapping(value = "/get/with/cookies",method = RequestMethod.GET)
    @ApiOperation(value = "这是响应结果中携带cookie的get请求方法",httpMethod = "GET")
    public String responseString(HttpServletResponse response){
        //HttpServletResponse 处理响应结果的类
        //HttpServletRequest 处理请求的类
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        return "this is get with cookies";
    }

    @RequestMapping(value = "/request/cookie",method = RequestMethod.GET)
    @ApiOperation(value = "这是请求中必须携带cookie的get请求",httpMethod = "GET")
    public String requestWithCookie(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            response.sendError(500,"cookie is null,please checkout");
            return "cookies is null";
        }
        for (Cookie cookie:cookies) {
            if(cookie.getName().equals("login") && cookie.getValue().equals("true")){
                return "response success";
            }
        }
        return "you must request with cookies";
    }

    @RequestMapping(value = "/get/param",method = RequestMethod.GET)
    @ApiOperation(value = "带参数请求的url地址get请求",httpMethod = "GET")
    public Map<String,Integer> getWithParam(@RequestParam int start, @RequestParam int end){
        /**
         * http://localhost:8888/get/param?start=1&end=20,当使用该类型路径进行访问，参数使用 @RequestParam
         * */
        Map<String,Integer> list = new HashMap<String, Integer>(){};
        list.put("aaa",209);
        list.put("bbb",30);
        list.put("ccc",333);
        return list;
    }

    @RequestMapping(value = "/get/with/path/{start}/{end}",method = RequestMethod.GET)
    @ApiOperation(value = "这是使用path路径的get请求",httpMethod = "GET")
    public Map<String,Integer> getWithPath(@PathVariable Integer start,@PathVariable Integer end){
        /**
         * http://localhost:8888/get/with/path/1/23,当使用该类型路径访问时，使用参数：@PathVariable
         * */
        Map<String,Integer> list = new HashMap<String, Integer>(){};
        list.put("aaa",209);
        list.put("bbb",30);
        list.put("ccc",333);
        return list;
    }
}
