package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    // annotate constructor with auto-wired
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent()) {
            throw new IllegalStateException("This email is taken.");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {

        boolean exists = studentRepository.existsById(id);

        if (!exists) {
            throw new IllegalStateException("Student with id " + id + " does not exist.");
        }

        studentRepository.deleteById(id);
    }
}
