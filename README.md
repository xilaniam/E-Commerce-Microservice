# 🛒 E-Commerce Microservices Backend

A **Java Spring Boot–based E-Commerce backend** built using a **microservices architecture**, featuring **JWT authentication**, a **centralized API Gateway**, and multiple domain services.

This project demonstrates **real-world backend engineering practices** such as secure API design, service separation, and gateway-based request handling. It is suitable for **portfolio presentation** and **production-style learning**.

---

## 📐 Architecture Overview

The system follows a **microservices architecture** with a single entry point via an **API Gateway**.
Client
|
| (JWT)
v
API Gateway (Port 4000)
|
|-------------------------------
| | |
Product Inventory Order
Service Service Service
(2001) (2002) (2003)
|
Auth Service (2004)


---

## 🧩 Services Included

### 🔐 Auth Service
- Handles user authentication
- Generates JWT tokens
- Acts as the central identity service
- Secured using Spring Security

---

### 🌐 API Gateway
- Single entry point for all client requests
- Routes requests to backend services
- Applies JWT validation filters
- Blocks unauthorized access before routing

---

### 📦 Product Service
- Manages product-related APIs
- Endpoints secured using JWT
- Accessible through API Gateway

---

### 🏬 Inventory Service
- Handles inventory and stock management
- JWT-protected APIs
- Designed for internal service communication

---

### 🧾 Order Service
- Manages order-related operations
- JWT-secured endpoints
- Designed for future service integrations

---

## 🔐 Authentication & Security Flow

1. User authenticates via **Auth Service**
2. Credentials are validated
3. A **JWT token** is generated
4. Client sends JWT with every request:
5. API Gateway validates the token
6. Valid requests are routed to the target service
7. Invalid or missing tokens result in `401 Unauthorized`

All services are additionally protected using **Spring Security**, preventing direct unauthorized access.

---

## 🛠 Tech Stack

- Java 17
- Spring Boot
- Spring Security
- Spring Cloud Gateway
- JWT (JSON Web Token)
- PostgreSQL
- Maven
- Docker & Docker Compose

---

## 📂 Project Structure
E-Commerce/
│
├── Api-Service/ # API Gateway
├── Auth-Service/ # Authentication & JWT
├── Product-Service/ # Product domain
├── Inventory-Service/ # Inventory domain
├── Order-Service/ # Order domain
│
├── share-common/ # Shared utilities / common code
├── docker-compose.yml # Multi-service orchestration
├── api-requests/ # API request samples
├── http-requests/ # HTTP test files
└── README.md


---

## ✨ Key Highlights

- Microservices-based architecture
- JWT-based authentication
- Centralized API Gateway security
- Secure service-to-service communication
- Clean separation of concerns
- Scalable and extensible design

---

## 🚧 Future Enhancements

- Role-based authorization (ADMIN / USER)
- Refresh token support
- Service discovery (Eureka)
- Centralized configuration server
- Swagger / OpenAPI documentation
- Rate limiting at API Gateway
- Monitoring and logging

---

## 👨‍💻 Author

**Suyog Mainali**  
Java Backend Developer | Spring Boot | Microservices  
