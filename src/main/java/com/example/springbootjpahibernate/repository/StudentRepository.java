package com.example.springbootjpahibernate.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.springbootjpahibernate.entity.Passport;
import com.example.springbootjpahibernate.entity.Student;

@Repository
public class StudentRepository {
    
    @Autowired
	EntityManager em;

    public Student findById(Long id) {
		return em.find(Student.class, id);
	}

	public Student save(Student student) {

		if (student.getId() == null) {
			em.persist(student);
		} else {
			em.merge(student);
		}

		return student;
	}

	public void deleteById(Long id) {
		Student student = findById(id);
		em.remove(student);
	}

	public void saveStudentWithPassport() {
		Passport passport = new Passport("Z123456");
		em.persist(passport);

		Student student = new Student("Mike");
		student.setPassport(passport);
		em.persist(student);	
	}
	
	public void someOperationToUnderstandPersistenceContext() {
		//Database Operation 1 - Retrieve student
		Student student = em.find(Student.class, 20001L);
		
		//Database Operation 2 - Retrieve passport
		Passport passport = student.getPassport();

		//Database Operation 3 - update passport
		passport.setNumber("E123457");
		
		//Database Operation 4 - update student
		student.setName("Ranga - updated");
	}
}
