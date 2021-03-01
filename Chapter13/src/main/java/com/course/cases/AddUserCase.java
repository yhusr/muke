package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.AddUser;
import com.course.model.User;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddUserCase {
    @Test(dependsOnGroups = "loginTrue",description = "����û���������")
    public void addUser() throws IOException {
        SqlSession sqlSession = DatabaseUtil.getSqlSession();
        AddUser addUser = sqlSession.selectOne("addUser");
        System.out.println(addUser);
        System.out.println(TestConfig.addUserUrl);

        //�����󣬻�ȡ���
        String result = getResult(addUser);
        //��֤���ؽ��
        User user = sqlSession.selectOne("addUserTest",addUser);
        System.out.println(user.toString());
        Assert.assertEquals(result,addUser.getExpected());

    }

    private String getResult(AddUser addUser) throws IOException {
        HttpPost post = new HttpPost(TestConfig.addUserUrl);
        JSONObject param = new JSONObject();
        param.put("userName",addUser.getUserName());
        param.put("password",addUser.getPassword());
        param.put("sex",addUser.getSex());
        param.put("age",addUser.getAge());
        param.put("permission",addUser.getPermission());
        param.put("isDelete",addUser.getIsDelete());
        //����ͷ��Ϣ
        post.setHeader("content-type","application/json");
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);

        //��ȡ��Ӧ��Ϣ
        HttpResponse response = TestConfig.httpClient.execute(post);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        return result;
    }
}
