#RUN mvnw clean package
#
#FROM openjdk:11
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} k8s-log-test.jar
#RUN mkdir -p /var/log/test
#CMD ["java", "-jar", "/k8s-log-test.jar", "--logging.file=/var/log/test/k8s-log-test-%d{yyyy-MM-dd}.%i.log"]

FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} k8s-log-test.jar
ENV SPRING_PROFILES_ACTIVE=prod
ENV LOG_PATH=/var/log/test
ENV LOG_FILE_NAME=k8s-log-test
RUN mkdir -p /var/log/test
ENTRYPOINT ["java", "-jar", "/k8s-log-test.jar"]