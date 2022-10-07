# Tech-demo API service

## Requirements
1. JDK 17
2. MySQL 8
3. Docker

## Code Style
Code style is guarded by Checkstyle plugin, with these [rules](checkstyle/checkstyle.xml).
Formatting rules for Eclipse IDE can be found [here](checkstyle/code-formatter-rules.xml).

To import these rules in Eclipse, do:
```
Window > Preferences > Java > Code Style > Formatter > Import
```
To import these rules in IntelliJ IDEA, do:
```
Settings > Code Style > Java > Manage > Import
```

## Integration tests
Integration tests leverage [Testcontainers library](https://www.testcontainers.org/) for temporarily running external dependencies in Docker.
Tests can be run with following command:
```
mvn verify
```

## Running the application
```
./mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.datasource.url=jdbc:mysql://localhost:3306/tech-demo --spring.datasource.username=[db user] --spring.datasource.password=[db pwd]"
```
