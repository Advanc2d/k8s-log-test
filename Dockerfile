#RUN mvnw clean package
#
#FROM openjdk:11
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} k8s-log-test.jar
#RUN mkdir -p /var/log/test
#CMD ["java", "-jar", "/k8s-log-test.jar", "--logging.file=/var/log/test/k8s-log-test-%d{yyyy-MM-dd}.%i.log"]

FROM maven:3.8.4-openjdk-11 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package

FROM openjdk:11
WORKDIR /app
COPY --from=builder /app/target/k8s-log-test.jar .
CMD ["java", "-jar", "k8s-log-test.jar", "--logging.file=/var/log/test/k8s-log-test-%d{yyyy-MM-dd}.%i.log"]