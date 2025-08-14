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

## Getting Started

> Add this section to provide instructions on how to run and test the application.

Example:

```bash
# Clone the repository
git clone https://github.com/yourusername/axieva-assignment.git
cd axieva-assignment

# Build and run the application
./mvnw spring-boot:run
