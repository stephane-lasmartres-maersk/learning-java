# Consuming Azure Service Bus Queue

## Configuration
Add proper Azure Service Bus connection string into the file `src/main/java/com/example/App.java`.
```java
public class App 
{
    static String connectionString = "<put proper service bus connection string there>";

    /*...*/
}

```

## Resolve dependencies
```
mvn dependency:resolve
```

## Build 
```
mvn clean package
```

- Run the JAR file by executing:
```
java -jar target/java-project-1.0-SNAPSHOT.jar
```
