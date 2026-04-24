# Spring Boot Template Reference

## Maven Defaults
- `groupId`: `com.andres.course.codex.springboot`
- `artifactId`: workspace directory name
- `packaging`: `jar`
- `java.version`: `25`
- `spring-boot.version`: `4.0.3` or newer stable from `spring.io`

## Required Dependencies
- `org.springframework.boot:spring-boot-starter-web`
- `org.springframework.boot:spring-boot-starter-validation`
- `org.springframework.boot:spring-boot-starter-data-jpa`
- `com.h2database:h2`
- `org.springframework.boot:spring-boot-devtools`
- `org.springframework.boot:spring-boot-starter-actuator`

## Base Package
Use:

```text
com.andres.course.codex.springboot.{artifactId}.app
```

## Suggested File Skeleton

```text
src/main/java/com/andres/course/codex/springboot/{artifactId}/app/
  Application.java
  controllers/
  dtos/
  mappers/
  models/
  repositories/
  services/
src/main/resources/
  application.properties
  templates/
    layouts/base.html
    fragments/header.html
    fragments/footer.html
```

## application.properties Baseline

```properties
spring.application.name=${artifactId}
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:${artifactId}
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
management.endpoints.web.exposure.include=health,info
```

## Layer Checklist Per Aggregate
1. Entity in `models`
2. DTO record in `dtos`
3. Mapper in `mappers`
4. `JpaRepository` interface in `repositories`
5. Service in `services`
6. Controller in `controllers`
