package com.example.stevenmeow.template;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.jsoup.nodes.Element;

public class ScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String html = bundle.getString("result");
        Element sb = Jsoup_curriculumSchedule.Schedule(html);
        ;


        TextView[] tvb = new TextView[30];
        tvb[0] = (TextView) findViewById(R.id.textView0);
        tvb[1] = (TextView) findViewById(R.id.textView1);
        tvb[2] = (TextView) findViewById(R.id.textView2);
        tvb[3] = (TextView) findViewById(R.id.textView3);
        tvb[4] = (TextView) findViewById(R.id.textView4);
        tvb[5] = (TextView) findViewById(R.id.textView5);
        tvb[6] = (TextView) findViewById(R.id.textView6);
        tvb[7] = (TextView) findViewById(R.id.textView7);
        tvb[8] = (TextView) findViewById(R.id.textView8);
        tvb[9] = (TextView) findViewById(R.id.textView9);
        tvb[10] = (TextView) findViewById(R.id.textView10);
        tvb[11] = (TextView) findViewById(R.id.textView11);
        tvb[12] = (TextView) findViewById(R.id.textView12);
        tvb[13] = (TextView) findViewById(R.id.textView13);
        tvb[14] = (TextView) findViewById(R.id.textView14);
        tvb[15] = (TextView) findViewById(R.id.textView15);
        tvb[16] = (TextView) findViewById(R.id.textView16);
        tvb[17] = (TextView) findViewById(R.id.textView17);
        tvb[18] = (TextView) findViewById(R.id.textView18);
        tvb[19] = (TextView) findViewById(R.id.textView19);
        tvb[20] = (TextView) findViewById(R.id.textView20);
        tvb[21] = (TextView) findViewById(R.id.textView21);
        tvb[22] = (TextView) findViewById(R.id.textView22);
        tvb[23] = (TextView) findViewById(R.id.textView23);
        tvb[24] = (TextView) findViewById(R.id.textView24);
        tvb[25] = (TextView) findViewById(R.id.textView25);
        tvb[26] = (TextView) findViewById(R.id.textView26);
        tvb[27] = (TextView) findViewById(R.id.textView27);
        tvb[28] = (TextView) findViewById(R.id.textView28);
        tvb[29] = (TextView) findViewById(R.id.textView29);

        String[][] tvbs=Jsoup_curriculumSchedule.curriculumSchedules(sb);

        for (int i = 0; i < 5; i++) {
            tvb[i].setText(Jsoup_curriculumSchedule.curriculumSchedules(sb)[0][i + 1]);
        }
        for (int i = 5; i < 10; i++) {
            tvb[i].setText(Jsoup_curriculumSchedule.curriculumSchedules(sb)[1][i - 4]);
        }
        for (int i = 10; i < 15; i++) {
            tvb[i].setText(Jsoup_curriculumSchedule.curriculumSchedules(sb)[3][i - 9]);
        }
        for (int i = 15; i < 20; i++) {
            tvb[i].setText(Jsoup_curriculumSchedule.curriculumSchedules(sb)[4][i - 14]);
        }
        for (int i = 20; i < 25; i++) {
            tvb[i].setText(Jsoup_curriculumSchedule.curriculumSchedules(sb)[5][i - 19]);
        }
        for (int i = 25; i < 30; i++) {
            tvb[i].setText(Jsoup_curriculumSchedule.curriculumSchedules(sb)[5][i - 24]);
        }
    }
}
