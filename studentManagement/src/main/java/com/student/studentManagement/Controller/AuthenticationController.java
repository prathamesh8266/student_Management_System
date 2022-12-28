package com.student.studentManagement.Controller;

import com.student.studentManagement.Entity.DatabaseUser;
import com.student.studentManagement.Exceptions.ResourceNotFoundException;
import com.student.studentManagement.Service.DatabaseUserService;
import com.student.studentManagement.Utils.TokenParse;
import com.student.studentManagement.Utils.VerifiedUserResponse;
import com.student.studentManagement.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final DatabaseUserService databaseUserService;
    private final JwtUtil jwtUtil;

    @PostMapping("/getToken")
    public ResponseEntity<?> authenticate(@RequestBody DatabaseUser user){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword())
            );
        }catch(Exception e){
            throw new ResourceNotFoundException("User","Email or Password","email : " + user.getEmail() +" & password : " + user.getPassword());
        }
        final UserDetails spring_user = databaseUserService.findByEmail(user.getEmail());
        if(spring_user != null){
            String token  = jwtUtil.generateToken(spring_user);
            System.out.println("token -> "+ token);
            Integer id = databaseUserService.findDatabaseUserByEmail(user.getEmail()).getId();
            String userName = spring_user.getUsername();
            VerifiedUserResponse verifiedUserResponse =
                    new VerifiedUserResponse(token , id , userName );
            return new ResponseEntity<>(verifiedUserResponse , HttpStatus.OK);
        }
        return new ResponseEntity<>("Some error occurred" , HttpStatus.OK);
    }

    @PostMapping("/isValidToken")
    public ResponseEntity<Boolean> isValidToken(@RequestBody TokenParse tokenParse){
        // code of checking if the token is still valid
        System.out.println("validating");
        long expirationDate = jwtUtil.extractExpiration(tokenParse.getToken()).getTime();
        long currentTime = new Date().getTime();
        if(expirationDate > currentTime){
            return new ResponseEntity<>(false , HttpStatus.OK);
        }
        return new ResponseEntity<>(true , HttpStatus.OK);
    }

}
