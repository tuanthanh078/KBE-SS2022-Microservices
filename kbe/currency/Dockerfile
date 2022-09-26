FROM openjdk:17-alpine
COPY target/currency-*.jar /usr/local/bin/currency.jar
RUN chmod +x /usr/local/bin/currency.jar
ENTRYPOINT ["java", "-jar", "/usr/local/bin/currency.jar"]