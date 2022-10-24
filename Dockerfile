FROM adoptopenjdk/openjdk11:alpine-jre

ENV TZ=Europe/Moscow

RUN mkdir -p /user/project/prostaBot
WORKDIR /user/project/
COPY build/libs/*.jar prostaBot.jar

ENV LC_ALL C.utf8

ENTRYPOINT ["java", "-jar", "prostaBot.jar"]