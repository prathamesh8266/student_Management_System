package com.student.studentManagement.Service;

import com.student.studentManagement.Entity.DatabaseUser;
import com.student.studentManagement.Entity.Grade;
import com.student.studentManagement.Entity.Student;
import com.student.studentManagement.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final DatabaseUserService databaseUserService;
    private final GradeService gradeService;

    public Student saveStudent(Student student){
        Grade grade = student.getStudentClass();
        Grade checkGrade = gradeService.findByClass(grade.getGrade());
        if(checkGrade == null){
            gradeService.saveGrade(grade);
        }else{
            student.setStudentClass(checkGrade);
        }
        DatabaseUser user = databaseUserService.findDatabaseUserByEmail(student.getUser().getEmail());
        student.setUser(user);
        return studentRepository.save(student);
    }

    public List<Student> getAllByUserId(Integer id){
        DatabaseUser user = databaseUserService.findById(id);
        return studentRepository.findByUser(user);
    }

    public List<Student> getAll(){
        return studentRepository.findAll();
    }

    public List<Student> getAllByUserAndGrade(Integer userId , Integer grade){
        Grade student_grade = gradeService.getGrade(grade);
        DatabaseUser user = databaseUserService.findById(userId);
        return studentRepository.findByUserAndStudentClass(user , student_grade);
    }

}
