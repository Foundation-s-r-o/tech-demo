# Tech-demo API service

## Requirements
1. JDK 17

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

## Running the application
```
./mvnw spring-boot:run
```
