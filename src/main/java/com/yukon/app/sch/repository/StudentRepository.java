package com.yukon.app.sch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.yukon.app.sch.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
