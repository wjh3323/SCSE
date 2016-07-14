package com.example.stevenmeow.template;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jsoup.select.Elements;

public class Activity_CheckOnWorkAttrndance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__check_on_work_attrndance);
        //接受数据
        Intent intent = getIntent();
        String result = intent.getStringExtra("result2");
        Elements abc = Jsoup_checkOnWorkAttendance.JAttendance(result);
        String[][] abc1 = Jsoup_checkOnWorkAttendance.workAttendance(abc);
        for (int i = 0;i<abc1.length;i++){
            LinearLayout linearLayout = (LinearLayout)findViewById(R.id.cowall);

            android.support.v7.widget.CardView cardView = new android.support.v7.widget.CardView(Activity_CheckOnWorkAttrndance.this);
            cardView.setCardBackgroundColor(0xffebf6f7);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,15,0,0);
            LinearLayout linearLayout1 = new LinearLayout(Activity_CheckOnWorkAttrndance.this);
            linearLayout1.setOrientation(LinearLayout.VERTICAL);

            LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            TextView tv1 = new TextView(Activity_CheckOnWorkAttrndance.this);
            params2.setMargins(0,20,0,10);
            tv1.setText(abc1[i][0]);
            tv1.setTextSize(15);
            tv1.setGravity(Gravity.CENTER);
            linearLayout1.addView(tv1,params2);

            TextView tv2 = new TextView(Activity_CheckOnWorkAttrndance.this);
            tv2.setText(abc1[i][1]);
            tv2.setTextSize(18);
            tv2.setGravity(Gravity.CENTER);
            tv2.getPaint().setFakeBoldText(true);
            params3.setMargins(20,0,20,10);
            linearLayout1.addView(tv2,params3);

            TextView tv3 = new TextView(Activity_CheckOnWorkAttrndance.this);
            tv3.setText(abc1[i][2]);
            tv3.setTextSize(18);
            tv3.setGravity(Gravity.CENTER);
            params4.setMargins(0,0,0,30);
            linearLayout1.addView(tv3,params4);

            cardView.addView(linearLayout1);
            linearLayout.addView(cardView,params);
        }



    }
}
