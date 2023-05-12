package com.yukon.app.sch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.studentteacherapi.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
