# AGENTS

## Purpose
This repository uses local agent guidance to keep Spring Boot work consistent.
Project-specific conventions can be added later as the application takes shape.

## Registered Skill
- Local skill path: `.agents/skills/spring-boot-best-practices/SKILL.md`
- Skill name: `spring-boot-best-practices`
- Role: guide the creation and modification of Spring Boot applications and Spring Web monoliths with a layered architecture.

## When To Use The Spring Boot Skill
- Use it when creating a new Spring Boot project in this workspace.
- Use it when adding or changing entities, repositories, services, controllers, DTOs, mappers, or Thymeleaf layouts.
- Use it when the user asks for a Spring Boot API, a Spring Web monolith, or related Spring structure.

## Expected Usage
- Read the skill before making Spring Boot code or scaffolding decisions.
- For new projects, follow the skill rule that the base project should be generated with Spring Initializr first when available.
- Treat the skill as the default source of truth for stack defaults, layered package structure, and Spring Boot scaffolding behavior in this repository.

## Current Scope
- This file intentionally does not define project-specific business rules, domain models, coding standards, or deployment instructions yet.
- Add those details later once the project structure and requirements are defined.
