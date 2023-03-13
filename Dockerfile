FROM openjdk:8
EXPOSE 8080
COPY /target/currency-convertor.jar currency-convertor.jar
ENTRYPOINT ["java","-jar","/currency-convertor.jar"]