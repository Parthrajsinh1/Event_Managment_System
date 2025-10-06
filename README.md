# Event Management System

> A Spring Boot backend for managing events, users, roles, and registrations — built with Spring Boot, Spring Data JPA, MySQL and secured with role-based access control.

---



## Project Overview

This repository contains the backend service for an Event Management System. It exposes RESTful APIs to manage users, roles, events and user registrations (attendance), and enforces access control by role. The service uses Spring Boot for the web layer and business logic, Spring Data JPA for database interaction, and MySQL for persistence.

**Primary goals:**

* CRUD operations for events, users and roles
* Secure endpoints with authentication and RBAC
* Clean database design with normalization
* Easy local development and API testing via Postman

---

## Key Features

* Spring Boot REST API (CRUD): users, roles, events, registrations
* Spring Data JPA + Hibernate for ORM and entity management
* MySQL relational schema with normalized tables (users, roles, events, registrations, user_roles)
* Authentication endpoints (register, login) and role-based access to protected APIs
* API testing with Postman and optional Swagger/OpenAPI support
* Clear separation of concerns (controller -> service -> repository)

---

## Tech Stack & Architecture

* Java 17+ (or compatible)
* Spring Boot (Web, Security, Data JPA, Validation)
* MySQL 8.x (or compatible)
* Maven / Gradle (Maven examples shown)
* Optional: Springdoc OpenAPI (Swagger) for interactive API docs



