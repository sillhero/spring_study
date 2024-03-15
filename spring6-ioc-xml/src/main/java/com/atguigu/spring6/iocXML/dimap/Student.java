package com.atguigu.spring6.iocXML.dimap;

import java.util.Map;

public class Student {

    private Map<String, Teacher> teacherMap;
    private String sid;
    private String sname;

    public Map<String, Teacher> getTeacherMap() {
        return teacherMap;
    }

    public void setTeacherMap(Map<String, Teacher> teacherMap) {
        this.teacherMap = teacherMap;
    }

    public void run() {
        System.out.println("学生ID" + sid + "学生姓名" + sname);
        System.out.println(teacherMap);
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
}
