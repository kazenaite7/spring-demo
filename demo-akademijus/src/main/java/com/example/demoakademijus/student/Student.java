package com.example.demoakademijus.student;

import com.example.demoakademijus.address.Address;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Student")
public class Student implements Serializable {
    @Id
    private Long id;
    private String name;
    private String studySubject;
    private Double gradeAverage;

    @OneToOne
    @JoinColumn(name = "address_id")
    @RestResource(path = "studentAddress", rel = "address")
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudySubject() {
        return studySubject;
    }

    public void setStudySubject(String studySubject) {
        this.studySubject = studySubject;
    }

    public Double getGradeAverage() {
        return gradeAverage;
    }

    public void setGradeAverage(Double gradeAverage) {
        this.gradeAverage = gradeAverage;
    }
}
