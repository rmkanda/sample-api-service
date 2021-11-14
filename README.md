# Sample Application with REST API Endpoints

Sample api application to do CURD operations on users data
(Spring, Java 17, In Memory Database)

## Building the application

### Gradle

```
./gradlew build

java -jar build/libs/UserManager-0.0.1-SNAPSHOT.jar
```

### Maven

```
./mvnw package

java -jar target/UserManager-0.0.1-SNAPSHOT.jar
```

## API Endpoints

- GET http://localhost:8080/users
- POST http://localhost:8080/users
- GET http://localhost:8080/users/{id}
- PUT http://localhost:8080/users/{id}
- DELETE http://localhost:8080/users/{id}

## API Documentation

http://localhost:8080/swagger-ui/index.html
http://localhost:8080/v3/api-docs
