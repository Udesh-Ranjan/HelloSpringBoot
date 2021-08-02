package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository) {
        return args -> {
            Student dev = new Student(
//                    1l,
                    "DevParzival",
                    "devparzival@net.com",
                    LocalDate.of(1999, 02, 02));
            Student herb = new Student(
//                    2l,
                    "herbtSchildt",
                    "herb@net.com",
                    LocalDate.of(1990, 1, 2));
            studentRepository.saveAll(List.of(dev, herb));

        };
    }
}
