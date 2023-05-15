package com.yukon.app.sch;

// import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.yukon.app.sch.repository.StudentRepository;
import com.yukon.app.sch.model.Student;

@DataJpaTest
public class SchApplicationTests {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	public void testFindAll() {
    // given
		Student student1 = new Student("John", "Doe");
		Student student2 = new Student("Jane", "Doe");
		studentRepository.save(student1);
		studentRepository.save(student2);

    // when
		List<Student> students = studentRepository.findAll();

    // then
		assertThat(students).hasSize(2);
		assertThat(students).contains(student1, student2);
	}
}
