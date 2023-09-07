# Hotel Room Booking Application

## Overview
The Hotel Room Booking Application is designed as a microservices-based system for booking hotel rooms. It consists of three main microservices:

1. **API Gateway**: This service serves as the entry point for external requests and routes them to the appropriate microservices internally.
2. **Booking Service**: Responsible for collecting user booking information and confirming bookings.
3. **Payment Service**: A dummy payment service used for processing payments after room bookings are confirmed.
4. **Eureka Server**: Manages service registration and discovery, allowing microservices to locate each other.

## Tech Stack
The Hotel Room Booking Application is built using the following technologies and tools:

- **Java**: The primary programming language for building the application.
- **Spring Boot**: A Java-based framework for building microservices.
- **Spring Cloud Netflix Eureka**: For service registration and discovery.
- **Spring Boot Web**: For building web-based APIs.
- **Spring Boot Data JPA**: For data persistence and database interactions.
- **Spring Boot Actuator**: For monitoring and managing services.
- **MySQL**: The relational database used for data storage and management.
- **(Add any additional technologies or tools specific to your project)**

## Rationale for Microservices
The project is divided into microservices to adhere to the single responsibility principle, ensuring that each service handles a specific function. This architectural choice allows for easy updates and scalability. For example, adding new payment methods can be done by updating only the payment service.

## Application Workflow
Here's a high-level overview of how the Hotel Room Booking Application works:

![Application Workflow](‪C:\Users\Meet\Programming\Back-End\Java\Spring Boot\Upgrad Spring-Boot Graded Projects\Sweet Home WorkFlow.png) 

1. The API Gateway, Booking Service, Payment Service, and Eureka Server play key roles in the application's functionality.
2. The API Gateway routes external requests to the appropriate microservices internally.
3. The Booking Service collects user booking information and returns room availability and prices.
4. The Payment Service processes payments and returns transaction details.
5. The Eureka Server is responsible for service registration and discovery, allowing microservices to locate each other.

## Rationale for Communication
Synchronous communication (REST) is chosen between the Booking and Payment services because the response ('transactionId') is required before confirming bookings and sending messages.

