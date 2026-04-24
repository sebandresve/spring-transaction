---
name: spring-boot-best-practices
description: Generate new Spring Boot projects from Spring Initializr or modify layered Spring Boot applications and Spring Web monoliths using Maven, JAR packaging, Java 25, application.properties, and a repository/service/controller architecture. Use when the user asks to create a basic Spring Boot API, a Spring Web monolith, or to add or change an entity, repository, service, controller, DTO, mapper, Thymeleaf layout, or related Spring Boot structure.
---

# Spring Boot Best Practices

## Overview
Use this skill to create or extend Spring Boot projects with a consistent layered architecture. For new projects, generate the base project with Spring Initializr at `https://start.spring.io` or its API before adding application code. Default to Spring Boot `4.0.3` or the newest stable release from `spring.io` when the environment or user explicitly requires a newer stable version.

## Default Stack
- Use Java `25`.
- Use Maven with `jar` packaging.
- Use `application.properties`, not YAML.
- Set Maven `groupId` to `com.andres.course.codex.springboot`.
- Set Maven `artifactId` to the workspace directory name unless the user overrides it.
- Set the base package to `com.andres.course.codex.springboot.{artifactId}.app`.
- Include `spring-boot-starter-web`, `spring-boot-starter-validation`, `spring-boot-starter-data-jpa`, `com.h2database:h2`, `spring-boot-devtools`, and `spring-boot-starter-actuator`.
- Include Maven Wrapper files: `.mvn/`, `mvnw`, and `mvnw.cmd`.

## Creation Rule
- When the task is to create a new Spring Boot project, generate the base structure with Spring Initializr first instead of hand-writing the initial `pom.xml`, wrapper files, and bootstrap classes.
- Use Spring Initializr with the defaults from this skill unless the user overrides them: Java `25`, Maven, `jar`, `application.properties`, `groupId` `com.andres.course.codex.springboot`, `artifactId` from the workspace directory, and base package `com.andres.course.codex.springboot.{artifactId}.app`.
- Select dependencies in Initializr based on the requested project type, then extend the generated project with the layered architecture from this skill.
- Treat manual scaffolding as a fallback only when `https://start.spring.io` or its API cannot be used because of network restrictions, environment limits, or service unavailability. In that fallback, mirror the same stack and project metadata as closely as possible.

## Project Layout
Create this package structure under the base package:

```text
config/
controllers/
dtos/
mappers/
models/
repositories/
services/
```

Use `src/main/resources/templates/` for Thymeleaf views and `src/main/resources/templates/fragments/` plus `src/main/resources/templates/layouts/` for shared layouts.

## Layer Rules
- Keep `controllers` limited to REST or web endpoints, request validation, and service delegation.
- Keep `services` responsible for business rules, transaction boundaries, orchestration, and explicit entity/DTO conversion.
- Define `repositories` as interfaces extending `JpaRepository`.
- Keep `models` as JPA entities only; do not expose them directly from controllers.
- Use Java `record` types for DTOs.
- Exclude sensitive or internal fields from DTOs, especially `password`, `password_hash`, `created_at`, `updated_at`, `create_at`, and `update_at`.
- Reuse the same DTO record for request and response only when the same public shape is safe in both directions.
- Add an explicit mapper class for each aggregate so services can convert `entity -> dto` and `dto -> entity`.

## Thymeleaf Monolith Rules
For Spring Web monoliths, use Thymeleaf with reusable fragments and a shared layout:
- Create `layouts/base.html` as the main layout.
- Create `fragments/header.html` and `fragments/footer.html`.
- Keep pages semantically structured with `header`, `main`, and `footer`.
- Center the main content area and keep navigation reusable across pages.
- Use Tailwind CSS for styling.

## Scaffolding Workflow
1. Detect whether the user wants a new project or changes to an existing project.
2. For new projects, detect whether the user wants a REST API, a Spring Web monolith, or both.
3. Derive `artifactId` from the workspace directory name unless the user overrides it.
4. For new projects, generate the base project with Spring Initializr using Spring Boot `4.0.3` or newer stable, Java `25`, Maven, `jar`, and the required dependencies for the requested app type.
5. If Spring Initializr is unavailable, create a manual fallback project only after confirming the service or network cannot be used, and keep the same metadata and dependencies.
6. Verify the generated project uses the expected base package, wrapper files, and `application.properties`, then complete any missing defaults for app name, H2 console, actuator exposure, and datasource settings.
7. Create packages for `models`, `controllers`, `repositories`, `services`, `dtos`, `mappers`, and `config`.
8. For each aggregate, create entity, DTO record, repository, service, mapper, and controller together.
9. For monolith requests, add Thymeleaf templates, layout fragments, and Tailwind integration.
10. Prefer `./mvnw -DskipTests spring-boot:run` as the default local run command.

## Implementation Notes
- Name controllers as `{Entity}Controller`, services as `{Entity}Service`, repositories as `{Entity}Repository`, mappers as `{Entity}Mapper`, DTOs as `{Entity}Dto`, and entities as singular nouns.
- Keep REST endpoints resource-oriented, for example `/users` and `/users/{id}`.
- Validate request bodies with Jakarta Validation annotations on DTO records.
- Return DTOs from controllers, not entities.
- Keep H2 as the default local database unless the user asks for another database.
- If a user asks to add or modify one layer, inspect and update the adjacent layers as needed so the aggregate remains consistent.

## Resources
- Read [spring-boot-template.md](references/spring-boot-template.md) when you need a concrete starter layout, dependency checklist, or file skeleton.
