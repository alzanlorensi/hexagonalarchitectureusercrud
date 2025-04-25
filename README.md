Hexagonal Architecture User CRUD API
Overview
A clean and simple User CRUD API implemented using Hexagonal Architecture (Ports & Adapters) with Spring Boot.
This project demonstrates separation of concerns between business logic and infrastructure details.

Key Features
✅ Hexagonal Architecture implementation
✅ Complete CRUD operations for User entity
✅ Proper separation of business logic from infrastructure
✅ Custom exception handling
✅ JPA database integration
✅ MapStruct for object mapping

Tech Stack
Core: Java 17, Spring Boot 3.x
Persistence: Spring Data JPA
API: REST, JSON
Mapping: MapStruct
Testing: JUnit 5, Mockito

Getting Started
Prerequisites
JDK 17+
Maven 3.8+

Installation
Clone the repository:
bash
git clone https://github.com/your-repo/hexagonal-user-crud.git
Build the project:
bash
mvn clean install
Run the application:
bash
mvn spring-boot:run

API Endpoints
```
POST	/api/users	Create new user
GET	/api/users/{id}	Get user by ID
GET	/api/users/email	Get user by email
GET	/api/users/all	Get all users
PUT	/api/users	Update user
```

License
MIT License
