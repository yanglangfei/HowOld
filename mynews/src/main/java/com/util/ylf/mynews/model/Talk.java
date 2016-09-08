package com.util.ylf.mynews.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/8.
 */
public class Talk implements Serializable {


    /**
     * _id : 17210910
     * day : 10
     * des : 在295年前的今天，1721年9月10日 (农历七月十九)，俄罗斯帝国与瑞典王国签订尼斯塔德不平等条约。
     * lunar :
     * month : 9
     * pic :
     * title : 俄罗斯帝国与瑞典王国签订尼斯塔德不平等条约
     * year : 1721
     */

    private String _id;
    private int day;
    private String des;
    private String lunar;
    private int month;
    private String pic;
    private String title;
    private int year;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getLunar() {
        return lunar;
    }

    public void setLunar(String lunar) {
        this.lunar = lunar;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
