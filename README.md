# Spending API
This project is a basic starter API written in Java Spring Boot. It is intended to provide access to USA spending data, allowing users to explore and analyze financial transactions made by the U.S. government.
This project is configured to interact with the public usaspending api. Documentation can be found at: https://api.usaspending.gov/docs/endpoints.
This project exposes Swagger so a user may easily discover and invoke endpoints. 

## Getting Started
To get started, all you need to do is run the following command (Linux):
```bash
./run.sh
```
or (Windows)
```commandline
run.bat
```
This should build the docker image (if it doesn't exist), build the jar and start the containerized application (if Docker is configured to run through the command line. 
Once the application is running, you can access the Swagger UI at http://localhost:8080/swagger-ui/index.html.

## The Ask
There are various TODOs in this repo. The goal is to complete as many of them as possible and demonstrate your ability to work with Java, Spring Boot, and APIs.
In addition to the TODOs, feel free to expand on the project in any way you see fit. For some ideas, please see below.

## Some Ideas
* Add new controllers, services, models to explore more of the data found in the USASpending API (https://api.usaspending.gov/docs/endpoints)
* Implement things like paging, sorting, ordering, filtering, etc.
* Ingest the data into a local database and provide a way to query it, potentially create a docker-compose file to run the database and application together
