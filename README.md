# Customer_Scheduling_Application
Software allows user to schedule and manage appointments and customers

### The WGU C195 Software II Project 
The project emphasizes the application of key software engineering principles, object-oriented programming (OOP) concepts, and best practices learned throughout the course. Below are the main requirements of the project:

1. Core Functionality:
Database Integration: Implement a simple database system (using SQLite, MySQL, or similar) to store data persistently. This could include records like user information, product details, or transaction logs.
CRUD Operations: Implement full Create, Read, Update, Delete (CRUD) functionality for the database, allowing users to interact with data in a meaningful way. This requires using Java to handle database connections, perform queries, and manipulate records.

2. GUI Development: Build a graphical user interface (GUI) using JavaFX or Swing that allows users to interact with the application. The interface should allow users to perform basic operations (e.g., entering data, displaying records, and editing information).

3. Object-Oriented Design:
Classes and Objects: Apply OOP principles by designing and implementing classes that represent entities in the system (e.g., User, Product, Transaction). These should include appropriate attributes, methods, and constructors.
Inheritance: Use inheritance to create a class hierarchy where appropriate. For example, a base Person class can be extended by a Customer class or an Employee class.
Polymorphism and Encapsulation: Use polymorphism (overriding methods) and encapsulation (private fields, public getter/setter methods) to organize code efficiently and provide flexibility.

4. Event-Driven Programming:
Event Handlers: Implement event-driven programming by using listeners and handlers in the GUI. For instance, when a user clicks a button, it should trigger an action (like saving data to the database or fetching records).

5. Exception Handling:
Implement robust exception handling throughout the application to manage errors such as invalid user input, database connection issues, or incorrect file access.

6. Input Validation:
Perform input validation to ensure users enter data correctly, providing appropriate feedback when invalid data is entered. For example, fields requiring numeric input should only accept numbers.

7. Testing:
Unit Testing: Write unit tests using JUnit or a similar framework to ensure that the core functionality (e.g., CRUD operations, business logic) is working correctly.
Integration Testing: Test how components interact with each other, including the database and GUI.

8. Project Documentation:
Provide comprehensive documentation, including design decisions, code comments, and a user guide. This helps others understand how the system works and how to use the application.

9. Deployment and Maintenance:
Prepare the project for deployment, ensuring the application runs correctly in the target environment. This might involve packaging the application into a JAR file or preparing for installation.

Example Use Cases:
User Management System: A system that allows users to sign up, log in, update their profiles, and manage their information.
Inventory Management System: A system that tracks products, including adding, editing, and removing items from inventory, with a database to store the product details.
Task Management Application: A to-do list application where users can add, edit, and delete tasks, and track progress.

Technical Requirements:
Java SE: The application must be developed using Java SE (Standard Edition).
Database Connectivity: Use JDBC (Java Database Connectivity) to interact with the database.
Design Patterns: Apply common software design patterns (e.g., Singleton, Factory, Observer) where appropriate.
Version Control: Use Git for version control throughout the development process.
