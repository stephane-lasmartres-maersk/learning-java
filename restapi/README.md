# Prerequisites

- Install JDK.

# Debug in VS Code
- Install Java extensions as recommended on [VS Code official website](https://code.visualstudio.com/docs/java/java-tutorial).
- Run the application by pressing F5
- Navigate to http://localhost:8080/greeting or http://localhost:8080/greeting?name=John%20Doe 


# Run the application
```
./mvnw spring-boot:run
```

# Build jar package
- Build the JAR file by executing the following command:
```
./mvnw clean package
```

- Run the JAR file by executing:
```
java -jar target/restapi-0.0.1-SNAPSHOT.jar
```
