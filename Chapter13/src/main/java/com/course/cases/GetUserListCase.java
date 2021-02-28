package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserList;
import com.course.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetUserListCase {

    @Test(dependsOnGroups = "loginTrue",description = "获取用户列表用例测试")
    public void getUsersList() throws IOException {
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        GetUserList getUserList = sqlSession.selectOne("getUsersList",1);
        System.out.println(getUserList);
        System.out.println(TestConfig.getUserListUrl);
    }

}
