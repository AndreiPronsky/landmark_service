# Student Project

This is a simple project developed using Spring Boot, featuring two services and two REST controllers. The application utilizes Liquibase for database migrations, Hibernate for ORM, and Testcontainers for integration testing. The project is built with Maven and deployed using Docker Compose.

## Table of Contents

- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Building the Project](#building-the-project)
- [Running the Application](#running-the-application)
- [Endpoints](#endpoints)
- [Testing](#testing)

## Technologies Used

- **Spring Boot** - Framework for building Java-based web applications.
- **Liquibase** - Tool for managing database schema changes.
- **Hibernate** - ORM framework for mapping Java objects to database tables.
- **Testcontainers** - Java library for integration tests using Docker containers.
- **Maven** - Build automation tool for Java projects.
- **Docker Compose** - Tool for defining and running multi-container Docker applications.

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.8 or higher
- Docker installed and running

### Clone the Repository

```bash
git clone https://github.com/AndreiPronsky/landmark_service
cd landmark_service
```

## Building the project

### To build the project, run the following command in the project root directory:

```bash
mvn clean install
```

## Running the Application
### To start the application, use Docker Compose:

```bash
docker-compose up --build
```

This command will build the Docker image and start the application along with any required services (such as the database).

## Endpoints

### The application exposes two REST controllers with the following endpoints:

#### Landmark Controller
 - **GET** /api/landmarks - Retrieves a list of landmarks, sorted by the given parameter and filtered by type.
 - **GET** /api/landmarks/by_settlement - Retrieves a list of landmarks located in the specified settlement.
 - **POST** /api/landmarks - Creates a new landmark based on the provided data transfer object.
 - **PATCH** /api/landmarks - Updates an existing landmark based on the provided data transfer object.
 - **DELETE** /api/landmarks - Deletes a landmark with the specified ID.


#### Settlement Controller
 - **GET** /api/endpoint2 - Updates an existing settlement based on the provided data transfer object.
 - **POST** /api/endpoint2 - Creates a new settlement based on the provided data transfer object.

## Testing
### The project uses Testcontainers for integration testing. To run the tests, execute:

```bash
mvn test
```

This will start a temporary Docker container with the required services and run the tests against it.