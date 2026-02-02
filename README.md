# 4.02-Spring-Boot-Api-Rest-Level3

## 📄 Description

This repository contains a Spring Boot REST API developed as a learning project to practice backend development with 
**MongoDB** and **document-based persistence**.

The application allows managing **fruit orders** made by clients. Each order contains:

* Client name
* Delivery date
* A list of ordered fruits with quantities

The API provides a complete CRUD:

* Create a new order
* List all orders
* Retrieve an order by ID
* Update an existing order
* Delete an order

The project is implemented following a **vertical slice (feature-based) architecture**,
where all layers related to a feature are grouped together.

## 💻 Technologies used

- Java 21+
- Spring Boot
- Spring Web
- Spring Data MongoDB
- Maven
- JUnit 5
- Spring Boot Test (MockMvc)
- Jackson (JSON serialization)
- Lombok
- Docker & Docker Compose
- IntelliJ IDEA
- Postman

## 📋 Requirements

- Java 21
- Maven (IntelliJ bundled Maven is sufficient)
- Docker & Docker Compose
- IDE capable of running Spring Boot projects (IntelliJ IDEA, Eclipse, etc.)

## 🛠️ Installation

1. Clone the repository:

```bash
git clone https://github.com/ccasro/4.02-Spring-Boot-Api-Rest-Level3/
```

2. Open the project in your IDE (e.g., IntelliJ IDEA)
3. Ensure Maven dependencies are downloaded automatically

## ▶️ Execution (Docker)

Run the application with MongoDB using Docker:

```bash
docker compose up --build
```

The API will be available at:

```arduino
http://localhost:8080
```

## 🌐 API Endpoints

| Method | Endpoint     | Description              |
|--------|--------------|--------------------------|
| POST   | /orders      | Create a new order       |
| GET    | /orders      | Get all orders           |
| GET    | /orders/{id} | Get order by ID          |
| PUT    | /orders/{id} | Update an existing order |
| DELETE | /orders/{id} | Delete an order          |

## 🧪 Testing

The project follows TDD (Test-Driven Development).

**Integration tests**

All endpoints are tested using:

* @SpringBootTest
* MockMvc
* Real MongoDB test container (via docker-compose.test)

Tests cover:

* Order creation with valid data
* Validation errors (400 Bad Request)
* Listing all orders
* Getting order by ID
* Updating orders
* Deleting orders
* 404 for non-existent orders

Run tests:

```bash
docker compose -f docker-compose.test.yml up -d
mvn test
```

## 🐳 Docker

The project includes:

**docker-compose.yml**

* MongoDB container for development
* Spring Boot app container

**docker-compose.test.yml**

* Isolated MongoDB instance for tests

This ensures:

* Production data is never affected by tests
* Tests run in a clean environment

## 🤝 Contributions

* Use feature branches for development
* Follow Conventional Commits:
    * feat:
    * fix:
    * refactor:
    * test:
* Keep commits small and focused
* Do not commit secrets or compiled files
* Use Pull Requests for improvements

## 📌 Notes

This project focuses on:

* REST API design
* MongoDB document persistence
* Embedded subdocuments
* Vertical slice architecture
* DTO usage and validation
* Global exception handling
* Dockerized environments
* Full TDD workflow with integration tests

