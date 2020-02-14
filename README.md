# pedido-api

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) pedido api.

## Requirements

For building and running the application you need:

- [JDK 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
- [Maven 3](https://maven.apache.org)
- [MySQL](https://www.mysql.com/downloads/)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.wandson.api.PedidoApiApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Database

Do not need to create the database, just that MySQL is running.

## API documentation - Swagger

To access the API documentation, it is necessary in your browser to visit http://localhost:8080/swagger-ui.html