package com.example.stevenmeow.template;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jsoup.nodes.Element;

public class ExamTimeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_time);
        LinearLayout li = (LinearLayout) findViewById(R.id.li);

        Intent intent = getIntent();
        String result = intent.getStringExtra("result2");
        Element JexamTime = Jsoup_examinationTime.JexamTime(result);
        String[][] a = Jsoup_examinationTime.examTimes(JexamTime);


        for (int i = 0; i < a.length; i++) {
            android.support.v7.widget.CardView cv = new android.support.v7.widget.CardView(ExamTimeActivity.this);

            LinearLayout linearLayout = new LinearLayout(ExamTimeActivity.this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            ViewGroup.LayoutParams p1 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


            //将TextView放入RelativeLayout
            TextView tv1 = new TextView(ExamTimeActivity.this);
            tv1.setText("课程代码："+a[i][0]);
            linearLayout.addView(tv1, p2);

            TextView tv2 = new TextView(ExamTimeActivity.this);
            tv2.setText("课程名称："+a[i][1]);
            linearLayout.addView(tv2, p2);

            TextView tv3 = new TextView(ExamTimeActivity.this);
            tv3.setText("考试日期："+a[i][2]);
            linearLayout.addView(tv3, p2);

            TextView tv4 = new TextView(ExamTimeActivity.this);
            tv4.setText("考试时间："+a[i][3]);
            linearLayout.addView(tv4, p2);

            TextView tv5 = new TextView(ExamTimeActivity.this);
            tv5.setText("考试编码："+a[i][4]);
            linearLayout.addView(tv5, p2);

            TextView tv6 = new TextView(ExamTimeActivity.this);
            tv6.setText("考场名称："+a[i][5]);
            linearLayout.addView(tv6, p2);

            TextView tv7 = new TextView(ExamTimeActivity.this);
            tv7.setText("考试座位："+a[i][6]);
            linearLayout.addView(tv7, p2);

            TextView tv8 = new TextView(ExamTimeActivity.this);
            tv8.setText("考试状态："+a[i][7]);
            linearLayout.addView(tv8, p2);

            //将包含TextView的LinearLayout放入卡片
            cv.addView(linearLayout, p1);
            //讲卡片放入外层LinearLayout
            LinearLayout.LayoutParams p3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            p3.setMargins(0, 16, 0, 0);
            li.addView(cv, p3);
        }
    }
}
