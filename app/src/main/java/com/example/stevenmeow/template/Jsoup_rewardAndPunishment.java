package com.example.stevenmeow.template;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by Joseph on 2016/5/26.
 */
public class Jsoup_rewardAndPunishment {

    public static Elements JrewardAndPunishment (String result){
        Document doc = Jsoup.parse(result);
        Elements table_RAP = doc.select("table.table1");
        return table_RAP;
    }

    /*返回奖励情况，以二维数组的形式，
     String[获奖数][具体获奖信息]
     {学年，学期，奖励级别	，奖励原因，奖励单位/部门，奖励日期}*/
    public static String[][] reward (Elements JrewardAndPunishment){
        int count = 0;

        Elements tr = JrewardAndPunishment.select("table.table1").get(1).select("tr");
        count = tr.size();
        String[][] rewards = new String[count-2][6];

        Elements td;

        //System.out.println(tr);

        for (int i = count-1; i > 1; i--){
            td = tr.get(i).select("td");
            for (int j = 0; j < 6; j++){
                rewards[i-2][j] = td.get(j).text();
            }
        }

        return rewards;
    }

    /*返回惩处情况，以二维数组的形式，
     String[惩处数][具体惩处信息]
     {学年，学期，处分等级，违纪原因，处分发文单位/部门，处分日期，所在系意见，意见人}*/
    public static String[][] punishment (Elements JrewardAndPunishment){
        int count = 0;

        Elements tr = JrewardAndPunishment.select("table.table1").get(2).select("tr");
        count = tr.size();
        String[][] punishments = new String[count-2][8];

        Elements td;

        //System.out.println(tr);

        for (int i = count-1; i > 1; i--){
            td = tr.get(i).select("td");
            for (int j = 0; j < 8; j++){
                punishments[i-2][j] = td.get(j).text();
            }
        }

        return punishments;
    }
}
