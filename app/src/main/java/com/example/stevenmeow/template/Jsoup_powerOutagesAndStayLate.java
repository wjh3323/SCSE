package com.example.stevenmeow.template;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by Joseph on 2016/5/27.
 */
public class Jsoup_powerOutagesAndStayLate {

    public static Elements JpowerOutagesAndStayLate (String result){
        Document doc = Jsoup.parse(result);
        Elements table_POASL = doc.select("table.table");
        return table_POASL;
    }

    /*返回停电情况，以二维数组的形式，
    String[停电次数][具体停电信息]
    {学年，学期，宿舍，停电日期，停电次数，停电天数，停电原因，责任人}*/
    public static String[][] powerOutages (Elements JpowerOutagesAndStayLate){
        int count = 0;
        Elements tr = JpowerOutagesAndStayLate.select("table.table").first().select("tr.odd,tr.even");
        count = tr.size();

        String[][] powerOutagess = new String[count][8];

        Elements td;

        for (int i = 0; i < count; i++){
            td = tr.get(i).select("td");
            for (int j = 0; j < 8; j++){
                powerOutagess[i][j] = td.get(j).text();
            }
        }

        return powerOutagess;
    }
}
