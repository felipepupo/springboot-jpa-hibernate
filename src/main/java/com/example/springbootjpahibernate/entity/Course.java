package com.example.springbootjpahibernate.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
//@Table(name = "CourseDetails")
@NamedQueries(
    value = {
        @NamedQuery(name = "query_get_all_couses", query = "Select c From Course c")
    }
)
public class Course {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @UpdateTimestamp
    private LocalDateTime lastUpdatedDate;
    
    @CreationTimestamp
    private LocalDateTime CreatedDate;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private List<Review> reviews  = new ArrayList<>();

    @ManyToMany(mappedBy="courses")
	private List<Student> students = new ArrayList<>();

    protected Course(){

    }

    public Course(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview (Review review) {
        this.reviews.add(review);
    }

    public void removeReview (Review review) {
        this.reviews.remove(review);
    }

    public List<Student> getStudents() {
		return students;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

    @Override
    public String toString() {
        return "Course [id=" + id + ", name=" + name + ", lastUpadtedDate=" + lastUpdatedDate + ", CreatedDate=" + CreatedDate + "]";
    }
}
