package com.example.stevenmeow.template;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableRow;
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
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.StringReader;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private static DefaultHttpClient httpclient;
    //private String result = "";
    private String result2 = "";
    private Handler handler;
    Document doc;
    static String setUpC="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //数据接收以及处理
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String html = bundle.getString("result");
        doc = Jsoup.parse(html);
        httpclient = new DefaultHttpClient();



        final LinearLayout re = (LinearLayout) findViewById(R.id.re);

        //浮动按钮以及监听事件
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "你确定要退出吗？", Snackbar.LENGTH_LONG)
                        .setAction("是的", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        }).show();
            }
        });


        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {

                    case 1://个人信息的事件处理
                        Element imfomation = Jsoup_userInfo.Jinfomation(result2);
                        String[] title = Jsoup_userInfo.info_title(imfomation);
                        String[] content = Jsoup_userInfo.info_concent(imfomation);
                        String[] qwe = new String[9];
                        for (int i = 0; i < 9; i++) {
                            qwe[i] = title[i] + content[i];
                        }
                        re.removeAllViews();
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, qwe);
                        ListView listView = new ListView(MainActivity.this);
                        listView.setAdapter(arrayAdapter);
                        android.support.v7.widget.CardView cd = new android.support.v7.widget.CardView(MainActivity.this);

                        re.addView(cd);
                        cd.addView(listView);

                        break;

                    case 2: //考试时间的事件处理
                        Intent intent1 = new Intent(MainActivity.this,ExamTimeActivity.class);
                        intent1.putExtra("result2",result2);
                        startActivity(intent1);
                        break;

                    case 3: //考勤信息的时间处理
                        Intent intent3 = new Intent(MainActivity.this,Activity_CheckOnWorkAttrndance.class);
                        intent3.putExtra("result2",result2);
                        startActivity(intent3);
                        break;

                    case 4: //课程表的事件处理
                        Bundle bundle = new Bundle();    //实例化一个Bundle对象
                        bundle.putCharSequence("result", "" + result2);    //将输入的基本信息保存到Bundle对象中
                        Intent intent = new Intent(MainActivity.this, ScheduleActivity.class);
                        intent.putExtras(bundle);	//将bundle保存到Intent对象中
                        startActivity(intent);
                        break;

                    case 5: //成绩查询的事件处理
                        Intent intent5 = new Intent(MainActivity.this,GradeActivity.class);
                        intent5.putExtra("result2",result2);
                        startActivity(intent5);
                        break;

                    case 6://奖罚情况的事件处理
                        Intent intent6 = new Intent(MainActivity.this,RandPActivity.class);
                        intent6.putExtra("result2",result2);
                        startActivity(intent6);
                        break;

                    case 7: //开设课程的事件处理
                        setUpC = result2;
                        Intent intent7 = new Intent(MainActivity.this, setUpCoursesActivity.class);
                        startActivity(intent7);
                        break;

                    case 8: //违规情况的事件处理
                            Intent intent8 = new Intent(MainActivity.this, Activity_powerOutagesAndStayLate.class);
                            intent8.putExtra("result2", result2);
                            startActivity(intent8);

                        break;
                }
            }
        };

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //给侧滑菜单增加监听事件，ActionBarDrawerToggle是一个开关，用于打开/关闭DrawerLayout抽屉
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //侧滑
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    //定义后退键的处理事件
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //利用Handler发送消息处理UI线程
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        //点击不同的选项，发出不同信号给Handler处理
        final Message message = handler.obtainMessage();
        if (id == R.id.personal) {
            new Thread(new Runnable() {
                public void run() {
                    String a = doc.select("td[onclick]").get(0).attr("onclick");//个人信息
                    connect(a.substring(54, a.length() - 1));
                    message.what = 1;
                    handler.sendMessage(message);
                }
            }).start(); // 开启线程

        } else if (id == R.id.TimeTable) {
            new Thread(new Runnable() {
                public void run() {
                    String a = doc.select("td[onclick]").get(2).attr("onclick");//考试时间
                    connect(a.substring(49, a.length() - 1));
                    message.what = 2;
                    handler.sendMessage(message);
                }
            }).start(); // 开启线程

        } else if (id == R.id.Check) {
            new Thread(new Runnable() {
                public void run() {
                    String a = doc.select("td[onclick]").get(3).attr("onclick");//考勤信息
                    connect(a.substring(49, a.length() - 1));
                    message.what = 3;
                    handler.sendMessage(message);
                }
            }).start(); // 开启线程


        } else if (id == R.id.schedule) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String a = "sise/module/student_schedular/student_schedular.jsp";//课程表
                    connect(a);
                    message.what = 4;
                    handler.sendMessage(message);
                }
            }).start(); // 开启线程
        } else if (id == R.id.Grade) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String a = doc.select("td[onclick]").get(0).attr("onclick");//成绩查询
                    connect(a.substring(54, a.length() - 1));
                    message.what = 5;
                    handler.sendMessage(message);
                }
            }).start(); // 开启线程
        } else if (id == R.id.History) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String a = doc.select("td[onclick]").get(5).attr("onclick");//奖惩情况
                    connect(a.substring(40, a.length() - 1));
                    message.what = 6;
                    handler.sendMessage(message);
                }
            }).start(); // 开启线程
        } else if (id == R.id.NewClass) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String a = "sise/module/selectclassview/selectclasscourse_view.jsp";//开设课程
                    connect(a);
                    message.what = 7;
                    handler.sendMessage(message);
                }
            }).start(); // 开启线程
        } else if (id == R.id.Violation) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String a = doc.select("td[onclick]").get(20).attr("onclick");//违规查询
                    connect(a.substring(49, a.length() - 1));
                    message.what = 8;
                    handler.sendMessage(message);
                }
            }).start(); // 开启线程
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //请求并返回不同的信息
    public void connect(String url) {
        String target = "http://class.sise.com.cn:7001/" + url; // 要提交的目标地址
        HttpGet httpRequest = new HttpGet(target); // 创建HttpGet对象
        HttpResponse httpResponse;
        try {
            httpclient.setCookieStore(LoginActivity.cookiestore);//传递cookie
            httpResponse = httpclient.execute(httpRequest); // 执行HttpClient请求
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                result2 = EntityUtils.toString(httpResponse.getEntity()); // 获取返回的字符串
            } else {
                result2 = "请求失败！";
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace(); // 输出异常信息
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
