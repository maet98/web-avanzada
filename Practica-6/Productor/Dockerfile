FROM openjdk:13-jdk-alpine

LABEL maintainer="Miguel Estevez <maletaveras@gmail.com>"

ENV NOMBRE_APP = 'Productor-Practica-6'

ENV CLIENT_ID = 1
ENV JMS_IP = "localhost"

COPY build/libs/Productor-0.0.1-SNAPSHOT.jar mi_app.jar

ENTRYPOINT java -jar mi_app.jar $CLIENT_ID
