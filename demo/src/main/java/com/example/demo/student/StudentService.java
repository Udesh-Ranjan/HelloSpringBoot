package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(final StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        /*return List.of(new Student(1l,
                "DevParzival",
                "devparzival@net.com",
                LocalDate.of(2005, 12, 25),
                26));*/
        return studentRepository.findAll();
    }

    public void addNewStudent(final Student student) {
        if (studentRepository.findStudentByEmail(student.getEmail()).isPresent())
            throw new IllegalStateException("Email taken");
        studentRepository.save(student);
    }

    public void deleteStudent(final Long id) {
        if (!studentRepository.existsById(id))
            throw new IllegalStateException("student with id " + id + " doesn't exists");
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(final Long studentId, final String name, final String email) {
        final Student student = studentRepository.findById(studentId).
                orElseThrow(() -> new IllegalStateException("student with id \" + id + \" doesn't exists"));
        if (name != null && name.length() != 0)
            student.setName(name);
        if (email != null && email.length() > 0) {
            if (studentRepository.findStudentByEmail(email).isPresent())
                throw new IllegalStateException("Email Taken");
            student.setEmail(email);
        }


    }

}
