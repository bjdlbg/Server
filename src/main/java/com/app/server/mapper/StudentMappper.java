package com.app.server.mapper;

import com.app.server.model.ClassModel;
import com.app.server.model.StudentModel;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * ClockingIn 学生dao接口
 */
public interface StudentMappper {

    /**
     * 通过学生名字查询一个学生所有信息
     * @param name
     * @return
     */
    @Select("select * from student where Sname = #{name}")
    StudentModel findStuByName(String name);

   // @Insert("insert into student(user, password,token) values(#{phone}, #{password}, #{token})")

    /**
     * 通过教师名字查询该教师的所有课程
     * @param teacherName
     */
    @Select("SELECT Tclass FROM teacher WHERE Tname= #{teacherName}")
    List<Map<String,Object>> findClassByTeacherName(String teacherName);


    /**
     * 查询选了某课程下的所有学生列表
     * @param className
     * @return
     */
    @Select("SELECT Sname FROM student where Sid = any(SELECT Sid FROM class WHERE Tid = any(SELECT Tid from teacher where Tclass = #{className}));")
    List<Map<String,Object>> findStuByClass(String className);

    /**
     * 通过卡片id查询学生信息
     * @param cardNum
     * @return
     */
    @Select("select Sname from student WHERE Scardnum= #{cardNum}")
    String findStuByCardNum(String cardNum);


    /**
     * 根据学生名字与当前周数更新上课状态
     * @param name
     * @param name
     * @return
     */
    @Update("UPDATE student SET Sone = '上过' WHERE Sname= #{name}")
    int updateWeekOneByName(String name);

    @Update("UPDATE student SET Stwo = '上过' WHERE Sname= #{name}")
    int updateWeekTwoByName(String name);

    @Update("UPDATE student SET Sthree = '上过' WHERE Sname= #{name}")
    int updateWeekThreeByName(String name);

    @Update("UPDATE student SET Sfour = '上过' WHERE Sname= #{name}")
    int updateWeekFourByName(String name);

    @Update("UPDATE student SET Sfive = '上过' WHERE Sname= #{name}")
    int updateWeekFiveByName(String name);

    @Update("UPDATE student SET Ssix = '上过' WHERE Sname= #{name}")
    int updateWeekSixByName(String name);

    @Update("UPDATE student SET Sseven = '上过' WHERE Sname= #{name}")
    int updateWeekSevenByName(String name);

    @Update("UPDATE student SET Seight = '上过' WHERE Sname= #{name}")
    int updateWeekEightByName(String name);

    @Update("UPDATE student SET Snine = '上过' WHERE Sname= #{name}")
    int updateWeekNineByName(String name);

    @Update("UPDATE student SET Sten = '上过' WHERE Sname= #{name}")
    int updateWeekTenByName(String name);



}
