package com.example.stevenmeow.template;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by Joseph on 2016/5/24.
 */
public class Jsoup_userInfo {

    //提取个人信息，放到一个Element里面，，参数为MainActivity的result
    public static Element Jinfomation(String result){
        Document doc = Jsoup.parse(result);
        Element table_info = doc.select("table[align='left']").first();
        return table_info;
    }

    //返回个人信息的标题,以数组的形式 String[标题]
    public static String[] info_title (Element infomation){
        ArrayList<String> title = new ArrayList<String> ();

        //个人信息标题
        Elements title_info = infomation.select("td.td_right");

        //遍历标题，,把内容存到一个数组里面
        for (Element link : title_info) {
            if (link.text().equals("")||link.text().equals(" ")||link.text().equals("\\n")) {
            }
            else {
                title.add(link.text());
            }
        }


        return title.toArray(new String[title.size()]);
    }

    //返回个人信息的内容,以数组的形式 String[内容]
    public static String[] info_concent (Element infomation){
        ArrayList<String> content = new ArrayList<String> ();

        //个人信息内容
        Elements content_info = infomation.select("td.td_left");

        //遍历内容,把内容存到一个数组里面
        for (Element link : content_info){

            if (link.text().equals("")||link.text().equals(" ")||link.text().equals("\\n")){
            }
            else {
                content.add(link.text());
            }
        }
        return content.toArray(new String[content.size()]);
    }
}