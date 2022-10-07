FROM maven:3.8.6-eclipse-temurin-17 AS MAVEN_BUILD
COPY checkstyle checkstyle
COPY pom.xml .
COPY src src
RUN --mount=type=cache,target=/root/.m2 mvn package
RUN java -Djarmode=layertools -jar target/*.jar extract --destination target/extracted

FROM eclipse-temurin:17-jre
WORKDIR /application
COPY --from=MAVEN_BUILD ./target/extracted/dependencies/ ./
COPY --from=MAVEN_BUILD ./target/extracted/snapshot-dependencies/ ./
COPY --from=MAVEN_BUILD ./target/extracted/spring-boot-loader/ ./
COPY --from=MAVEN_BUILD ./target/extracted/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]