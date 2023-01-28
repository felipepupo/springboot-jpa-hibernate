package com.example.springbootjpahibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.Query;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.springbootjpahibernate.entity.Course;
import com.example.springbootjpahibernate.entity.Student;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringbootJpaHibernateApplication.class)
public class JPQLTest {
    
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;

	@Test
	public void jpql_basic() {
		Query query = em.createQuery("Select  c  From Course c");

		List resultList = query.getResultList();
		logger.info("Select  c  From Course c -> {}", resultList);
	}

	@Test
	public void jpql_typed() {
		TypedQuery<Course> query = 
					em.createQuery("Select  c  From Course c", Course.class);

		List<Course> resultList = query.getResultList();	
		logger.info("Select  c  From Course c -> {}", resultList);
	}

	@Test
	public void jpql_where() {
		TypedQuery<Course> query = 
					em.createQuery("Select  c  From Course c where name like '%100 Steps'", Course.class);

		List<Course> resultList = query.getResultList();
		logger.info("Select  c  From Course c where name like '%100 Steps'-> {}", resultList);
	}

	@Test
	public void jpql_courses_without_students() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c where c.students is empty", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
	}

	
	@Test
	public void jpql_courses_with_atleast_2_students() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);

	}

	@Test
	public void jpql_courses_ordered_by_students() {
		TypedQuery<Course> query = em.createQuery("Select c from Course c order by size(c.students) desc", Course.class);
		List<Course> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
	}

	@Test
	public void jpql_students_with_passports_in_a_certain_pattern() {
		TypedQuery<Student> query = em.createQuery("Select s from Student s where s.passport.number like '%1234%'", Student.class);
		List<Student> resultList = query.getResultList();
		logger.info("Results -> {}", resultList);
	}

	@Test
	public void join(){
		Query query = em.createQuery("Select c, s from Course c JOIN c.students s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Results Size -> {}", resultList.size());
		for(Object[] result:resultList){
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}

	@Test
	public void left_join(){
		Query query = em.createQuery("Select c, s from Course c LEFT JOIN c.students s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Results Size -> {}", resultList.size());
		for(Object[] result:resultList){
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}

	@Test
	public void cross_join(){
		Query query = em.createQuery("Select c, s from Course c, Student s");
		List<Object[]> resultList = query.getResultList();
		logger.info("Results Size -> {}", resultList.size());
		for(Object[] result:resultList){
			logger.info("Course{} Student{}", result[0], result[1]);
		}
	}
}
