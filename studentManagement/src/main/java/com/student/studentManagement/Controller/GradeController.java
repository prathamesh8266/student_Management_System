package com.student.studentManagement.Controller;

import com.student.studentManagement.Entity.DatabaseUser;
import com.student.studentManagement.Entity.Grade;
import com.student.studentManagement.Service.GradeService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grade")
@RequiredArgsConstructor
@CrossOrigin
public class GradeController {

    private final GradeService gradeService;

    @GetMapping("/getGrade/{id}")
    public List<Grade> getAllGrade(@PathVariable Integer id){
        return gradeService.findAllByUser(id);
    }

}
