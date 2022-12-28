package com.student.studentManagement.Repository;

import com.student.studentManagement.Entity.DatabaseUser;
import com.student.studentManagement.Entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
    public Grade findByGrade(Integer grade);
    public List<Grade> findByUserOrderByGrade(DatabaseUser user);
}
