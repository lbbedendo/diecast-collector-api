# Diecast Collector API

**diecast-collector-api** Is a REST API developed with **Micronaut Framework**. It's goal is to help me keep track of my miniature car collection (Hot Wheels, Maisto, California Collectibles, etc) and to provide a general use case for studying new technologies.
Also check out the [Web App](https://github.com/lbbedendo/diecast-collector-app) built with React.  


## Technology Stack
- [Micronaut Framework](https://micronaut.io/) with Java
- PostgreSQL Database
- Testcontainers  
- Gradle

## Running the application
1. Run docker-compose to setup a postgresql 12.5 instance `docker-compose up`

## Reference Documentation

### jdbc-hikari documentation

- [Micronaut Hikari JDBC Connection Pool documentation](https://micronaut-projects.github.io/micronaut-sql/latest/guide/index.html#jdbc)

### testcontainers documentation

- [https://www.testcontainers.org/](https://www.testcontainers.org/)

### jooq documentation

- [Micronaut jOOQ fluent API for typesafe SQL query construction and execution documentation](https://micronaut-projects.github.io/micronaut-sql/latest/guide/index.html#jooq)

### flyway documentation

- [Micronaut Flyway Database Migration documentation](https://micronaut-projects.github.io/micronaut-flyway/latest/guide/index.html)

- [https://flywaydb.org/](https://flywaydb.org/)

### http-client documentation

- [Micronaut Micronaut HTTP Client documentation](https://docs.micronaut.io/latest/guide/index.html#httpClient)

