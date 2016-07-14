package com.example.stevenmeow.template;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Joseph on 2016/5/25.
 */
public class Jsoup_grade {

    //提取个人信息的所有table于一个Elements中，参数为MainActivity的result
    public static Elements Jgrade (String result){
        Document doc = Jsoup.parse(result);
        Elements table_grade = doc.select("table.table");
        //System.out.println(table_grade);
        return table_grade;
    }

    /*返回各科成绩情况，以二维数组的形式，
     String[学科数目][具体学科成绩信息]
     {课程代码，课程名称，学分，考核方式，修读学年学期，成绩，已得学分}*/
    public static String[][] obligatoryCourseScores (Elements table_grade){
        int count = 0;
        Elements tr = table_grade.select("table").first().select("tr.odd,tr.even");
        count = tr.size();
        String[][] obligatoryScores = new String[count][7];

        Elements td;


        //遍历用户考试科目
        for (int i = 0; i < count; i++){
            td = tr.get(i).select("td");
            int m =0;
            //遍历考试科目中的各个信息
            for (int j = 0; j< 9;j++){
                if (j == 0|| j == 5|| j ==6){

                }else {
                    obligatoryScores[i][m] = td.get(j).text();
                    m++;

                }
            }

        }
        return obligatoryScores;
    }



}
