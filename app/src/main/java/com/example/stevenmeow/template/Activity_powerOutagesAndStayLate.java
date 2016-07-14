package com.example.stevenmeow.template;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.select.Elements;

public class Activity_powerOutagesAndStayLate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_outages_and_stay_late);

        Intent intent = getIntent();
        String result = intent.getStringExtra("result2");
        Elements JpowerOutagesAndStayLate = Jsoup_powerOutagesAndStayLate.JpowerOutagesAndStayLate(result);
        try {
            String[][] powerOutagess = Jsoup_powerOutagesAndStayLate.powerOutages(JpowerOutagesAndStayLate);
            System.out.println(powerOutagess.length);
            for (int i = 0; i < powerOutagess.length; i++) {
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.poasl);

                android.support.v7.widget.CardView cardView = new android.support.v7.widget.CardView(Activity_powerOutagesAndStayLate.this);
                cardView.setCardBackgroundColor(0xffebf6f7);

                LinearLayout.LayoutParams Params_cardView = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                Params_cardView.setMargins(0, 15, 0, 0);

                LinearLayout linearLayoutInCardView = new LinearLayout(Activity_powerOutagesAndStayLate.this);
                linearLayoutInCardView.setOrientation(LinearLayout.VERTICAL);

                LinearLayout.LayoutParams params_xueQi = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                LinearLayout.LayoutParams params_riQi = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                LinearLayout.LayoutParams params_ciShu = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                LinearLayout.LayoutParams params_yuanYin = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                LinearLayout.LayoutParams params_zeRenRen = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                params_xueQi.setMargins(0, 20, 0, 10);
                params_riQi.setMargins(0, 0, 0, 10);
                params_ciShu.setMargins(0, 0, 0, 10);
                params_yuanYin.setMargins(0, 5, 0, 10);
                params_zeRenRen.setMargins(0, 0, 0, 30);

                TextView tv_xueQi = new TextView(Activity_powerOutagesAndStayLate.this);
                tv_xueQi.setText(powerOutagess[i][0] + "学年第" + powerOutagess[i][1] + "学期");
                tv_xueQi.setTextSize(15);
                tv_xueQi.setGravity(Gravity.CENTER);

                TextView tv_riQi = new TextView(Activity_powerOutagesAndStayLate.this);
                tv_riQi.setText("停电日期：" + powerOutagess[i][3] + "宿舍：" + powerOutagess[i][2]);
                tv_riQi.setTextSize(13);
                tv_riQi.setGravity(Gravity.CENTER);

                TextView tv_ciShu = new TextView(Activity_powerOutagesAndStayLate.this);
                tv_ciShu.setText("停电次数：" + powerOutagess[i][4] + "停电天数：" + powerOutagess[i][5]);
                tv_ciShu.setTextSize(13);
                tv_ciShu.setGravity(Gravity.CENTER);

                TextView tv_yuanYin = new TextView(Activity_powerOutagesAndStayLate.this);
                tv_yuanYin.setText("原因：" + powerOutagess[i][6]);
                tv_yuanYin.setTextSize(20);
                tv_yuanYin.setGravity(Gravity.CENTER);
                tv_yuanYin.getPaint().setFakeBoldText(true);

                TextView tv_zeRenRen = new TextView(Activity_powerOutagesAndStayLate.this);
                tv_zeRenRen.setText(powerOutagess[i][7]);
                tv_zeRenRen.setTextSize(18);
                tv_zeRenRen.setGravity(Gravity.CENTER);

                linearLayoutInCardView.addView(tv_xueQi, params_xueQi);
                linearLayoutInCardView.addView(tv_riQi, params_riQi);
                linearLayoutInCardView.addView(tv_ciShu, params_ciShu);
                linearLayoutInCardView.addView(tv_yuanYin, params_yuanYin);
                linearLayoutInCardView.addView(tv_zeRenRen, params_zeRenRen);

                cardView.addView(linearLayoutInCardView);
                linearLayout.addView(cardView, Params_cardView);
            }
        }catch(NullPointerException e){
            Toast.makeText(this," 恭喜你没有违规记录！",Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
