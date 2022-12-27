package com.student.studentManagement.Repository;

import com.student.studentManagement.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student , Integer> {
}
