package com.example.springbootjpahibernate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.springbootjpahibernate.entity.Course;
import com.example.springbootjpahibernate.repository.CourseRepository;


@SpringBootTest(classes = SpringbootJpaHibernateApplication.class)
class SpringbootJpaHibernateApplicationTests {

	@Autowired
	private CourseRepository repository;

	@Test
	void findById_basic() {
		Course course = repository.findById(10002L);
		assertEquals("Microservices in 20 Steps", course.getName());
	}
}
