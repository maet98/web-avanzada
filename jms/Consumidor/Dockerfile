FROM openjdk:13-jdk-alpine

LABEL maintainer="Miguel Estevez <maletaveras@gmail.com>"

ENV NOMBRE_APP = 'Consumidor-Practica-6'

COPY build/libs/Consumidor-0.0.1-SNAPSHOT.jar mi_app.jar

ENTRYPOINT java -jar mi_app.jar 
