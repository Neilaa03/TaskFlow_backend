# Task Manager API

A RESTful backend application built with **Java and Spring Boot** to manage users, projects, and tasks. The project aims to provide a structured and scalable API for organizing team activities, assigning tasks, and tracking project progress.

This project is also an opportunity to explore backend development concepts and professional software engineering practices within the Spring ecosystem.

## Overview

Task Manager API is designed to simplify project and task management through a centralized REST API.

Users will be able to create projects, organize tasks, assign responsibilities, and monitor progress through different task statuses and priorities.

The application follows a layered architecture to ensure separation of concerns, maintainability, and scalability.

## Features

### Current Implementation
- RESTful API architecture.
- User, Project, and Task entities.
- Database persistence using Spring Data JPA.
- CRUD foundations for managing application resources.
- Layered architecture (Controller, Service, Repository).
- REST endpoints for retrieving users, projects, and tasks.
- User creation endpoint.

### Planned Features
- Complete CRUD operations for users, projects, and tasks.
- User roles and access management (USER, MANAGER, ADMIN).
- Task assignment and project membership.
- Task status and priority management.
- Input validation and centralized exception handling.
- Authentication and authorization using Spring Security and JWT.
- Pagination and filtering.
- Unit and integration testing.
- API documentation with Swagger / OpenAPI.

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 17+ | Programming language |
| Spring Boot 3 | Backend framework |
| Spring Web | REST API development |
| Spring Data JPA | Database access and persistence |
| Hibernate | ORM |
| PostgreSQL | Relational database |
| Maven | Dependency and build management |
| Postman | API testing |
| JUnit 5 | Testing |
| Mockito | Mocking and unit testing |
| Spring Security | Authentication and authorization |

*Some technologies are planned for later development stages.*

## Architecture

The application follows a layered architecture:

```text
             Client
               |
               v
          Controller
               |
               v
            Service
               |
               v
           Repository
               |
               v
           PostgreSQL
```

Each layer has a specific responsibility:

- **Controller:** Handles incoming HTTP requests and returns API responses.
- **Service:** Contains business logic and application rules.
- **Repository:** Handles database operations through Spring Data JPA.
- **Entity:** Represents the application's domain objects and database tables.

This separation makes the application easier to maintain, test, and extend.

## Project Structure

```text
src/
└── main/
    ├── java/
    │   └── com/example/taskmanager/
    │       ├── controller/
    │       │   ├── HomeController.java
    │       │   ├── UserController.java
    │       │   ├── ProjectController.java
    │       │   └── TaskController.java
    │       │
    │       ├── service/
    │       │   ├── UserService.java
    │       │   ├── ProjectService.java
    │       │   └── TaskService.java
    │       │
    │       ├── repository/
    │       │   ├── UserRepository.java
    │       │   ├── ProjectRepository.java
    │       │   └── TaskRepository.java
    │       │
    │       └── entity/
    │           ├── User.java
    │           ├── Project.java
    │           ├── Task.java
    │           └── enums/
    │               ├── UserRole.java
    │               ├── TaskStatus.java
    │               └── TaskPriority.java
    │
    └── resources/
        └── application.properties
```

## Data Models

The application currently defines three main entities.

### User

Represents a user of the application.

| Attribute | Type | Description |
|---|---|---|
| id | Long | Unique identifier |
| name | String | User's full name |
| email | String | Unique email address |
| password | String | User password |
| role | UserRole | User's role |

Available roles:
- `USER`
- `MANAGER`
- `ADMIN`

### Project

Represents a project that groups related tasks.

| Attribute | Type | Description |
|---|---|---|
| id | Long | Unique identifier |
| title | String | Project title |
| description | String | Project description |
| createdAt | LocalDateTime | Creation timestamp |

### Task

Represents an individual task within the application.

| Attribute | Type | Description |
|---|---|---|
| id | Long | Unique identifier |
| title | String | Task title |
| description | String | Task description |
| status | TaskStatus | Current task status |
| priority | TaskPriority | Task priority |
| dueDate | LocalDate | Task deadline |

Available statuses:
- `TODO`
- `IN_PROGRESS`
- `DONE`

Available priorities:
- `LOW`
- `MEDIUM`
- `HIGH`

## API Endpoints

Base URL: `http://localhost:8080`

### Users

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/users` | Retrieve all users |
| GET | `/api/users/{id}` | Retrieve a user by ID |
| POST | `/api/users` | Create a new user |

### Projects

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/projects` | Retrieve all projects |
| GET | `/api/projects/{id}` | Retrieve a project by ID |

### Tasks

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/tasks` | Retrieve all tasks |
| GET | `/api/tasks/{id}` | Retrieve a task by ID |

Additional endpoints for updating and deleting resources will be implemented as the project evolves.

## Getting Started

### Prerequisites

Make sure you have installed:

- Java 17 or later
- Maven
- PostgreSQL
- Git

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/task-manager-api.git
```

```bash
cd task-manager-api
```

### 2. Create the database

Open PostgreSQL and create a database:

```sql
CREATE DATABASE taskmanager;
```

### 3. Configure the database connection

Open `src/main/resources/application.properties` and configure your PostgreSQL credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/taskmanager
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

**Note:** `ddl-auto=update` is intended for development purposes. A database migration tool such as Flyway or Liquibase is more suitable for production environments.

### 4. Run the application

Using Maven:

```bash
./mvnw spring-boot:run
```

Or, if Maven is installed globally:

```bash
mvn spring-boot:run
```

The API will be available at:

```text
http://localhost:8080
```

## Testing the API

You can test the endpoints using [Postman](https://www.postman.com/).

For example, to retrieve all users:

```http
GET http://localhost:8080/api/users
```

To create a user:

```http
POST http://localhost:8080/api/users
Content-Type: application/json
```

Request body:

```json
{
    "name": "Alice",
    "email": "alice@example.com",
    "password": "password123",
    "role": "USER"
}
```

## Development Roadmap

The project is being developed progressively, with the following objectives:

- [x] Initialize Spring Boot application.
- [x] Define JPA entities and enumerations.
- [x] Configure PostgreSQL connection.
- [x] Create Spring Data JPA repositories.
- [x] Implement service and controller layers.
- [x] Implement basic GET endpoints.
- [x] Implement user creation.
- [ ] Complete CRUD operations for all entities.
- [ ] Implement DTOs and input validation.
- [ ] Define entity relationships.
- [ ] Implement business logic.
- [ ] Add centralized exception handling.
- [ ] Implement Spring Security and JWT authentication.
- [ ] Add pagination and filtering.
- [ ] Write unit and integration tests.
- [ ] Document the API with Swagger / OpenAPI.

## Learning Objectives

Through this project, I aim to strengthen my understanding of:

- Java backend development.
- Spring Boot and REST API design.
- Object-Relational Mapping (ORM) with JPA and Hibernate.
- Database management with PostgreSQL.
- Layered architecture and separation of concerns.
- Dependency Injection and Inversion of Control.
- Backend security and authentication.
- Software testing and clean code practices.

---

**Status:** In Development

**License:** To be determined.