package com.example.stevenmeow.template;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Joseph on 2016/5/27.
 */
public class Jsoup_curriculumSchedule {
    public static Element Schedule (String result){
        Document doc = Jsoup.parse(result);
        Element table_content = doc.select("table[bordercolor=\"#999999\"]").get(1);
        return table_content;
    }

    /*返回课程表，以二维数组的形式，
     String[节数][对应一周的当前节数的所有课程]
     {节数，星期一，星期二	，星期三，星期四，星期五，星期六，星期日}*/
    public static String[][] curriculumSchedules (Element Schedule){
        int count = 0;
        Elements tr = Schedule.select("tr[bgcolor=\"#FFFFFF\"]");
        count = tr.size();

        String[][] Schedules = new String[count][8];

        Elements td;

        for (int i = 0; i < count; i++){
            td = tr.get(i).select("td");
            for (int j = 0; j < 8; j++){
                Schedules[i][j] = td.get(j).text();
            }
        }

        return Schedules;
    }
}
