package com.example.studentsearch.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "students", indexes = { @Index(name = "idx_student_name", columnList = "name") })
public class Student {
    @Id
    private Long id;
    private String name;
    private Integer age;
    @Column(name = "class_name")
    private String className;
    private String grade;

    public Student() {}
    public Student(Long id, String name, Integer age, String className, String grade) {
        this.id = id; this.name = name; this.age = age; this.className = className; this.grade = grade;
    }
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getName() { return name; } public void setName(String name) { this.name = name; }
    public Integer getAge() { return age; } public void setAge(Integer age) { this.age = age; }
    public String getClassName() { return className; } public void setClassName(String className) { this.className = className; }
    public String getGrade() { return grade; } public void setGrade(String grade) { this.grade = grade; }
}
