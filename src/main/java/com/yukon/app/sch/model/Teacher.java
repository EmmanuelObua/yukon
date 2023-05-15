package com.yukon.app.sch.model;

import java.util.List;
// import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teachers")
public class Teacher {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // @ElementCollection
    // private List<String> studentList;

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

    // public List<String> getStudentList() {
    //     return studentList;
    // }

    // public void setStudentList(List<String> studentList) {
    //     this.studentList = studentList;
    // }

}
