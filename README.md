# BuyOrSell_

## What you will need

- Docker and Docker Compose
- Springboot 
- Angular
- Java
- Maven
- SQL Server Extension in your editor (preferably VSCode)

## To run the web app

- In the root folder of the project
    - `docker-compose up -d`
    - `docker ps -a` ==> To check whether the container is running
    - create a connection profile in the SQL Server Extension
    - In the backend folder run `mvn package`
    - go to the target folder and run `java -jar buyorsell-02.1.8.RELEASE.jar `
    - you can test the Server using postman
    - 
    - Go to the frontend folder and into the BuyOrSell_ folder
    - run` ng serve` 
    - go to [text](http://localhost:4200/) in your browser
