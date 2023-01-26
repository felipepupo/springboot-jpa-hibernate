package com.example.springbootjpahibernate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.springbootjpahibernate.entity.Course;
import com.example.springbootjpahibernate.entity.Student;
import com.example.springbootjpahibernate.repository.CourseRepository;
import com.example.springbootjpahibernate.repository.StudentRepository;

@SpringBootApplication
public class SpringbootJpaHibernateApplication implements CommandLineRunner{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public void run(String... args) throws Exception {

		// Courser test
		Course course = courseRepository.findById(10001L);
		logger.info("Course 10001 -> {}", course);
		courseRepository.save(new Course("Microservices in 100 steps"));
		courseRepository.deleteById(10001L);
		
        logger.info("SELECT * FROM COURSE_DETAILS -> {}", courseRepository.findAllUsingNativeQuerie());
		logger.info("SELECT * FROM COURSE_DETAILS -> {}", courseRepository.findByIdUsingNativeQuerie(10003L));

		// Student test
		Student student = studentRepository.findById(20001L);
		logger.info("student -> {}", student);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaHibernateApplication.class, args);
	}

}
