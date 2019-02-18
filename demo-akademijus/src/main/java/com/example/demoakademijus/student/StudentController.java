package com.example.demoakademijus.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/")
    public String getIndex() {
        return "The quick brown fox";
    }

    @GetMapping(value = "/students")
    public List<Student> getStudents() {
        return this.studentService.getStudents();
    }

    @PostMapping(value = "/createStudent")
    public Student createStudent(@RequestBody Student student) {
        return this.studentService.createStudent(student);
    }

    @GetMapping(value = "/message")
    public String getMessage() {
        return this.studentService.getMessage();
    }

    @GetMapping(value = "/student/{id}")
    public Student getStudent(@PathVariable("id") long id) {
        try {
            return studentService.getStudent(id);
        } catch (StudentNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found", ex);
        }
    }

    @DeleteMapping(value = "/student/{id}")
    public void deleteStudent(@PathVariable long id) {
        studentService.deleteById(id);
    }

    @PostMapping(value = "/createStudents")
    public ResponseEntity<Object> createStudentWithReturn(@RequestBody Student student) {
        Student savedStudent = studentService.createStudent(student);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedStudent.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/updateStudent/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long id) {
        return  studentService.getStudentById(id)
                .map(s -> studentService.updateStudent(student, id))
                .map(s -> ResponseEntity.noContent().build())
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    //@RequestMapping(path = "/student", method = HttpMethod.GET)
}
