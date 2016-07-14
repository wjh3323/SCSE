package com.example.stevenmeow.template;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Joseph on 2016/5/25.
 */
public class Jsoup_checkOnWorkAttendance {

    //提取考勤页面的所有table于一个Elements中，参数为MainActivity的result
    public static Elements JAttendance (String result){
        Document doc = Jsoup.parse(result);
        Elements table_content = doc.select("table");
        return table_content;
    }

    //返回旷课总数
    public static String withoutAttendanceCount (Elements table_content){
        Element Jcount = table_content.select("table").get(5).select("td").last();
        String count = Jcount.text();

        return count;
    }

    /*返回各科考勤情况，以二维数组的形式，
     String[学科数目][具体学科考勤信息]
     {课程编号，课程名称，详细信息}*/
    public static String[][] workAttendance (Elements table_content){
        int count = 0;
        Elements tr = table_content.select("table").get(6).select("tr.odd,tr.even");
        count = tr.size();

        String[][] Attendance = new String[count][3];

        Elements td;

        for (int i = 0; i < count; i++){
            td = tr.get(i).select("td");
            for (int j = 0; j < 3; j++){
                Attendance[i][j] = td.get(j).text();

            }
        }

        return Attendance;
    }
}
