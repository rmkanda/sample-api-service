FROM openjdk:17.0.1

COPY target/UserManager-0.0.1-SNAPSHOT.jar .

CMD ["java","-jar","UserManager-0.0.1-SNAPSHOT.jar"]

