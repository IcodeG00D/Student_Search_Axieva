package com.example.studentsearch;

import com.example.studentsearch.entity.Student;
import com.example.studentsearch.repository.StudentRepository;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvAutoLoader implements CommandLineRunner {

    private final StudentRepository repo;

    @Value("${app.csv.filename:students.csv}")
    private String csvFileName;

    public CsvAutoLoader(StudentRepository repo) { this.repo = repo; }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (repo.count() > 0) {
            System.out.println("Students already present. Skipping CSV import.");
            return;
        }
        ClassLoader cl = getClass().getClassLoader();
        InputStream is = cl.getResourceAsStream(csvFileName);
        if (is == null) {
            System.out.println("CSV file not found in resources: " + csvFileName + ". Skipping import.");
            return;
        }
        try (CSVReader reader = new CSVReader(new InputStreamReader(is))) {
            String[] row;
            boolean header = true;
            List<Student> batch = new ArrayList<>();
            int batchSize = 2000;
            long count = 0;
            while ((row = reader.readNext()) != null) {
                if (header) { header = false; continue; }
                if (row.length < 5) continue;
                Long id = Long.parseLong(row[0].trim());
                String name = row[1].trim();
                Integer age = Integer.parseInt(row[2].trim());
                String className = row[3].trim();
                String grade = row[4].trim();
                batch.add(new Student(id, name, age, className, grade));
                if (batch.size() >= batchSize) { repo.saveAll(batch); count += batch.size(); batch.clear(); }
            }
            if (!batch.isEmpty()) { repo.saveAll(batch); count += batch.size(); }
            System.out.println("CSV import completed. Rows inserted: " + count);
        }
    }
}
