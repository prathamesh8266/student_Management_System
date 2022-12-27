package com.student.studentManagement.Repository;

import com.student.studentManagement.Entity.DatabaseUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatabaseUserRepository extends JpaRepository<DatabaseUser , Integer> {

    public DatabaseUser findByEmail(String email);
//    public DatabaseUser findById(Integer id);
}
