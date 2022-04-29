FROM openjdk:17-alpine
COPY target/price-*.jar /usr/local/bin/price.jar
RUN chmod +x /usr/local/bin/price.jar
ENTRYPOINT ["java", "-jar", "/usr/local/bin/price.jar"]