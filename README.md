# Project Management Platform - Spring Backend

This is one of the backend versions for the Project Management Platform. This backend is implemented using the Spring framework, including Spring Boot and Spring Data JPA, and provides RESTful APIs for managing projects, tasks, users, comments, and more.

## Technologies Used

- Spring Boot
- Spring Data JPA
- Thymeleaf (for frontend rendering)
- Spring Web for RESTful APIs
- Spring WebFlux (for reactive programming)
- Spring Security for authentication and authorization
- Flyway for database migrations
- PostgreSQL as the database
- Lombok for reducing boilerplate code
- MapStruct for object mapping
- SpringDoc for generating OpenAPI documentation
- JSON Web Token (JWT) for user authentication
- TestContainers for integration testing
- RestAssured for API testing

## Getting Started

To get started with the Spring backend, follow these steps:

1. Clone the repository:
   ```git clone https://github.com/maciej-MKan/projects-manager.git```

2. Navigate to the spring backend directory:
   ```cd backend/projects-manager-spring-api```

3. Run the application using Docker Compose:
   ```/../../docker-compose up```
4. Access the API and OpenAPI documentation:
- The API is accessible at `http://localhost:8080/`
- The OpenAPI documentation is available at `http://localhost:8080/swagger-ui/index.html`

## ERD (Entity Relationship Diagram)

![Entity Relationship Diagram](https://github.com/maciej-MKan/projects-manager-spring-api/blob/1fae4acdecb49568583fd511bf23aa03a2c6643e/images/zajavka%20-%20ERD.png)
