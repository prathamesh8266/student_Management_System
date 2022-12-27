package com.student.studentManagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer age;
    private String motherName;
    private String fatherName;
    private String bloodGroup;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "class")
    private Grade student_class;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "user_id")
    private DatabaseUser user;
}

//{
//        "id": 1,
//        "name": "Prathamesh",
//        "age": 20,
//        "motherName": "Supriya",
//        "fatherName": "Dasharath",
//        "bloodGroup": "O+ve",
//        "student_class": {
//        "id": 1,
//        "grade": 12,
//        "user": {
//        "id": 1,
//        "email": "admin",
//        "password": "password"
//        }
//        },
//        "user": {
//        "id": 1,
//        "email": "admin",
//        "password": "password"
//        }
//}