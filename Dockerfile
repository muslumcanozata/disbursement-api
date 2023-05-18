FROM openjdk:20-jdk
COPY build/libs/*.jar disbursement-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "disbursement-api-0.0.1-SNAPSHOT.jar"]