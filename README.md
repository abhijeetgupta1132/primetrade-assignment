# Task Manager — JWT Authentication & RBAC

A secure and scalable REST API built using **Spring Boot** with **JWT Authentication** and **Role-Based Access Control**, along with a simple frontend UI for interaction.

---

# 📌 Features

## 🔐 Authentication & Security

- User Registration
- User Login with JWT
- Password hashing using BCrypt
- Stateless authentication
- Role-based access (USER / ADMIN)
- Protected APIs using JWT filter

---

## 📋 Task Management

- Create Task
- Get All Tasks
- Get Task by ID
- Update Task
- Delete Task
- User-specific task ownership

---

## 🧪 Validation & Error Handling

- Request validation using Jakarta Validation
- Proper HTTP status codes
- Global exception handling
- Input sanitization

---

## 📚 API Documentation

- Swagger UI integrated
- Postman collection supported

Access Swagger at:


http://localhost:8080/swagger-ui/index.html


---

## 🗄️ Database

- JPA / Hibernate ORM
- Relational schema with proper mappings
- Supports MySQL / PostgreSQL (configurable)

---

# 🏗️ Project Structure


backend/
├── config/
├── controller/
├── dto/
├── entity/
├── repository/
├── security/
├── service/
└── resources/


The project follows a **layered architecture** ensuring separation of concerns and maintainability.

---

# ⚙️ Tech Stack

- Java 17+
- Spring Boot
- Spring Security
- JWT
- JPA / Hibernate
- Maven
- Swagger (OpenAPI)
- Lombok

Frontend:

- HTML
- CSS
- Vanilla JavaScript

---

# 🚀 How to Run

## 🔹 Backend

```bash
mvn clean install
mvn spring-boot:run

Server runs at:

http://localhost:8080
🔹 Frontend

Open directly in browser:

frontend/index.html
🔐 Default Roles

USER — normal access

ADMIN — elevated access (if configured)

📈 Scalability & Design

The application is designed using a layered architecture (Controller → Service → Repository → Entity) which ensures clear separation of concerns and maintainability.

Security is implemented using JWT-based stateless authentication with password hashing via BCrypt.

The system is designed to scale horizontally by:

Using stateless JWT authentication

Keeping business logic in the service layer

Supporting database indexing and pagination

Allowing future Redis caching integration

Being container-ready for Docker deployment

This architecture allows smooth evolution toward microservices if required.

✅ Assignment Coverage

✔ User registration & login
✔ JWT authentication
✔ Role-based access
✔ CRUD APIs
✔ Validation
✔ Swagger documentation
✔ Database integration
✔ Basic frontend UI
✔ Secure architecture

👨‍💻 Author
Abhijeet Gupta — Software Engineer
GitHub: https://github.com/abhijeetgupta1132
LinkedIn: https://www.linkedin.com/in/abhijeet-gupta-807876381
