FROM openjdk:13-jdk-alpine

WORKDIR /

LABEL maintainer="Miguel Estevez <maletaveras@gmail.com>"

ENV NOMBRE_APP = 'Vaadin-App'

ADD target/Pratica-vaadin-1.0-SNAPSHOT.jar mi_app.jar

EXPOSE 8080

CMD java -jar -Dspring.profiles.active=prod mi_app.jar

