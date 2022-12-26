package com.student.studentManagement;

import com.student.studentManagement.Entity.DatabaseUser;
import com.student.studentManagement.Repository.DatabaseUserRepository;
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

	@Override
	public void run(String... args) throws Exception {
		DatabaseUser dbUser = new DatabaseUser();
		dbUser.setEmail("admin");
		dbUser.setPassword("password");
		databaseUserRepository.save(dbUser);
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
