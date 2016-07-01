package cn.com.tcsl.rxjava;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/28 0028.
 */
public class Student implements Serializable{
    String name;
    ArrayList<String> courses=new ArrayList<String>();

    public Student(String name, ArrayList<String> courses) {
        this.name = name;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }
}
