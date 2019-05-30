package com.app.server.model;

import lombok.ToString;

/**
 * 学生model
 */
public class StudentModel {
    String sName;
    String sClass;
    String sPhone;
    String sNumber;
    String sCardNum;
    int sId;

    String sOne;
    String sTwo;
    String sThree;
    String sFour;
    String sFive;
    String sSix;
    String sSeven;
    String sEight;
    String sNine;
    String sTen;


    public String getString(){
        return "名字："+sName+","+
                "班级："+sClass+","+
                "手机号："+sPhone+","+
                "学号："+sNumber+","+
                "卡号："+sCardNum+","+
                "------------签到情况----------"+","+
                "第一周："+sOne+","+
                "第二周："+sTwo+","+
                "第三周："+sThree+","+
                "第四周："+sFour+","+
                "第五周："+sFive+","+
                "第六周："+sSix+","+
                "第七周："+sSeven+","+
                "第八周："+sEight+","+
                "第九周："+sNine+","+
                "第十周："+sTen+","+
                "------------签到情况----------";
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "student='" + sName + '\'' +
                ", class='" + sClass + '\'' +
                ", phone='" + sPhone + '\'' +
                ", number='" + sNumber + '\'' +
                ", cardNum='" + sCardNum + '\'' +
                ", id='" + sId + '\'' +
                '}';
    }
}
