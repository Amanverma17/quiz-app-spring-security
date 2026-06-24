Here's the updated README with RBAC added and known limitations fixed:

```
# 📝 Quiz Application

A RESTful Quiz Management API built with **Spring Boot**, secured with **JWT authentication**, and backed by **PostgreSQL**.

---

## 🚀 Tech Stack

| Layer              | Technology                          |
|--------------------|-------------------------------------|
| Backend Framework  | Spring Boot 4.0.4                   |
| Language           | Java 17                             |
| Database           | PostgreSQL                          |
| ORM                | Spring Data JPA / Hibernate         |
| Security           | Spring Security + JWT (jjwt 0.13.0) |
| API Docs           | Springdoc OpenAPI (Swagger UI)      |
| Build Tool         | Maven                               |

---

## ✨ Features

- JWT-based stateless authentication with BCrypt password hashing
- Role-based access control (ADMIN / USER)
- Question bank with category and difficulty filtering
- Quiz creation via random question selection (native SQL query)
- Quiz submission with automatic percentage scoring
- DTO pattern — correct answers never exposed to users
- Swagger UI for interactive API testing

---

## 🏗️ Project Structure

```
src/main/java/com/quizapp/quizapplication/
├── config/
│   ├── JwtFilter.java
│   └── SecurityConfiguration.java
├── controller/
│   ├── QuestionController.java
│   ├── QuizController.java
│   └── UserController.java
├── model/
│   ├── Question.java
│   ├── Quiz.java
│   ├── QuestionDTO.java
│   ├── Response.java
│   └── Users.java
├── repository/
│   ├── QuestionRepo.java
│   ├── QuizRepo.java
│   └── UserRepo.java
├── service/
│   ├── JWTservice.java
│   ├── QuestionService.java
│   ├── QuizService.java
│   ├── UserDetailService.java
│   └── UserService.java
└── QuizApplication.java
```

---

## 📡 API Endpoints

| Method | Endpoint                       | Auth | Role  | Description                          |
|--------|--------------------------------|------|-------|--------------------------------------|
| POST   | `/register`                    | ❌   | -     | Register new user                    |
| POST   | `/login`                       | ❌   | -     | Login, receive JWT                   |
| GET    | `/question/allQuestion`        | ✅   | USER  | Get all questions                    |
| GET    | `/question/difficulty/{level}` | ✅   | USER  | Filter by difficulty                 |
| POST   | `/question/add`                | ✅   | ADMIN | Add a question                       |
| POST   | `/quiz/create`                 | ✅   | ADMIN | Create quiz (category, difficulty, count) |
| GET    | `/quiz/get`                    | ✅   | USER  | Get quiz questions (no answers)      |
| POST   | `/quiz/submit/{quizId}`        | ✅   | USER  | Submit answers, get score %          |

---

## ⚙️ Setup & Run

**Prerequisites:** Java 17+, Maven, PostgreSQL on port 5432

```sql
CREATE DATABASE questiondb;
```

```properties
# application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/questiondb
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
jwt.secret=your_jwt_secret_here
```

```bash
mvn clean install
mvn spring-boot:run
```

App runs at `http://localhost:8080`  
Swagger UI at `http://localhost:8080/swagger-ui/index.html`

---

## 🛠️ Future Improvements

- [ ] Fetch quiz by ID instead of category + difficulty filter
- [ ] Add quiz timer support
- [ ] Dockerize with Docker Compose
- [ ] Add unit and integration tests

---

## 👤 Author

**Aman Verma**
- GitHub: [github.com/Amanverma17](https://github.com/Amanverma17)
- LinkedIn: [linkedin.com/in/aman-verma-r-87601134b](https://linkedin.com/in/aman-verma-r-87601134b)
- LeetCode: [amanverma17](https://leetcode.com/amanverma17)
```

**What changed:**
- Added RBAC to Features section ✅
- Added Role column to API Endpoints table ✅
- Added `jwt.secret` to application.properties example ✅
- Removed Known Limitations (since JWT bug is fixed and RBAC is done) ✅

