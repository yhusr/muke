package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.AddUser;
import com.course.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddUserCase {
    @Test(dependsOnGroups = "loginTrue",description = "添加用户用例测试")
    public void addUser() throws IOException {
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        AddUser addUser = sqlSession.selectOne("addUser");
        System.out.println(addUser);
        System.out.println(TestConfig.addUserUrl);
    }
}
