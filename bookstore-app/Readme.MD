# Monolith

The monolith version uses an H2 in-memory database with the database structure and initial data created as soon as the application starts.

* Navigate to "/monolith"
* Run "mvn install"
* Navigate to "/monolith/target"
* Run the backend application with "java -jar monolith-1.0-SNAPSHOT.jar"
* Navigate to "/web-ui-composer-api-gateway"
* Run "npm install"
* Run the frontend with "npm start"

# Structured monolith and microservices prerequisites

Both the structured monolith and the microservices version require PostgreSQL and RabbitMQ as infrastructure. Both can be installed and started manually or using Docker with the following commands:

> docker run -d --name postgres -e POSTGRES_PASSWORD=postgres postgres
> docker run -d -p 5672:5672 -p 15672:15672 --name rabbitmq -e RABBITMQ_DEFAULT_USER=rabbitmq -e RABBITMQ_DEFAULT_PASS=rabbitmq merapar/rabbitmq-docker:3.6.0-management

## PostgreSQL setup

Once PostgreSQL is started, use a client application to create a "bookstore" database. Create the tables and populate the database by running the commands in the "/Data.sql" scirpt.

## RabbitMQ setup

Sign in at the admin console at http://localhost:15672 using rabbitmq/rabbitmq as credntials. Add a new "bookstore" virtual host by navigating to "Admin" -> "Virtual Hosts". Ince added, grant the "rabbitmq" user all permissions to the newly created virtual host.

# Structured monolith

Make sure that PostgreSQL and RabbitMQ are running. Check the prerequisites section for more details.

* Navigate to "/monolith-structured"
* Run "mvn install"
* Navigate to "/monolith-structured/application/target"
* Run the backend application with "java -jar application-1.0-SNAPSHOT.jar"
* Navigate to "/web-ui-composer-api-gateway"
* Run "npm install"
* Run the frontend with "npm start"

# Microservices

Make sure that PostgreSQL and RabbitMQ are running. Check the prerequisites section for more details.

* Run "mvn install" from within "/structured-monolith" if haven't done previously. The microservices runs its components as separate services so they need to be present in the local Maven repository.
* Navigate to "/microservices"
* Run "mvn install"
* Run the ".jar" file in the target directory for the following services: ("java -jar <service-name>-1.0-SNAPSHOT.jar")
  * "/microserivces/service-discovery-eureka-service"
  * "/microservices/book-catalog-service"
  * "/microservices/rating-service"
  * "/microservices/recommendation-service"
  * "/microservices/static-resources-service"
* You can then run the following combinations of frontend and API gateways: (Use "npm install" followed by "npm start" for the web-ui's, and "java -jar <service-name>-1.0-SNAPSHOT.jar" for the API gateway services).
  * "/web-ui-directon-connection" needs no API gateway.
  * "/web-ui-reverse-proxy-api-gateway" requires the "/microservices/api-gateway-zuul-service" API gateway.
  * "/web-ui-composer-api-gateway" requires the "/microservices/api-gateway-composer-service" API gateway.
  * "/web-ui-cqrs-api-gateway" requires the "/microservices/api-gateway-cqrs-service" API gateway.
