FROM openjdk:17-alpine
COPY target/warehouse-*.jar /usr/local/bin/warehouse.jar
COPY data/components.csv /usr/local/bin/warehouse/data/components.csv
COPY data/products.csv /usr/local/bin/warehouse/data/products.csv
WORKDIR /usr/local/bin/warehouse
RUN chmod +x /usr/local/bin/warehouse.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "/usr/local/bin/warehouse.jar"]