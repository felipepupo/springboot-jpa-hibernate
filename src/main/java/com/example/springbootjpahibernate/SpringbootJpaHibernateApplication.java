package com.example.springbootjpahibernate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootJpaHibernateApplication implements CommandLineRunner{

	@Override
	public void run(String... args) throws Exception {
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaHibernateApplication.class, args);
	}

}
