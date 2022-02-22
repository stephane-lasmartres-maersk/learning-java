# Logs to DataDog

Basic setup to write logs to Console, File or EventHub using `log4j2`.

## Configuration
- Replace __APP_EH_CONN_STR__ by proper EventHub connection string in file `log4j2.xml`:
```xml
<Configuration packages="com.example.loggingwithjava.EventHubLogAppender,org.apache.logging.slf4j.Log4jLogger">
    <Properties>
        <Property name="EH_CONN_STR">__APP_EH_CONN_STR__</Property>
    </Properties>
    ...
</Configuration>
```
- Check and replace values if needed for environment vaiables in file `.vscode/launch.json`:
```json
{
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "Launch loggingwithjava",
            "request": "launch",
            "mainClass": "com.example.loggingwithjava.App",
            "projectName": "loggingwithjava",
            "env": {
                "APP_ENV": "",
                "APP_SERVICE": "",
                "APP_RG": "",
                "APP_RN": "",
                "APP_REGION": ""
            }
        }
    ]
}
``` 
You can look at log4j2 configuration file `src/main/resources/log4j2.xml`.


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
java -jar target/loggingwithjava-1.0-SNAPSHOT.jar
```
