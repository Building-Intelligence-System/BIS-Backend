FROM openjdk:17-jdk-slim
MAINTAINER Ilya Gehrman <gehrman.ilya@gmail.com>

EXPOSE 8080

ADD build/libs/service.jar /

CMD echo "Service is starting..." && \
    \
    java -XX:+ExitOnOutOfMemoryError \
    -Djava.security.egd=file:/dev/./urandom -Djavax.xml.accessExternalDTD=all $JAVA_OPTIONS \
    -jar /service.jar