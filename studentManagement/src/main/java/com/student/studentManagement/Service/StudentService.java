package com.student.studentManagement.Service;

import com.student.studentManagement.Entity.Grade;
import com.student.studentManagement.Entity.Student;
import com.student.studentManagement.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final GradeService gradeService;

    public Student saveStudent(Student student){
        Grade grade = student.getStudent_class();
        Grade checkGrade = gradeService.findByClass(grade.getGrade());
        if(checkGrade == null){
            gradeService.saveGrade(grade);
        }else{
            student.setStudent_class(checkGrade);
        }
        return studentRepository.save(student);
    }

}
