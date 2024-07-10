# Student Management System

## Overview

The Student Management System is a Java-based console application designed to manage student records, including registration, deletion, and retrieval of student information. It also supports storing and loading student details from a file, as well as sorting and viewing students based on various criteria.

## Usage

1. **Run the Application**:
   Execute the `StudentManagementSystem` class. The main menu will be displayed.

2. **Main Menu Options**:
   - **Check available seats**: Displays the number of available seats for new registrations.
   - **Register student**: Prompts for student ID and name to register a new student.
   - **Delete student**: Prompts for student ID to delete an existing student.
   - **Find student**: Prompts for student ID to find and display student details.
   - **Store student details into a file**: Saves current student details to `studentDetails.txt`.
   - **Load student details from the file**: Loads student details from `studentDetails.txt`.
   - **View the list of students based on their names**: Displays a list of students sorted by name.
   - **Extra controls**:
     - **Add student name**: Adds or updates a student's name.
     - **Add module marks**: Adds or updates module marks for a student.
     - **Generate summary**: Generates a summary of student performance.
     - **Generate complete report**: Generates a complete report of student details (Student ID, Student Name, Module 1 marks, Module 2 marks, Module 3 marks, Total, Average, Grade).
   - **Exit**: Exits the application.

## Data Format

Student details are stored in a CSV format in `studentDetails.txt`:
```
ID,Name,Module1Marks,Module2Marks,Module3Marks,Grade
```
