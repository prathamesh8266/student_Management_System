package com.student.studentManagement.Controller;


import com.student.studentManagement.Entity.Student;
import com.student.studentManagement.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
@CrossOrigin
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/add" )
    public Student addStudent(@RequestBody Student student){
        return studentService.saveStudent(student);
    }

    @GetMapping("/getAll/{userId}")
    public List<Student> getAll(@PathVariable Integer userId){
        return studentService.getAllByUserId(userId);
    }

    @GetMapping("/getAll/{userId}/{grade}")
    public List<Student> getAll(@PathVariable Integer userId , @PathVariable Integer grade){
        return studentService.getAllByUserAndGrade(userId,grade);
    }

}
