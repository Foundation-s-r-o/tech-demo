# Tech-demo API service

## Requirements
1. JDK 17
2. MySQL 8
3. Docker

## Code Style
Code style is guarded by Checkstyle plugin, with these [rules](checkstyle/checkstyle.xml).
Formatting rules for Eclipse IDE can be found [here](checkstyle/code-formatter-rules.xml).
[docker-compose.yml](..%2Fdocker-compose.yml)
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

## Continuous Integration
![Continuous Integration](https://img.shields.io/github/actions/workflow/status/Foundation-s-r-o/tech-demo/maven.yml)

## CodeClimate
[![CodeClimate](https://api.codeclimate.com/v1/badges/1ab4a2eefa91189315eb/maintainability)](https://codeclimate.com/github/Foundation-s-r-o/tech-demo/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/1ab4a2eefa91189315eb/test_coverage)](https://codeclimate.com/github/Foundation-s-r-o/tech-demo/test_coverage)

## SonarQube
![Quality Gate Status](https://sonarcloud.io/api/project_badges/quality_gate?project=Foundation-s-r-o_tech-demo&branch=master)

![Duplicated Lines Density](https://sonarcloud.io/api/project_badges/measure?branch=master&project=Foundation-s-r-o_tech-demo&metric=duplicated_lines_density)
![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?branch=master&project=Foundation-s-r-o_tech-demo&metric=vulnerabilities)
![Bugs](https://sonarcloud.io/api/project_badges/measure?branch=master&project=Foundation-s-r-o_tech-demo&metric=bugs)
![Security Rating](https://sonarcloud.io/api/project_badges/measure?branch=master&project=Foundation-s-r-o_tech-demo&metric=security_rating)
![Sqale Rating](https://sonarcloud.io/api/project_badges/measure?branch=master&project=Foundation-s-r-o_tech-demo&metric=sqale_rating)
![Code Smells](https://sonarcloud.io/api/project_badges/measure?branch=master&project=Foundation-s-r-o_tech-demo&metric=code_smells)
![Ncloc](https://sonarcloud.io/api/project_badges/measure?branch=master&project=Foundation-s-r-o_tech-demo&metric=ncloc)
![Sqale Index](https://sonarcloud.io/api/project_badges/measure?branch=master&project=Foundation-s-r-o_tech-demo&metric=sqale_index)
![Alert Status](https://sonarcloud.io/api/project_badges/measure?branch=master&project=Foundation-s-r-o_tech-demo&metric=alert_status)
![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?branch=master&project=Foundation-s-r-o_tech-demo&metric=reliability_rating)
