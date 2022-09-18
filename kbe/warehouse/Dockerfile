FROM openjdk:17-alpine
COPY target/warehouse-*.jar /usr/local/bin/warehouse.jar
COPY data/components.csv /usr/local/bin/data/components.csv
COPY data/products.csv /usr/local/bin/data/products.csv
WORKDIR /usr/local/bin
RUN chmod +x /usr/local/bin/warehouse.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "warehouse.jar"]