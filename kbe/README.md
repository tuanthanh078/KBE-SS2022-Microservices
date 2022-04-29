# KBE-SS2022-Microservices

## Getting Started

### Prerequisites

* [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [Maven 3.8.5](https://maven.apache.org/download.cgi)
* [Docker](https://www.docker.com/get-started/)

### Installation

1. Clone the repository
   ```sh
   git clone https://github.com/tuanthanh078/KBE-SS2022-Microservices/
   ```
2. Enter the `kbe` folder
   ```sh
   cd kbe
   ```
3. Clean and install dependencies with Maven
   ```sh
   mvn clean package
   ```
4. Build and run containers with docker-compose
   ```sh
   docker-compose up -d --build
   ```
   
## Usage

* Frontend: http://localhost:3000
* Get all products: GET http://localhost:8084/products
* Get all hardwares: GET http://localhost:8084/hardwares
* Get a specific product: GET http://localhost:8084/products/{id}
* Get a specific hardware: GET http://localhost:8084/hardwares/{id}
* Customize a product: POST http://localhost:8084/custom 
    
    `body: {"selectedHardwares": [{"hardwareId": 1, "selectedAmount": 2},...]}`
