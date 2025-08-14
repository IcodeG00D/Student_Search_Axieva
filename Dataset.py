import csv
import random

first_names = ["Alice", "Bob", "Charlie", "David", "Eva", "Frank", "Grace", "Hannah", "Ivan", "Jack"]
last_names = ["Sharma", "Verma", "Khan", "Patel", "Singh", "Roy", "Das", "Nair", "Iyer", "Mehta"]
classes = ["10A", "10B", "11A", "11B", "12A", "12B"]
grades = ["A", "B", "C", "D", "E"]

with open("students.csv", mode="w", newline="") as file:
    writer = csv.writer(file)
    writer.writerow(["ID", "Name", "Age", "Class", "Grade"])
    
    for i in range(1, 100001):  # one lakh records
        name = f"{random.choice(first_names)} {random.choice(last_names)}"
        age = random.randint(14, 18)
        class_name = random.choice(classes)
        grade = random.choice(grades)
        writer.writerow([str(i), name, age, class_name, grade])
