🛒 E-Commerce Microservices Backend

A Java Spring Boot–based E-Commerce backend built using a microservices architecture, featuring JWT authentication, a centralized API Gateway, and multiple domain services.

This project is designed to demonstrate real-world backend engineering practices and is suitable for portfolio and production-style learning.

📐 Architecture Overview

The system follows a microservices architecture with a single entry point via an API Gateway.

Client
  |
  |  (JWT)
  v
API Gateway (Port 4000)
  |
  |-------------------------------
  |           |           |
Product   Inventory     Order
Service    Service     Service
(2001)     (2002)       (2003)
  |
Auth Service (2004)
🧩 Services Included
🔐 Auth Service

Handles user authentication

Generates JWT tokens

Central identity and security service

Uses Spring Security

🌐 API Gateway

Single entry point for all client requests

Routes requests to backend services

Applies JWT validation filter

Blocks unauthorized requests

📦 Product Service

Manages product-related APIs

JWT-protected endpoints

Accessible only via API Gateway

🏬 Inventory Service

Handles inventory and stock logic

JWT-secured APIs

Gateway-routed access

🧾 Order Service

Manages order-related operations

JWT-protected endpoints

Designed for service-to-service integration

🔐 Authentication & Security Flow

User authenticates via Auth Service

Auth Service validates credentials

Auth Service generates a JWT

Client sends JWT in every request:

Authorization: Bearer <JWT>

API Gateway validates the JWT

Valid requests are forwarded to the target service

Invalid or missing tokens result in 401 Unauthorized

All services are protected using Spring Security to prevent unauthorized access.

🛠 Tech Stack

Java 17

Spring Boot

Spring Security

Spring Cloud Gateway

JWT (JSON Web Token)

PostgreSQL

Maven

Docker & Docker Compose

📂 Project Structure
E-Commerce/
│
├── Api-Service/          # API Gateway
├── Auth-Service/         # Authentication & JWT
├── Product-Service/      # Product domain
├── Inventory-Service/    # Inventory domain
├── Order-Service/        # Order domain
│
├── share-common/         # Shared utilities / common code
├── docker-compose.yml   # Multi-service orchestration
├── api-requests/        # API request samples
├── http-requests/       # HTTP test files
└── README.md
✨ Key Highlights

Microservices-based architecture

JWT-based authentication

Centralized API Gateway security

Secure service-to-service communication

Clean separation of concerns

Scalable and extensible design

Docker-ready setup

🚧 Future Enhancements

Role-based authorization (ADMIN / USER)

Refresh token implementation

Service discovery (Eureka)

Centralized configuration server

Swagger / OpenAPI documentation

Rate limiting at API Gateway

Monitoring and logging
