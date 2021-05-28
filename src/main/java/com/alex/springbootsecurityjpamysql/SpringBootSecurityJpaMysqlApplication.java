package com.alex.springbootsecurityjpamysql;

import com.alex.springbootsecurityjpamysql.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class SpringBootSecurityJpaMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityJpaMysqlApplication.class, args);
	}

}
