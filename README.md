# EstudosNoSQLMongodb

A Spring Boot REST API built while studying NoSQL / MongoDB with Spring Data, focused on modeling a small blog-style domain (users and posts) using document-oriented patterns instead of a relational schema.

🇧🇷 [Leia em português](LEIAME.md)

## About the project

This is a practice project (based on Nelio Alves's Spring Boot + MongoDB course) exploring how document modeling differs from relational modeling: embedded documents, DTOs to avoid infinite reference loops, lazy `@DBRef`, and custom MongoDB queries with regex and date ranges.

## Technologies

- Java 25
- Spring Boot 4.1.1 (Web MVC + Spring Data MongoDB)
- MongoDB
- Maven

## Domain model

- **User** — `id`, `name`, `email`, and a lazily-loaded list of `Post` references (`@DBRef`)
- **Post** — `id`, `date`, `title`, `body`, an embedded `AuthorDTO`, and a list of embedded `CommentDTO`

Two DTOs (`AuthorDTO`, `CommentDTO`) are embedded directly inside `Post` documents to avoid circular references between `User` and `Post` and to keep the author/comment data denormalized, as is common in document databases.

## Endpoints

**Users** — `/users`
| Method | Path | Description |
|---|---|---|
| GET | `/users` | List all users |
| GET | `/users/{id}` | Find a user by id |
| POST | `/users` | Create a user |
| PUT | `/users/{id}` | Update a user |
| DELETE | `/users/{id}` | Delete a user |
| GET | `/users/{id}/posts` | List a user's posts |

**Posts** — `/posts`
| Method | Path | Description |
|---|---|---|
| GET | `/posts/{id}` | Find a post by id |
| GET | `/posts/titlesearch?text=` | Search posts by title (case-insensitive regex) |
| GET | `/posts/fullsearch?text=&minDate=&maxDate=` | Search posts by title, body, or comment text within a date range |

## Highlights

- Custom `@Query` methods on `PostRepository` combining regex and date-range filters (`fullSearch`), alongside Spring Data's derived-query mechanism (`findByTitleContainingIgnoreCase`)
- Centralized exception handling via `ControllerExceptionHandler` and a custom `ObjectNotFoundException`, mirroring the layered-architecture pattern used in the [Workshop-springboot-jpa](https://github.com/Menezesvm/Workshop-springboot-jpa) project
- `Instantiation` (`CommandLineRunner`) seeds the database with sample users, posts, and comments on startup

## Running locally

1. Have a MongoDB instance running locally (default: `mongodb://localhost:27017`)
2. Adjust `src/main/resources/application.properties` if needed
3. Run:
   ```bash
   ./mvnw spring-boot:run
   ```

## Status

🚧 Study project — part of a broader Java/Spring Boot learning path, following JPA/Hibernate (relational) studies.
