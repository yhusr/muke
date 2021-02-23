package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

public class PostWithCookies {
    private ResourceBundle bundle;
    private String host;
    private CookieStore store;

    @BeforeTest
    public void beforeTest(){
        // ��ȡproperties�ļ��е�key-valueֵ
        bundle = ResourceBundle.getBundle("application");
        host = bundle.getString("test.host");
    }

    @Test
    public void test() throws IOException {
        String uri = bundle.getString("test.uri");
        String url = host + uri;
        HttpGet get = new HttpGet(url);
        // ��ȡcookies��Ϣ
        this.store = new BasicCookieStore();
        HttpClient client = HttpClientBuilder.create().setDefaultCookieStore(this.store).build();
//        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(get);
        String result = EntityUtils.toString(response.getEntity());
        System.out.println(result);

        List<Cookie> list = this.store.getCookies();
        for (Cookie cookie : list) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie name=" + name + "; cookie value=" + value);
        }
    }

    @Test(dependsOnMethods = {"test"})
    public void postCookies() throws IOException {
        String uri = this.bundle.getString("test.post.cookie");
        String url = this.host + uri;
        //����һ�����󷽷�
        HttpPost post = new HttpPost(url);
        //�½�һ��client��������cookie��Ϣ
        HttpClient client = HttpClientBuilder.create().
                setDefaultCookieStore(this.store)
                .build();
        //����post����header��Ϣ
        post.setHeader("content-type","application/json");
        //����post�����еĲ�����Ϣ
        JSONObject json = new JSONObject();
        json.put("name","zhaosi");
        json.put("age","23");
        //������������뷽����
        StringEntity entity = new StringEntity(json.toString(),"utf-8");
        post.setEntity(entity);
        //����������������Ӧ���
        HttpResponse response = client.execute(post);
        //��ӡ��Ӧ���
        String result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
        //��Ӧ���ת��Ϊjson����
        JSONObject jsonResult = new JSONObject(result);
        //��ȡ��Ӧ��Ϣ
        String name = jsonResult.getString("name");
        int status =jsonResult.getInt("status");
        //��֤���
        Assert.assertEquals(name,"zhaosi");
        Assert.assertEquals(status,1);
    }
}
