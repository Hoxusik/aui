# 🎬 Movie & Director Microservices System (AUI)

> A microservices-based application for managing directors and movies, built for the "Aplikacje Usług Internetowych" course.

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-green?style=for-the-badge&logo=springboot)
![Angular](https://img.shields.io/badge/Angular-17-red?style=for-the-badge&logo=angular)
![Docker](https://img.shields.io/badge/Docker-Compose-blue?style=for-the-badge&logo=docker)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-18-336791?style=for-the-badge&logo=postgresql)

## 🏗 Architecture

The project follows the **Microservices** architecture pattern. It consists of two independent backend services, an API Gateway, and a Single Page Application (SPA) frontend served via NGINX.

### System Components

| Service | Technology | Internal Port | Description |
| :--- | :--- | :--- | :--- |
| **Frontend** | Angular + NGINX | `80` | User Interface. Acts as a reverse proxy for API requests. |
| **Gateway** | Spring Cloud Gateway | `8080` | Central entry point. Routes traffic to microservices. |
| **Director Service** | Spring Boot | `808x` | Manages directors data. |
| **Movie Service** | Spring Boot | `808x` | Manages movies data. |
| **Databases** | PostgreSQL 18 | `5432` | Dedicated database instances for each service. |
| **Adminer** | PHP | `8080` | Database management tool (GUI). |

### Data Flow

```mermaid
graph LR
    User((User)) -->|HTTP :80| NGINX[Angular UI + NGINX]
    NGINX -->|/api/*| Gateway[Gateway :8080]
    Gateway -->|/api/directors| Director[Director Service]
    Gateway -->|/api/movies| Movie[Movie Service]
    Director --> DB1[(Director DB)]
    Movie --> DB2[(Movie DB)]
