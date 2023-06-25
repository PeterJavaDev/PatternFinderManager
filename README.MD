Pattern Finder Manager - Sample project

docker-compose.yml and init.sql file included to setup Postgres DB.
I've focused on providing scalability so it's a SOA with this app managing search requests <br /> 
and status checks. While another app Pattern Finder Worker is responsible for data processing which is looking for pattern (more in Pattern Finder Worker README). <br />
Pattern Finder Manager is stateless so it can be easily scaled horizontal. <br />

There is intentionally few test in both apss. There is not much to test here since it's only crud operations <br /> 
and in worked app there is only scanning algorithm to test.

It's a Spring Boot application build with Java 17 and Maven.
To build use:

```
mvn clean install
```
<br />

To run application you can use Spring Boot Plugin:

```
mvn spring-boot:run
```

Simple two API methods:

Requesting search <br />
POST http://localhost:8080/findPattern <br />
Sample body:
```
{
    "pattern": "partes",
    "input": "Gallia est omnis divisa in partes tres"
}
```
<br />
Checking status <br />
GET http://localhost:8080/checkStatus/2  <br />
Where task ID is a path variable
