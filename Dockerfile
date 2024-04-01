FROM openjdk:21-jdk-slim
EXPOSE 8080
ENV GOOGLE_CLIENT_ID=abc
ENV GOOLE_CLIENT_SECRET=xyz
ENV DATA_SOURCE_URL=jdbc:mysql://localhost:3306/attendance
ENV DATA_USERNAME=miu
ENV DATA_PASSWORD=password
ENV SMTP_USERNAME=xyz
ENV SMTP_PASSWORD=abc
ENV ALERT_LOW_BALANCE_CRON_JOB="0/20 * * * * ?"
RUN apk add --no-cache maven
WORKDIR /attendance-server
COPY ./ ./
RUN mvn package
CMD ["java", "-jar","/attendance-server/target/AttendanceSystem-2.0.0-SNAPSHOT.jar"]
