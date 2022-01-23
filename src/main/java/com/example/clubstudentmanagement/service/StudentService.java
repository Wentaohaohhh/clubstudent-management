package com.example.clubstudentmanagement.service;

import com.example.clubstudentmanagement.dao.StudentDao;
import com.example.clubstudentmanagement.dao.ClubDao;
import com.example.clubstudentmanagement.exceptions.InvalidClubException;
import com.example.clubstudentmanagement.exceptions.StudentNonExistException;
import com.example.clubstudentmanagement.mapper.StudentMapper;
import com.example.clubstudentmanagement.model.Student;
import com.example.clubstudentmanagement.model.Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentDao studentDao;
    private ClubDao clubDao;
    private StudentMapper studentMapper;
    @Autowired
    public StudentService(StudentDao studentDao,
                          ClubDao clubDao,
                          StudentMapper studentMapper) {
        this.studentDao = studentDao;
        this.clubDao = clubDao;
        this.studentMapper = studentMapper;
    }

    public Student addStudent(Student student){
        if(student.getName().isEmpty()){
            throw new RuntimeException("Student name cannot be empty");
        }
        return studentDao.save(student);
    }
    public Student updateStudent(Student student){
        if (student.getId() == null || !studentDao.existsById(student.getId())){
            throw new StudentNonExistException("Cannot find student Id");
        }
        return studentDao.save(student);
    }

    public Student assignClub(Long studentId, Long classId){
        if(!studentDao.existsById(studentId)){
            throw new StudentNonExistException("Cannot find student Id " + studentId);
        }
        if(!clubDao.existsById((classId))){
            throw new InvalidClubException("Cannot find class Id "+classId);
        }

        Student student = getStudentById(studentId).get();
        Club uClass = clubDao.findById(classId).get();

        student.setClub(uClass);
        return studentDao.save(student);
    }

    public List<Student> getAllStudents(){
        return (List<Student>) studentDao.findAll();
    }

    public Optional<Student> getStudentById(Long id){
        return studentDao.findById(id);
    }
    public List<Student> getStudentsByName(String name){
        return studentDao.findByName(name);
    }
    public List<Student> getStudentsContainName(String name){
        return studentMapper.getStudentsContainStrInName("%" + name + "%");
    }
    public List<Student> getStudentsInClub(int year, int number){
        return studentMapper.getStudentsInClub(year, number);
    }

}
