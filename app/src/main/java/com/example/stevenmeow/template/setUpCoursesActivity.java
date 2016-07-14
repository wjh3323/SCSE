package com.example.stevenmeow.template;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class setUpCoursesActivity extends AppCompatActivity {
    RelativeLayout rl;
    TableLayout tablelayout;
    TableRow tr = null;
    public static setUpCoursesActivity instance = null;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_courses);
        rl = (RelativeLayout) findViewById(R.id.sca_rl);
        tablelayout = (TableLayout) findViewById(R.id.sca_tablelayout);

        tablelayout.removeAllViews();

        showObligatoryCourse();

        spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String week = parent.getItemAtPosition(position).toString();
                switch (week){
                    case "必修课":
                        tablelayout.removeAllViews();
                        showObligatoryCourse();
                        break;
                    case "艺术限选课":
                        tablelayout.removeAllViews();
                        showLimitedCourse();
                        break;
                    case "选修课":
                        tablelayout.removeAllViews();
                        showElectiveCourse();
                        break;
                    default:
                        showObligatoryCourse();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void showObligatoryCourse(){
        String[] header = {"课程代码","课程名称","学分","考核方式","先修课程","同修课程"};
        String[][] obligatoryCourse = Jsoup_setUpCourses.obligatoryCourse(Jsoup_setUpCourses.setUpCourse(MainActivity.setUpC));
        tr = new TableRow(setUpCoursesActivity.this);
        for(String a:header){
            TextView tx = new TextView(setUpCoursesActivity.this);
            tx.setText(a);
            tr.addView(tx);
        }
        tablelayout.addView(tr);

        for (String[] n : obligatoryCourse) {
            tr = new TableRow(setUpCoursesActivity.this);
            for (String nn : n) {
                TextView tx = new TextView(setUpCoursesActivity.this);
                tx.setText(nn);
                tr.addView(tx);
            }
            tablelayout.addView(tr);
        }

    }

    public void showLimitedCourse(){
        String[] header = {"课程代码","课程名称","学分","考核方式","先修课程","同修课程"};
        String[][] limitedCourse = Jsoup_setUpCourses.limitedCourse(Jsoup_setUpCourses.setUpCourse(MainActivity.setUpC));
        tr = new TableRow(setUpCoursesActivity.this);
        for(String a:header){
            TextView tx = new TextView(setUpCoursesActivity.this);
            tx.setText(a);
            tr.addView(tx);
        }
        tablelayout.addView(tr);

        for (String[] n : limitedCourse) {
            tr = new TableRow(setUpCoursesActivity.this);
            for (String nn : n) {
                TextView tx = new TextView(setUpCoursesActivity.this);
                tx.setText(nn);
                tr.addView(tx);
            }
            tablelayout.addView(tr);
        }


    }

    public void showElectiveCourse(){
        String[] header = {"课程代码","课程名称","学分","考核方式","先修课程","同修课程"};
        String[][] ElectiveCourse = Jsoup_setUpCourses.ElectiveCourse(Jsoup_setUpCourses.setUpCourse(MainActivity.setUpC));
        tr = new TableRow(setUpCoursesActivity.this);
        for(String a:header){
            TextView tx = new TextView(setUpCoursesActivity.this);
            tx.setText(a);
            tr.addView(tx);
        }
        tablelayout.addView(tr);

        for (String[] n : ElectiveCourse) {
            tr = new TableRow(setUpCoursesActivity.this);
            for (String nn : n) {
                TextView tx = new TextView(setUpCoursesActivity.this);
                tx.setText(nn);
                tr.addView(tx);
            }
            tablelayout.addView(tr);
        }

    }


}
