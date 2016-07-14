package com.example.stevenmeow.template;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by Joseph on 2016/5/27.
 */
public class Jsoup_setUpCourses {

    public static Elements setUpCourse (String result){
        Document doc = Jsoup.parse(result);
        Elements table_content = doc.select("table.table1");
        return table_content;
    }
    
    /*返回必修课，以二维数组的形式，
    String[必修课][具体信息]
    {课程代码，课程名称，学分，考核方式，先修课程，同修课程}*/
	public static String[][] obligatoryCourse(Elements table_content) {
		int count = 0;
        Elements tr = table_content.select("table.table1").get(1).select("tr");
        count = tr.size();
        
        String[][] obligatoryCourses = new String[count-2][6];

        Elements td;
        
        for (int i = count-1; i > 1; i--){
            td = tr.get(i).select("td");
            for (int j = 0; j < 6; j++){
            	obligatoryCourses[i-2][j] = td.get(j).text()+"     ";
                System.out.println("rewards["+(i-2)+"]["+j+"]== "+obligatoryCourses[i-2][j]);
            }
        }
        
		return obligatoryCourses;
	}
	
	/*返回艺术限选课，以二维数组的形式，
    String[艺术限选课][具体信息]
    {课程代码，课程名称，学分，考核方式，先修课程，同修课程}*/
	public static String[][] limitedCourse(Elements table_content) {
		int count = 0;
        Elements tr = table_content.select("table.table1").get(2).select("tr");
        count = tr.size();
        
        String[][] limitedCourses = new String[count-2][6];

        Elements td;
        
        for (int i = count-1; i > 1; i--){
            td = tr.get(i).select("td");
            for (int j = 0; j < 6; j++){
            	limitedCourses[i-2][j] = td.get(j).text()+"     ";
                System.out.println("rewards["+(i-2)+"]["+j+"]== "+limitedCourses[i-2][j]);
            }
        }
        
        return limitedCourses;
	}
	
	/*返回选修课，以二维数组的形式，
    String[选修课][具体信息]
    {课程代码，课程名称，学分，考核方式，先修课程，同修课程}*/
	public static String[][] ElectiveCourse(Elements table_content) {
		int count = 0;
        Elements tr = table_content.select("table.table1").get(3).select("tr");
        count = tr.size();
        
        String[][] ElectiveCourses = new String[count-2][6];

        Elements td;
        
        for (int i = count-1; i > 1; i--){
            td = tr.get(i).select("td");
            for (int j = 0; j < 6; j++){
            	ElectiveCourses[i-2][j] = td.get(j).text()+"     ";
            }
        }
        
        return ElectiveCourses;
	}

}
