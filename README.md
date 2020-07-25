# Truextend Problem 1
TX Proposed solution to problem 1

## Pre-Requirements
In order to execute the solution you need to install:
- Maven Download and install instructions: https://maven.apache.org/install.html
- Java 1.8 Download and install instructions: https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html

## About the Application
The Application have the Models, Services, Controllers and Unit Tests According to specifications and suggestions bringed in the exercise. The solution uses Java Spring-Boot to create a REST API. 

Also it was implemented Swagger-UI to improve and facilitate the use and testing of the application to test the endpoints of each controller according to the project description.

## Program Execution
After install the prerequired tools, the project could be run to test it with the following command:

`` mvn spring-boot:run ``

Immediately the program will start to download the dependencies and compile, once finished compiling you can access the Swagger to verify the operation of each one of the Endpoints created to satisfy the requirements of the project.

The project is deployed on port 8080 by default, however this configuration can be easily changed by modifying the ``Application.properties`` file in which you must change the port for the port on which you want to run the application.

- server.port=8080    (change the 8080 to the port where you want to run the app)

Finally you can access the Swagger in the following link (if you changed the port you must also change the url port to see the swagger):
- http://localhost:8080/swagger-ui.html#