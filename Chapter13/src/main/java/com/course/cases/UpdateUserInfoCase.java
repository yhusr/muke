package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.UpdateUserInfo;
import com.course.utils.DatabaseUtil;
import org.apache.ibatis.session.SqlSession;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdateUserInfoCase {
    @Test(dependsOnGroups = "loginTrue",description = "�����û���Ϣ����������")
    public void updateUserInfo() throws IOException {
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        UpdateUserInfo updateUserInfo = sqlSession.selectOne("updateUserInfo",1);
        System.out.println(updateUserInfo);
        System.out.println(TestConfig.updateUserInfoUrl);
    }
}
