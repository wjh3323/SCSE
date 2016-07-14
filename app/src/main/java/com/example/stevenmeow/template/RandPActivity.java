package com.example.stevenmeow.template;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RandPActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rand_p);
        LinearLayout li = (LinearLayout) findViewById(R.id.li);

        Intent intent = getIntent();
        String result = intent.getStringExtra("result2");
        Elements sb=Jsoup_rewardAndPunishment.JrewardAndPunishment(result);
        String[][] a = Jsoup_rewardAndPunishment.reward(sb);
        if(a.length!=0) {
            for (int i = 0; i < a.length; i++) {
                android.support.v7.widget.CardView cv = new android.support.v7.widget.CardView(RandPActivity.this);
                LinearLayout linearLayout = new LinearLayout(RandPActivity.this);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                p2.setMarginStart(16);
                p2.setMarginEnd(16);


                cv.setCardBackgroundColor(0xffc6feee);
                //将TextView放入RelativeLayout
                TextView tv1 = new TextView(RandPActivity.this);
                tv1.setGravity(Gravity.CENTER);
                tv1.setTextSize(10);
                tv1.setText(a[i][0]);
                linearLayout.addView(tv1, p2);

                TextView tv2 = new TextView(RandPActivity.this);
                tv2.setGravity(Gravity.CENTER);
                tv2.setTextSize(11);
                tv2.setText(a[i][1]);
                linearLayout.addView(tv2, p2);

                TextView tv3 = new TextView(RandPActivity.this);
                tv3.setGravity(Gravity.CENTER);
                tv3.setTextSize(12);
                tv3.setText(a[i][2]);
                linearLayout.addView(tv3, p2);

                TextView tv4 = new TextView(RandPActivity.this);
                tv4.setGravity(Gravity.CENTER);
                tv4.setTextSize(14);
                tv4.setText(a[i][3]);
                linearLayout.addView(tv4, p2);

                TextView tv5 = new TextView(RandPActivity.this);
                tv5.setGravity(Gravity.CENTER);
                tv5.setTextSize(12);
                tv5.setText(a[i][4]);
                linearLayout.addView(tv5, p2);

                TextView tv6 = new TextView(RandPActivity.this);
                tv6.setGravity(Gravity.CENTER);
                tv6.setTextSize(11);
                tv6.setText(a[i][5]);
                linearLayout.addView(tv6, p2);


                //将包含TextView的LinearLayout放入卡片

                cv.addView(linearLayout, p1);
                //讲卡片放入外层LinearLayout
                LinearLayout.LayoutParams p3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                p3.setMargins(10, 10, 10, 10);

                li.addView(cv, p3);
            }
        }else{
            Toast.makeText(RandPActivity.this,"没有你任何相关信息",Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
