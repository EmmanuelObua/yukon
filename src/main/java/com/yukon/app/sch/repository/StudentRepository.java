package com.yukon.app.sch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.studentteacherapi.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
