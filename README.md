# Documentation
Service for creating and searching of ip records. 

### Creating the IP record example
POST /ip-records
```json
{
"type": "ipv4",
"value": "1.2.3.4",
"firstSeen": "2020-07-10 15:00:00.000",
"totalCount": 8
}
```
### Getting the IP record by value example
GET /ip-records?value=1.2.3.4

## Libraries
* spring boot web
* spring boot validation
* spring boot test
* open api validator for tests

## Running the application (requires Java 17 in PATH - running in docker recommended)

* './gradlew clean' - cleans the output/build directories
* './gradlew build' - compiles the code
* './gradlew test' - executes eu.man.mannow.vb. tests
* './gradlew bootRun' - starts the service

To run application with docker container use following commands:
* './gradlew jibDockerBuild' - generates docker image
* docker run -p 8080:8080 dev.jdsoft.intel-471-task:latest

# Task description

Create a Spring Boot microservice, that accepts the following json:
```json
{
"type": "ipv4",
"value": "1.2.3.4",
"firstSeen": "2020-07-10 15:00:00.000",
"totalCount": 8
}
```
### The following restrictions apply on the data:
1. TotalCount cannot be less than 0 and cannot be more than 100.
2. firstSeen date should ALWAYS be converted to ISO 8601 format. The user can enter the date
   in any string format. Return error if it is not well formed.
   Expose the following RESTful APIs
3. Create a new record (as shown above, id auto-generated)
4. Get record by value

### Please take care of the following while implementing:
1. Proper Testing
2. Proper Documentation of code and API
3. Proper Data Validation using standard validation frameworks.
4. Proper Error Handling
5. Ability to generate a Docker Image (good to have)