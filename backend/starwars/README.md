Star Wars API Backend

- Overview

The Star Wars API Backend is a Spring Boot application that provides data about Star Wars characters and planets. It integrates with external APIs to fetch real-time data and also supports an offline mode.

- Features

  1. Fetch Star Wars characters and planets based on type and name.

  2. Supports online and offline modes.

  3. Provides Swagger UI for API documentation.

  4. Built with Spring Boot 3, following RESTful best practices.

  4. Uses Docker & Kubernetes (Minikube) for containerized deployment.

-️ Technologies Used

    Java 17
    
    Spring Boot 3 (REST API)
    
    Swagger
    
    Docker & Kubernetes

    Feign Clients (for external API communication)


- Setup & Installation

🔹 Prerequisites
    
    Java 17
    
    Maven
    
    Docker
    
    Minikube (for local Kubernetes deployment)


- Run Locally

    1. Build the application
    
    2. mvn clean package
    
    3. Run the Spring Boot application
    
    4. mvn spring-boot:run

- Swagger API Documentation is available at: http://localhost:8081/swagger-ui/index.html


Docker Build & Run

    Build the Docker image :- docker build -t starwars-project-backend:latest .
    
    Run the container :- docker run -p 8080:8080 starwars-project-backend
Deploy to Minikube

    - Start Minikube -> minikube start

    - Use Minikube Docker environment -> eval $(minikube docker-env)

    - Build the image inside Minikube -> docker build -t starwars-project-backend:latest .

    - Deploy to Kubernetes -> kubectl apply -f backend-deployment.yaml

    - Check the running pods -> kubectl get pods


