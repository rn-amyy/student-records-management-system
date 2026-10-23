# Student Records Management System

A Java-based Student Records Management System that uses MySQL to store and manage student information.

## Features

- Add student records
- View all student records
- Search for a student by matriculation number
- Update a student's email address
- Delete student records
- Validate user input for required fields, CGPA, and academic level
- Handle duplicate matriculation and registration numbers

## Technologies Used

- Java
- MySQL
- JDBC (Java Database Connectivity)
- IntelliJ IDEA

## Student Information Stored

The system stores student information such as:

- Full name
- Department
- Email address
- Gender
- Matriculation number
- Registration number
- CGPA
- Academic level

## Database Setup

1. Install MySQL Server.
2. Create a database named `student_records`.
3. Create a table named `students` using the required schema.
4. Configure the database connection in the project.

## Database Credentials

The application reads the MySQL password from an environment variable named `DB_PASSWORD`. Do not hardcode database passwords or publish them in the repository.

Configure `DB_PASSWORD` in your IDE's run configuration before running the application.

## Running the Project

1. Open the project in IntelliJ IDEA.
2. Ensure MySQL Server is running.
3. Ensure the `student_records` database and `students` table exist.
4. Add the MySQL Connector/J library to the project if it is not already configured.
5. Set the `DB_PASSWORD` environment variable in the run configuration.
6. Run `Main.java`.

## Project Status

This project was built as a hands-on learning project to practise Java programming, JDBC, MySQL database operations, and CRUD functionality.