# Sample Application with REST API Endpoints
Sample api application to do CURD operations on users data

## Building the application

```
./gradlew build

java -jar build/libs/UserManager-0.0.1-SNAPSHOT.jar
```

## API Endpoints

* GET http://localhost:8080/users
* POST http://localhost:8080/users
* GET http://localhost:8080/users/{id}
* PUT http://localhost:8080/users/{id}
* DELETE http://localhost:8080/users/{id}

## API Documentation

http://localhost:8080/v3/api-docs
