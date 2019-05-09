package com.app.server.mapper;

import com.app.server.model.StudentModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface StudentMappper {

    /**
     * 通过学生名字查询一个学生
     * @param name
     * @return
     */
    @Select("select * from student where Sname = #{name}")
    StudentModel findStuByName(String name);

   // @Insert("insert into student(user, password,token) values(#{phone}, #{password}, #{token})")

}
