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
}
