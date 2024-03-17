# Attendance-System
Attendance System for Enterprise Architecture

## Prerequisites
- Java 21
- Docker
- Maven

## Setup Project
1. clone these project
    - ``git clone https://github.com/Samnang-An/Attendance-System.git``
2. goto Attendance project
    - ``cd Attendance-System``
4. run docker compose
    - ``docker compose up``
5. run the project
    - ``mvn clean install``
    - ``mvn spring-boot:run``

## API Documentation
- [Swagger](http://localhost:8080/swagger-ui/index.html)
    - username: miu
    - password: password

## Credentials
- Database: 
    - username: miu
    - password: password
    - port: 3307
    - database: attendance
- ActiveMQ:
    - username: admin
    - password: admin
    - port: 61616

