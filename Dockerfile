FROM gradle:latest AS BUILD_STAGE

COPY --chown=gradle:gradle . /home/gradle
WORKDIR /home/gradle

RUN gradle build || return 1

FROM openjdk:17.0.1-jdk-slim

ARG JAR_FILE=build/libs/*SNAPSHOT.jar

WORKDIR /opt/app

COPY --from=BUILD_STAGE /home/gradle/${JAR_FILE} yandex-lavka.jar

#configuration
ENV POSTGRES_SERVER=localhost
ENV POSTGRES_PORT=5432
ENV POSTGRES_DB=postgres
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=posgres

ENTRYPOINT java -jar yandex-lavka.jar
