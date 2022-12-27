package com.student.studentManagement.Service;


import com.student.studentManagement.Entity.DatabaseUser;
import com.student.studentManagement.Entity.Grade;
import com.student.studentManagement.Exceptions.ResourceNotFoundException;
import com.student.studentManagement.Repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;
    private final DatabaseUserService databaseUserService;
    public Grade saveGrade(Grade g){
        return gradeRepository.save(g);
    }
    public Grade findByClass(Integer grade){
        return gradeRepository.findByGrade(grade);
    }
    public List<Grade> findAllByUser(Integer id){
        DatabaseUser db_user = databaseUserService.findById(id);
        if(db_user == null){
            throw new ResourceNotFoundException("User" , "Id" , id+"");
        }
        return gradeRepository.findByUser(db_user);
    }

}
