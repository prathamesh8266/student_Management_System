package com.student.studentManagement.Repository;

import com.student.studentManagement.Entity.DatabaseUser;
import com.student.studentManagement.Entity.Grade;
import com.student.studentManagement.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student , Integer> {
    public List<Student> findByUser(DatabaseUser user);
    public List<Student> findByUserAndStudentClass(DatabaseUser user , Grade grade);
}
