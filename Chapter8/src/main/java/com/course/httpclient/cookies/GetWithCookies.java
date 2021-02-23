package com.course.httpclient.cookies;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

public class GetWithCookies {
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
    public void testWithCookies() throws IOException {
        String uri = this.bundle.getString("test.cookie");
        String url = this.host + uri;
        HttpClient client = HttpClientBuilder.create().setDefaultCookieStore(this.store).build();
        HttpGet get = new HttpGet(url);
        HttpResponse response = client.execute(get);
        int status = response.getStatusLine().getStatusCode();
        System.out.println("status =" + status);
        if (200 == status){
            String result = EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println(result);
        }

    }

}
