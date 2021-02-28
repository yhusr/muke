package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.GetUserInfo;
import com.course.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetUserInfoCase {
    @Test(dependsOnGroups = "loginTrue",description = "��ȡ�û���Ϣ����������")
    public void getUserInfo() throws IOException {
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        GetUserInfo getUserInfo = sqlSession.selectOne("getUserInfo",1);
        System.out.println(getUserInfo);
        System.out.println(TestConfig.getUserInfoUrl);
    }
}
