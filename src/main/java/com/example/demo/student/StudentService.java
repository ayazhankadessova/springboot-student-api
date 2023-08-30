package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.transaction.Transactional;

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

    @Transactional
    public void updateStudent(Long id, String userName, String email) {
        // Optional<Student> student = studentRepository.findById(id);

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(("Student not found for this id :: " + id)));

        if (student.isPresent()) {
            if (userName != null) {
                student.setName(userName);
            }
            if (email != null) {
                student.setEmail(email);
            }
            // Save the updated student record
            studentRepository.save(student);
        }
    }

}
