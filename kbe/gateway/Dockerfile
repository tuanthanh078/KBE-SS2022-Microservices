FROM openjdk:17-alpine
COPY target/gateway-*.jar /usr/local/bin/gateway.jar
RUN chmod +x /usr/local/bin/gateway.jar
ENTRYPOINT ["java", "-jar", "/usr/local/bin/gateway.jar"]