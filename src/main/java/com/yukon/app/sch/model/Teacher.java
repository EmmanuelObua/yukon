package com.yukon.app.sch.model;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import com.yukon.app.sch.model.Student;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "teachers")
public class Teacher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL)
	private List<Student> students;
	
	public List<Student> getStudents() {
		return students;
	}

	public List<Long> getStudentIds() {
		List<Long> studentIds = new ArrayList<>();
		for (Student student : students) {
			studentIds.add(student.getId());
		}
		return studentIds;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public void setStudentIds(List<Long> studentIds) {
		List<Student> students = new ArrayList<>();
		for (Long studentId : studentIds) {
			Student student = new Student();
			student.setId(studentId);
			students.add(student);
		}
		this.students = students;
	}

}
