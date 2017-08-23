# Spring Boot - Monitoring

This is a simple [spring](https://spring.io) [boot](http://projects.spring.io/spring-boot/) showcase project, which based on [https://start.spring.io](https://start.spring.io).

## Docker

Start a monitoring setup with grafana, prometheus, cadvisor and this sample application.

```shell
docker-compose up
open http://localhost:8080
# make some requests in the sample application
open http://localhost:3000
# grafana login with admin:admin
docker-compose down
```