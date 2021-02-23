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
        // 获取properties文件中的key-value值
        bundle = ResourceBundle.getBundle("application");
        host = bundle.getString("test.host");
    }

    @Test
    public void test() throws IOException {
        String uri = bundle.getString("test.uri");
        String url = host + uri;
        HttpGet get = new HttpGet(url);
        // 获取cookies信息
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
        //声明一个请求方法
        HttpPost post = new HttpPost(url);
        //新建一个client，并设置cookie信息
        HttpClient client = HttpClientBuilder.create().
                setDefaultCookieStore(this.store)
                .build();
        //设置post方法header信息
        post.setHeader("content-type","application/json");
        //设置post方法中的参数信息
        JSONObject json = new JSONObject();
        json.put("name","zhaosi");
        json.put("age","23");
        //将请求参数放入方法中
        StringEntity entity = new StringEntity(json.toString(),"utf-8");
        post.setEntity(entity);
        //声明对象来储存响应结果
        HttpResponse response = client.execute(post);
        //打印响应结果
        String result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
        //响应结果转化为json对象
        JSONObject jsonResult = new JSONObject(result);
        //获取响应信息
        String name = jsonResult.getString("name");
        int status =jsonResult.getInt("status");
        //验证结果
        Assert.assertEquals(name,"zhaosi");
        Assert.assertEquals(status,1);
    }
}
