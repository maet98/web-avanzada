FROM openjdk:13-jdk-alpine

LABEL maintainer="Miguel Estevez <maletaveras@gmail.com>"

ENV NOMBRE_APP = 'Practica-1'

VOLUME ./data

COPY /build/libs/Practica-1-0.0.1-SNAPSHOT.jar mi_app.jar

ENTRYPOINT java -jar mi_app.jar --server.port=${PORT:-8080}
