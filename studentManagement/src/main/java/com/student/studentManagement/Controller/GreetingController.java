package com.student.studentManagement.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GreetingController {

    @GetMapping("/greeting")
    public ResponseEntity<String> greet(){
        return new ResponseEntity<>("Hello from spring..!!" , HttpStatus.OK);
    }
}
