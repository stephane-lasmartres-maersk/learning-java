# Consuming a Rest API
This application consumes the [Greeting Rest API example from GitHub](https://github.com/stephane-lasmartres-maersk/restapi-java). When the API response is coming it is logged as following.

![application output](https://github.com/stephane-lasmartres-maersk/consuming-restapi-java/blob/master/documentation/application%20output.jpg?raw=true)



## Prerequisites

- Install JDK.
- Download or clone [Greeting Rest API example from GitHub](https://github.com/stephane-lasmartres-maersk/restapi-java), build and run it,. See related README file for details. 

## Debug in VS Code
- Install Java extensions as recommended on [VS Code official website](https://code.visualstudio.com/docs/java/java-tutorial).
- Run this Consuming Rest API Application by pressing F5

## Run the application
```
./mvnw spring-boot:run
```

## Build jar package
- Build the JAR file by executing the following command:
```
./mvnw clean package
```

- Run the JAR file by executing:
```
java -jar target/consuming-restapi-0.0.1-SNAPSHOT.jar
```
