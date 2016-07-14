package com.example.stevenmeow.template;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by StevenMeow on 2016/5/25.
 */
public class LoginActivity extends Activity {


    private Button button; // 声明一个“直接访问”按钮对象
    private Handler handler, handler2; // 声明一个Handler对象
    private String result = ""; // 声明一个代表显示内容的字符串
    public static DefaultHttpClient httpclient; // 声明一个静态的全局的HttpClient对象
    public static org.apache.http.client.CookieStore cookiestore;
    private String username;    //保存用户名的变量
    private String pwd;    //保存密码的变量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        httpclient = new DefaultHttpClient(); // 创建HttpClient对象

        button = (Button) findViewById(R.id.button);//获取按钮组件
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = ((EditText) findViewById(R.id.editText)).getText().toString();    //获取输入的用户名
                pwd = ((EditText) findViewById(R.id.editText2)).getText().toString();    //获取输入的密码
                // 创建一个新线程，实现用户登录
                if("".equals(username) || "".equals(pwd)) {
                    Snackbar.make(v,"请完整输入",Snackbar.LENGTH_LONG).show();
                    //Toast.makeText(LoginActivity.this,"请完整输入！",Toast.LENGTH_SHORT).show();
                }else {
                    new Thread(new Runnable() {
                        public void run() {
                            login(); //用户登录
                            Message m = handler.obtainMessage(); // 获取一个Message
                            handler.sendMessage(m); // 发送消息
                        }
                    }).start(); // 开启线程
                }
            }
        });
        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                System.out.println(result);
                System.out.println(result.length());
                //resultTV.setText(result); // 显示获得的结果
                // if (result != null) {
                if(result.length()!=63){ //验证是否不成功
                    Toast.makeText(LoginActivity.this,"用户名或密码错误或网络出错",Toast.LENGTH_SHORT).show();
                }else{

                     Bundle bundle = new Bundle();    //实例化一个Bundle对象
                     bundle.putCharSequence("result", "" + result);    //将输入的基本信息保存到Bundle对象中
                    Intent intent = new Intent(LoginActivity.this, Wait.class);
                    intent.putExtras(bundle);	//将bundle保存到Intent对象中
                    startActivity(intent);
                    finish();//关闭顺便关cookies
                    //Toast.makeText(LoginActivity.this,"恭喜登录成功",Toast.LENGTH_SHORT).show();//提示登录成功
                    super.handleMessage(msg);
                }
            }
        };

    }

    //处理请求
    public void login() {
        Document doc = null;
        try {
            doc = Jsoup.connect("http://class.sise.com.cn:7001/sise/login.jsp").get();//解析登录页面
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (doc != null) {
            Element input = doc.select("input").first();//获取第一行数据p
            String a = doc.select("[type='hidden']").get(0).attr("name");//取name的值
            String b = doc.select("[type='hidden']").get(0).attr("value");//取value的值
            String target = "http://class.sise.com.cn:7001/sise/login_check.jsp"; // 要提交的目标地址
            HttpPost httpRequest = new HttpPost(target); // 创建HttpPost对象
            // 将要传递的参数保存到List集合中
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", username)); // 用户名
            params.add(new BasicNameValuePair("password", pwd)); // 密码
            params.add(new BasicNameValuePair(a, b));//将获取的hidden值提交
            try {
                httpRequest.setEntity(new UrlEncodedFormEntity(params, "gbk")); // 设置编码方式
                HttpResponse httpResponse = httpclient.execute(httpRequest); // 执行HttpClient请求
                cookiestore = httpclient.getCookieStore();//获取cookies
                if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 如果请求成功
                    result = EntityUtils.toString(httpResponse.getEntity()); // 获取返回的字符串
                } else {
                    result = "请求失败！";
                }
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace(); // 输出异常信息
            } catch (ClientProtocolException e) {
                e.printStackTrace(); // 输出异常信息
            } catch (IOException e) {
                e.printStackTrace(); // 输出异常信息
            }
        }
    }
}
    //处理响应

    

