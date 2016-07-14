package com.example.stevenmeow.template;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GradeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        LinearLayout li2 = (LinearLayout) findViewById(R.id.li2);

        Intent intent = getIntent();
        String result = intent.getStringExtra("result2");

        Elements grade = Jsoup_grade.Jgrade(result);
        String[][] a = Jsoup_grade.obligatoryCourseScores(grade);

        for (int i = 0;i<a.length;i++){
            android.support.v7.widget.CardView cardView = new android.support.v7.widget.CardView(GradeActivity.this);
            LinearLayout linearLayout1 = new LinearLayout(GradeActivity.this);
            linearLayout1.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            ViewGroup.LayoutParams params2 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            //设置卡片内textView距离卡片边框的距离
            params1.setMarginEnd(16);
            params1.setMarginStart(16);
            cardView.setCardBackgroundColor(0xffe9e7ef);

            TextView tv1 = new TextView(GradeActivity.this);
            tv1.setText("课程名称："+a[i][0]);
            linearLayout1.addView(tv1, params1);

            TextView tv2 = new TextView(GradeActivity.this);
            tv2.setText("课程名称："+a[i][1]);
            linearLayout1.addView(tv2, params1);

            TextView tv3 = new TextView(GradeActivity.this);
            tv3.setText("学分："+a[i][2]);
            linearLayout1.addView(tv3, params1);

            TextView tv4 = new TextView(GradeActivity.this);
            tv4.setText("考核方式："+a[i][3]);
            linearLayout1.addView(tv4, params1);

            TextView tv5 = new TextView(GradeActivity.this);
            tv5.setText("修读学年学期："+a[i][4]);
            linearLayout1.addView(tv5, params1);

            TextView tv6 = new TextView(GradeActivity.this);
            tv6.setText("成绩："+a[i][5]);
            linearLayout1.addView(tv6, params1);


            cardView.addView(linearLayout1,params1);
            LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params3.setMargins(0, 16, 0, 0);
            li2.addView(cardView,params3);

        }

    }
}
