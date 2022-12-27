package com.student.studentManagement.Controller;


import com.student.studentManagement.Entity.Student;
import com.student.studentManagement.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/add" )
    public Student addStudent(@RequestBody Student student){
        return studentService.saveStudent(student);
    }

}
