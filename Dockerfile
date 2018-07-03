FROM openjdk:8-jdk-alpine
MAINTAINER codecentric.de
VOLUME /tmp
EXPOSE 8080

ENV USER_NAME monitoring
ENV APP_HOME /home/$USER_NAME/app

RUN adduser -D -u 1000 $USER_NAME
RUN mkdir $APP_HOME

ADD ["target/spring-boot-monitoring-0.0.1-SNAPSHOT.jar" ,"$APP_HOME/spring-boot-monitoring.jar"]
RUN chown $USER_NAME $APP_HOME/spring-boot-monitoring.jar

USER $USER_NAME
WORKDIR $APP_HOME
RUN touch spring-boot-monitoring.jar

ENTRYPOINT ["java","-jar","spring-boot-monitoring.jar"]