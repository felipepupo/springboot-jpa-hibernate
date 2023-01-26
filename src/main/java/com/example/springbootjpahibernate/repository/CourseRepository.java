package com.example.springbootjpahibernate.repository;

import java.sql.Array;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springbootjpahibernate.entity.Course;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;


@Repository
@Transactional
public class CourseRepository {
    @Autowired
    EntityManager em;

    public Course findById(Long id){
        return em.find(Course.class, id);
    }

    public Course save(Course course){
        if(course.getId() == null)
            em.persist(course);
        else
            em.merge(course);

        return course;
    }

    public void deleteById(Long id){
        Course course = findById(id);
        em.remove(course);
    }

    public List<Course> findAllUsingNativeQuerie(){
        Query query = em.createNativeQuery("SELECT * FROM COURSE_DETAILS", Course.class);
        return query.getResultList();
    }

    public List<Course> findByIdUsingNativeQuerie(Long id){
        Query query = em.createNativeQuery("SELECT * FROM COURSE_DETAILS WHERE id = ?", Course.class);
        query.setParameter(1, id);

        return query.getResultList();
    }
}
