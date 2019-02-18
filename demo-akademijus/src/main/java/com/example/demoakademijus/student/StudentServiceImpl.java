package com.example.demoakademijus.student;

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
        Optional<Student> student = repo.findById(id);
        if (student.isPresent()) {
            return student.get();
        } else {
            throw new StudentNotFoundException();
        }
    }

    public Optional<Student> getStudentById(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<Student> getStudents() {
        logger.debug("Inside of getStudents method");
        List<Student> allStudentList = new ArrayList<>();
        for (Student student : repo.findAll()) {
            allStudentList.add(student);
        }
        return allStudentList;
    }

    public Student createStudent(Student student) {
        return (Student) repo.save(student);
    }

    public String getMessage() {
        logger.debug("Inside getMessage()");
        return ("working...");
    }

    public void deleteById(long id) {
        repo.deleteById(id);
    }

}
