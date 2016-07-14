package com.example.stevenmeow.template;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Joseph on 2016/5/25.
 */
public class Jsoup_examinationTime {

    //提取考试信息于一个Element中，参数为MainActivity的result
    public static Element JexamTime (String result){
        Document doc = Jsoup.parse(result);
        Element user_exams = doc.select("table.table").first();
        return user_exams;
    }

    /*返回各科考试信息，以二维数组的形式，
    String[考试科目数目][具体考试科目信息]
    {课程代码，课程名称，考试日期，考试时间，考场编码，考场名称，考试座位，考试状态}*/
    public static String[][] examTimes (Element JexamTime){
        int examCount = 0;
        Elements tr = JexamTime.select("tr.odd,tr.even");
        examCount = tr.size();
        String[][] exams= new String[examCount][8];

        Elements td;

        //遍历用户考试科目
        for (int i = 0; i < examCount; i++){
            td = tr.get(i).select("td");

            //遍历考试科目中的各个信息
            for (int j = 0; j< 8;j++){
                exams[i][j] = td.get(j).text();
            }



        }
        return exams;
    }
}
