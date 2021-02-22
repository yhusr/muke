package com.course.httpclient.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyHttpClient {
    @Test
    public void test1() throws IOException {
        String result=null;
        HttpGet get = new HttpGet("http://www.baidu.com");
        /**
         *
         * httpclient 4.3版本后使用的是
         * HttpClient hc = HttpClientBuilder.create().build();
         */
        HttpClient hc = HttpClientBuilder.create().build();
        HttpResponse hr = hc.execute(get);
        result = EntityUtils.toString(hr.getEntity(),"utf-8");
        System.out.println(result);
    }
}
