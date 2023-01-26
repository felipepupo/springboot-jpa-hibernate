package com.example.springbootjpahibernate;

import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.springbootjpahibernate.entity.Course;
import com.example.springbootjpahibernate.repository.CourseRepository;

@SpringBootApplication
public class SpringbootJpaHibernateApplication implements CommandLineRunner{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository repository;

	@Override
	public void run(String... args) throws Exception {

		// Using JPA
		Course course = repository.findById(10001L);
		logger.info("Course 10001 -> {}", course);

		repository.save(new Course("Microservices in 100 steps"));

		repository.deleteById(10001L);
		

		// Using Native Queries
        logger.info("SELECT * FROM COURSE_DETAILS -> {}", repository.findAllUsingNativeQuerie());

		logger.info("SELECT * FROM COURSE_DETAILS -> {}", repository.findByIdUsingNativeQuerie(10003L));
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaHibernateApplication.class, args);
	}

}
