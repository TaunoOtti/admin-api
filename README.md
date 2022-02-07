# Admin-Api
Spring boot application to provide api endpoints for https://github.com/TaunoOtti/admin-frontend
* Java 11
* Gradle 7.3.3

## Run postgre database with Docker
* Run `docker compose up` in root. Database data will be saved ./db/pgdata/
* Test connection localhost:5432
* Run `docker compose down` to shut down database.

## Run spring application
* `gradle clean bootrun` 
* Application will be started on localhost:8080
* Liquibase commit init tables and data to db 

TODO...
