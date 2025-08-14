Thanks for sharing the full draft! I've gone through your README and cleaned up formatting, fixed section headers, improved Markdown structure, and ensured code blocks render correctly. Here's your polished, GitHub-ready version:

---

````markdown
# Axieva Assignment

## Objective

Design and implement a **Spring Boot-based RESTful service** that:

- Reads a large dataset (CSV format) containing student details.
- Stores the data **in memory**.
- Creates an **index based on student names** to optimize search operations.
- Exposes APIs to **create the index** and **search for students by name**.

---

## Dataset

- **Format:** CSV file  
- **Record Count:** Approximately **100,000** student records  
- **Columns (5 fields):**
  - `ID` (String)
  - `Name` (String)
  - `Age` (Integer)
  - `Class` (String)
  - `Grade` (String)

---

## Functional Requirements

### 1. Create Index API

- **Method:** `POST`  
- **Endpoint:** `/index`  
- **Description:**
  - Parses the CSV file.
  - Stores student records in memory.
  - Creates an in-memory index on the `Name` field using a suitable data structure:
    ```java
    Map<String, List<Student>>
    ```
  - Parsing and indexing should be performed using **multithreading** for better performance.

### 2. Search API

- **Method:** `GET`  
- **Endpoint:** `/search`  
- **Query Parameters:**
  - `name` (String)
  - `mode` (optional, `linear` or `index`; default is `index`)  
- **Description:**
  - Searches for student records by name.
  - Supports two search modes:
    - **linear**: Performs a linear search through all records.
    - **index**: Uses the in-memory index for optimized search.
  - Returns a list of matching student records.

---

## Technical Requirements

- Use **Java** and **Spring Boot** framework.
- No use of external databases — all data should reside **in memory**.
- Utilize **multithreading** during CSV parsing to improve performance.
- Maintain proper **separation of concerns** (`Controller`, `Service`, `Model` layers).
- **Optional:** Log or compare performance between linear search and index-based search.

---

## Deliverables

- Complete **Spring Boot project** (source code).
- **Sample CSV file** (or support for uploading/reading file path).
- **API documentation** (endpoints and usage).
- **README** with steps to run and test the application.

---

## Evaluation Criteria

- **Correctness** and **completeness** of the implementation.
- **Code structure** and **clarity**.
- Use of appropriate **data structures** for indexing.
- Effective use of **multithreading**.
- **REST API design** following best practices.

---

## Project Documentation

### About Dataset

- Dataset is generated using a **Python script** utilizing the `random` module.

### Source Format

- **File Type:** CSV (Comma-Separated Values)
- **Filename (example):** `students.csv`
- **Location:**  
  - Placed in `src/main/resources/`  
  - Or loaded via **absolute file path** or **file upload**

### Record Volume

The dataset contains approximately **100,000** student records, simulating a **large-scale data** scenario for testing performance, indexing, and in-memory storage efficiency.

---

### Schema / Columns

| Column Name | Data Type | Description                   | Example        |
|-------------|-----------|-------------------------------|----------------|
| ID          | Integer   | Unique identifier             | `3`            |
| Name        | String    | Full name of the student      | `Amit Sharma`  |
| Age         | Integer   | Age of the student            | `16`           |
| Class       | String    | Class and section             | `10A`          |
| Grade       | String    | Academic grade                | `A`, `B+`      |

---

## Architecture

The system follows a simple layered architecture:

- **Controller Layer**: Handles HTTP requests and maps them to service methods.
- **Service Layer**: Contains the business logic for loading, indexing, and searching student data.
- **Model Layer**: Defines the `Student` entity used for data storage.
- **Indexing**: Uses a `ConcurrentHashMap` to store lowercase student names as keys for fast retrieval.
- **Parallel Processing**: Utilizes Java `ForkJoinPool` for multi-threaded CSV parsing.

---

## Project Structure

````

student-search-inmemory/
├── src/main/java/com/example/inmemory/controller
├── src/main/java/com/example/inmemory/service
├── src/main/java/com/example/inmemory/model
├── src/main/resources/students.csv
└── pom.xml

````

---

## Algorithms Used for Searching

- **Linear Search**: Iterates through the entire list of students to find matches (O(n)).
- **Index Search**: Uses a pre-built `ConcurrentHashMap` to look up students by lowercase exact name (O(1)).
- **Prefix Search**: Scans index keys to find matches starting with the given prefix (O(k), where k = number of unique names).

---

## API Endpoints

1. **POST `/index`** - Build index from CSV file  
   - Params: `csvPath` (optional)  
   - Response: Stats about loading and indexing  

2. **GET `/search`** - Search students by exact name  
   - Params: `name` (required), `mode` (`linear` or `index`)  
   - Response: List of matching students  

3. **GET `/search-prefix`** - Search students by name prefix  
   - Params: `prefix` (required)  
   - Response: List of matching students  

4. **GET `/debug-index`** - Return the complete index map (for debugging)

---

## Learnings

- Spring Boot Basics: REST controllers, dependency injection
- Maven: Dependency management and builds
- Multithreading in Java: `ForkJoinPool` for parallelism
- Data Structures: `ConcurrentHashMap` for thread-safe access
- Algorithm Complexity: Linear vs Indexed searching
- REST API Design: Endpoints, query params, JSON responses
- File I/O in Java: Reading from both classpath and absolute paths
- OpenCSV Library: For parsing CSV data
- Indexing Concepts: How indexing improves search performance

---

## Conclusion

This project showcases a practical implementation of an **in-memory search engine** using Spring Boot. It highlights the trade-offs between different search algorithms, demonstrates the performance benefits of indexing, and serves as a great learning project for:

- Multithreading
- REST API development
- Java data structures

---

## 🚀 Steps to Use

### 📦 Build the Project

```bash
mvn clean package
````

Generates a JAR in `target/` (e.g. `student-search-inmemory-0.0.1-SNAPSHOT.jar`)

---

### ▶️ Run the JAR

```bash
java -jar target/student-search-inmemory-0.0.1-SNAPSHOT.jar
```

---

### 🌐 Open the App in Browser

Visit: [http://localhost:8080](http://localhost:8080)
(Full frontend + backend served from the same place)

---

### 🔧 Use the App

* **Default behavior**: If no CSV path is provided, it uses the `students.csv` file in the `resources` directory.
* **Custom CSV**: You can input an **absolute file path** to use your own CSV file.

---

## 🧪 Instructions

### **Step A** — Build Index

1. Open [http://localhost:3000](http://localhost:3000)
2. Optionally enter a full file path (e.g., `D:\Data\my_students.csv`)
3. Click **"Build Index"**
4. Backend reads and parses the CSV, then builds the in-memory index (multi-threaded)

Sample stats:

```
Rows: 100000
Unique names: 100
Parse: 54 ms
Index: 33 ms
```

---

### **Step B** — Search Students

1. Enter a **name** or **prefix**
2. Choose search mode:

   * `linear`: scans all records
   * `index`: uses the in-memory map
3. Choose match type:

   * `exact`: matches full name
   * `prefix`: matches name starting with prefix
4. Click **Search**
5. Results will be shown in a table

---

## ✅ Done

Your project is now fully documented and GitHub-ready.
Let me know if you'd like a `.gitignore`, `.gitattributes`, or Swagger/OpenAPI docs added to the project.

```

---

Let me know if you'd like me to generate this as a downloadable `README.md` file or help you push it to GitHub.
```
