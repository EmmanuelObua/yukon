package com.yukon.app.sch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.yukon.app.sch.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
