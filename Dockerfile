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
RUN mkdir -p /var/log/test
ENV SPRING_PROFILES_ACTIVE=prod
ENV LOG_PATH=/var/log/test
ENV LOG_FILE_NAME=k8s-log-test
ENV SERVER_PORT=4003
ENV REDIS_SERVER=localhost
ENV REDIS_PORT=6379
ENV MYSQL_HOST=localhost
ENV MYSQL_PORT=3306
ENV MYSQL_DB=sso
ENV DB_USER=sso
ENV DB_PASSWORD=sso
ENTRYPOINT ["java", "-jar", "/k8s-log-test.jar"]