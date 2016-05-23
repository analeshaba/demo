# demo 
Sample REST API

-------


Generated project characteristics
-------------------------
* Spring Boot
 
Installation
------------

To install in your local repository execute following commands:

```bash
    git https://github.com/analeshaba/demo.git
    cd demo
    mvn clean install
```


Run the application
----------------
 Maven, you can run the application using 
```bash
	mvn spring-boot:run  OR
	mvn package && java -jar target/sample-rest-application-0.0.1-SNAPSHOT.jar
```

Stop the application
----------------
curl -u admin:admin  -X POST <server>:<port>/shutdown

Test on the API
-------------------
Content-Type → application/json;charset=UTF-8
```bash
GET : Get a list of all events
	http://localhost:8080/event - Returns a list of all events
	
GET : Get a specific event
	http://localhost:8080/event{id} - The Event	
 
POST : Create a event
	http://localhost:8080/event
	JSON Body: e.g. {"details": "Event details"}

PUT/PATCH : Update a event
	http://localhost:8080/event
	JSON Body: e.g. {"id": 1,"details": "Sample Dummy Event 1 Updated"}

DELETE : Delete an event
	http://localhost:8080/event/{id}
	
GET : Get a list of all customers
	http://localhost:8080/customer - Returns a list of all customers
	
GET : Get a specific customer
	http://localhost:8080/customer{id} - The customer	
 
POST : Create a customer
	http://localhost:8080/customer
	
POST: Register a Customer for an Event
	http://localhost:8080/customer/{customerId}/event/{eventId}
	

PUT/PATCH : Update a customer
	http://localhost:8080/customer
	 
DELETE : Delete an customer
	http://localhost:8080/customer/{id}

DELETE: UnRegister a Customer for an Event
	http://localhost:8080/customer/{customerId}/event/{eventId}	
```

	
