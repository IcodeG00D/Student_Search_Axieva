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

---

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

---

### Record Volume

The dataset contains approximately **100,000** student records, simulating a **large-scale data** scenario for testing performance, indexing, and in-memory storage efficiency.

---

### Schema / Columns

| Column Name | Data Type | Description                   | Example        |
|-------------|------------|-------------------------------|----------------|
| ID          | Integer    | Unique identifier             | `3`            |
| Name        | String     | Full name of the student      | `Amit Sharma`  |
| Age         | Integer    | Age of the student            | `16`           |
| Class       | String     | Class and section             | `10A`          |
| Grade       | String     | Academic grade                | `A`, `B+`      |

---

# AXIEVA PROJECT  
## In-Memory Student Search - Project Documentation

### 1. Problem Statement  
Design and implement a Spring Boot-based RESTful service that reads a large dataset (in CSV format) containing student details, stores the data in memory, creates an index based on student names to optimize search operations, and exposes APIs to create the index and search for students by name.

### 2. Introduction  
The In-Memory Student Search project is a high-performance search system built using Spring Boot. It loads student data from a CSV file into memory, builds an index for fast lookups, and provides REST APIs to search for students by exact name or prefix. The project demonstrates both linear and index-based searching, with optional multi-threaded CSV parsing.

### 3. Architecture  
The system follows a simple layered architecture:
- **Controller Layer**: Handles HTTP requests and maps them to service methods.
- **Service Layer**: Contains the business logic for loading, indexing, and searching student data.
- **Model Layer**: Defines the Student entity used for data storage.
- **Indexing**: Uses a `ConcurrentHashMap` to store lowercase student names as keys for fast retrieval.
- **Parallel Processing**: Utilizes Java `ForkJoinPool` for multi-threaded CSV parsing.

### 4. Project Structure

student-search-inmemory/
├── src/main/java/com/example/inmemory/controller
├── src/main/java/com/example/inmemory/service
├── src/main/java/com/example/inmemory/model
├── src/main/resources/students.csv
└── pom.xml


### 5. Dataset  
- The dataset is provided as a CSV file (`students.csv`) with the following columns generated using a Python script with the `random` module:
  - `ID`, `Name`, `Age`, `Class`, `Grade`
- Example row: `1, Grace Patel, 16, 10A, A`
- The system can also load CSV files from an **absolute path** provided by the user.

### 6. Algorithms Used for Searching  
- **Linear Search**: Iterates through the entire list of students to find matches (O(n)).
- **Index Search**: Uses a pre-built `ConcurrentHashMap` to look up students by lowercase exact name (O(1)).
- **Prefix Search**: Scans index keys to find matches starting with the given prefix (O(k), where k = number of unique names).

### 7. API Endpoints

1. **POST `/index`** - Build index from CSV file  
   - Params: `csvPath` (optional)  
   - Response: Stats about loading and indexing  

2. **GET `/search`** - Search students by exact name  
   - Params: `name` (required), `mode` (`linear` or `exact`)  
   - Response: List of matching students  

3. **GET `/search-prefix`** - Search students by name prefix  
   - Params: `prefix` (required)  
   - Response: List of matching students  

4. **GET `/debug-index`** - Return the complete index map (for debugging)

### 8. Learnings

- Spring Boot Basics: REST controllers, dependency injection
- Maven: Dependency management and builds
- Multithreading in Java: ForkJoinPool for parallelism
- Data Structures: `ConcurrentHashMap` for thread-safe access
- Algorithm Complexity: Linear vs Indexed searching
- REST API Design: Endpoints, query params, JSON responses
- File I/O in Java: Reading from both classpath and absolute paths
- OpenCSV Library: For parsing CSV data
- Indexing Concepts: How indexing improves search performance

### 9. Conclusion

This project showcases a practical implementation of an **in-memory search engine** using Spring Boot. It highlights the trade-offs between different search algorithms, demonstrates the performance benefits of indexing, and serves as a great learning project for:
- Multithreading
- REST API development
- Java data structures

---

## 🚀 Steps to Use

### 📦 Build the Project

--- bash
mvn clean package
Generates a JAR in target/ (e.g. student-search-inmemory-0.0.1-SNAPSHOT.jar)

▶️ Run the JAR
java -jar target/student-search-inmemory-0.0.1-SNAPSHOT.jar

🌐 Open the App in Browser

Visit: http://localhost:8080
(Full frontend + backend served from same place)

🔧 Use the App

Leave CSV path empty to use the default students.csv in resources.

Or enter a full local file path (e.g. D:\Data\my_students.csv).

**Step A** — Build Index

Open http://localhost:3000

Leave file path empty to use default CSV, or enter full path to your own CSV.

Click "Build Index"

Backend reads, parses CSV, and builds in-memory index (multi-threaded).

See stats like:

Rows: 100000
Unique names: 100
Parse: 54 ms
Index: 33 ms

**Step B** — Search Students

Enter a name or prefix

**Choose mode**:

linear → scans all records

index → uses in-memory map

**Choose match type:**

- exact → name must match exactly

- prefix → name must start with your input

- Click Search

- View results in the table
