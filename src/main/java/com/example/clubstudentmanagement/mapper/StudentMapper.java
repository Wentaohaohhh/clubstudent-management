package com.example.clubstudentmanagement.mapper;

import com.example.clubstudentmanagement.model.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper {
    //SELECT * FROM student where name LIKE %T%
    @Select("SELECT * FROM student where name LIKE #{name}")
    List<Student> getStudentsContainStrInName(@Param("name") String name);

    //SELECT * FROM student where club_id IN
    //(SELECT id FROM club where year = 2021 AND number = 1)
    @Select("SELECT * FROM student where club_id IN" +
    "(SELECT id FROM club where year = #{year} AND number = #{number})")
    List<Student> getStudentsInClub(@Param("year") int year, @Param("number") int number);
}
