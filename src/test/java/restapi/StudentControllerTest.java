package restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.http.ResponseEntity;

import restapi.model.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @Autowired
    private TestRestTemplate restTestTemplate;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception{
        objectMapper = new ObjectMapper();
    }

    @After
    public void tearDown() throws Exception{
        objectMapper = null;
    }

    @LocalServerPort
    private int port;

	@Test
	public void testGetStudent() {
        // get string
        String studentString = restTestTemplate.getForObject(
                "http://localhost:" + port + "/students/1", String.class);
        System.out.println("Get student by id");
        System.out.println(studentString);

        // Jackson ObjectMapper
        try {
            Student student = objectMapper.readValue(studentString, Student.class);
            System.out.println("Jackson ObjectMapper");
            System.out.println(student);
            assertEquals(student.getName(), "Ranga Karanam");
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e);
        }

        // Using Gson, convert to Json
        System.out.println("Convert Gson");
        Gson gson = new Gson();
        Student convertStudent = gson.fromJson(studentString, Student.class);
        assertEquals(convertStudent.getName(), "Ranga Karanam");

        // RestTestTemplate Convert obj
        System.out.println("RestTestTemplate Convert obj");
        Student studentObj = restTestTemplate.getForObject(
                "http://localhost:" + port + "/students/1", Student.class);
        System.out.println(studentObj);
        assertEquals(studentObj.getName(), "Ranga Karanam");

        // ResponseEntity
        System.out.println("RestTestTemplate ResponseEntity");
        ResponseEntity<Student> responseEntity = restTestTemplate.getForEntity("/students/1", Student.class);
        Student studentEntity = responseEntity.getBody();
        System.out.println(studentEntity);
        assertEquals(studentEntity.getName(), "Ranga Karanam");
    }
}