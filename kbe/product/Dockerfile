FROM openjdk:17-alpine
COPY target/product-*.jar /usr/local/bin/product.jar
RUN chmod +x /usr/local/bin/product.jar
ENTRYPOINT ["java", "-jar", "/usr/local/bin/product.jar"]