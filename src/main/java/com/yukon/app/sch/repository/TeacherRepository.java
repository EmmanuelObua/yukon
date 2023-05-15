package com.yukon.app.sch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yukon.app.sch.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
