package com.yukon.app.sch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.yukon.app.sch.model.Student;
import com.yukon.app.sch.model.Teacher;
import com.yukon.app.sch.repository.StudentRepository;
import com.yukon.app.sch.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class StudentTeacherController {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	// GET requests

	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> students = studentRepository.findAll();
		if (students.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(students);
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable(value = "id") Long studentId) {
		Optional<Student> student = studentRepository.findById(studentId);
		
		if(student.isPresent()) {
			return ResponseEntity.ok(student.get());
		}
		
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/teachers")
	public ResponseEntity<List>  getAllTeachers() {
		List<Teacher> teachers = teacherRepository.findAll();

		if (teachers.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(teachers);
	}

	@GetMapping("/teachers/{id}")
	public ResponseEntity<?> getTeacherById(@PathVariable(value = "id") Long teacherId) {
		Optional<Teacher> teacher = teacherRepository.findById(teacherId);

		if (teacher.isPresent()) {
			return ResponseEntity.ok(teacher.get());
		}

		return ResponseEntity.noContent().build();
	}

	// POST requests

	@PostMapping("/students")
	public ResponseEntity<?> createStudent(@RequestBody Student student) {
		Student savedStudent = studentRepository.save(student);
		return ResponseEntity.ok().body(savedStudent);
	}

	@PostMapping("/teachers")
	public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
		List<Student> students = new ArrayList<>();
		if (teacher.getStudentIds() != null) {
			for (Long studentId : teacher.getStudentIds()) {
				Optional<Student> optionalStudent = studentRepository.findById(studentId);
				if (optionalStudent.isPresent()) {
					students.add(optionalStudent.get());
				}
			}
		}
		teacher.setStudents(students);
		Teacher savedTeacher = teacherRepository.save(teacher);

		for (Student student : students) {
			student.setTeacher(savedTeacher);
			studentRepository.save(student);
		}

		return ResponseEntity.ok(savedTeacher);
	}

	// PUT requests

	@PutMapping("/students/{id}")
	public ResponseEntity<?> updateStudent(@PathVariable(value = "id") Long studentId, @RequestBody Student studentDetails) {
		Student student = studentRepository.findById(studentId)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

		student.setName(studentDetails.getName());
		student.setSurname(studentDetails.getSurname());

		Student updated = studentRepository.save(student);

		return ResponseEntity.ok(updated);
	}

	@PutMapping("/teachers/{id}")
	public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacher) {
		Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
		if (optionalTeacher.isPresent()) {
			List<Student> students = new ArrayList<>();
			if (teacher.getStudentIds() != null) {
				for (Long studentId : teacher.getStudentIds()) {
					Optional<Student> optionalStudent = studentRepository.findById(studentId);
					if (optionalStudent.isPresent()) {
						students.add(optionalStudent.get());
					}
				}
			}
			Teacher existingTeacher = optionalTeacher.get();
			existingTeacher.setName(teacher.getName());
			existingTeacher.setSurname(teacher.getSurname());
			existingTeacher.setStudents(students);
			Teacher savedTeacher = teacherRepository.save(existingTeacher);

			for (Student student : students) {
				student.setTeacher(savedTeacher);
				studentRepository.save(student);
			}

			return ResponseEntity.ok(savedTeacher);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// DELETE requests

	@DeleteMapping("/students/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable(value = "id") Long studentId) {
		Student student = studentRepository.findById(studentId)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

		studentRepository.delete(student);

		return ResponseEntity.ok().build();
	}


	@DeleteMapping("/teachers/{id}")
	public ResponseEntity<?> deleteTeacher(@PathVariable(value = "id") Long teacherId) {
		Teacher teacher = teacherRepository.findById(teacherId)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found"));

		teacherRepository.delete(teacher);

		return ResponseEntity.ok().build();
	}

}
