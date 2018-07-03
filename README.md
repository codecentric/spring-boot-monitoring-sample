# Spring Boot - Monitoring

This is a simple [spring](https://spring.io) [boot](http://projects.spring.io/spring-boot/) showcase project, which based on [https://start.spring.io](https://start.spring.io).

## Build application


```shell
mvn clean package
```

## Start infrastructure

Start docker monitoring setup with grafana, prometheus, cadvisor and the application.

```shell
docker-compose up
docker-compose down
```

## Smart configuration

* Application: [http://localhost:8080/cat](http://localhost:8080/cat)
    * make some requests
* Prometheus: [http://localhost:9090](http://localhost:9090)
* Grafana: [http://localhost:3000](http://localhost:3000)
    * login with admin:admin
    * setup "prometheus" data source
    * import grafana-dashboard
