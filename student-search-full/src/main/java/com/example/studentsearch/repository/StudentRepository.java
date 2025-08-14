package com.example.studentsearch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.studentsearch.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :q, '%'))")
    List<Student> searchContains(@Param("q") String q);
    @Query("SELECT s FROM Student s WHERE LOWER(s.name) LIKE LOWER(CONCAT(:prefix, '%'))")
    List<Student> searchPrefix(@Param("prefix") String prefix);
    @Query("SELECT s FROM Student s WHERE LOWER(s.name) = LOWER(:name)")
    List<Student> searchExact(@Param("name") String name);
}
