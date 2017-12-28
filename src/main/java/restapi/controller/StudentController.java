package restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import restapi.model.Student;
import restapi.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentService studentService = new StudentService();

	@GetMapping("/students/{studentId}")
	public Student retrieveStudent(@PathVariable String studentId) {
		return studentService.retrieveStudent(studentId);
	}

	@GetMapping("/students")
	public List<Student> getStudents() {
		return studentService.retrieveStudents();
	}

}