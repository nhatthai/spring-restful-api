package restapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import restapi.model.Student;

@Component
public class StudentService {

	private static List<Student> students = new ArrayList<>();

	static {
		//Initialize Data
		Student student1 = new Student("1", "Nhat Thai",
				"Programmer and Architect");

		Student student2 = new Student("2", "Quang Nhat",
				"Business, Programmer and Architect");

		students.add(student1);
		students.add(student2);
	}

	public List<Student> retrieveStudents() {
		return students;
    }

    public Student retrieveStudent(String studentId) {
        for (Student student : students) {
			if (student.getId().equals(studentId)) {
				return student;
			}
		}
		return null;
    }
}