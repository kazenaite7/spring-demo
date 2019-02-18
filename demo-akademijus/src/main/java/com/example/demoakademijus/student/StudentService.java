package com.example.demoakademijus.student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student getStudent(Long id) throws StudentNotFoundException;

    List<Student> getStudents();

    Student createStudent(Student student);
    Student updateStudent(Student student, long id);

    void deleteById(long id);

    String getMessage();
    Optional<Student> getStudentById(Long id);
}
