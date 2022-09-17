# KBE-SS2022-Microservices

## Getting Started

### Prerequisites

* [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [Maven 3.8.5](https://maven.apache.org/download.cgi)
* [Docker and Docker-Compose](https://www.docker.com/get-started/)

### Local development

1. Clone the repository
   ```sh
   git clone https://github.com/tuanthanh078/KBE-SS2022-Microservices/
   ```
2. Enter the `kbe` folder
   ```sh
   cd KBE-SS2022-Microservices/kbe
   ```
3. Turn on required containers
   ```sh
   docker-compose up -d product-db-mysql product-db-mongo warehouse-db-mysql rabbitmq
   ```
4. Clean and install dependencies with Maven
   ```sh
   mvn clean package
   ```
5. Build and run all containers with docker-compose
   ```sh
   docker-compose up -d --build
   ```
### Deployment on Cloud

1. Clone the repository
   ```sh
   git clone https://github.com/tuanthanh078/KBE-SS2022-Microservices/
   ```
2. Enter the `kbe` folder
   ```sh
   cd KBE-SS2022-Microservices/kbe
   ```
3. Run all containers with docker-compose
   ```sh
   docker-compose -f docker-compose-production.yml up -d --build
   ```
## Demo
* Frontend: http://34.77.18.137:3000/
* Gateway: http://34.77.18.137:8000/hardwares
   
## Usage

* Frontend: http://localhost:3000
* Get all products: GET http://localhost:8084/products
* Get all hardwares: GET http://localhost:8084/hardwares
* Get a specific product: GET http://localhost:8084/products/{id}
* Get a specific hardware: GET http://localhost:8084/hardwares/{id}
* Customize a product: POST http://localhost:8084/custom 
    
    `body: {"selectedHardwares": [{"hardwareId": 1, "selectedAmount": 2},...]}`
    
## Documentation
* API-Gateway: http://localhost:8000/swagger-ui.html
* Product: http://localhost:8084/swagger-ui.html
