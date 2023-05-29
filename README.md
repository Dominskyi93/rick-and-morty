# Rick and Morty App

The main task of this project was to create a program that reads data from a third-party source and writes it to a database (PostgreSQL).
It should also be possible to output a random character's Wiki and to return a list of all characters whose name contains a certain string.

## Project structure
 - `controller` : This package contains the controllers.
 - `dto` : This package contains data transfer objects that are used to encapsulate and transfer data between the different layers of the application. These objects help to unify requests and responses in the controllers.
    - `mapper` : This package contains mapper classes that convert model objects to DTO objects. These mappers are used to convert between dto and model (and are not used for DB communication).
 - `model` : This package contains the model for the database. This model is used to represent data entities in the database and is used by the DAO to map database records to Java objects.
 - `repository` : This package contains the data access layer (also known as the repository layer) that is responsible for accessing and manipulating data in the database.
 - `service` : This package contains the services that call the repositories. These services are responsible for performing business logic and coordinating the interactions between the controllers and the DAO.

## Getting Started
To get started with the Rick and Morty App, follow these steps:

1. Clone the repository
2. Configure the `application.properties` file.
3. Build the project: `mvn clean install`
4. Run the application
5. Access the application in your web browser at `http://localhost:8080`

Or you can use docker
1. First, make sure you have docker installed and running
2. Clone the repository
3. Build the project: `mvn clean install`
4. Run the command `docker-compose up` in the terminal

## API Endpoints
The following API endpoints are available:

- `GET /movie-characters/random`: Retrieves a random movie character.
- `GET /movie-characters/by-name?namePart={namePart}`: Searches for movie characters that contain the specified `namePart` in their names.

## Technologies Used
- Java
- Spring Boot
- Maven
- Docker
- JUnit5
- PostgreSQL
