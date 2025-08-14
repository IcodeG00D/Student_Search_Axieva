package com.example.studentsearch.controller;

import com.example.studentsearch.entity.Student;
import com.example.studentsearch.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentApiController {
    private final StudentRepository repo;
    public StudentApiController(StudentRepository repo) { this.repo = repo; }

    @GetMapping("/search/prefix")
    public List<Student> searchPrefix(@RequestParam("q") String q) { return repo.searchPrefix(q.trim()); }

    @GetMapping("/search/exact")
    public List<Student> searchExact(@RequestParam("name") String name) { return repo.searchExact(name.trim()); }

    @GetMapping("/search/contains")
    public List<Student> searchContains(@RequestParam("q") String q) { return repo.searchContains(q.trim()); }

    @GetMapping("/all")
    public List<Student> all() { return repo.findAll(); }
}
