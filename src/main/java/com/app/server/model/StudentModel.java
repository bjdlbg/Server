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
