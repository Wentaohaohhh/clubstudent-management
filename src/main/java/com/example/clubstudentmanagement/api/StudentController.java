package com.example.clubstudentmanagement.api;

import com.example.clubstudentmanagement.exceptions.InvalidClubException;
import com.example.clubstudentmanagement.exceptions.StudentEmptyNameException;
import com.example.clubstudentmanagement.exceptions.StudentNonExistException;
import com.example.clubstudentmanagement.model.Student;
import com.example.clubstudentmanagement.service.StudentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @RequiresPermissions("student:read")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/name")
    public List<Student> getStudents(@RequestParam String name){
        return studentService.getStudentsByName(name);
    }

    @GetMapping("/contain_name")
    public List<Student> getStudentsContainName(@RequestParam String name){
        return studentService.getStudentsContainName(name);
    }
    @GetMapping("/club")
    public List<Student> getStudentsInClub(@RequestParam int year, @RequestParam int number){
        return studentService.getStudentsInClub(year, number);
    }
    @RequestMapping("/register")
    @PostMapping
    public ResponseEntity<String> registerStudent(@RequestBody Student student){
        try{
            Student saveStudent = studentService.addStudent(student);
            return ResponseEntity.ok("Registered student. " + saveStudent.toString());
        }catch(StudentEmptyNameException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(path = "assignclub/{sid}/{cid}")
    public ResponseEntity<String> assignClub(@PathVariable("sid") Long studentId,
                                              @PathVariable("cid") Long clubId){
        try {
            Student updateStudent = studentService.assignClub(studentId, clubId);
            return ResponseEntity.ok("Assigned class. " + updateStudent.toString());
        }catch (StudentNonExistException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (InvalidClubException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
