# testapi  
This RESTful service provides user details for the given user id. 
Merges data fetched from database and external service "https://jsonplaceholder.typicode.com/users/{id}"
  ,respond back to user. Also have the ability to update company details. Please find the 
 request and response details as below.    
 
  
### Technology Stack:  

SpringBoot - RESTful service development  
Cassandra  - NoSQL database to store the user details  (id and company information).   
Gradle - Build Tool  
Docker - Run cassandra container.  

### Endpoint URL and Methods:  

GET - http://localhost:8080/testapi/v1/user/{id}  
PUT - http://localhost:8080/testapi/v1/user/{id}  
### Example:  
Request - GET URL - http://localhost:8080/testapi/v1/user/1   

Response:  

```sh
{
    "website": "ramiro.info",
    "address": {
        "zipcode": "59590-4157",
        "geo": {
            "lng": "-47.0653",
            "lat": "-68.6102"
        },
        "suite": "Suite 847",
        "city": "McKenziehaven",
        "street": "Douglas Extension"
    },
    "phone": "1-463-123-4447",
    "name": "Clementine Bauch",
    "company": {
        "bs": "harness real-time e-markets",
        "catchPhrase": "Multi-layered client-server neural-net",
        "name": "name"
    },
    "id": 3,
    "email": "Nathan@yesenia.net",
    "username": "Samantha"
}   
```   

Request:  PUT URL - http://localhost:8080/testapi/v1/user/1  

Request Body:  

```sh
{
        "bs": "harness real-time e-markets",
        "catchPhrase": "Multi-layered client-server neural-net",
        "name": "name-edited"
    } 
```  

Response:  


```sh
{
    "website": "ramiro.info",
    "address": {
        "zipcode": "59590-4157",
        "geo": {
            "lng": "-47.0653",
            "lat": "-68.6102"
        },
        "suite": "Suite 847",
        "city": "McKenziehaven",
        "street": "Douglas Extension"
    },
    "phone": "1-463-123-4447",
    "name": "Clementine Bauch",
    "company": {
        "bs": "harness real-time e-markets",
        "catchPhrase": "Multi-layered client-server neural-net",
        "name": "name-edited"
    },
    "id": 3,
    "email": "Nathan@yesenia.net",
    "username": "Samantha"
}  
```  

## Usage  

Clone the repo.    

Install docker.  

Reference to install docker -  https://docs.docker.com/docker-for-mac/install/  

Start the docker instance.  

Execute the below commands from command line based on the IDE. To make project IDE compatible.   
For eclipse   
  ```sh
  $ ./gradlew eclipse
  ```    
For intellij   
  ```sh
  $ ./gradlew idea
  ```    
Build JAR  
  ```sh
  $ ./gradlew clean build
  ```   

To bring up cassandra in docker container   
  ```sh
  $ cd docker
  ```   
  ```sh
  $ ./docker.sh
  ```   
  ```sh
  $ ./schema.sh
  ```   
    
docker.sh - To spin up cassandra docker image.  

schema.sh - To create required keyspace, Also inserts sample data into table.   

## Run the App   
Below mentioned options can be used  
  ```sh
  $ ./gradlew bootRun
  ```   
  ```sh
  $ java -jar testapi-0.0.1-SNAPSHOT.jar
  ```   
  ```sh
  $ To start app in IDE (Run TestapiApplication.java class)
  ```    