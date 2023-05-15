package com.yukon.app.sch.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.yukon.app.sch.model.Student;
import com.yukon.app.sch.model.Teacher;
import com.yukon.app.sch.repository.StudentRepository;
import com.yukon.app.sch.repository.TeacherRepository;

@RestController
@RequestMapping("/api")
public class StudentTeacherController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    // GET requests

    @GetMapping("/students")
    public ResponseEntity<?> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return new ResponseEntity(students, HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable(value = "id") Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));
    }

    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @GetMapping("/teachers/{id}")
    public Teacher getTeacherById(@PathVariable(value = "id") Long teacherId) {
        return teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found"));
    }

    // POST requests

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @PostMapping("/teachers")
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    // PUT requests

    @PutMapping("/students/{id}")
    public Student updateStudent(@PathVariable(value = "id") Long studentId, @RequestBody Student studentDetails) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        student.setName(studentDetails.getName());
        student.setSurname(studentDetails.getSurname());

        return studentRepository.save(student);
    }

    @PutMapping("/teachers/{id}")
    public Teacher updateTeacher(@PathVariable(value = "id") Long teacherId, @RequestBody Teacher teacherDetails) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found"));

        teacher.setName(teacherDetails.getName());
        // teacher.setStudentList(teacherDetails.getStudentList());

        return teacherRepository.save(teacher);
    }

    // DELETE requests

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable(value = "id") Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));

        studentRepository.delete(student);
    }

    @DeleteMapping("/teachers/{id}")
    public void deleteTeacher(@PathVariable(value = "id") Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found"));

        teacherRepository.delete(teacher);
    }

}
