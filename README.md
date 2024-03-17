# Attendance-System
Attendance System for Enterprise Architecture

## Prerequisites
- Java 21
- Docker


## Setup Project
1. clone these project
    - ``git clone https://comprodev@dev.azure.com/comprodev/CS544/_git/common-base-module``
    - ``git clone https://github.com/Samnang-An/Attendance-System.git``
2. open terminal and go to the project directory
    - ``cd common-base-module``
    - ``mvn install``
3. goto Attendance project
    - ``cd ../Attendance-System``
4. run docker compose
    - ``docker compose up``

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

## API Documentation
- [Role](http://localhost:8080/badge-system/roles)
- - username: miu
- - password: password