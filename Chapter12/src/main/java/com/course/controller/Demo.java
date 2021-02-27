package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j
@RestController
@Api(value = "v1",description = "这是第一个mysql查询语句")
@RequestMapping("v1")
public class Demo {
    //获取一个执行sql语句的对象
    @Autowired
    private SqlSessionTemplate template;

    @RequestMapping(value = "/get/user/count",method = RequestMethod.GET)
    @ApiOperation(value = "获取用户数",httpMethod = "GET")
    public int getUserCount(){
        return template.selectOne("getUserCount");
    }

    @RequestMapping(value = "/insertInto",method = RequestMethod.POST)
    @ApiOperation(value = "插入数据的接口",httpMethod = "POST")
    public int insertIntoData(@RequestBody User user){
        return template.insert("insertIntoUser",user);
    }

    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    @ApiOperation(value = "这是一个更新数据表的sql",httpMethod = "POST")
    public int updateUser(@RequestBody User user){
        return template.update("updateUser",user);
    }

    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    @ApiOperation(value = "这是一个删除数据的sql",httpMethod = "POST")
    public int deleteUser(@RequestParam int id){
        int result = template.delete("deleteUser",id);
        return result;
    }
}
