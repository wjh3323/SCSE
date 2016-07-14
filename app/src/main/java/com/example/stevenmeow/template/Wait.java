package com.example.stevenmeow.template;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Wait extends Activity {
    private static DefaultHttpClient httpclient;
    private String result = "";
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        httpclient = new DefaultHttpClient();

        new Thread(new Runnable() {
            public void run() {
                access();
                Message m = handler.obtainMessage(); // 获取一个Message
                handler.sendMessage(m); // 发送消息
            }
        }).start(); // 开启线程

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (result != null) {
                   // Toast.makeText(Wait.this,"哇！登陆成功！",Toast.LENGTH_SHORT).show();
                    Bundle bundle=new Bundle();	//实例化一个Bundle对象
                    bundle.putCharSequence("result",""+result);	//将输入的基本信息保存到Bundle对象中
                    Intent intent = new Intent(Wait.this,MainActivity.class);//等于重置登录状态
                    intent.putExtras(bundle);	//将bundle保存到Intent对象中
                    startActivity(intent);
                    finish();
                }
                super.handleMessage(msg);
            }
        };
    }

    public void access() {
        String target = "http://class.sise.com.cn:7001/sise/module/student_states/student_select_class/main.jsp"; // 要提交的目标地址

        HttpGet httpRequest = new HttpGet(target); // 创建HttpGet对象
        HttpResponse httpResponse;
        try {
            httpclient.setCookieStore(LoginActivity.cookiestore);
            httpResponse = httpclient.execute(httpRequest); // 执行HttpClient请求
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result = EntityUtils.toString(httpResponse.getEntity()); // 获取返回的字符串
            } else {
                result = "请求失败！";
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace(); // 输出异常信息
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
