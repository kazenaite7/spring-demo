package com.example.demoakademijus.student;

import com.example.demoakademijus.address.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StudentRepository repo;

    public Student getStudent(Long id) throws StudentNotFoundException {
        return repo.findById(id)
                .orElseThrow(StudentNotFoundException::new);
    }

    public Student getStudent__(Long id) throws StudentNotFoundException {
        return repo.findById(id)
                .orElseGet(this::getNewStudent);
    }

    private Student getNewStudent() {
        logger.info("getNewStudent ...");
        Address address = new Address(777L, "Gedimino 11", null);
        Student newStudent = new Student(666L, "Jonas", "filologija", 5.5, address);
        address.setStudent(newStudent);
        return newStudent;
    }

    public Optional<Student> getStudentById(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<Student> getStudents() {
        logger.debug("Inside of getStudents method");
        return new ArrayList<>(repo.findAll());
    }

    public Student createStudent(Student student) {
        return repo.save(student);
    }

    public Student updateStudent(Student student, long id) {
        student.setId(id);
        return repo.save(student);
    }

    public String getMessage() {
        logger.debug("Inside getMessage()");
        return ("working...");
    }

    public void deleteById(long id) {
        repo.deleteById(id);
    }

}
