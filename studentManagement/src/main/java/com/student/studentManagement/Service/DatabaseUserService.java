package com.student.studentManagement.Service;

import com.student.studentManagement.Entity.DatabaseUser;
import com.student.studentManagement.Exceptions.ResourceNotFoundException;
import com.student.studentManagement.Repository.DatabaseUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class DatabaseUserService {

    private final DatabaseUserRepository databaseUserRepository;

    public User findByEmail(String email){
        DatabaseUser DBUser = databaseUserRepository.findByEmail(email);
        if(DBUser == null){
            throw new UsernameNotFoundException("User not found");
        }
        User user = new User(DBUser.getEmail(), DBUser.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
        return user;
    };

    public DatabaseUser findDatabaseUserByEmail(String email){
        DatabaseUser DBUser = databaseUserRepository.findByEmail(email);
        if(DBUser == null){
            throw new ResourceNotFoundException("User","Email",email);
        }
        return DBUser;
    };
}
