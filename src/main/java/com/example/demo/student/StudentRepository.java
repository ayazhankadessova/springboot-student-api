package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// type of repo we want to work with
// id
// this interface is responsible for data access
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
