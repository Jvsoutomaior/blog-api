# BlogAPi

A REST API build using spring boot for the [roadmap challenge](https://roadmap.sh/projects/blogging-platform-api). This project is designed to manage blog posts, allowing users to create, read, update, and delete their posts. 

It implements a modern backend architecture with a focus on clean code, separation of concerns, and best practices in API design.

## Architecture

The project follows a standard layered architecture:
1.  **Controller Layer:** Manages HTTP requests and maps them to service calls. Includes OpenAPI annotations for documentation.
2.  **Service Layer:** Handles business logic and performs conversions between Entities and DTOs.
3.  **Repository Layer:** Uses Spring Data JPA for easy and efficient database interaction.
4.  **Entity Layer:** Defines the `BlogPost` entity representing the database structure.
5.  **DTO Layer:** Contains request and response records to ensure a clear separation between internal data models and API contracts.

## Getting Started

### Requirements:
- Docker and Docker Compose

### Running with Docker
1. Clone the repository
2. Build and launch the application:
    ```
    docker compose -f compose-dev.yaml up --build
    ```
3. Access the API documentation at: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Testing

To execute the automated integration tests:
Bash
```
./mvnw test
```

## License

This project is licensed under the terms of the GNU General Public License v3.0. See the [LICENSE](LICENSE) file for details.