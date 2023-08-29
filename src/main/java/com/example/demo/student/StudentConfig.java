package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student Mariyam = new Student("Mariam", "mariam.jamal@gmail.com", LocalDate.of(2000, Month.JANUARY, 23),
                    21);

            Student Alex = new Student("Alex", "alex@gmail.com", LocalDate.of(2000, Month.JANUARY, 23),
                    21);

            repository.saveAll(Mariyam, Alex);
        };
    }

}
