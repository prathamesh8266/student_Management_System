package com.student.studentManagement;

import com.student.studentManagement.Entity.DatabaseUser;
import com.student.studentManagement.Entity.Grade;
import com.student.studentManagement.Entity.Student;
import com.student.studentManagement.Repository.DatabaseUserRepository;
import com.student.studentManagement.Repository.GradeRepository;
import com.student.studentManagement.Repository.StudentRepository;
import com.student.studentManagement.Service.DatabaseUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@RequiredArgsConstructor
public class StudentManagementApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

	private final DatabaseUserRepository databaseUserRepository;
	private final GradeRepository gradeRepository;
	private final StudentRepository studentRepository;

	@Override
	public void run(String... args) throws Exception {
		DatabaseUser dbUser = new DatabaseUser();
		dbUser.setEmail("admin@gmail.com");
		dbUser.setPassword("password");
		databaseUserRepository.save(dbUser);

		// grades
		Grade grade1 = new Grade();
		grade1.setUser(dbUser);
		grade1.setGrade(12);
		gradeRepository.save(grade1);

		Grade grade2 = new Grade();
		grade2.setUser(dbUser);
		grade2.setGrade(11);
		gradeRepository.save(grade2);

		Grade grade3 = new Grade();
		grade2.setUser(dbUser);
		grade2.setGrade(10);
		gradeRepository.save(grade3);

		// students
		Student student1 = new Student();
		student1.setName("Prathamesh");
		student1.setAge(20);
		student1.setFatherName("Dasharath");
		student1.setMotherName("Supriya");
		student1.setBloodGroup("O+ve");
		student1.setStudentClass(grade1);
		student1.setUser(dbUser);
		studentRepository.save(student1);

		Student student2 = new Student();
		student2.setName("Megha");
		student2.setAge(20);
		student2.setFatherName("Shivaram");
		student2.setMotherName("Nagaratna");
		student2.setBloodGroup("O+ve");
		student2.setStudentClass(grade2);
		student2.setUser(dbUser);
		studentRepository.save(student2);
	}

	@Bean
	public WebMvcConfigurer configure(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedOrigins("*");
			}
		};
	}
}

//{
//		"name" : "Prathamesh",
//		"age" : 20,
//		"motherName" : "Supriya",
//		"fatherName" : "Dasharath",
//		"bloodGroup" : "O+ve",
//		"student_class":{
//		"grade" : 10,
//		"user" : {
//		"id" : 1,
//		"email" : "admin",
//		"password" : "password"
//		}
//		},
//		"user" : {
//		"id" : 1,
//		"email" : "admin",
//		"password" : "password"
//		}
//		}